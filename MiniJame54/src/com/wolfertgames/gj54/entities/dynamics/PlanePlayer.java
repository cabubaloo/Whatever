package com.wolfertgames.gj54.entities.dynamics;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.wolfertgames.gj54.math.Vector2;
import com.wolfertgames.mj54.Handler;
import com.wolfertgames.mj54.display.gfx.Assets;
import com.wolfertgames.mj54.entities.Entity;

public class PlanePlayer extends Dynamic {
	
	protected final float speed;
	
	public PlanePlayer(Handler handler, Vector2 position, float speed) {
		super(handler, position, Entity.DEFAULT_CREATURE_BOUNDS_H, Entity.DEFAULT_CREATURE_BOUNDS_W);
		this.speed = speed;
		bounds = new Rectangle(0, 0, width, height);
	}

	@Override
	public void tick(float deltaTime) {
		dPos.zero();
		getInput(deltaTime);
		move(deltaTime, handler.getWorld().getEntityManager());
	}

	@Override
	public void render(float deltaTime, Graphics g) {
		//System.out.println("Player Coords: " + x + ", " + y);
		//System.out.println("Camera Coords: " + game.getCamera().getxOffset() + ", " + game.getCamera().getyOffset());
		g.drawImage(Assets.buttonNotPressed, (int)(position.x - handler.getCamera().getXOffset()), (int)(position.y - handler.getCamera().getYOffset()), width, height, null);
	    drawBoudingBox(g);
	}
	
	public void getInput(float deltaTime) {

		if (handler.getKeyManager().up) {
			dPos.y -= speed * deltaTime;
		}
		if (handler.getKeyManager().down) {
			dPos.y += speed * deltaTime;
		}
		if (handler.getKeyManager().left) {
			dPos.x -= speed * deltaTime;
		}
		if (handler.getKeyManager().right) {
			dPos.x += speed * deltaTime;
		}
	}

}
