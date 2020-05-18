package com.wolfertgames.mj54.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ListIterator;

import com.wolfertgames.mj54.Handler;
import com.wolfertgames.mj54.entities.Entity;
import com.wolfertgames.mj54.input.MouseEventResponder;

public class UIManager implements MouseEventResponder {
	
	private ArrayList<UIObject> objects;
	
	public UIManager() {
		objects = new ArrayList<UIObject>();
	}
	
	public void tick(float deltaTime) {
		for (UIObject o : objects) {
			o.tick(deltaTime);
		}
	}
	
	public void render(float deltaTime, Graphics g) {
		for (UIObject o : objects) {
			o.render(deltaTime, g);
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
	
	public void purge() {
		ListIterator<UIObject> itr = objects.listIterator();
	    while (itr.hasNext()) {
	    	itr.next();
	    	itr.remove();
	    }
	}
}
