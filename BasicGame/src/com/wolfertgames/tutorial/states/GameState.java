package com.wolfertgames.tutorial.states;

import java.awt.Graphics;
import com.wolfertgames.tutorial.Handler;
import com.wolfertgames.tutorial.entities.creatures.Player;
import com.wolfertgames.tutorial.gfx.Assets;
import com.wolfertgames.tutorial.tiles.Tile;
import com.wolfertgames.tutorial.world.World;

public class GameState extends State {
	
	private Player player;
	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		player = new Player(handler, Assets.player, world.getSpawnX() * Tile.TILE_WIDTH, world.getSpawnY() * Tile.TILE_HEIGHT);
	}
	
	@Override
	public State tick() {
		//System.out.println("Called GameState tick()");
		world.tick();
		player.tick();
		handler.getCamera().fleeWorldEdges(world);
		return this;
	}

	@Override
	public void render(Graphics g) {
		//System.out.println("Called GameState render()");
		world.render(g);
		player.render(g);
	}

}
