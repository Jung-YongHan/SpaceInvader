package org.newdawn.spaceinvaders.entity;

import org.newdawn.spaceinvaders.Game;

/**
 * The entity that represents the players ship
 * 
 * @author Kevin Glass
 */
public class ShipEntity extends Entity {
	/** The game in which the ship exists */
	private Game game;
	private int health = 3;
	private boolean shieldActive;
	private long shieldStartTime;
	private long SHIELD_DURATION = 10000;
	private String normalImage = "sprites/ship/ship.png";
	private String shieldedImage = "sprites/ship/shieldShip.png";

	/**
	 * Create a new entity to represent the players ship
	 *  
	 * @param game The game in which the ship is being created
	 * @param ref The reference to the sprite to show for the ship
	 * @param x The initial x location of the player's ship
	 * @param y The initial y location of the player's ship
	 */
	public ShipEntity(Game game,String ref,int x,int y) {
		super(ref,x,y);
		this.game = game;
		this.shieldActive = false;
	}
	
	/**
	 * Request that the ship move itself based on an elapsed ammount of
	 * time
	 * 
	 * @param delta The time that has elapsed since last move (ms)
	 */
	public void move(long delta) {
		// if we're moving left and have reached the left hand side
		// of the screen, don't move
		if ((dx < 0) && (x < 10)) {
			return;
		}
		// if we're moving right and have reached the right hand side
		// of the screen, don't move
		if ((dx > 0) && (x > 750)) {
			return;
		}
		
		super.move(delta);
	}
	
	/**
	 * Notification that the player's ship has collided with something
	 * 
	 * @param other The entity with which the ship has collided
	 */

	public void collidedWith(Entity other) {
		// if its an alien, notify the game that the player
		// is dead

		if (other instanceof AlienEntity || other instanceof ObstacleEntity) {
			if (!this.isShieldActive()) {
				if (health == 1)
					game.notifyDeath();
				else
					health--;
			}
		}
	}

	public int getHP(){ return health; }
	public void setHP(int health){
		this.health = health;
	}

	public void activateShield() {
		this.shieldActive = true;
		this.shieldStartTime = System.currentTimeMillis();
		// 바뀐 이미지로 변경하세요.
	}

	public void updateShieldStatus() {
		if (this.shieldActive && System.currentTimeMillis() - this.shieldStartTime > SHIELD_DURATION) {
			this.shieldActive = false;
			// 원래 이미지로 변경하세요.
		}
	}

	public boolean isShieldActive() {
		return this.shieldActive;
	}
//	public void setNormalImage() {
//		try {
//			Sprite sprite = new Sprite(normalImage);
//			setSprite(sprite);
//		} catch (Exception e) {
//			System.out.println("Failed to load normal image: " + e.getMessage());
//		}
//	}
//
//	public void setShieldedImage() {
//		try {
//			Sprite sprite = new Sprite(shieldedImage);
//			setSprite(sprite);
//		} catch (Exception e) {
//			System.out.println("Failed to load shielded image: " + e.getMessage());
//		}
//	}
}