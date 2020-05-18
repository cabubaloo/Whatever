package com.wolfertgames.gj54.entities.statics.scripted;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wolfertgames.gj54.math.Vector2;
import com.wolfertgames.mj54.Handler;

public class ImageCollectable extends ScriptedStaticCollider {
	
	private BufferedImage texture;
	private boolean visible;

	public ImageCollectable(Handler handler, Vector2 position, int width, int height, BufferedImage texture, CollisionListener collisionListener) {
		super(handler, position, width, height, collisionListener);
		this.texture = texture;
		this.collisionListener = collisionListener;
		bounds = new Rectangle(0, 0, width, height);
		visible = true;
	}
	
	@Override
	public void tick(float deltaTime) {
		checkForCollisionEvent(this);
	}

	@Override
	public void render(float deltaTime, Graphics g) {
		if (visible) {
			g.drawImage(texture, (int)(position.x - handler.getCamera().getXOffset()), (int)(position.y - handler.getCamera().getYOffset()), width, height, null);
			drawBoudingBox(g);
		}
	}

}
