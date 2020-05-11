package com.wolfertgames.tutorial.display;

import java.awt.*;

import javax.swing.JFrame;

public class Display {
	
	/////// Variables ///////
	
	private String title;
	private int width;
	private int height;
	
	private JFrame frame;
	private Canvas canvas;
	
	/////// Constructor ///////
	
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
	}
	
	/////// Getter / Setters ///////
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	/////// Member Functions ///////
	
	//Initialize the display window
	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);	//Centers the pop up window
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		
		frame.add(canvas);
		frame.pack();	//Internal System check to ensure everything is visible
	}
}