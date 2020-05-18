package com.wolfertgames.mj54.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Display {
	
	/*
	 *	Class:	Display
	 *	Role:	Creates a window for the game to run in
	 *
	 */
	
	private JFrame frame;
	private Canvas canvas;
	
	private JTextField textField;
	
	private String title;
	private int width, height;
	
	public JFrame getFrame() {
		return frame;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}	
	
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
	}
	
	public void createDisplay() {
		
		textField = new JTextField(20);
		
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
		canvas.setFocusable(false);
		
		frame.add(canvas);
		//frame.pack();	//Internal System check to ensure everything is visible
	}

}
