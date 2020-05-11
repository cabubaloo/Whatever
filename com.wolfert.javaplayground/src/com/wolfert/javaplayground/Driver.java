package com.wolfert.javaplayground;

public class Driver {

	public static void main(String[] args) {
		
		long prevTime = System.currentTimeMillis();
		int INTERVAL = 10;
		
		int red = 160, green = 0, blue = 80;
		PrimWindow window = new PrimWindow(300, 300);
		
		while (true) {
			if (System.currentTimeMillis() - prevTime >= INTERVAL) {
				red = (red + 1) % 256;
				green = (green + 1) % 256;
				blue = (blue+ 1) % 256;
				window.updateColor(red, green, blue);
				prevTime = System.currentTimeMillis();
			}
		}
	}

}
