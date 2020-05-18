package com.wolfertgames.mj54.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyManager implements KeyListener {
	
	/*
	 *  Class:	KeyManager
	 *  Role:	Maintains an array in memory reflecting the current state of all buttons
	 */
	
	//MEMBER VARIABLES
	private boolean[] keys;
	public boolean up, down, left, right;
	private ArrayList<KeyResponder> listeners;
	
	//GETTERS AND SETTERS
	
	public boolean[] getKeys() {
		return keys;
	}

	public KeyManager() {
		keys = new boolean[256];
		listeners = new ArrayList<KeyResponder>();
	}
	
	public void tick() {
		up = keys [KeyEvent.VK_UP];
		down = keys [KeyEvent.VK_DOWN];
		left = keys [KeyEvent.VK_LEFT];
		right = keys [KeyEvent.VK_RIGHT];
	}
	
	public void addResponder(KeyResponder kr) {
		listeners.add(kr);
	}
	
	public void removeResponder(KeyResponder kr) {
		listeners.remove(kr);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		//System.out.println("Key Pressed: " + e.getKeyChar());
		for (KeyResponder l : listeners) l.onKeyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		for (KeyResponder l : listeners) l.onKeyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println("Key Typed: " + e.getKeyCode());
		//System.out.println("Key Typed: " + e.getKeyCode() + " | "+ e.getKeyChar() + " | " + (int)e.getKeyChar());
		for (KeyResponder l : listeners) l.onKeyTyped(e);
	}
}
