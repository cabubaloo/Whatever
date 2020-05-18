package com.wolfertgames.gj54.entities.dynamics;

import java.awt.image.BufferedImage;

import com.wolfertgames.gj54.math.Vector2;
import com.wolfertgames.mj54.Handler;
import com.wolfertgames.mj54.entities.EntityManager;

public abstract class Newtonian extends Dynamic {
	
	protected Vector2 velocity, dVel;
	protected Vector2 force;
	protected float mass;

	public Newtonian(Handler handler, Vector2 position, int width, int height, float mass) {
		super(handler, position, width, height);
		
		this.mass = mass;
		velocity = new Vector2(0f,0f);
		dPos = new Vector2(0f,0f);
		dVel = new Vector2(0f,0f);
		force = new Vector2(0f,0f);
	}
	
	@Override
	public void moveX(EntityManager em) {
		while (true) {
			if (checkEntityCollisions(em, dPos.x, 0) == null) {
				position.x += dPos.x;
				velocity.x += dVel.x;
				break;
			} else if (Math.abs(dPos.x) < 1) {
				velocity.x = 0;
				break;
			} else {
				dPos.x *= 0.9;
			}
		}
	}
	
	@Override
	public void moveY(EntityManager em) {
		while (true) {
			if (checkEntityCollisions(em, 0, dPos.y) == null) {
				position.y += dPos.y;
				velocity.y += dVel.y;
				break;
			} else if (Math.abs(dPos.y) < 1) {
				velocity.y = 0;
				break;
			} else {
				dPos.y *= 0.9;
			}
		}
	}
	
	@Override
	public void move(float deltaTime, EntityManager em) {
		dPos.zero();
		dVel.zero();
		force.zero();
		sumForces();
		calculateMotion(deltaTime);
		moveX(em);
		moveY(em);
	}
	
	public abstract void sumForces();
	
	public void calculateMotion(float deltaTime) {
		dVel.y = force.y / mass * deltaTime;
		dVel.x = force.x / mass * deltaTime;
		
		dPos.x = velocity.x;
		dPos.y = velocity.y;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public Vector2 getdVel() {
		return dVel;
	}

	public void setdVel(Vector2 dVel) {
		this.dVel = dVel;
	}

	public Vector2 getForce() {
		return force;
	}

	public void setForce(Vector2 force) {
		this.force = force;
	}

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}
	
	
}
