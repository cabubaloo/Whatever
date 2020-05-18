package com.wolfertgames.mj54.display.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage sheet;
	private BufferedImage errorImage = ImageLoader.loadImage("/textures/Error.png");
	
	private final int spriteWidth;
	private final int spriteHeight;
	private final int spritesAcross;
	private final int spritesDown;
	
	public SpriteSheet(BufferedImage sheet, int spriteWidth, int spriteHeight, int spritesAcross, int spritesDown) {
		this.sheet = sheet;
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.spritesAcross = spritesAcross;
		this.spritesDown = spritesDown;
	}
	
	//Returns a subsection of the spritesheet
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}
	
	public BufferedImage cropTile(int x, int y, int width, int height) {
		return sheet.getSubimage(x * spriteWidth, y * spriteHeight, width * spriteWidth, height * spriteHeight);
	}
	
	public BufferedImage cropTile(int x, int y) {
		return sheet.getSubimage(x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight);
	}
	
	//Returns a tile from the spritesheet
	public BufferedImage tile(int x, int y) {
		if (x >= spritesAcross || y >= spritesDown) {
			System.out.println("SpriteSheet.tiles() out of range");
			return errorImage;
		}
		return crop(x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight);
	}
	
	//Fills a BufferedImage Array with tiles
	public void fillArray(BufferedImage[][] array) {
		for (int i = 0; i < spritesAcross; i++) {
			for (int j = 0; j < spritesDown; j++) {
				array[i][j] = tile(i,j);
			}
		}
	}
	
	//Fills a BufferedImage Array with tiles
	public void fillArray(BufferedImage[][] array, int arrayWidth, int arrayHeight) {
		if (arrayWidth >= spritesAcross || arrayHeight >= spritesDown) {
			System.out.println("SpriteSheet.fillArray() out of range");
			return;
		}
		for (int i = 0; i < arrayWidth; i++) {
			for (int j = 0; j < arrayHeight; j++) {
				array[i][j] = tile(i,j);
			}
		}
	}

	public int getSpriteWidth() {
		return spriteWidth;
	}

	public int getSpriteHeight() {
		return spriteHeight;
	}

	public int getSpritesAcross() {
		return spritesAcross;
	}

	public int getSpritesDown() {
		return spritesDown;
	}
	
}
