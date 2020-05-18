package com.wolfertgames.mj54.display.gfx;

import java.awt.Rectangle;

import com.wolfertgames.mj54.Handler;
import com.wolfertgames.mj54.entities.Entity;

public class Camera {
	
	//MEMBER VARIABLES
	
	private Handler handler;
	private float xOffset, yOffset;
	
	//GETTERS AND SETTERS
	
	public float getXOffset() {
		return xOffset;
	}

	public void setXOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getYOffset() {
		return yOffset;
	}

	public void setYOffset(float yOffset) {
		this.yOffset = yOffset;
	}
	
	//CONSTRUCTORS
	
	public Camera(Handler handler, float xOffset, float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	//MEMBER FUNCTIONS
	
	public void moveRel(float x, float y) {
		xOffset += x;
		yOffset += y;
	}
	
	public void moveAbs(float x, float y) {
		xOffset = x;
		yOffset = y;
	}
	
	public void center(float x, float y) {
		xOffset = x - handler.getGame().getWidth() / 2;
		yOffset = y - handler.getGame().getHeight() / 2;
	}
	
	public void centerOnEntity(Entity e) {
		xOffset = e.getPosition().x - (handler.getGame().getWidth() - e.getWidth())/ 2;
		yOffset = e.getPosition().y - (handler.getGame().getHeight() - e.getHeight()) / 2;
	}
	
	public void offsetOnEntity(Entity e, int offsetx, int offsety) {
		xOffset = (e.getPosition().x + offsetx) - (handler.getGame().getWidth() - e.getWidth())/ 2;
		yOffset = (e.getPosition().y + offsety) - (handler.getGame().getHeight() - e.getHeight()) / 2;
	}
	
	public Rectangle getScreen() {
		return new Rectangle((int)getXOffset(), (int)getYOffset(), handler.getGame().getWidth(), handler.getGame().getHeight());
	}

}
