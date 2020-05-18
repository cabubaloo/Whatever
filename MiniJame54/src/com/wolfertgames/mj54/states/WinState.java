package com.wolfertgames.mj54.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.wolfertgames.mj54.Handler;
import com.wolfertgames.mj54.display.gfx.Assets;
import com.wolfertgames.mj54.entities.EntityManager;
import com.wolfertgames.mj54.ui.ClickListener;
import com.wolfertgames.mj54.ui.UIImageButton;
import com.wolfertgames.mj54.ui.UIManager;
import com.wolfertgames.mj54.ui.UIObject;
import com.wolfertgames.mj54.world.World;

public class WinState extends State {
	
	/*
	 * 	Class:	WinState 
	 * 	Role:	Runs the Menu Screen on boot up
	 * 
	 */
	
	private UIManager uiManager;
	
	public WinState(Handler handler) {
		super(handler);
		init();
	}
	
	public void init() {
		uiManager = new UIManager();
		handler.getGame().getMouseManager().addResponder(uiManager);
		
	}

	@Override
	public void tick(float deltaTime) {
		uiManager.tick(deltaTime);
	}

	@Override
	public void render(float deltaTime, Graphics g) {
		drawBackground(g);
		drawBanner(g);
	}

	private void drawBanner(Graphics g) {
		g.setColor(Color.magenta);
		g.setFont(new Font("Serif", Font.BOLD,80));
		g.drawString("You Win!", 200, 200);
	}

	private void drawBackground(Graphics g) {
		g.drawImage(Assets.matrix, 0, 0, handler.getGame().getWidth(), handler.getGame().getHeight(), null);
	}

	
}
