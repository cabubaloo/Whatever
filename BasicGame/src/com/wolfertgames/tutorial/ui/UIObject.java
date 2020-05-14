package com.wolfertgames.tutorial.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import com.wolfertgames.tutorial.Handler;

public abstract class UIObject {
	
	protected Rectangle bounds;
	protected Handler handler;
	protected boolean hovering;
	
	public UIObject(Handler handler, Rectangle bounds) {
		this.handler = handler;
		this.bounds = bounds;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void onClick();
	
	public void onMousePressed(MouseEvent e) {
		//System.out.println("Mouse Pressed!");
	}
	
	public void onMouseReleased(MouseEvent e) {
		//System.out.println("Mouse Released!");
	}
	
	public void onMouseMoved(MouseEvent e) {
		//System.out.println("Mouse Moved!");
		hovering = bounds.contains(e.getX(), e.getY());
	}
	
	public void onMouseClicked(MouseEvent e) {
		System.out.println("Mouse Clicked!");
		if (hovering) onClick();
	}
	
	public void onMouseEntered(MouseEvent e) {
		//System.out.println("Mouse Entered!");
	}
	
	public void onMouseExited(MouseEvent e) {
		//System.out.println("Mouse Exited!");
	}
	
	public void onMouseDragged(MouseEvent e) {
		//System.out.println("Mouse Dragged!");
	}
}
