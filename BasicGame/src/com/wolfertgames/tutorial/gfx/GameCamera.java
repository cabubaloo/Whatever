package com.wolfertgames.tutorial.gfx;

import com.wolfertgames.tutorial.Game;
import com.wolfertgames.tutorial.entities.Entity;
import com.wolfertgames.tutorial.tiles.Tile;
import com.wolfertgames.tutorial.world.World;

public class GameCamera {
	
	private float xOffset, yOffset;
	private Game game;
	
	public GameCamera(Game game, float xOffset, float yOffset) {
		this.game = game;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - (game.getWidth() - e.getWidth()) / 2;
		yOffset = e.getY() - (game.getHeight() - e.getHeight()) / 2;
	}
	
	public void fleeWorldEdges(World w) {
		int posXEdge = w.getWidth() * Tile.TILE_WIDTH - game.getWidth();
		int posYEdge = w.getHeight() * Tile.TILE_HEIGHT - game.getHeight();
		int negXEdge = 0;
		int negYEdge = 0;
		xOffset = (xOffset < negXEdge) ? negXEdge : (xOffset > posXEdge) ? posXEdge : xOffset;
		yOffset = (yOffset < negYEdge) ? negYEdge : (yOffset > posYEdge) ? posYEdge : yOffset;
	}
	
	public void move(float xDelta, float yDelta) {
		xOffset += xDelta;
		yOffset += yDelta;
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
	
	
	
}
