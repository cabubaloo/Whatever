package com.wolfertgames.tutorial.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import com.wolfertgames.tutorial.Handler;

public abstract class Entity {
	
	protected float x, y;
	protected int width, height;
	protected Handler handler;
	protected BufferedImage texture;
	protected Rectangle bounds;
	protected boolean collides;
	
	public Entity(Handler handler, BufferedImage texture, float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.handler = handler;
		this.texture = texture;
		
		//Does not collide by default
		bounds = null;
		collides = false;
	}
	
	/////// MEMBER FUNCTIONS ///////
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	protected void drawBoudingBox(Graphics g) {
		g.setColor(Color.red);
		g.setColor(Color.red);
		g.drawRect((int) (x + bounds.x - handler.getCamera().getxOffset()), (int) (y + bounds.y - handler.getCamera().getyOffset()), bounds.width, bounds.height);
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this)) continue;
			if (e.getCollisionBounds(0f, 0f).intersects(this.getCollisionBounds(xOffset, yOffset))) {
				return true;
			}
		}
		return false;
	}
	
	/////// GETTERS / SETTERS ///////

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

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

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
	
	
	
}
