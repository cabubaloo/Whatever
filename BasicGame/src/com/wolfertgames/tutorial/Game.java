package com.wolfertgames.tutorial;

import java.awt.*;
import java.awt.image.*;

import com.wolfertgames.tutorial.display.Display;

public class Game implements Runnable{
	
	private Display display;
	public String title;
	public int width, height;
	
	private Thread thread;
	public boolean running;
	
	private BufferStrategy buffer;
	private Graphics graphicsObject;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		running = false;
	}
	
	//Runs Once to initialize game variables
	private void init() {
		display = new Display(title, width, height);
	}
	
	//Process and Update Game Variables
	private void tick() {
		
	}
	
	//Update Graphical Elements
	private void render() {
		buffer = display.getCanvas().getBufferStrategy();
		if (buffer == null) {
			//If buffer had not been created, make one now and skip to next render
			display.getCanvas().createBufferStrategy(2);
			return;
		}
		graphicsObject = buffer.getDrawGraphics();
		
		//All drawing commands go here
		{
			graphicsObject.setColor(Color.ORANGE);
			graphicsObject.fillRect(0, 0, width, height);
		}
		
		//Tell Java VM we are done drawing, direct the buffer to the screen
		buffer.show();
		graphicsObject.dispose();
	}
	
	@Override //Called by Java VM when executing game
	public void run() {
		
		init();
		while(running) {
			tick();
			render();
		}
		stop();
	}
	
	//Starts the Game's thread
	public synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	//Kills the Games's thread
	public synchronized void stop() {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
