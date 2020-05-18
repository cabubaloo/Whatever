package com.wolfertgames.mj54.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wolfertgames.gj54.math.Vector2;
import com.wolfertgames.mj54.Handler;
import com.wolfertgames.mj54.display.gfx.Camera;
import com.wolfertgames.mj54.world.World;

public abstract class Entity {
	
	public final static int DEFAULT_CREATURE_WIDTH = 80;
	public final static int DEFAULT_CREATURE_HEIGHT = 80;
	public final static int DEFAULT_CREATURE_BOUNDS_X = 10;
	public final static int DEFAULT_CREATURE_BOUNDS_Y = 10;
	public final static int DEFAULT_CREATURE_BOUNDS_W = 60;
	public final static int DEFAULT_CREATURE_BOUNDS_H = 60;
	
	protected boolean inputEnabled = true;
	protected boolean visible = true;
	
	protected Vector2 position;
	protected int width, height;
	protected Handler handler;
	protected BufferedImage texture;
	protected Rectangle bounds;
	protected boolean collides;
	
	protected boolean active = true;
	
	public boolean isActive() {
		return active;
	}

	public void remove() {
		active = false;
	}

	public Entity(Handler handler, Vector2 position, int width, int height) {
		this.position = position;
		this.width = width;
		this.height = height;
		this.handler = handler;
		collides = false;
	}
	
	/////// MEMBER FUNCTIONS ///////
	
	public abstract void tick(float deltaTime);
	
	public abstract void render(float deltaTime, Graphics g);
	
	protected void drawBoudingBox(Graphics g) {
		if (bounds == null) System.out.println("ERROR: drawBoundingBox() -- No Bounding Box defined for this object");
		g.setColor(Color.red);
		g.setColor(Color.red);
		g.drawRect((int) (position.x + bounds.x - handler.getCamera().getXOffset()), (int) (position.y + bounds.y - handler.getCamera().getYOffset()), bounds.width, bounds.height);
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		if (bounds == null) System.out.println("ERROR: getCollisionBounds() -- No Bounding Box defined for this object");
		return new Rectangle((int) (position.x + bounds.x + xOffset), (int) (position.y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	public Entity checkEntityOverlap(EntityManager em, float xOffset, float yOffset) {
		if (this.bounds == null) return null;
		for (Entity e : em.getEntities()) {
			if (e.equals(this) || e.getBounds() == null) continue;
			if (e.getCollisionBounds(0f, 0f).intersects(this.getCollisionBounds(xOffset, yOffset))) {
				return e;
			}
		}
		return null;
	}
	
	public Entity checkEntityCollisions(EntityManager em, float xOffset, float yOffset) {
		if (this.bounds == null || this.collides == false) return null;
		for (Entity e : em.getEntities()) {
			if (e.equals(this) || e.getBounds() == null || e.collides == false) continue;
			if (e.getCollisionBounds(0f, 0f).intersects(this.getCollisionBounds(xOffset, yOffset))) {
				return e;
			}
		}
		return null;
	}
	
	/////// GETTERS / SETTERS ///////

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
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

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public boolean isInputEnabled() {
		return inputEnabled;
	}

	public void setInputEnabled(boolean inputEnabled) {
		this.inputEnabled = inputEnabled;
	}

	public boolean isCollides() {
		return collides;
	}

	public void setCollides(boolean collides) {
		this.collides = collides;
	}
	
	public boolean onScreen() {
		return new Rectangle((int)position.x, (int)position.y, bounds.width, bounds.height).intersects(handler.getCamera().getScreen());
	}
	
	public Rectangle getLocationRect() {
		return new Rectangle((int)position.x, (int)position.y, bounds.width, bounds.height);
	}
	
	public boolean intersecting(Entity e) {
		return getLocationRect().intersects(e.getLocationRect());
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}
