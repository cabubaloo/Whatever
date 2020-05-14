package com.wolfertgames.tutorial.gfx;

import java.awt.image.BufferedImage;

//Loads up all game assets
public class Assets {
	
	public static BufferedImage[][] sprites;
	public static BufferedImage[][] tiles;
	public static BufferedImage[][] anim;
	
	public static BufferedImage[] water, waterMudS, mudWaterC, mudWaterS, waterMudC;
	public static BufferedImage test, mud, stone, player;
	
	//Loads all assets into member variables to be accessed elsewhere
	public static void init() {
		
		System.out.println("Called Assets Init()");
		
		//Game Sprites
		SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.loadImage("/textures/rotmg_spritesheet.png"), 40, 40, 25, 88);
		sprites = new BufferedImage[25][88];
		spriteSheet.fillArray(sprites);
		
		//Game Tiles
		SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/mudtiles.jpeg"), 9, 9, 4, 6);
		tiles = new BufferedImage[4][6];
		tileSheet.fillArray(tiles);
		
		//Reyermo Animations
		SpriteSheet animSheet = new SpriteSheet(ImageLoader.loadImage("/textures/ReyermoAnimations.png"), 8, 8, 5, 2);
		anim = new BufferedImage[5][2];
		animSheet.fillArray(anim);
		
		test = sprites[1][1];
		player = sprites[7][6];
		
		waterMudC = new BufferedImage[4];
		waterMudC[0] = tiles[0][2];
		waterMudC[1] = tiles[0][1];
		waterMudC[2] = tiles[1][1];
		waterMudC[3] = tiles[1][2];
		
		mudWaterC = new BufferedImage[4];
		mudWaterC[0] = tiles[3][3];
		mudWaterC[1] = tiles[3][4];
		mudWaterC[2] = tiles[2][4];
		mudWaterC[3] = tiles[2][3];
		
		waterMudS = new BufferedImage[4];
		waterMudS[0] = tiles[0][3];
		waterMudS[1] = tiles[1][3];
		waterMudS[2] = tiles[1][4];
		waterMudS[3] = tiles[0][4];
		
		mudWaterS = new BufferedImage[4];
		mudWaterS[0] = tiles[2][2];
		mudWaterS[1] = tiles[2][1];
		mudWaterS[2] = tiles[3][1];
		mudWaterS[3] = tiles[3][2];
	
		water = new BufferedImage[3];
		water[0] = tiles[1][5];
		water[1] = tiles[2][5];
		water[2] = tiles[3][5];
		
		stone = tiles[0][0];
		mud = tiles[0][5];
		
	}
	
}
