package com.wolfertgames.mj54.input;

import java.awt.event.KeyEvent;

public interface KeyResponder {
	public void onKeyTyped(KeyEvent e);
	public void onKeyPressed(KeyEvent e);
	public void onKeyReleased(KeyEvent e);
}
