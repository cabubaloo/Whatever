package com.wolfertgames.gj54.entities.statics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.wolfertgames.gj54.math.Vector2;
import com.wolfertgames.mj54.Handler;
import com.wolfertgames.mj54.display.gfx.AssetBuilder;
import com.wolfertgames.mj54.display.gfx.Assets;

public class Platform extends StaticEntity {
	
	private Image texture;
	
	public Platform(Handler handler, Vector2 position, int width, int height) {
		super(handler, position, width, height);
		bounds = new Rectangle(0, 0, width, height);
		collides = true;
		
		texture = AssetBuilder.tileImage(Assets.wall, width, height);
	}

	@Override
	public void tick(float deltaTime) {
		
	}

	@Override
	public void render(float deltaTime, Graphics g) {
		drawImage(g, Assets.wall, getAdjX(), getAdjY(), width, height);
		drawImage(g, Assets.walltop, getAdjX(), getAdjY() - height / 2, (width / 2) * 3, height / 2);
		drawImage(g, Assets.wallside, getAdjX() + width, getAdjY() - height / 2, width / 2, (height / 2) * 3);
	}

	private void drawImage(Graphics g, Image texture, float x, float y, int width, int height) {
		g.drawImage(texture, (int)(x - handler.getCamera().getXOffset()), (int)(y - handler.getCamera().getYOffset()), width, height, null);
	}
	
	private float getAdjX() {
		return position.x - width/4;
	}
	
	private float getAdjY() {
		return position.y + height/4;
	}

}
