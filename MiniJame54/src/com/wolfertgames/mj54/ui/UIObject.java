package com.wolfertgames.mj54.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import com.wolfertgames.mj54.Handler;
import com.wolfertgames.mj54.input.MouseEventResponder;

public abstract class UIObject implements MouseEventResponder {
	
	protected Handler handler;
	
	protected Rectangle clickBounds;
	protected boolean hovering;
	protected boolean visible = true;
	protected boolean active = true;
	
	public UIObject(Handler handler, Rectangle clickBounds) {
		this.handler = handler;
		this.clickBounds = clickBounds;
	}
	
	public Rectangle getClickBounds() {
		return clickBounds;
	}

	public void setClickBounds(Rectangle clickBounds) {
		this.clickBounds = clickBounds;
	}
	
	public void center() {
		setClickBounds(new Rectangle((handler.getGame().getWidth() - clickBounds.width) / 2,
				(handler.getGame().getHeight() - clickBounds.height) / 2, clickBounds.width, clickBounds.height));
	}

	public abstract void tick(float deltaTime);
	public abstract void render(float deltaTime, Graphics g);
	public abstract void onClick();
	
	public void onMouseMoved(MouseEvent e) {
		hovering = clickBounds.contains(e.getX(), e.getY());
	}
	
	public void onMouseClicked(MouseEvent e) {
		if (hovering) onClick();
	}
	
	public void onMousePressed(MouseEvent e) {
		//System.out.println("Mouse Pressed!");
	}
	
	public void onMouseReleased(MouseEvent e) {
		//System.out.println("Mouse Released!");
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

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean onScreen() {
		return new Rectangle((int)clickBounds.x, (int)clickBounds.y, clickBounds.width, clickBounds.height).intersects(handler.getCamera().getScreen());
	}
	
	public void remove() {
		active = false;
	}

	public boolean isActive() {
		return active;
	}
	
}
