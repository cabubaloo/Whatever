package com.wolfert.javaplayground;

import java.awt.*;
import java.awt.event.*;

public class PrimWindow extends Frame {
	
	int TEXTBOX_H = 20;
	int BAR_H = 28;
	
	int windowWidth;
	int windowHeight;
	
	Color background = Color.WHITE, foreground = Color.WHITE;
	
	Button b;
	Button c;
	
	TextField tf;
	
	public void updateColor(int r, int g, int b) {
		background = new Color(r, g, b);
		foreground = new Color(255 - r, 255 - g, 255 - b);
		setBackground(background);
		this.b.setBackground(foreground);
		this.c.setBackground(foreground);
	}
	
	private void updateComponents() {
		tf.setBounds(windowWidth/10, windowHeight*95/100, windowWidth*8/10, windowHeight*5/100);
		tf.setText("Window Width: " + windowWidth + "    Window Height: " + windowHeight);
		setSize(windowWidth, windowHeight);
		tf.setFont(new Font("Arial", Font.PLAIN, windowWidth*35/1000));
		b.setBounds(windowWidth/10, BAR_H + (windowHeight - BAR_H)/10, windowWidth*8/10, (windowHeight - BAR_H)*35/100);
		c.setBounds(windowWidth/10, BAR_H + (windowHeight - BAR_H)*55/100, windowWidth*8/10, (windowHeight - BAR_H)*35/100);
		b.setFont(new Font("Arial", Font.PLAIN, windowWidth/10));
		c.setFont(new Font("Arial", Font.PLAIN, windowWidth/10));
	}
	
	PrimWindow (int startingWidth, int startingHeight) {
		windowWidth = startingWidth;
		windowHeight = startingHeight;
		
		b = new Button("Thiccen");
		b.addActionListener(		
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					windowHeight *= 1.1;
					windowWidth *= 1.1;
					updateComponents();
				}
			}	
		);
		add(b);
		
		c = new Button("Scfinkter Down");
		c.addActionListener(		
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					windowHeight /= 1.1;
					windowWidth /= 1.1;
					updateComponents();
				}
			}	
		);
		add(c);
		
		tf = new TextField();
		add(tf);
		
		updateComponents();
		
		setResizable(false);
		setLayout(null);
		setVisible(true);
		
	}

}
