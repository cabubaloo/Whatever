package com.wolfertgames.mj54.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.wolfertgames.gj54.entities.statics.scripted.Timer;
import com.wolfertgames.mj54.Handler;
import com.wolfertgames.mj54.display.gfx.AssetBuilder;

public class TimedPopUp extends TextBox implements Timer {
	
	protected long prevTime;
	protected long interval = 1000;
	protected Color originalText;
	
	public TimedPopUp(Handler handler, Rectangle dimensions, String displayText, Color originalText, Font font) {
		super(handler, dimensions, displayText, new Color(0,0,0,0), originalText, font);
		this.originalText = originalText;
	}
	
	public void popUp(long interval, String text) {
		System.out.println("Popup!");
		this.interval = interval;
		this.displayText = text;
		textColor = originalText;
		clearTimer();
	}
	
	@Override
	public void tick(float deltaTime) {
		if (getTimer() > interval && textColor.getAlpha() > 0 ) {
			System.out.println("Fading");
			System.out.println(textColor + " | " + textColor.getAlpha());
			textColor = fade(textColor, 0.95f);
		}
	}

	@Override
	public void render(float deltaTime, Graphics g) {
		if (visible)
			g.drawImage(AssetBuilder.buildPopup(this), textArea.x, textArea.y, textArea.width, textArea.height, null);
	}

	@Override
	public long getTimer() {
		return System.currentTimeMillis() - prevTime;
	}

	@Override
	public void clearTimer() {
		prevTime = System.currentTimeMillis();
	}
	
	private Color fade(Color c, float scale) {
		int a = c.getAlpha();
		if (a < 1) {
			return new Color(c.getRed(), c.getGreen(), c.getBlue(), 0);
		} else {
			return new Color(c.getRed(), c.getGreen(), c.getBlue(), (int) (c.getAlpha() * scale));
		}
	}
	
	public void clear() {
		textColor = new Color(textColor.getRed(), textColor.getGreen(), textColor.getBlue(), 0);
	}

}
