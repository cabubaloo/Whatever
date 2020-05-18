package com.wolfertgames.mj54.world;

import java.awt.Graphics;

import com.wolfertgames.gj54.math.Vector2;
import com.wolfertgames.mj54.entities.EntityManager;
import com.wolfertgames.mj54.ui.UIManager;

public class World {
	
	private EntityManager entityManager;
	private UIManager uiManager;
	
	private Vector2 gravity;
	
	public World(EntityManager entityManager, UIManager uiManager) {
		this.entityManager = entityManager;
		this.uiManager = uiManager;
		
		this.setGravity(new Vector2(0,20));
	}
	
	public void tick(float deltaTime) {
		entityManager.tick(deltaTime);
		uiManager.tick(deltaTime);
	}
	
	public void render(float deltaTime, Graphics g) {
		entityManager.render(deltaTime, g);
		uiManager.render(deltaTime, g);
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public UIManager getUiManager() {
		return uiManager;
	}
	
	public void setUiManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}

	public Vector2 getGravity() {
		return gravity;
	}

	public void setGravity(Vector2 gravity) {
		this.gravity = gravity;
	}
	
	public void purge() {
		entityManager.purge();
		uiManager.purge();
	}

}
