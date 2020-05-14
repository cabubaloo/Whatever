package com.wolfertgames.tutorial;

import java.awt.*;
import java.awt.image.*;
import com.wolfertgames.tutorial.display.Display;
import com.wolfertgames.tutorial.gfx.GameCamera;
import com.wolfertgames.tutorial.input.KeyManager;
import com.wolfertgames.tutorial.states.GameState;
import com.wolfertgames.tutorial.states.State;

//Video Game Infrastructure
public class Game implements Runnable{
	
	///////// Member Variables ///////
	
	private Display display;
	private String title;
	private int width, height;
	
	private Thread thread;
	public boolean running;
	
	private BufferStrategy buffer;
	private Graphics g;
	
	private KeyManager keyManager;
	
	private State currentState;
	
	private GameCamera camera;
	
	private Handler handler;
	
	private int currentFps;
	
	/////// Getters / Setters ///////
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public GameCamera getCamera() {
		return camera;
	}
	
	public int getCurrentFps() {
		return currentFps;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	/////// Constructors ///////
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		keyManager = new KeyManager();
		camera = new GameCamera(this, 0, 0);
		handler = new Handler(this);
		
		currentState = new GameState(handler);
		running = false;
	}
	
	/////// Member Functions ///////
	
	//Runs Once to initialize game variables
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
	}
	
	//Process and Update Game Variables
	private void tick() {
		keyManager.tick();
		currentState = currentState.tick(); 
	}
	
	//Update Graphical Elements
	private void render() {
		
		//Initialize GraphicsBuffer
		buffer = display.getCanvas().getBufferStrategy();
		if (buffer == null) {
			//If buffer had not been created, make one now and skip to next render
			display.getCanvas().createBufferStrategy(2);
			return;
		}
		
		//Initialize Graphics Drawing
		g = buffer.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		currentState.render(g);
		
		//Tell Java VM we are done drawing, direct the buffer to the screen
		buffer.show();
		g.dispose();
		
		Toolkit.getDefaultToolkit().sync();	// Stop bug where window slows down if there is no input
	}
	
	//Called by Java VM when executing game
	@Override
	public void run() {
		
		init();
		
		
		/*Game Timing Explanation:
		 * This line: delta += (currentTime - prevTime) / timePerTick
		 * adds the decimal percent of a frame period that has passed to delta.
		 * So if only 5ns has passed but the frame period is 100ns, 5% of a period has
		 * passed. Therefore 0.05 is added to delta. When delta is greater than one, then
		 * a new frame is due and tick() and render() are called. If the computer starts lagging,
		 * delta will start to accumulate. Once it stops lagging, delta may be much higher than one,
		 * and many calls will be made to tick() and render(). That way the game will always be
		 * synchronized with real time even if a lag spikes slows it down for a while.
		 * 
		 */
		
		final int FPS = 60;		//User Selected Frames per second
		double timePerTick = 1000000000/ FPS;
		double delta = 0;
		long currentTime;
		long prevTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		
		while(running) {
			currentTime = System.nanoTime();
			delta += (currentTime - prevTime) / timePerTick;
			timer += currentTime - prevTime;
			prevTime = currentTime;
			
			if (delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if (timer > 1000000000) {
				currentFps = ticks;
				ticks = 0;
				timer = 0;
			}
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
