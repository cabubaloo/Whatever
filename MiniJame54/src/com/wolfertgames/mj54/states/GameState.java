package com.wolfertgames.mj54.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import com.wolfertgames.gj54.entities.controllers.Controller;
import com.wolfertgames.gj54.entities.dynamics.Dynamic;
import com.wolfertgames.gj54.entities.dynamics.PlanePlayer;
import com.wolfertgames.gj54.entities.dynamics.PlatformingPlayer;
import com.wolfertgames.gj54.entities.statics.ImageSEntity;
import com.wolfertgames.gj54.entities.statics.Platform;
import com.wolfertgames.gj54.entities.statics.scripted.Coin;
import com.wolfertgames.gj54.entities.statics.scripted.CollisionListener;
import com.wolfertgames.gj54.entities.statics.scripted.FunctionPass;
import com.wolfertgames.gj54.entities.statics.scripted.ImageCollectable;
import com.wolfertgames.gj54.entities.statics.scripted.ScriptedStaticCollider;
import com.wolfertgames.gj54.math.Vector2;
import com.wolfertgames.mj54.Handler;
import com.wolfertgames.mj54.commands.Subject;
import com.wolfertgames.mj54.commands.Verb;
import com.wolfertgames.mj54.display.gfx.Assets;
import com.wolfertgames.mj54.entities.Entity;
import com.wolfertgames.mj54.entities.EntityManager;
import com.wolfertgames.mj54.input.KeyResponder;
import com.wolfertgames.mj54.input.ReadInString;
import com.wolfertgames.mj54.ui.Console;
import com.wolfertgames.mj54.ui.TextBox;
import com.wolfertgames.mj54.ui.TimedPopUp;
import com.wolfertgames.mj54.ui.UIManager;
import com.wolfertgames.mj54.world.World;
import com.wolfertgames.tutorial.utils.Utilities;

public class GameState extends State {
	
	/*
	 * 	Class:	GameState 
	 * 	Role:	Runs Game
	 * 
	 */
	
	//CONSTANTS
	private static final int PROMPT_HEIGHT = 100;

	
	//CLASS VARIABLES
	static EntityManager entityManager;
	private static UIManager uiManager;
	private static World world;
	
	public static TextBox prompt;
	public static ReadInString scanner;
	public static Console console;
	
	public static int points = 0;
	public static boolean playerControlEnabled = false;
	
	private static int worldEdgeU;
	private static int worldEdgeD;
	private static int worldEdgeL;
	private static int worldEdgeR;
	
	static boolean playerDead;
	
	//DRIVEN GAME OBJECTS
	public static PlatformingPlayer player;
	public static Entity npc;
	public static Entity key;
	public static Entity book;
	public static Entity vault;
	public static Entity rag;
	
	public static TimedPopUp popup;
	
	//CONSTRUCTOR
	
	public GameState(Handler handler) {
		super(handler);
		entityManager = new EntityManager();
		uiManager = new UIManager();
		world = new World(entityManager, uiManager);
		handler.setWorld(world);
		
		init();
	}
	
	//PRIMARY METHODS
	
	private static void init() {
		handler.getCamera().moveAbs(0,0);
		setVariables();
		lanuchUI();
		lanuchLevel();
		TextHandler.startTextGame();
	}

	private static void setVariables() {
		points = 0;
		playerControlEnabled = false;
	}

	private static void lanuchUI() {
		loadConsole();
		loadPrompt();
		loadPopup();
	}

	private static void lanuchLevel() {
		loadWorld(world, "res/worlds/level.txt");
		loadWorldObjects();
	}
	
	static void loadPlayer() {
		if (player == null) {
			makeNewPlayer();
		} else {
			refreshPlayer();
		}
	}

	private static void makeNewPlayer() {
		player = new PlatformingPlayer(handler, new Vector2(100, 400 - 120));
		entityManager.addPlayer(player);
	}
	
	private static void refreshPlayer() {
		playerDead = false;
		player.setVisible(true);
		enablePlayer();
		player.setPosition(new Vector2(100, 400 - 120));
	}
	
	static void restart() {
		world.purge();
		player = null;
		key = null;
		vault = null;
		book = null;
		rag = null;
		init();
	}
	
	static void backToConsole() {
		if (player != null) {
			disablePlayer();
			hidePlayer();
		}
		handler.getCamera().moveAbs(0,0);
	}

	private static void loadPopup() {
		popup = new TimedPopUp(handler, new Rectangle(0, 0, handler.getGame().getWidth(), handler.getGame().getWidth()),
				"EMPTY STRING", new Color(0, 255, 0, 255), new Font("Serif", Font.BOLD, 30));
		popup.clear();
		uiManager.addObject(popup);
	}

	private static void loadWorldObjects() {
		
		npc = new ImageSEntity(handler, Assets.bear, new Vector2(400,-550), 400, 200);
		entityManager.addEntity(npc);
		
		//key = new ImageSEntity(handler, Assets.key, new Vector2(1000,200), 100, 100);
		key = new ImageSEntity(handler, Assets.key, new Vector2(900,900), 200, 100);
		entityManager.addEntity(key);
		
		vault = new ImageSEntity(handler, Assets.bank, new Vector2(2700,-800), 280, 300);
		entityManager.addEntity(vault);
		vault.setCollides(true);
		
		//Lecturn
		entityManager.addEntity(new ImageSEntity(handler, Assets.lecturn, new Vector2(3300,-650), 100, 150));
		
		book = new ImageSEntity(handler, Assets.book, new Vector2(3300,-700), 80, 80);
		book.setBounds(new Rectangle(0,0,100,200));
		entityManager.addEntity(book);
		
		rag = new ImageSEntity(handler, Assets.rag, new Vector2(2350,-300), 50, 100);
		entityManager.addEntity(rag);
	}
	
	@Override
	public void tick(float deltaTime) {
		
		readPrompt();
		world.tick(deltaTime);
		checkEvents();
		
		popup.setVisible(!console.onScreen());
	}

	private void checkEvents() {
		if (player != null) {
			if (player.getPosition().y > worldEdgeD + 1000) {
				if (!playerDead) killPlayer();
			}
			if (new Rectangle(3400, -1900, 800, 600).contains(player.getPosition().x, player.getPosition().y)) {
				win();
			}
		}
	}

	private void readPrompt() {
		if (scanner.isSubmitted()) {
			String command = scanner.retrieveSubmission();
			TextHandler.parseCommand(command);
		}
	}

	@Override
	public void render(float deltaTime, Graphics g) {
		updateCamera();
		drawBackground(g);
		world.render(deltaTime, g);
		if (player != null) player.render(deltaTime, g);
		prompt.setDisplayText(scanner.getString());
	}
	
	public static void win() {
		handler.getGame().setCurrentState(new WinState(handler));
	}

	private void drawBackground(Graphics g) {
		g.drawImage(Assets.matrix,0,0,handler.getGame().getWidth(), handler.getGame().getHeight(),null);
	}

	private void updateCamera() {
		if (player != null && player.isVisible()) {
			if (new Rectangle(-100, 0, 470, 500).contains(player.getPosition().x, player.getPosition().y)) {
				//Inside Console Window
				handler.getCamera().setXOffset(0);
				handler.getCamera().setYOffset(0);
			} else {
				handler.getCamera().offsetOnEntity(world.getEntityManager().getPlayer(),0,-45);
				fleeWorldEdges();
			}
		}
	}

	private void fleeWorldEdges() {
		if (handler.getCamera().getXOffset() < worldEdgeL) handler.getCamera().setXOffset(worldEdgeL);
		if (handler.getCamera().getXOffset() > worldEdgeR) handler.getCamera().setXOffset(worldEdgeR);
		if (handler.getCamera().getYOffset() < worldEdgeU) handler.getCamera().setYOffset(worldEdgeU);
		if (handler.getCamera().getYOffset() > worldEdgeD) handler.getCamera().setYOffset(worldEdgeD);
	}

	private static void loadPrompt() {
		scanner = new ReadInString();
		handler.getKeyManager().addResponder(scanner);
		prompt = new TextBox(handler, new Rectangle(0, handler.getGame().getHeight() - PROMPT_HEIGHT,
				handler.getGame().getWidth(), PROMPT_HEIGHT), "Initialized Text",
				new Color(0,0,0,200), new Color(0,255,0,255), new Font("Serif", Font.BOLD, 30));
		closePrompt();
		uiManager.addObject(prompt);
		openPrompt();
	}

	private static void loadConsole() {
		console = new Console(handler, new Rectangle(0, 0, handler.getGame().getWidth(), handler.getGame().getHeight()),
				new Color(0,0,0,255), new Color(0,255,0,255), new Font("Serif", Font.BOLD, 30));				
		uiManager.addObject(console);
	}
	
	//HELPER METHODS
	private static void loadWorld(World world, String path) {
		
		int startX, startY;
		int tileWidth, tileHeight;
		int width, height;
		
		String file = Utilities.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		
		tileWidth = Utilities.parseInt(tokens[0]);
		tileHeight = Utilities.parseInt(tokens[1]);
		width = Utilities.parseInt(tokens[2]);
		height = Utilities.parseInt(tokens[3]);
		startX = Utilities.parseInt(tokens[4]);
		startY = Utilities.parseInt(tokens[5]);
		
		worldEdgeU = startY;
		worldEdgeD = startY + height * tileHeight;
		worldEdgeL = startX;
		worldEdgeR = startX + width * tileWidth;
		
		/*for (int i = 0 ; i < height; i++) {
			for (int j = 0 ; j < width; j++) {
				ImageSEntity plank = new ImageSEntity(handler, Assets.planks,
						new Vector2(startX + j * tileWidth, startY + i * tileHeight), tileWidth, tileHeight);
				plank.setCollides(false);
				entityManager.addEntity(plank);
			}
		}*/
	
		for (int i = height - 1 ; i >= 0; i--) {
			for (int j = 0 ; j < width; j++) {
				if (tokens[i * width + j + 6].contains("1")) {
					entityManager.addEntity(new Platform(handler, 
							new Vector2(startX + j * tileWidth, startY + i * tileHeight), tileWidth, tileHeight));
				}
			}
		}
	}
	
	private static void openPrompt() {
		scanner.setString("");
		scanner.setListening(true);
		prompt.setDisplayText(scanner.getString());
		prompt.setVisible(true);
	}
	
	private static void closePrompt() {
		scanner.setListening(false);
		prompt.setVisible(false);
	}
	
	//Enables Player Key Response
	private static void enablePlayer() {
		player.setInputEnabled(true);
	}
	
	//Disables Player Key Response
	private static void disablePlayer() {
		player.setInputEnabled(false);
	}
	
	private static void hidePlayer() {
		player.setVisible(false);
	}
	
	private static void showPlayer() {
		player.setVisible(true);
	}
	
	private static void updatePlayerRef() {
		entityManager.removeEntity(player);
		entityManager.addEntity(player);
	}
	
	static void killPlayer() {
		playerDead = true;
		disablePlayer();
		//Play Animation
		TextHandler.logUser("You have perished");
	}
	
	//GETTERS AND SETTERS
	
	public static void incPoints(int i) {
		points += i;
		System.out.println("Points: " + i);
	}
	
	public static EntityManager getEntityManager() {
		return entityManager;
	}

	public static boolean isPlayerControlEnabled() {
		return playerControlEnabled;
	}

	public static void setPlayerControlEnabled(boolean playerControlEnabled) {
		GameState.playerControlEnabled = playerControlEnabled;
	}

	public static int getPoints() {
		return points;
	}

	public static void setPoints(int points) {
		GameState.points = points;
	}
}
