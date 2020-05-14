package com.wolfertgames.tutorial.states;

import java.awt.Graphics;

import com.wolfertgames.tutorial.Game;
import com.wolfertgames.tutorial.Handler;

public abstract class State {
	
	protected Game game;
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract State tick();
	public abstract void render(Graphics g);
	
}
