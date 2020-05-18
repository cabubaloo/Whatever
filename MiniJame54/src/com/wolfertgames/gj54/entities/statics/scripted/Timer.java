package com.wolfertgames.gj54.entities.statics.scripted;

public interface Timer {
	long prevTime = System.currentTimeMillis();
	long getTimer();
	void clearTimer();
}
