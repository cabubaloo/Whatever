package com.wolfertgames.mj54.ui;

import java.awt.Color;

public class TextLine {
	
	private String text;
	private Color color;
	
	public TextLine(String text, Color color) {
		this.text = text;
		this.color = color;
	}
	
	public TextLine(TextLine line) {
		this.text = line.getText();
		this.color = line.getColor();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
}
