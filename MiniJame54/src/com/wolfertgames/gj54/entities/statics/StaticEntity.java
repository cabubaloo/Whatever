package com.wolfertgames.gj54.entities.statics;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wolfertgames.gj54.math.Vector2;
import com.wolfertgames.mj54.Handler;
import com.wolfertgames.mj54.entities.Entity;

public abstract class StaticEntity extends Entity {
	
	public StaticEntity(Handler handler, Vector2 position, int width, int height) {
		super(handler, position, width, height);
	}
	
}
