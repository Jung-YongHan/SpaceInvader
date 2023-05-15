package org.newdawn.spaceinvaders.entity;

import org.newdawn.spaceinvaders.Game;

public class BossAlienEntity extends Entity{
    /** The speed at which the alien moves horizontally */
    private double moveSpeed = 75;
    /** The game in which the entity exists */
    private Game game;
    private int stage;
    private String ref;

    /**
     * Construct a entity based on a sprite image and a location.
     *
     * @param game The game in which this entity is being created
     * @param stage The number of stage
     * @param x The initial x location of this alien
     * @param y The initial y location of this alien
     */
    public BossAlienEntity(Game game, int stage, int x, int y) {
        super(getRefFromStage(stage), x, y);

        this.game = game;
        this.stage = stage;
        set(stage);
        dx = -moveSpeed;
    }

    private static String getRefFromStage(int stage) {
        switch (stage) {
            case 1:
                return "sprites/bossAlien/bossAlien1.png";
            case 2:
                return "sprites/bossAlien/bossAlien2.png";
            case 3:
                return "sprites/bossAlien/bossAlien3.png";
            case 4:
                return "sprites/bossAlien/bossAlien4.png";
            case 5:
                return "sprites/bossAlien/bossAlien5.png";
            default:
                return "";
        }
    }

    private void set(int stage) {
        switch (stage) {
            case 1:
                this.moveSpeed = 300;
                break;
            case 2:
                this.moveSpeed = 100;
                break;
            case 3:
                this.moveSpeed = 500;
                break;
            case 4:
                this.moveSpeed = 350;
                break;
            case 5:
                this.moveSpeed = 200;
                break;
        }
    }

    public void move(long delta) {
        // if we have reached the left hand side of the screen and
        // are moving left then request a logic update
        if ((dx < 0) && (x < 10)) {
            game.updateLogic();
        }
        // and vice vesa, if we have reached the right hand side of
        // the screen and are moving right, request a logic update
        if ((dx > 0) && (x > 750)) {
            game.updateLogic();
        }

        // proceed with normal move
        super.move(delta);
    }

    public void doLogic() {
        // swap over horizontal movement and move down the
        // screen a bit
        dx = -dx;
        y += 10;

        // if we've reached the bottom of the screen then the player
        // dies
        if (y > 570) {
            game.notifyDeath();
        }
    }

    @Override
    public void collidedWith(Entity other) {
        // collisions with aliens are handled elsewhere
    }
}
