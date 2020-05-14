package com.wolfertgames.tutorial;

import com.wolfertgames.tutorial.gfx.Assets;
import com.wolfertgames.tutorial.tiles.Tile;

public class Launcher {
	
	public static void main(String args[]) {
		Game game = new Game("The Legend of Reyermo", 1200, 800);
		game.start();
	}
}
