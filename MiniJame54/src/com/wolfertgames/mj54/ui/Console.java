package com.wolfertgames.mj54.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.wolfertgames.mj54.Handler;
import com.wolfertgames.mj54.display.gfx.AssetBuilder;

public class Console extends TextBox {
	
	public static final int NUMBER_LINES = 10;
	
	private String[] strings;
	
	private Image render;
	private int writeIndex = 0;
	
	private boolean fixed = false;
	
	public Console(Handler handler, Rectangle textArea, Color background, Color text, Font font) {
		super(handler, textArea, "EMPTY STRING", background, text, font);
		strings = new String[NUMBER_LINES];
		updateRender();
	}
	
	@Override
	public void render(float deltaTime, Graphics g) {
		if (visible) {
			if (fixed) {
				g.drawImage(render, textArea.x, textArea.y, textArea.width, textArea.height, null);
			} else {
				g.drawImage(render, (int)(textArea.x - handler.getCamera().getXOffset()),
						(int)(textArea.y - handler.getCamera().getYOffset()), textArea.width, textArea.height, null);
			}
		}
	}
	
	public void updateRender() {
		render = AssetBuilder.buildConsole(this);
	}
	
	public void addNewLine(String string) {
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
			strings[i] = new String(strings[i + 1]);
		}
	}

	public String[] getStrings() {
		return strings;
	}

	public void setStrings(String[] strings) {
		this.strings = strings;
	}
	
	public void clear() {
		for (int i = 0; i < NUMBER_LINES; i++) {
			strings[i] = null;
		}
	}

}
