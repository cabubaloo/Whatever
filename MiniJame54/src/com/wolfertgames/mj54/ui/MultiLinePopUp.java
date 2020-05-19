package com.wolfertgames.mj54.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.wolfertgames.mj54.Handler;
import com.wolfertgames.mj54.display.gfx.AssetBuilder;

public class MultiLinePopUp extends TimedPopUp {
	
	public static final int NUMBER_LINES = 13;
	
	private TextLine[] strings;
	
	private Image render;
	private int writeIndex = 0;
	
	public MultiLinePopUp(Handler handler, Rectangle dimensions, TextLine displayText, Font font) {
		super(handler, dimensions, displayText, font);
		strings = new TextLine[NUMBER_LINES];
		updateRender();
	}
	
	@Override
	public void tick(float deltaTime) {
		if (getTimer() > interval && currentAlpha > 0 ) {
			fadeAll(0.95f);
			updateRender();
		}
	}
	
	@Override
	public void render(float deltaTime, Graphics g) {
		if (visible) {
			g.drawImage(render, textArea.x, textArea.y, textArea.width, textArea.height, null);
		}
	}
	
	public void popUpMany(long interval, TextLine[] strings) {
		this.interval = interval;
		addNewLinesAndClear(strings);
		updateRender();
		clearTimer();
	}
	
	@Override
	public void popUp(long interval, TextLine text) {
		this.interval = interval;
		clear();
		addNewLine(text);
		currentAlpha = 255;
		updateRender();
		clearTimer();
	}
	
	private void fadeAll(float scale) {
		if (currentAlpha < 1) {
			currentAlpha = 0;
		} else {
			currentAlpha *= scale;
		}
		for (TextLine t : strings) {
			if (t == null) break;
			t.setColor(new Color(t.getColor().getRed(), t.getColor().getGreen(), t.getColor().getBlue(), currentAlpha));
		}
	}
	
	public void updateRender() {
		render = AssetBuilder.buildMultiLiner(strings, textArea, new Color(0,0,0,0), font);
	}
	
	public void addNewLinesAndClear(TextLine[] newStrings) {
		clear();
		for (TextLine t : newStrings) {
			addNewLine(t);
		}
	}
	
	public void addNewLine(TextLine string) {
		if (writeIndex == NUMBER_LINES) {
			shiftStrings();
			strings[writeIndex - 1] = string;
		} else {
			strings[writeIndex] = string;
			writeIndex++;
		}
	}
	
	private void shiftStrings() {
		for (int i = 0; i < NUMBER_LINES - 1; i++) {
			strings[i] = new TextLine(strings[i + 1]);
		}
	}

	public TextLine[] getStrings() {
		return strings;
	}

	public void setStrings(TextLine[] strings) {
		this.strings = strings;
	}
	
	public void clear() {
		writeIndex = 0;
		for (int i = 0; i < NUMBER_LINES; i++) {
			strings[i] = null;
		}
	}

}
