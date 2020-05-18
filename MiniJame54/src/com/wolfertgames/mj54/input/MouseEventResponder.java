package com.wolfertgames.mj54.input;

import java.awt.event.MouseEvent;

public interface MouseEventResponder {
	public void onMouseMoved(MouseEvent e);
	public void onMouseReleased(MouseEvent e);
	public void onMousePressed(MouseEvent e);
	public void onMouseEntered(MouseEvent e);
	public void onMouseExited(MouseEvent e);
	public void onMouseClicked(MouseEvent e);
	public void onMouseDragged(MouseEvent e);
}
