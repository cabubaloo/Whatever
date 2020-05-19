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
	protected TextLine displayText;
	
	protected Color backgroundColor;
	protected Font font;

	public TextBox(Handler handler, Rectangle dimensions,
				TextLine displayText, Color background, Font font) {
		super(handler, dimensions);
		this.textArea = dimensions;
		this.displayText = displayText;
		backgroundColor = background;
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

	public TextLine getDisplayText() {
		return displayText;
	}

	public void setDisplayText(TextLine displayText) {
		this.displayText = displayText;
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
