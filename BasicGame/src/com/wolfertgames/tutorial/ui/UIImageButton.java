package com.wolfertgames.tutorial.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wolfertgames.tutorial.Handler;
import com.wolfertgames.tutorial.input.ClickListener;

public class UIImageButton extends UIObject{
	
	private BufferedImage[] images;
	private ClickListener clicker;
	
	public UIImageButton(Handler handler, Rectangle bounds, BufferedImage[] images, ClickListener clicker) {
		super(handler, bounds);
		this.images = images;
		this.clicker = clicker;
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		if (hovering) {
			g.drawImage(images[1], bounds.x, bounds.y, bounds.width, bounds.height, null);
		} else {
			g.drawImage(images[0], bounds.x, bounds.y, bounds.width, bounds.height, null);
		}
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}

}