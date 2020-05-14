package com.wolfertgames.tutorial.entities.statics;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.wolfertgames.tutorial.Handler;
import com.wolfertgames.tutorial.entities.creatures.Creature;
import com.wolfertgames.tutorial.gfx.Assets;
import com.wolfertgames.tutorial.tiles.Tile;

public class Tree extends StaticEntity {
	
	public Tree(Handler handler, float x, float y) {
		super(handler, Assets.sprites[13][55], x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
		bounds = new Rectangle(Creature.DEFAULT_CREATURE_WIDTH / 5,
				   Creature.DEFAULT_CREATURE_HEIGHT / 2,
				   Creature.DEFAULT_CREATURE_WIDTH * 3 / 5,
				   Creature.DEFAULT_CREATURE_HEIGHT * 2 / 5);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(texture, (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()), width, height, null);
		//drawBoudingBox(g);
	}

}
