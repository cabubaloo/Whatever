package com.wolfertgames.gj54.entities.dynamics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.wolfertgames.gj54.math.Vector2;
import com.wolfertgames.mj54.Handler;
import com.wolfertgames.mj54.display.gfx.Animation;
import com.wolfertgames.mj54.display.gfx.Assets;
import com.wolfertgames.mj54.entities.Entity;
import com.wolfertgames.mj54.sound.AudioPlayer;

public class PlatformingPlayer extends Newtonian {
	
	protected final float speed = 10f;
	
	protected Animation idle = Assets.breathing;
	protected Animation walking= Assets.running;
	
	private boolean prevUp = false;
	
	private Vector2 appliedForce;
	private int jumps = 0;
	private boolean jumpEnabled = false;
	private boolean doubleJumpEnabled = false;
	private boolean wallJumpEnabled = false;
	
	public PlatformingPlayer(Handler handler, Vector2 position) {
		super(handler, position, 75, 115, 1);
		bounds = new Rectangle(0, 0, width, height);
		appliedForce = new Vector2(0,0);
	}
	
	@Override
	public void tick(float deltaTime) {
		idle.tick();
		//walking.setSpeed((int) (1000 - dPos.x * 30));
		walking.tick();
		if (onGround()) jumps = 0; 
		move(deltaTime, handler.getWorld().getEntityManager());
		prevUp = handler.getKeyManager().up;
	}
	

	@Override
	public void render(float deltaTime, Graphics g) {
		//System.out.println("Player Coords: " + x + ", " + y);
		//System.out.println("Camera Coords: " + game.getCamera().getxOffset() + ", " + game.getCamera().getyOffset());
		if (isVisible()) {
			if (inputEnabled) {
				if (dPos.x < 5) {
					g.drawImage(getImage(), (int)(position.x - handler.getCamera().getXOffset()) + 65, (int)(position.y - handler.getCamera().getYOffset()),
							-120, 160, null);
				} else {
					g.drawImage(getImage(), (int)(position.x - handler.getCamera().getXOffset()), (int)(position.y - handler.getCamera().getYOffset()),
							120, 160, null);
				}
			} else {
				g.drawImage(Assets.playerIdle, (int)(position.x - handler.getCamera().getXOffset()), 80 + (int)(position.y - handler.getCamera().getYOffset()),
						120, 80, null);
			}
		    //drawBoudingBox(g);
		}
	}
	
	private Image getImage() {
		if (!onGround()) {
			return Assets.playerJumping;
		} else if (Math.abs(dPos.x) > 5) {
			return walking.getCurrentFrame();
		} else {
			return idle.getCurrentFrame();
		}
	}
	

	@Override 
	public void sumForces() {
		
		if (inputEnabled) {
		
			float floorResistance = 10f;
			float airResistance = 0.5f;
			
			force.zero();
			force.x += handler.getWorld().getGravity().x;
			force.y += handler.getWorld().getGravity().y;
			force.x -= airResistance * velocity.x;
			force.y -= airResistance * velocity.y;
			
			if (onGround()) {
				force.x -= floorResistance * velocity.x;
			}
			
			if (handler.getKeyManager().up) {
				if (jumpEnabled && onGround()) {
					jump();
				} else if (doubleJumpEnabled && jumps == 1 && prevUp != true) {
					jump();
				}
				
				if (wallJumpEnabled) {
					if (checkEntityCollisions(handler.getWorld().getEntityManager(), 1, 0) != null
							&& handler.getKeyManager().left)
						wallJump(-1);
					else if (checkEntityCollisions(handler.getWorld().getEntityManager(), -1, 0) != null
							&& handler.getKeyManager().right)
						wallJump(1);
				}
			}
			
			if (handler.getKeyManager().down) {
				force.y += speed;
			}
			if (handler.getKeyManager().left) {
				force.x -= (onGround()) ? speed * 10: speed;
			}
			if (handler.getKeyManager().right) {
				force.x += (onGround()) ? speed * 10: speed;
			}
		}
		
	}

	private void wallJump(int direction) {
		new AudioPlayer(Assets.jump);
		force.y -= speed * 30;
		force.x += speed * 30 * direction;
		jumps++;
	}

	private void jump() {
		new AudioPlayer(Assets.jump);
		force.y -= speed * 40;
		jumps++;
	}
	
	public void applyForceNextTick(Vector2 force) {
		appliedForce.add(force);
	}

	public Vector2 getAppliedForce() {
		return appliedForce;
	}

	public void setAppliedForce(Vector2 appliedForce) {
		this.appliedForce = appliedForce;
	}

	public int getJumps() {
		return jumps;
	}

	public void setJumps(int jumps) {
		this.jumps = jumps;
	}

	public boolean isJumpEnabled() {
		return jumpEnabled;
	}

	public void setJumpEnabled(boolean jumpEnabled) {
		this.jumpEnabled = jumpEnabled;
	}

	public boolean isDoubleJumpEnabled() {
		return doubleJumpEnabled;
	}

	public void setDoubleJumpEnabled(boolean doubleJumpEnabled) {
		this.doubleJumpEnabled = doubleJumpEnabled;
	}

	public boolean isWallJumpEnabled() {
		return wallJumpEnabled;
	}

	public void setWallJumpEnabled(boolean wallJumpEnabled) {
		this.wallJumpEnabled = wallJumpEnabled;
	}

	public float getSpeed() {
		return speed;
	}
}
