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
	protected int currentAlpha;
	
	public TimedPopUp(Handler handler, Rectangle dimensions, TextLine displayText, Font font) {
		super(handler, dimensions, displayText, new Color(0,0,0,0), font);
	}
	
	public void popUp(long interval, TextLine text) {
		this.interval = interval;
		this.displayText = text;
		currentAlpha = 255;
		clearTimer();
	}
	
	@Override
	public void tick(float deltaTime) {
		if (getTimer() > interval && currentAlpha > 0 ) {
			fade(displayText, 0.95f);
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
	
	private void fade(TextLine t, float scale) {
		if (currentAlpha < 1) {
			currentAlpha = 0;
		} else {
			currentAlpha *= scale;
		}
		t.setColor(new Color(t.getColor().getRed(), t.getColor().getGreen(), t.getColor().getBlue(), currentAlpha));
	}
	
	public void fadeCompletly() {
		displayText.setColor(new Color(displayText.getColor().getRed(),
				displayText.getColor().getGreen(), displayText.getColor().getBlue(), 0));
	}

}
