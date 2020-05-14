package com.wolfertgames.tutorial.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wolfertgames.tutorial.gfx.Assets;

public class Tile {
	
	// *** STATICS *** //
	public static final int TILE_WIDTH = 80;
	public static final int TILE_HEIGHT = 80;
	
	//Assigning tile id numbers
	public static Tile[] tiles;
	
	// *** CLASS *** //
	
	protected BufferedImage texture;
	protected final int id;
	protected boolean solid;
	
	public static Tile mud;
	public static Tile stone1, stone2, stone3, stone4;
	public static Tile water1, water2, water3;
	public static Tile mudWaterN, mudWaterE, mudWaterS, mudWaterW,
					   mudWaterNE, mudWaterSE, mudWaterSW, mudWaterNW;
	public static Tile waterMudN, waterMudE, waterMudS, waterMudW,
					   waterMudNE, waterMudSE, waterMudSW, waterMudNW;
	
	
	public Tile(BufferedImage texture, int id, boolean solid) {
		this.texture = texture;
		this.id = id;
		this.solid = solid;
		
		tiles[id] = this;
	}
	
	public static void init() {
		System.out.println("Called Tile Init()");
		tiles = new Tile[256];
		
		mud = new Tile(Assets.mud, 20, false); //20
		mudWaterN = new Tile(Assets.mudWaterS[0], 10, false);
		mudWaterE = new Tile(Assets.mudWaterS[1], 6, false);
		mudWaterS = new Tile(Assets.mudWaterS[2], 7, false);
		mudWaterW = new Tile(Assets.mudWaterS[3], 11, false);
		mudWaterNE = new Tile(Assets.mudWaterC[0], 15, false);
		mudWaterSE = new Tile(Assets.mudWaterC[1], 19, false);
		mudWaterSW = new Tile(Assets.mudWaterC[2], 18, false);
		mudWaterNW = new Tile(Assets.mudWaterC[3], 14, false);
		waterMudN = new Tile(Assets.waterMudS[0], 12, true);
		waterMudE = new Tile(Assets.waterMudS[1], 13, true);
		waterMudS = new Tile(Assets.waterMudS[2], 17, true);
		waterMudW = new Tile(Assets.waterMudS[3], 16, true);
		waterMudNE = new Tile(Assets.waterMudC[0], 8, true);
		waterMudSE = new Tile(Assets.waterMudC[1], 4, true);
		waterMudSW = new Tile(Assets.waterMudC[2], 5, true);
		waterMudNW = new Tile(Assets.waterMudC[3], 9, true);
		water1 = new Tile(Assets.water[0], 22, true); //22
		water2 = new Tile(Assets.water[1], 23, true);
		water3 = new Tile(Assets.water[2], 21, true);
		stone1 = new Tile(Assets.stone, 0, true); //0
		stone2 = new Tile(Assets.stone, 1, true);
		stone3 = new Tile(Assets.stone, 2, true);
	    stone4 = new Tile(Assets.stone, 3, true);
	}
	
	public void tick() {
		System.out.println("Ticked tile with ID " + getId());
	}
	
	public void render(Graphics g, int x, int y) {
		if (texture == null) {
			System.out.println("Tile texture is null. Terminiating.");
			System.exit(2);
		}
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public int getId() {
		return id;
	}
}
