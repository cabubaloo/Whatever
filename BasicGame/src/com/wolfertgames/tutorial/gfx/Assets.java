package com.wolfertgames.tutorial.gfx;

import java.awt.image.BufferedImage;

//Loads up all game assets
public class Assets {
	
	private static int SPRITE_WID = 40;
	private static int SPRITE_HGT = 40;
	private static int SPRITES_ACROSS = 25;
	private static int SPRITES_DOWN = 88;
	
	public static BufferedImage[][] sprites;
	
	//Loads all assets into member variables to be accessed elsewhere
	public static void init() {
		
		//Chops up spritesheet into an array of BufferedImages
		sprites = new BufferedImage[SPRITES_ACROSS][SPRITES_DOWN];
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/rotmg_spritesheet.png"));
		for (int i = 0; i < SPRITES_ACROSS; i++) {
			for (int j = 0; j < SPRITES_DOWN; j++) {
				sprites[i][j] = sheet.crop(i * SPRITE_WID, j * SPRITE_HGT, SPRITE_WID, SPRITE_HGT);
			}
		}
		
	}
	
}
