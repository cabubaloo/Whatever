package com.wolfertgames.gj54.entities.statics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.wolfertgames.gj54.math.Vector2;
import com.wolfertgames.mj54.Handler;

public class ImageSEntity extends StaticEntity {
	
	private Image texture;
	
	public ImageSEntity(Handler handler, Image texture, Vector2 position, int width, int height) {
		super(handler, position, width, height);
		this.texture = texture;
		bounds = new Rectangle(0, 0, width, height);
	}

	@Override
	public void tick(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float deltaTime, Graphics g) {
		g.drawImage(texture, (int)(position.x - handler.getCamera().getXOffset()), (int)(position.y - handler.getCamera().getYOffset()), width, height, null);
	    //drawBoudingBox(g);
	}

}
