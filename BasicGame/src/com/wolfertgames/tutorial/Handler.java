package com.wolfertgames.tutorial;

import com.wolfertgames.tutorial.gfx.GameCamera;
import com.wolfertgames.tutorial.input.KeyManager;
import com.wolfertgames.tutorial.input.MouseManager;
import com.wolfertgames.tutorial.states.State;
import com.wolfertgames.tutorial.world.World;

public class Handler {
	
	private Game game;
	private World world;
	
	public Handler(Game game) {
		this.game = game;
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	
	public State getCurrentState() {
		return game.getCurrentState();
	}
	
	public void setCurrentState(State s) {
		game.setCurrentState(s);
	}
	
	public GameCamera getCamera() {
		return game.getCamera();
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
}
