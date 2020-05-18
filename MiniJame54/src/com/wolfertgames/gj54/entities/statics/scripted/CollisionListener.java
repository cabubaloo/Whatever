package com.wolfertgames.gj54.entities.statics.scripted;

import com.wolfertgames.mj54.entities.Entity;

public interface CollisionListener {
	public void onCollision(ScriptedStaticCollider self, Entity e);
}
