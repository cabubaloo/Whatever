package com.wolfertgames.mj54.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wolfertgames.mj54.Handler;

public class UIImageButton extends UIObject {
	
	protected BufferedImage[] texture;
	private ClickListener clicker;
	
	public UIImageButton(Handler handler, Rectangle clickBounds, BufferedImage[] texture, ClickListener clicker) {
		super(handler, clickBounds);
		this.texture = texture;
		this.clicker = clicker;
	}
	
	@Override
	public void onClick() {
		clicker.onClick();
	}

	@Override
	public void tick(float deltaTime) {}

	@Override
	public void render(float deltaTime, Graphics g) {
		BufferedImage currTexture = (hovering) ? texture[1] : texture[0];
		g.drawImage(currTexture, clickBounds.x, clickBounds.y, clickBounds.width, clickBounds.height, null);
	}

}
