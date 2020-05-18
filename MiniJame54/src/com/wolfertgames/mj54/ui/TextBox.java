package com.wolfertgames.mj54.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.wolfertgames.mj54.Handler;
import com.wolfertgames.mj54.display.gfx.AssetBuilder;

public class TextBox extends UIObject {
	
	protected Rectangle textArea;
	protected String displayText;
	
	protected Color textColor;
	protected Color backgroundColor;
	protected Font font;

	public TextBox(Handler handler, Rectangle dimensions,
				String displayText, Color background, Color text, Font font) {
		super(handler, dimensions);
		this.textArea = dimensions;
		this.displayText = displayText;
		this.backgroundColor = background;
		this.textColor = text;
		this.font = font;
	}

	@Override
	public void tick(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float deltaTime, Graphics g) {
		if (visible)
			g.drawImage(AssetBuilder.buildTextBox(this), textArea.x, textArea.y, textArea.width, textArea.height, null);
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
	}

	public Rectangle getTextArea() {
		return textArea;
	}

	public void setTextArea(Rectangle textArea) {
		this.textArea = textArea;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
	
	
}
