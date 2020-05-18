package com.wolfertgames.mj54.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.wolfertgames.gj54.entities.dynamics.Dynamic;
import com.wolfertgames.mj54.Handler;

public class EntityManager {
	
	private Entity player;	//Unique Player
	
	private ArrayList<Entity> entities;
	private Comparator<Entity> renderSort = new Comparator<Entity>() {
		@Override
		public int compare(Entity a, Entity b) {
			if (a.getPosition().x < b.getPosition().x) return -1;
			else if (a.getPosition().x == b.getPosition().x) return 0;
			else return 1;
		}
	};
	
	public EntityManager() {
		entities = new ArrayList<Entity>();
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void removeEntity(Entity e) {
		entities.remove(e);
	}
	
	public void addPlayer(Entity p) {
		player = p;
		entities.add(p);
	}
	
	public void tick(float deltaTime) {
		ListIterator<Entity> itr = entities.listIterator();
	    while (itr.hasNext()) {
	    	Entity e = itr.next();
	    	e.tick(deltaTime);
	    	if (!e.isActive()) itr.remove();
	    }
	    //entities.sort(renderSort);
	}
	
	public void render(float deltaTime, Graphics g) {
		for (Entity e : entities) {
			if (inRenderRange(e) && e.isVisible())
				e.render(deltaTime, g);
		}
	}

	public Entity getPlayer() {
		return player;
	}

	public void setPlayer(Entity player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	public void purge() {
		for (Entity e : entities) {
			e.remove();
		}
		tick(0);
	}
	
	public static boolean inRenderRange(Entity e) {
		Rectangle screen = new Rectangle((int)e.handler.getGame().getCamera().getXOffset() - 200,
				(int)e.handler.getGame().getCamera().getYOffset() - 200,
				e.handler.getGame().getWidth() + 400, 
				e.handler.getGame().getHeight() + 400);
		return new Rectangle((int)e.getPosition().x, (int)e.getPosition().y, e.getWidth(), e.getHeight()).intersects(screen);
	}
}
