package com.wolfertgames.tutorial.states;

import java.awt.Graphics;

import com.wolfertgames.tutorial.Game;
import com.wolfertgames.tutorial.Handler;

public abstract class State {
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
}
