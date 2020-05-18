package com.wolfertgames.mj54;

public class JamLauncher {
	
	/*
	 *	Class:	Launcher
	 *	Role:	Starts the game
	 */
	
	public static void main(String[] args) {
		Game game = new Game("Jam 54", 800, 600);
		game.start();
	}
}
