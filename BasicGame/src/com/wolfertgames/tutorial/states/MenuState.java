package com.wolfertgames.tutorial.states;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wolfertgames.tutorial.Handler;
import com.wolfertgames.tutorial.gfx.Assets;
import com.wolfertgames.tutorial.input.ClickListener;
import com.wolfertgames.tutorial.ui.UIImageButton;
import com.wolfertgames.tutorial.ui.UIManager;

public class MenuState extends State {
	
	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUiManager(uiManager);
		
		BufferedImage[] buttonImages = { Assets.button, Assets.buttonPressed };
		uiManager.addObject(new UIImageButton(handler,
				new Rectangle(handler.getWidth() / 2 - 150, handler.getHeight() / 2 - 75, 300, 150),
				buttonImages, new ClickListener() {
				@Override
				public void onClick() {
					handler.getGame().setCurrentState(new GameState(handler));
				}}));
	}
	
	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}

}
