package com.wolfertgames.tutorial.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.wolfertgames.tutorial.ui.UIManager;

public class MouseManager implements MouseListener, MouseMotionListener{
	
	/*
	 * This class listens for mouse events and updates itself.
	 * If a UIManager is attached, it relays those events to it.
	 */
	
	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	private UIManager uiManager;
	
	public MouseManager() {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		if (uiManager != null) uiManager.onMouseMoved(e);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = false;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			rightPressed = false;
		}
		if (uiManager != null) uiManager.onMouseReleased(e);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = true;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			rightPressed = true;
		}
		if (uiManager != null) uiManager.onMousePressed(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (uiManager != null) uiManager.onMouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (uiManager != null) uiManager.onMouseExited(e);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (uiManager != null) uiManager.onMouseClicked(e);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (uiManager != null) uiManager.onMouseDragged(e);
	}
	
	public void setUiManager(UIManager uiManager) {
		this.uiManager = uiManager;
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
