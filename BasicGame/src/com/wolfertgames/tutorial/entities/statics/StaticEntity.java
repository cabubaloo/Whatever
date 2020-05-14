package com.wolfertgames.tutorial.entities.statics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wolfertgames.tutorial.Handler;
import com.wolfertgames.tutorial.entities.Entity;

public abstract class StaticEntity extends Entity {
	
	public final static int DEFAULT_SENTITY_WIDTH = 80;
	public final static int DEFAULT_SENTITY_HEIGHT = 80;
	public final static int DEFAULT_SENTITY_BOUNDS_X = 0;
	public final static int DEFAULT_SENTITY_BOUNDS_Y = 0;
	public final static int DEFAULT_SENTITY_BOUNDS_W = DEFAULT_SENTITY_WIDTH;
	public final static int DEFAULT_SENTITY_BOUNDS_H = DEFAULT_SENTITY_HEIGHT;
	
	public StaticEntity(Handler handler, BufferedImage texture, float x, float y, int width, int height) {
		super(handler, texture, x, y, width, height);
		
		//Default colliding settings for StaticEntity
		bounds = new Rectangle(DEFAULT_SENTITY_BOUNDS_X, DEFAULT_SENTITY_BOUNDS_Y,
							   DEFAULT_SENTITY_BOUNDS_W, DEFAULT_SENTITY_BOUNDS_H);
		collides = true;
	}
	
}
