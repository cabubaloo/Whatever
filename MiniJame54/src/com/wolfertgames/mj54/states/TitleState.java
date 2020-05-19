package com.wolfertgames.mj54.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.wolfertgames.mj54.Handler;
import com.wolfertgames.mj54.display.gfx.Assets;
import com.wolfertgames.mj54.entities.EntityManager;
import com.wolfertgames.mj54.input.MouseManager;
import com.wolfertgames.mj54.input.ReadInString;
import com.wolfertgames.mj54.sound.AudioPlayer;
import com.wolfertgames.mj54.ui.ClickListener;
import com.wolfertgames.mj54.ui.TextBox;
import com.wolfertgames.mj54.ui.UIImageButton;
import com.wolfertgames.mj54.ui.UIManager;
import com.wolfertgames.mj54.ui.UIObject;
import com.wolfertgames.mj54.world.World;

public class TitleState extends State {
	
	/*
	 * 	Class:	TitleState 
	 * 	Role:	Runs the Menu Screen on boot up
	 * 
	 */
	private UIManager uiManager;
	
	public TitleState(Handler handler) {
		super(handler);
		
		init();
	}
	
	public void init() {
		uiManager = new UIManager();
		handler.setWorld(new World(new EntityManager(), uiManager));
		handler.getGame().getMouseManager().addResponder(uiManager);
		
		UIObject obj = new UIImageButton(handler, new Rectangle(250, 370, 300, 150), Assets.buttons, new ClickListener() {
			@Override
			public void onClick(UIObject o) {
				System.out.println("Started Game...");
				if (handler.getGame().getCurrentState() instanceof TitleState)
					handler.getGame().setCurrentState(new GameState(handler));
			}});
		uiManager.addObject(obj);
	}

	@Override
	public void tick(float deltaTime) {
		uiManager.tick(deltaTime);
	}

	@Override
	public void render(float deltaTime, Graphics g) {
		drawBackground(g);
		drawBanner(g);
		uiManager.render(deltaTime, g);
	}
	
	private void drawBanner(Graphics g) {
		g.setColor(Color.magenta);
		g.setFont(new Font("Serif", Font.BOLD,80));
		g.drawString("SHIFTED", 180, 200);
	}

	private void drawBackground(Graphics g) {
		g.drawImage(Assets.matrix,0,0,handler.getGame().getWidth(), handler.getGame().getHeight(),null);
	}

}
