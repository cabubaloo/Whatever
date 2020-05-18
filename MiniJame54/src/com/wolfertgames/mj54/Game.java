package com.wolfertgames.mj54;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import com.wolfertgames.mj54.display.Display;
import com.wolfertgames.mj54.display.gfx.Assets;
import com.wolfertgames.mj54.display.gfx.Camera;
import com.wolfertgames.mj54.input.KeyManager;
import com.wolfertgames.mj54.input.MouseManager;
import com.wolfertgames.mj54.states.State;
import com.wolfertgames.mj54.states.TitleState;
import com.wolfertgames.mj54.world.World;

public class Game implements Runnable {
	
	/*
	 * 	Class: 	Game
	 *  Role:	Starts Game thread and continuously calls tick() and render()
	 *  		Also holds the current game state and input managers
	 */
	
	//MEMBER VARIABLES	
	
	//Display
	private Display display;
	private String title;
	private int width, height;
	
	private BufferStrategy buffer;
	private Graphics g;
	
	private Camera camera;
	
	//Game Management
	private Thread thread;
	public boolean running;
	
	private Handler handler;
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//States
	private State currentState;
	
	//Info
	private int currentFps;
	
	/////// Getters / Setters ///////
	
	public Display getDisplay() {
		return display;
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public Camera getCamera() {
		return camera;
	}

	public String getTitle() {
		return title;
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
		
		running = false;
	}
	
	/////// Member Functions ///////
	
	//Runs Once to initialize game variables
	private void init() {
		
		Assets.init();
		
		handler = new Handler(this, new World(null, null));
		
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		camera = new Camera(handler, 0, 0);
		display = new Display(title, width, height);
		
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		currentState = new TitleState(handler);
	}
	
	//Process and Update Game Variables
	private void tick(float deltaTime) {
		keyManager.tick();
		currentState.tick(deltaTime);
	}
	
	//Update Graphical Elements
	private void render(float deltaTime) {
		
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
		
		currentState.render(deltaTime, g);
		
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
		float timePerTick = 1000000000/ FPS;
		float delta = 0;
		long currentTime;
		long prevTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		
		while(running) {
			currentTime = System.nanoTime();
			delta += (currentTime - prevTime);
			timer += currentTime - prevTime;
			prevTime = currentTime;
			
			if (delta >= timePerTick) {
				tick(delta / 1000000000);
				render(delta / 1000000000);
				ticks++;
				delta = 0;
			}
			
			if (timer > 1000000000) {
				currentFps = ticks;
				//System.out.println("FPS: " + currentFps);
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
