package com.wolfertgames.mj54.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import com.wolfertgames.mj54.ui.UIManager;

public class MouseManager implements MouseListener, MouseMotionListener{
	
	/*
	 * This class listens for mouse events and updates itself.
	 * If a MouseEventResponder is attached, it relays those events to it.
	 */
	
	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	private ArrayList<MouseEventResponder> listeners;
	
	public MouseManager() {
		listeners = new ArrayList<MouseEventResponder>();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		for (MouseEventResponder l : listeners) l.onMouseMoved(e);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = false;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			rightPressed = false;
		}
		for (MouseEventResponder l : listeners) l.onMouseReleased(e);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = true;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			rightPressed = true;
		}
		for (MouseEventResponder l : listeners) l.onMousePressed(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		for (MouseEventResponder l : listeners) l.onMouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		for (MouseEventResponder l : listeners) l.onMouseExited(e);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		for (MouseEventResponder l : listeners) l.onMouseClicked(e);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		for (MouseEventResponder l : listeners) l.onMouseDragged(e);
	}
	
	public void addResponder(MouseEventResponder mer) {
		listeners.add(mer);
	}
	
	public void removeResponder(MouseEventResponder mer) {
		listeners.remove(mer);
	}
	
	public boolean isLeftPressed() {
		return leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

}
