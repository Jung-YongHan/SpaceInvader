package org.newdawn.spaceinvaders.character;

import org.newdawn.spaceinvaders.user.Player;

public abstract class Character {
    protected Player player;
    protected String shipImage = "ship.png";
    protected String shieldShipImage = "shieldShip.png";
    protected String shipShotImage = "shot.png";
    protected String path = "sprites/ship/";
    protected double moveSpeed;
    protected double firingInterval;
    protected int hp;
    protected int bulletCount;

    public abstract String getShipImageFolderPath();
    public String getShipImage() {return getShipImageFolderPath()+shipImage;}
    public String getShipShieldImage() {return getShipImageFolderPath()+shieldShipImage;}
    public String getShipShotImage() {return getShipImageFolderPath()+shipShotImage;}

    public int getHp() {
        return hp;
    }

    public double getFiringInterval() {
        return firingInterval;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public int getBulletCount() {
        return bulletCount;
    }
}