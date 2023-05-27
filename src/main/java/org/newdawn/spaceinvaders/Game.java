package org.newdawn.spaceinvaders;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.google.firebase.auth.FirebaseAuthException;
import org.newdawn.spaceinvaders.database.DB;
import org.newdawn.spaceinvaders.entity.*;
import org.newdawn.spaceinvaders.user.ItemManager;
import org.newdawn.spaceinvaders.user.Player;

public class Game extends Canvas
{
	private int timer;
	/** The strategy that allows us to use accelerate page flipping */
	private BufferStrategy strategy;
	/** True if the game is currently "running", i.e. the game loop is looping */
	private boolean gameRunning = true;

	private ArrayList<Entity> entities = new ArrayList<>();
	/** The list of entities that need to be removed from the game this loop */
	private ArrayList<Entity> removeList = new ArrayList<>();
	/** The entity representing the player */
	private ShipEntity ship;
	/** The speed at which the player's ship should move (pixels/sec) */
	private double moveSpeed = 300;
	/** The time at which last fired a shot */
	private long lastFire = 0;
	/** The interval between our players shot (ms) */
	private double firingInterval = 200;
	/** The number of aliens left on the screen */
	private int alienCount;
	/** The message to display which waiting for a key press */
	private String message = "";
	/** True if we're holding up game play until a key has been pressed */
	private boolean waitingForKeyPress = true;
	/** True if the left cursor key is currently pressed */
	private boolean leftPressed = false;
	/** True if the right cursor key is currently pressed */
	private boolean rightPressed = false;
	/** True if we are firing */
	private boolean firePressed = false;
	/** True if game logic needs to be applied this loop, normally as a result of a game event */
	private boolean logicRequiredThisLoop = false;
	/** The last time at which we recorded the frame rate */
	private long lastFpsTime;
	/** The current number of frames recorded */
	private int fps;
	/** The normal title of the game window */
	private String windowTitle = "Space Invaders 102";
	/** The game window that we'll update with the frame count */
	private JFrame container;
	private Image background;
	private int currentLevel;
	private int level;
	/** item 관련 변수 */
	private Player player;
	private ItemManager itemManager;
	private int bulletCount = 1;
	private int spaceBetweenBullets = 30;
	private long lastItemUsed = 0;
	private DB db;
	private int coinCount = 0;


	/**
	 * Construct our game and set it running.
	 */
	public Game(JFrame frame, Player player) throws FirebaseAuthException {
			container = frame;
			this.player = player;
			// setup our canvas size and put it into the content of the frame
			setBounds(0,0,800,600);
			container.getContentPane().add(this);

			container.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});

		// add a key input system (defined below) to our canvas
		// so we can respond to key pressed
		addKeyListener(new KeyInputHandler());

		// request the focus so key events come to us
		requestFocus();

		// create the buffering strategy which will allow AWT
		// to manage our accelerated graphics
		createBufferStrategy(2);
		strategy = getBufferStrategy();

		itemManager = new ItemManager(this.player);

		db = new DB();

		initEntities();
	}

	/**
	 * Start a fresh game, this should clear out any old data and
	 * create a new set.
	 */
	private void startGame() {
		// clear out any existing entities and initialise a new set
		entities.clear();
		initEntities();

		// blank out any keyboard settings we might currently have
		leftPressed = false;
		rightPressed = false;
		firePressed = false;
	}

	/**
	 * Initialise the starting state of the entities (ship and aliens). Each
	 * entity will be added to the overall list of entities in the game.
	 */
	int alienkill =0;

	private void initEntities() {
		createShip();
		alienCount = 0;
		db.getPlayCount(count -> {
			if (count != 0 && count % 5 == 0) {
				setLevel(6);
			}
			createAliens();
		});

	}

	private void createShip() {
		// create the player ship and place it roughly in the center of the screen
		ship = new ShipEntity(this, this.player.getSkin().getShipImage(),370,500, this.player);
		entities.add(ship);
	}

	private void createAliens() {
		int numRows, numCols, rowSpace, colSpace;
		if (level == 1) {
			numRows = 4; numCols = 6; rowSpace = 40; colSpace = 80;
		} else if (level == 2) {
			numRows = 5; numCols = 8; rowSpace = 35; colSpace = 70;
		} else if (level == 6) {
			numRows = 15; numCols = 1; rowSpace = 0; colSpace = 0;
		} else {
			numRows = 5; numCols = 12; rowSpace = 35; colSpace = 50;
		}
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				Entity alien = level == 6 ?
						new BossAlienEntity(this, 700, 50) :
						new AlienEntity(this, 100 + (col * colSpace), 50 + (row * rowSpace), this.player);
				entities.add(alien);
				alienCount++;
			}
		}
	}

	public ShipEntity getShip(){
		return ship;
	}

	public ShipEntity getEntity(){
		return (ShipEntity) entities.get(0);
	}

	public void setSpeed(double moveSpeed){
		this.moveSpeed = moveSpeed;
	}

	public double getSpeed() {
		return moveSpeed;
	}

	public void setFireSpeed(double firingInterval){
		this.firingInterval = firingInterval;
	}

	public double getFireSpeed(){
		return firingInterval;
	}

	public void increaseBulletCount() {
		bulletCount++;
	}

	/**
	 * Notification from a game entity that the logic of the game
	 * should be run at the next opportunity (normally as a result of some
	 * game event)
	 */
	public void updateLogic() {
		logicRequiredThisLoop = true;
	}

	/**
	 * Remove an entity from the game. The entity removed will
	 * no longer move or be drawn.
	 *
	 * @param entity The entity that should be removed
	 */
	public void removeEntity(Entity entity) {
		removeList.add(entity);
	}

	/**
	 * Notification that the player has died.
	 */
	public void notifyDeath() {
		message = "Level "+level+", Score :"+ alienkill;
		waitingForKeyPress = true;
		updatePlayInfo(timer, coinCount);
		alienkill=0;
		coinCount = 0;
	}

	/**
	 * Notification that the player has won since all the aliens
	 * are dead.
	 */
	public void notifyWin() {
		message = "Level " + level + ", Score :" + alienkill;
		waitingForKeyPress = true;
		db.storeHighScore(alienkill);
		alienkill = 0;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		updatePlayInfo(timer, coinCount);
	}

	public void updatePlayInfo(int timer, int coin) {
		db.increasePlayCount();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		db.updatePlayTime(timer / 100);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		db.updateCoin(coin);
		coinCount = 0;
	}

	private int coinCount = 0;

	public void increaseCoinCount() {
		coinCount++;
		System.out.println("Coin Count: " + coinCount); // 콘솔에 현재 코인 개수를 출력합니다.
	}

	// 에일리언 처치 시 관련 함수
	public void notifyAlienKilled(Entity alienEntity) {
		// reduce the alien count, if there are none left, the player has won!
		alienCount--;
		alienkill++;

		if (alienCount == 0) {
			notifyWin();
		}

		generateCoin(alienEntity);
		speedUpAliens();
	}

	private void generateCoin(Entity alienEntity) {
		Random rand = new Random();
		if (rand.nextInt(100) < 50) { // 50% chance to generate a coin
			CoinEntity coin = new CoinEntity(this, "sprites/coin.png", alienEntity.getX(), alienEntity.getY());
			entities.add(coin);
		}
	}

	private void speedUpAliens() {
		for (Entity entity : entities) { // Assuming 'aliens' is a collection that holds only alien entities
			if (entity instanceof AlienEntity) {
				// speed up by 2%
				entity.setHorizontalMovement(entity.getHorizontalMovement() * 1.02);
			}
		}
	}

	/**
	 * Attempt to fire a shot from the player. Its called "try"
	 * since we must first check that the player can fire at this
	 * point, i.e. has he/she waited long enough between shots
	 */
	public void tryToFire() {
		// check that we have waiting long enough to fire
		if (System.currentTimeMillis() - lastFire < firingInterval) {
			return;
		}

		// if we waited long enough, create the shot entity, and record the time.
		lastFire = System.currentTimeMillis();

		for (int i = 0; i < bulletCount; i++) {
			int bulletX = ship.getX() + 10 - (bulletCount - 1) * spaceBetweenBullets / 2 + i * spaceBetweenBullets;
			ShotEntity shot = new ShotEntity(this, this.player.getSkin().getShipShotImage(), bulletX, ship.getY() - 30);
			entities.add(shot);
		}
	}

	/**
	 * The main game loop. This loop is running during all game
	 * play as is responsible for the following activities:
	 * <p>
	 * - Working out the speed of the game loop to update moves
	 * - Moving the game entities
	 * - Drawing the screen contents (entities, text)
	 * - Updating game events
	 * - Checking Input
	 * <p>
	 */

	public void gameLoop() {
		long lastLoopTime = SystemTimer.getTime();

		new Sound("sound/bgm.wav");

		try {
			background = ImageIO.read(new File(player.getTheme().getBackgroundImage()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// keep looping round til the game ends
		while (gameRunning) {
			// work out how long its been since the last update, this
			// will be used to calculate how far the entities should
			// move this loop
			long delta = SystemTimer.getTime() - lastLoopTime;
			lastLoopTime = SystemTimer.getTime();

			// update the frame counter
			lastFpsTime += delta;
			fps++;
			timer ++;
			// update our FPS counter if a second has passed since
			// we last recorded
			if (lastFpsTime >= 1000) {
				container.setTitle(windowTitle+" (FPS: "+fps+")");
				lastFpsTime = 0;
				fps = 0;
			}

			// Get hold of a graphics context for the accelerated
			// surface and blank it out
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

			// draw the background image
			if (background != null) {
				g.drawImage(background, 0, 0, null);
			}

			// cycle round asking each entity to move itself
			if (!waitingForKeyPress) {
				for (int i=0;i<entities.size();i++) {
					Entity entity = entities.get(i);
					entity.move(delta);
				}
				if ((level == 4 && timer % 50 == 0) || (level == 5 && timer % 20 == 0) || (level == 6 && timer % 20 == 0)) {
					ObstacleEntity.addObstacle(this, player.getTheme(), this.entities, this.level);
				}
			}


			// cycle round drawing all the entities we have in the game
			for (int i=0;i<entities.size();i++) {
				Entity entity = entities.get(i);

				entity.draw(g);
			}

			// brute force collisions, compare every entity against
			// every other entity. If any of them collide notify
			// both entities that the collision has occured
			for (int p=0;p<entities.size();p++) {
				for (int s=p+1;s<entities.size();s++) {
					Entity me = entities.get(p);
					Entity him = entities.get(s);

					if (me.collidesWith(him)) {
						me.collidedWith(him);
						him.collidedWith(me);
					}
				}
			}

			// remove any entity that has been marked for clear up
			entities.removeAll(removeList);
			removeList.clear();

			// if a game event has indicated that game logic should
			// be resolved, cycle round every entity requesting that
			// their personal logic should be considered.
			if (logicRequiredThisLoop) {
				for (int i=0;i<entities.size();i++) {
					Entity entity = entities.get(i);
					entity.doLogic();
				}

				logicRequiredThisLoop = false;
			}

			// if we're waiting for an "any key" press then draw the
			// current message
			if (waitingForKeyPress) {
				g.setColor(Color.white);
				g.drawString(message,(800-g.getFontMetrics().stringWidth(message))/2,250);
				g.drawString("Press any key", (800 - g.getFontMetrics().stringWidth("Press any key")) / 2, 300);
				//타이머(스코어) 0 초기화
				timer =0;
			}

			//죽인 에일리언 표시
			g.setColor(Color.white);
			g.drawString("죽인 에일리언" + alienkill, 30, 30);
			g.drawString("HP: " + ship.getHP(), 720, 30);

			// finally, we've completed drawing so clear up the graphics
			// and flip the buffer over
			g.dispose();
			strategy.show();

			// resolve the movement of the ship. First assume the ship
			// isn't moving. If either cursor key is pressed then
			// update the movement appropriately
			ship.setHorizontalMovement(0);

			if ((leftPressed) && (!rightPressed)) {
				ship.setHorizontalMovement(-moveSpeed);
			} else if ((rightPressed) && (!leftPressed)) {
				ship.setHorizontalMovement(moveSpeed);
			}

			// if we're pressing fire, attempt to fire
			if (firePressed) {
				tryToFire();

			}

			// we want each frame to take 10 milliseconds, to do this
			// we've recorded when we started the frame. We add 10 milliseconds
			// to this and then factor in the current time to give
			// us our final value to wait for
			SystemTimer.sleep(lastLoopTime+10-SystemTimer.getTime());

			ship.updateShieldStatus();
		}
	}

	/**
	 * A class to handle keyboard input from the user. The class
	 * handles both dynamic input during game play, i.e. left/right
	 * and shoot, and more static type input (i.e. press any key to
	 * continue)
	 *
	 * This has been implemented as an inner class more through
	 * habbit then anything else. Its perfectly normal to implement
	 * this as seperate class if slight less convenient.
	 *
	 * @author Kevin Glass
	 */

	/** 레벨 선택 */
	public void setLevel(int level){
		if (level != 6) {
			this.currentLevel = level;
		}
		this.level = level;
	}


	private class KeyInputHandler extends KeyAdapter {
		/**
		 * The number of key presses we've had while waiting for an "any key" press
		 */
		private int pressCount = 1;

		/**
		 * Notification from AWT that a key has been pressed. Note that
		 * a key being pressed is equal to being pushed down but *NOT*
		 * released. Thats where keyTyped() comes in.
		 *
		 * @param e The details of the key that was pressed
		 */

		public char getKeyName(KeyEvent e) {
			return switch (e.getKeyCode()) {
				case KeyEvent.VK_Q -> 'q' ;
				case KeyEvent.VK_W -> 'w' ;
				case KeyEvent.VK_E -> 'e' ;
				case KeyEvent.VK_R -> 'r' ;
				case KeyEvent.VK_T -> 't' ;
				default -> '\0' ;
			};
		}

		public void handleItemKeyEvent(KeyEvent e) {
			char keyName = getKeyName(e);
			if (itemManager.canUseItem(lastItemUsed)) {
				itemManager.useItem(keyName, Game.this);
				lastItemUsed = System.currentTimeMillis();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// if we're waiting for an "any key" typed then we don't
			// want to do anything with just a "press"
			if (waitingForKeyPress) {
				return;
			}

			switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT -> leftPressed = true;
				case KeyEvent.VK_RIGHT -> rightPressed = true;
				case KeyEvent.VK_UP -> ship.setVerticalMovement(-moveSpeed);
				case KeyEvent.VK_DOWN -> ship.setVerticalMovement(moveSpeed);
				case KeyEvent.VK_SPACE -> firePressed = true;
				case KeyEvent.VK_Q, KeyEvent.VK_W, KeyEvent.VK_E, KeyEvent.VK_R, KeyEvent.VK_T -> handleItemKeyEvent(e);
				default -> {
				}
				// do nothing
			}
		}

		/**
		 * Notification from AWT that a key has been released.
		 *
		 * @param e The details of the key that was released
		 */
		@Override
		public void keyReleased(KeyEvent e) {
			// if we're waiting for an "any key" typed then we don't
			// want to do anything with just a "released"
			if (waitingForKeyPress) {
				return;
			}

			switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT -> leftPressed = false;
				case KeyEvent.VK_RIGHT -> rightPressed = false;
				case KeyEvent.VK_UP, KeyEvent.VK_DOWN -> ship.setVerticalMovement(0);
				case KeyEvent.VK_SPACE -> {
					firePressed = false;
					new Sound("sound/hitSound.wav");
				}
				default -> {
				}
				// do nothing
			}
		}

		/**
		 * Notification from AWT that a key has been typed. Note that
		 * typing a key means to both press and then release it.
		 *
		 * @param e The details of the key that was typed.
		 */
		@Override
		public void keyTyped(KeyEvent e) {
			// if we're waiting for a "any key" type then
			// check if we've recieved any recently. We may
			// have had a keyType() event from the user releasing
			// the shoot or move keys, hence the use of the "pressCount"
			// counter.
			if (waitingForKeyPress) {
				if (pressCount == 1) {
					// since we've now recieved our key typed
					// event we can mark it as such and start
					// our new game
					waitingForKeyPress = false;
					startGame();
					pressCount = 0;
				} else {
					pressCount++;
				}
			}

			// if we hit escape, then quit the game
			if (e.getKeyChar() == 27) {
				System.exit(0);
			}
		}
	}
}