package com.wolfertgames.tutorial.world;

import java.awt.Graphics;
import com.wolfertgames.tutorial.Handler;
import com.wolfertgames.tutorial.entities.EntityManager;
import com.wolfertgames.tutorial.entities.creatures.Player;
import com.wolfertgames.tutorial.entities.statics.Tree;
import com.wolfertgames.tutorial.tiles.Tile;
import com.wolfertgames.tutorial.utils.Utilities;

public class World {
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiledata;
	
	private EntityManager entityManager;
	
	public World(Handler handler, String path) {
		this.handler = handler;
		loadWorld(path);
		entityManager = new EntityManager(handler, new Player(handler, (int) spawnX * Tile.TILE_WIDTH, (int) spawnY * Tile.TILE_HEIGHT));
		entityManager.addEntity(new Tree(handler, (int) 5 * Tile.TILE_WIDTH, (int) 5 * Tile.TILE_HEIGHT));
		entityManager.addEntity(new Tree(handler, (int) 6 * Tile.TILE_WIDTH, (int) 5 * Tile.TILE_HEIGHT));
		entityManager.addEntity(new Tree(handler, (int) 7 * Tile.TILE_WIDTH, (int) 5 * Tile.TILE_HEIGHT));
		
	}
	
	/////// MEMBER FUNCTIONS ///////
	
	public void tick() {
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		
		int posXEdge = Math.min(width, 1 + (int) (handler.getCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH);
		int posYEdge = Math.min(height, 1 + (int) (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT);
		int negXEdge = Math.max(0, (int) handler.getCamera().getxOffset() / Tile.TILE_WIDTH);
		int negYEdge = Math.max(0, (int) handler.getCamera().getyOffset() / Tile.TILE_HEIGHT);
		
		for (int i = negXEdge ; i < posXEdge; i++) {
			for (int j = negYEdge; j < posYEdge; j++) {
				getTile(i,j).render(g, (int)(i * Tile.TILE_WIDTH - handler.getCamera().getxOffset()), (int)(j * Tile.TILE_HEIGHT - handler.getCamera().getyOffset()));
			}
		}
		
		entityManager.render(g);
	}
	
	//Retrieves Tile Class from array indices
	public Tile getTile(int x, int y) {
		
		//If outside of world array, return mudtile (camera wont follow but prevents a crash)
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.mud;
		
		Tile t = Tile.tiles[tiledata[x][y]];
		if (t == null) {
			System.out.println("Undefined TileID, returning MudTile");
			return Tile.mud;
		}
		return t;
	}
	
	
	//Loads World Data and populates tiledata
	private void loadWorld(String path) {
		
		String file = Utilities.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utilities.parseInt(tokens[0]);
		height = Utilities.parseInt(tokens[1]);
		spawnX = Utilities.parseInt(tokens[2]);
		spawnY = Utilities.parseInt(tokens[3]);
		
		tiledata = new int[width][height];
		
		for (int i = 0 ; i < width; i++) {
			for (int j = 0; j < height; j++) {
				tiledata[i][j] = Utilities.parseInt(tokens[j * width + i + 4]);
			}
		}
	}	
	
	/////// GETTERS / SETTERS ///////

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSpawnX() {
		return spawnX;
	}

	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}

	public int[][] getTiledata() {
		return tiledata;
	}

	public void setTiledata(int[][] tiledata) {
		this.tiledata = tiledata;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	
}
