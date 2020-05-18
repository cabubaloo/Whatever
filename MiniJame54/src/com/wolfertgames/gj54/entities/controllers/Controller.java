package com.wolfertgames.gj54.entities.controllers;

import com.wolfertgames.gj54.entities.statics.scripted.Timer;
import com.wolfertgames.mj54.entities.Entity;

public abstract class Controller implements Timer {
	
	protected Entity subject;
	
	public Controller(Entity e) {
		subject = e;
	}
	
	public abstract void init();
	public abstract void run();
}
