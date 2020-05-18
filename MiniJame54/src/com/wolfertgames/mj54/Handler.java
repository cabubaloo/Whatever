package com.wolfertgames.mj54;

import com.wolfertgames.mj54.display.gfx.Camera;
import com.wolfertgames.mj54.input.KeyManager;
import com.wolfertgames.mj54.world.World;

public class Handler {
	
	/*
	 *	Class:	Handler
	 *	Role:	Shuttle for classes and variables that need to be widely accessible
	 */
	
	//MEMBER VARIABLES
	
	private Game game;
	private World world;
	
	//GETTERS AND SETTERS
	
	public Game getGame() {
		return game;
	}
	
	public World getWorld() {
		return world;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	
	//CONSTRUCTORS
	
	public Handler(Game game, World world) {
		this.game = game;
		this.world = world;
	}
	
	//MEMBER FUNCTIONS
	
	public Camera getCamera() {
		return game.getCamera();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
}
