package com.wolfertgames.tutorial.states;

import java.awt.Graphics;
import com.wolfertgames.tutorial.Handler;
import com.wolfertgames.tutorial.entities.creatures.Player;
import com.wolfertgames.tutorial.entities.statics.Tree;
import com.wolfertgames.tutorial.gfx.Assets;
import com.wolfertgames.tutorial.tiles.Tile;
import com.wolfertgames.tutorial.world.World;

public class GameState extends State {
	
	public GameState(Handler handler) {
		super(handler);
		World world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
	}
	
	@Override
	public void tick() {
		//System.out.println("Called GameState tick()");
		handler.getWorld().tick();
		handler.getCamera().fleeWorldEdges(handler.getWorld());
	}

	@Override
	public void render(Graphics g) {
		//System.out.println("Called GameState render()");
		handler.getWorld().render(g);
	}

}
