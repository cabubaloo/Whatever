package com.wolfertgames.mj54.states;

import java.awt.Graphics;

import com.wolfertgames.mj54.Handler;

public abstract class State {
	
	/*
	 * 	Class:	State
	 * 	Role:	Sub classes of State contain all the code required to run distinct portions of the game.
	 * 			They can change the state by accessing handler.setCurrentState(State state)
	 */
	
	protected static Handler handler;
	
	public State(Handler handler) {
		State.handler = handler;
	}
	
	public abstract void tick(float deltaTime);
	public abstract void render(float deltaTime, Graphics g);
}
