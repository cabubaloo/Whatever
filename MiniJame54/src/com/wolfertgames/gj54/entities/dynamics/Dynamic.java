package com.wolfertgames.gj54.entities.dynamics;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wolfertgames.gj54.math.Vector2;
import com.wolfertgames.mj54.Handler;
import com.wolfertgames.mj54.entities.Entity;
import com.wolfertgames.mj54.entities.EntityManager;

public abstract class Dynamic extends Entity {
	
	protected Vector2 dPos;
	
	/////// CONSTURCTORS ///////
	
	public Dynamic(Handler handler, Vector2 position, int width, int height) {
		super(handler, position, width, height);
		dPos = new Vector2(0f,0f);
		collides = true;
	}
	
	/////// MEMBER FUNCTIONS ///////
	
	//Move creature by deltas
	public void move(float deltaTime, EntityManager em) {
		moveX(em);
		moveY(em);
	}
	
	public void moveX(EntityManager em) {
		while (true) {
			if (checkEntityCollisions(em, dPos.x, 0) == null) {
				position.x += dPos.x;
				break;
			} else if (Math.abs(dPos.x) < 1) {
				break;
			} else {
				dPos.x *= 0.9;
			}
		}
	}
	
	public void moveY(EntityManager em) {
		while (true) {
			if (checkEntityCollisions(em, 0, dPos.y) == null) {
				position.y += dPos.y;
				break;
			} else if (Math.abs(dPos.y) < 1) {
				break;
			} else {
				dPos.y *= 0.9;
			}
		}
	}
	
	public boolean onGround() {
		return checkEntityCollisions(handler.getWorld().getEntityManager(), 0, 1) != null;
	}

}
