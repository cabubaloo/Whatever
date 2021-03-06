package com.wolfertgames.mj54.display.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wolfertgames.mj54.ui.Console;
import com.wolfertgames.mj54.ui.TextBox;
import com.wolfertgames.mj54.ui.TextLine;
import com.wolfertgames.mj54.ui.TimedPopUp;

public class AssetBuilder {
	
	public static Image tileImage(Image image, int width, int height) {
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = bufferedImage.getGraphics();
		if (width > height) {
			for (int i = 0; i < width / image.getWidth(null); i++) {
				g.drawImage(image, i * height, 0, height, height, null);
			}
		} else {
			for (int i = 0; i < height / image.getHeight(null); i++) {
				g.drawImage(image, 0, i * width, width, width, null);
			}
		}
		g.dispose();
		return bufferedImage;
	}

	
	public static Image buildTextBox(TextBox t) {
		BufferedImage bufferedImage = new BufferedImage(t.getTextArea().width, t.getTextArea().height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bufferedImage.getGraphics();
		g.setColor(t.getBackgroundColor());
		g.fillRect(0, 0, t.getTextArea().width, t.getTextArea().height);
		g.drawImage(Assets.enterArrow, 0, 0, 60, 60, null);
		g.setColor(t.getDisplayText().getColor());
		g.setFont(t.getFont());
		g.drawString(t.getDisplayText().getText(), 60, 40);
		g.dispose();
		return bufferedImage;
	}

	public static Image buildConsole(Console console) {
		BufferedImage bufferedImage = new BufferedImage(console.getTextArea().width, console.getTextArea().height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bufferedImage.getGraphics();
		g.setColor(console.getBackgroundColor());
		g.fillRect(0, 0, console.getTextArea().width, console.getTextArea().height);
		g.setFont(console.getFont());
		for (int i = 0; i < Console.NUMBER_LINES; i++) {
			TextLine s = console.getStrings()[i];
			if (s == null) break;
			g.setColor(s.getColor());
			g.drawString(s.getText(), 10, 30 + (int) (i * console.getFont().getSize() * 1.1));
		}
		g.dispose();
		return bufferedImage;
	}
	
	public static Image buildMultiLiner(TextLine[] strings, Rectangle textArea, Color background, Font font) {
		BufferedImage bufferedImage = new BufferedImage(textArea.width, textArea.height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bufferedImage.getGraphics();
		g.setColor(background);
		g.fillRect(0, 0, textArea.width, textArea.height);
		g.setFont(font);
		for (int i = 0; i < strings.length; i++) {
			TextLine s = strings[i];
			if (s == null) break;
			g.setColor(s.getColor());
			g.drawString(s.getText(), 10, 30 + (int) (i * font.getSize() * 1.1));
		}
		g.dispose();
		return bufferedImage;
	}
	
	public static Image buildPopup(TimedPopUp t) {
		BufferedImage bufferedImage = new BufferedImage(t.getTextArea().width, t.getTextArea().height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bufferedImage.getGraphics();
		g.setColor(t.getBackgroundColor());
		g.fillRect(0, 0, t.getTextArea().width, t.getTextArea().height);
		g.setColor(t.getDisplayText().getColor());
		g.setFont(t.getFont());
		g.drawString(t.getDisplayText().getText(), 10, (int) (t.getFont().getSize() * 1.1));
		g.dispose();
		return bufferedImage;
	}

}
