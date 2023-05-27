package org.newdawn.spaceinvaders.entity;

import org.newdawn.spaceinvaders.Game;
import org.newdawn.spaceinvaders.Skin.CharacterStatus;
import org.newdawn.spaceinvaders.Sprite;
import org.newdawn.spaceinvaders.SpriteStore;
import org.newdawn.spaceinvaders.user.Player;

/**
 * The entity that represents the players ship
 * 
 * @author Kevin Glass
 */
public class ShipEntity extends Entity {
	private final CharacterStatus characterStatus;
	/** The game in which the ship exists */
	private Game game;
	private Player player;

	/**
	 * Create a new entity to represent the players ship
	 *
	 * @param game The game in which the ship is being created
	 * @param ref The reference to the sprite to show for the ship
	 * @param x The initial x location of the player's ship
	 * @param y The initial y location of the player's ship
	 */
	public ShipEntity(Game game, String ref, int x, int y, Player player, CharacterStatus characterStatus) {
		super(ref,x,y);
		this.game = game;
		this.player = player;
		this.characterStatus = characterStatus;
	}

	/**
	 * Request that the ship move itself based on an elapsed ammount of
	 * time
	 *
	 * @param delta The time that has elapsed since last move (ms)
	 */
	@Override
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
			if (!characterStatus.getShieldStatus().isShieldActive()) {
				if (characterStatus.getHp() == 1)
					game.notifyDeath();
				else
					characterStatus.setHp(characterStatus.getHp()-1);
			}
		}
	}

	public void setNormalImage() {
		try {
			Sprite sprite = SpriteStore.get().getSprite(player.getCharacter().getShipImage());
			setSprite(sprite);
		} catch (Exception e) {
			System.out.println("Failed to load normal image: " + e.getMessage());
		}
	}

	public void setShieldedImage() {
		try {
			Sprite sprite = SpriteStore.get().getSprite(player.getCharacter().getShipShieldImage());
			setSprite(sprite);
		} catch (Exception e) {
			System.out.println("Failed to load shielded image: " + e.getMessage());
		}
	}

}