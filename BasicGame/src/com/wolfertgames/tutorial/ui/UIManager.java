package com.wolfertgames.tutorial.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.wolfertgames.tutorial.Handler;

public class UIManager {
	
	private ArrayList<UIObject> objects;
	private Handler handler;
	
	public UIManager(Handler handler) {
		this.handler = handler;
		objects = new ArrayList<UIObject>();
	}
	
	public void tick() {
		for (UIObject o : objects) {
			o.tick();
		}
	}
	
	public void render(Graphics g) {
		for (UIObject o : objects) {
			o.render(g);
		}
	}
	
	public void onMouseMoved(MouseEvent e) {
		for (UIObject o : objects) {
			o.onMouseMoved(e);
		}
	}
	
	public void onMouseReleased(MouseEvent e) {
		for (UIObject o : objects) {
			o.onMouseReleased(e);
		}
	}
	
	public void onMouseClicked(MouseEvent e) {
		for (UIObject o : objects) {
			o.onMouseClicked(e);
		}
	}
	
	public void onMousePressed(MouseEvent e) {
		for (UIObject o : objects) {
			o.onMousePressed(e);
		}
	}
	
	public void onMouseEntered(MouseEvent e) {
		for (UIObject o : objects) {
			o.onMouseEntered(e);
		}
	}
	
	public void onMouseExited(MouseEvent e) {
		for (UIObject o : objects) {
			o.onMouseExited(e);
		}
	}
	
	public void onMouseDragged(MouseEvent e) {
		for (UIObject o : objects) {
			o.onMouseDragged(e);
		}
	}
	
	public void addObject(UIObject o) {
		objects.add(o);
	}
	
	public void removeObject(UIObject o) {
		objects.remove(o);
	}
}
