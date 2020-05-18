package com.wolfertgames.mj54.display.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage[][] sprites;
	public static BufferedImage[] buttons;
	public static BufferedImage buttonNotPressed, buttonPressed, coin, player, bank,
	enterArrow, npc, key, book, matrix, lecturn, wall, rag, planks, walltop, wallside,
	playerIdle, playerWalking, playerJumping, bear, bearJam;
	
	public static void init() {
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/textures.png"), 16, 16, 10, 20);
		sprites = new BufferedImage[10][20];
		sheet.fillArray(sprites);
		
		matrix = ImageLoader.loadImage("/textures/Matrix.png");
		
		wall = sheet.cropTile(5, 2, 2, 2);
		walltop = sheet.cropTile(0, 7, 3, 1);
		wallside = sheet.cropTile(9, 0, 1, 3);
		
		playerIdle = sheet.cropTile(0, 12, 2, 3);
		playerWalking = sheet.cropTile(4, 12, 2, 3);
		playerJumping = sheet.cropTile(6, 12, 2, 3);
		
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
	}
}
