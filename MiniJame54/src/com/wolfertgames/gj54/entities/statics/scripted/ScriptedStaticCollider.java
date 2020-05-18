package com.wolfertgames.gj54.entities.statics.scripted;

import com.wolfertgames.gj54.entities.statics.StaticEntity;
import com.wolfertgames.gj54.math.Vector2;
import com.wolfertgames.mj54.Handler;
import com.wolfertgames.mj54.entities.Entity;

public abstract class ScriptedStaticCollider extends StaticEntity implements Timer{
	
	protected CollisionListener collisionListener;
	protected boolean listening;
	
	protected long prevTime;
	protected long timer;
	
	public ScriptedStaticCollider(Handler handler, Vector2 position, int width, int height, CollisionListener collisionListener) {
		super(handler, position, width, height);
		this.collisionListener = collisionListener;
		listening = true;
	}
	
	public void checkForCollisionEvent(ScriptedStaticCollider self) {
		if (listening) {
			Entity e = checkEntityOverlap(handler.getWorld().getEntityManager(), 0, 0);
			if (e != null) collisionListener.onCollision(self , e);
		}
	}

	public boolean isListening() {
		return listening;
	}

	public void setListening(boolean listening) {
		this.listening = listening;
	}
	
	public void clearTimer() {
		prevTime = System.currentTimeMillis();
	}
	
	public long getTimer() {
		return System.currentTimeMillis() - prevTime;
	}
	
}
