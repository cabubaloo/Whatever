package com.wolfertgames.mj54.display.gfx;

import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.sound.sampled.AudioInputStream;

import com.wolfertgames.tutorial.utils.Utilities;

public class Assets {
	
	public static BufferedImage[][] sprites;
	public static BufferedImage[] buttons;
	public static BufferedImage buttonNotPressed, buttonPressed, coin, bank,
	enterArrow, npc, key, book, matrix, lecturn, wall, rag, planks, walltop, wallside,
	playerIdle, playerWalking, playerJumping, bear, bearJam, hook;
	
	public static Animation breathing, running;
	public static Font font80, customFontSmall;
	
	public static String ostTrack, buttonSound, computerBeep, drawerOpening, famousScream, scrape,
		switchSound, applause, walk, bearRoar, bigThumpSound, grabSound, error,
		keySound, paper, slapSound, type, jump, goodSound, loadPlayer;
	
	public static void init() {
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/textures.png"), 16, 16, 10, 20);
		sprites = new BufferedImage[10][20];
		sheet.fillArray(sprites);
		
		matrix = ImageLoader.loadImage("/textures/Matrix.png");
		
		hook = sheet.cropTile(5, 6, 2, 2);
		
		wall = sheet.cropTile(5, 2, 2, 2);
		walltop = sheet.cropTile(0, 7, 3, 1);
		wallside = sheet.cropTile(9, 0, 1, 3);
		
		breathing = new Animation(500, new BufferedImage[] {sheet.cropTile(0, 12, 2, 3), sheet.cropTile(2, 12, 2, 3)});
		running = new Animation(200, new BufferedImage[] {
				sheet.cropTile(0, 15, 2, 3), sheet.cropTile(2, 15, 2, 3), sheet.cropTile(4, 15, 2, 3), sheet.cropTile(6, 15, 2, 3)});
		
		playerIdle = sheet.cropTile(0, 12, 2, 3);
		playerWalking = sheet.cropTile(4, 12, 2, 3);
		playerJumping = sheet.cropTile(8, 12, 2, 3);
		
		bear = sheet.cropTile(0, 8, 4, 2);
		bearJam = sheet.cropTile(0, 10, 4, 2);
		
		coin = sprites[2][0];
		bank = sheet.cropTile(0, 4, 3, 3);
		enterArrow = sheet.cropTile(3, 0, 2, 2);
		npc = sheet.cropTile(3, 2, 2, 2);
		lecturn = sheet.cropTile(7, 0, 2, 3);
		planks = sheet.cropTile(3, 6, 2, 2);
		
		buttonNotPressed = sheet.cropTile(0, 0, 2, 1);
		buttonPressed = sheet.cropTile(0, 1, 2, 1);
		
		buttons = new BufferedImage[2];
		buttons[0] = buttonNotPressed;
		buttons[1] = buttonPressed;
		
		key = sheet.cropTile(3, 4, 4, 2);
		rag = sheet.cropTile(7, 3, 2, 4); 
		book = sheet.cropTile(5, 0, 2, 2);  
		
		font80 = Utilities.loadFont("/fonts/veteran_typewriter.ttf", 80);
		customFontSmall = Utilities.loadFont("/fonts/veteran_typewriter.ttf", 35);
		
		//font80 = new Font("Serif", Font.BOLD, 80);
		//customFontSmall = new Font("Serif", Font.BOLD, 30);
		
		applause = 		"/sounds/applause.wav";
		ostTrack = 		"/sounds/tranquilityOST.wav";
		buttonSound = 	"/sounds/button.wav";
		computerBeep = 	"/sounds/computer.wav";
		drawerOpening = "/sounds/drawer.wav";
		famousScream = 	"/sounds/scream.wav";
		
		scrape = 		"/sounds/squeak.wav";
		switchSound = 	"/sounds/switch.wav";
		walk = 			"/sounds/walk.wav";
		bearRoar = 		"/sounds/bearRoar.wav";
		bigThumpSound = "/sounds/bigThumpSound.wav";
		grabSound = 	"/sounds/bumpSound.wav";
		
		error = 		"/sounds/error.wav";
		keySound = 		"/sounds/keySound.wav";
		paper = 		"/sounds/paper.wav";
		slapSound = 	"/sounds/thudSound.wav";
		type = 			"/sounds/type.wav";
		
		jump = 			"/sounds/jump.wav";
		goodSound = 	"/sounds/someThingGood.wav";
		loadPlayer = 	"/sounds/loadPlayer.wav";
	}
}
