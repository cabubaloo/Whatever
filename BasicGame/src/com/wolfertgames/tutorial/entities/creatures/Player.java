package com.wolfertgames.tutorial.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import com.wolfertgames.tutorial.Handler;
import com.wolfertgames.tutorial.gfx.Animation;
import com.wolfertgames.tutorial.gfx.Assets;

public class Player extends Creature {
	
	private Animation animDown, animUp, animLeft, animRight, animStill;
	
	public Player(Handler handler, float x, float y) {
		super(handler, Assets.player, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		bounds = new Rectangle(Creature.DEFAULT_CREATURE_WIDTH / 5,
							   Creature.DEFAULT_CREATURE_HEIGHT * 4 / 5,
							   Creature.DEFAULT_CREATURE_WIDTH * 3 / 5,
							   Creature.DEFAULT_CREATURE_HEIGHT / 5);
		
		animStill = new Animation(500, Assets.anim[0]);
		animDown = new Animation(500, Assets.anim[3]);
		animUp = new Animation(500, Assets.anim[4]);
		animLeft = new Animation(500, Assets.anim[2]);
		animRight = new Animation(500, Assets.anim[1]);
	}

	@Override
	public void tick() {
		getInput();
		animStill.tick();
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		move();
		handler.getCamera().centerOnEntity(this);
	}
	
	private void getInput() {
		deltaX = 0;
		deltaY = 0;
		
		if (handler.getKeyManager().up) {
			deltaY -= speed;
		}
		if (handler.getKeyManager().down) {
			deltaY += speed;
		}
		if (handler.getKeyManager().left) {
			deltaX -= speed;
		}
		if (handler.getKeyManager().right) {
			deltaX += speed;
		}
	}

	@Override
	public void render(Graphics g) {
		//System.out.println("Player Coords: " + x + ", " + y);
		//System.out.println("Camera Coords: " + game.getCamera().getxOffset() + ", " + game.getCamera().getyOffset());
		g.drawImage(getAnimationFrame(), (int)(x - handler.getCamera().getxOffset()), (int)(y - handler.getCamera().getyOffset()), width, height, null);
	    //drawBoudingBox(g);
	}
	
	private BufferedImage getAnimationFrame() {
		if (deltaY > 0) {
			return animDown.getCurrentFrame();
		} else if (deltaY < 0) {
			return animUp.getCurrentFrame();
		} else if (deltaX > 0) {
			return animRight.getCurrentFrame();
		} else if (deltaX < 0) {
			return animLeft.getCurrentFrame();
		} else {
			return animStill.getCurrentFrame();
		}
	}

}
