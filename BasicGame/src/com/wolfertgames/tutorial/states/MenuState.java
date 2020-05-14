package com.wolfertgames.tutorial.states;

import java.awt.Graphics;
import com.wolfertgames.tutorial.Handler;

public class MenuState extends State {
	
	public MenuState(Handler handler) {
		super(handler);
	}
	
	@Override
	public State tick() {
		System.out.println("Called MenuState tick()");
		return this;
	}

	@Override
	public void render(Graphics g) {
		System.out.println("Called MenuState render()");
	}

}
