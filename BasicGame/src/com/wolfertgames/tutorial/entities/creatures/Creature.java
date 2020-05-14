package com.wolfertgames.tutorial.entities.creatures;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import com.wolfertgames.tutorial.Handler;
import com.wolfertgames.tutorial.entities.Entity;
import com.wolfertgames.tutorial.tiles.Tile;

public abstract class Creature extends Entity {
	
	public final static float DEFAULT_HEALTH = 100f;
	protected float maxHealth;
	protected float health;
	
	public final static float DEFAULT_SPEED = 6;
	protected float speed;
	
	public final static int DEFAULT_CREATURE_WIDTH = 80;
	public final static int DEFAULT_CREATURE_HEIGHT = 80;
	public final static int DEFAULT_CREATURE_BOUNDS_X = 10;
	public final static int DEFAULT_CREATURE_BOUNDS_Y = 10;
	public final static int DEFAULT_CREATURE_BOUNDS_W = 60;
	public final static int DEFAULT_CREATURE_BOUNDS_H = 60;
	
	protected float deltaX, deltaY;
	
	/////// CONSTURCTORS ///////
	
	public Creature(Handler handler, BufferedImage texture, float x, float y, int width, int height) {
		super(handler, texture, x, y, width, height);
		maxHealth = DEFAULT_HEALTH;
		health = maxHealth;
		speed = DEFAULT_SPEED;
		
		//Default colliding settings for Creature
		bounds = new Rectangle(DEFAULT_CREATURE_BOUNDS_X, DEFAULT_CREATURE_BOUNDS_Y,
							   DEFAULT_CREATURE_BOUNDS_W, DEFAULT_CREATURE_BOUNDS_H);
		collides = true;
	}
	
	/////// MEMBER FUNCTIONS ///////
	
	//Move creature by deltas
	public void move() {
		moveX();
		moveY();
	}
	
	public void moveX() {
		while (true) {
			int tileRX = (int) (x + deltaX + bounds.x + bounds.width) / Tile.TILE_WIDTH;
			int tileLX = (int) (x + deltaX + bounds.x) / Tile.TILE_WIDTH;
			if (!collisionWithWorld(tileRX, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
			    !collisionWithWorld(tileRX, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT) &&
			    !collisionWithWorld(tileLX, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
			    !collisionWithWorld(tileLX, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT) &&
			    !checkEntityCollisions(deltaX, 0)) {
				x += deltaX;
				break;
			}
			if (deltaX > 0) {
				deltaX--;
				if (deltaX < 0) break;
			} else if (deltaX < 0) {
				deltaX++;
				if (deltaX > 0) break;
			} else break;
		}
	}
	
	public void moveY() {
		while (true) {
			int tileDX = (int) (y + deltaY + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
			int tileUX = (int) (y + deltaY + bounds.y) / Tile.TILE_HEIGHT;
			if (!collisionWithWorld((int) (x + bounds.x) / Tile.TILE_WIDTH, tileDX) &&
			    !collisionWithWorld((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, tileDX) &&
			    !collisionWithWorld((int) (x + bounds.x) / Tile.TILE_WIDTH, tileUX) &&
			    !collisionWithWorld((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, tileUX) &&
			    !checkEntityCollisions(0, deltaY)) {
				y += deltaY;
				break;
			}
			if (deltaY > 0) {
				deltaY--;
				if (deltaY < 0) break;
			} else if (deltaY < 0) {
				deltaY++;
				if (deltaY > 0) break;
			} else break;
		}
	}
	
	protected boolean collisionWithWorld(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	/////// GETTERS / SETTERS ///////

	public float getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(float maxHealth) {
		this.maxHealth = maxHealth;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
