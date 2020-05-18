package com.wolfertgames.mj54.display.gfx;

import java.awt.image.BufferedImage;

public class Animation {
	
	private int speed, index;
	private BufferedImage[] frames;
	
	private long prevTime, timer;
	
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		prevTime = System.currentTimeMillis();
	}
	
	public void tick() {
		timer += System.currentTimeMillis() - prevTime;
		prevTime = System.currentTimeMillis();
		
		if (timer > speed) {
			index = (index + 1) % frames.length;
			timer = 0;
		}
		
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
}
