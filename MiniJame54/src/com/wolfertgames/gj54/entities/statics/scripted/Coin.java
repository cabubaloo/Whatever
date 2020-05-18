package com.wolfertgames.gj54.entities.statics.scripted;

import java.awt.image.BufferedImage;

import com.wolfertgames.gj54.math.Vector2;
import com.wolfertgames.mj54.Handler;
import com.wolfertgames.mj54.display.gfx.Assets;
import com.wolfertgames.mj54.entities.Entity;

public class Coin extends ImageCollectable {
	
	//public static enum CoinMode { RESPAWN, CLEAR };
	
	public Coin(Handler handler, Vector2 position, int width, int height, FunctionPass inc) {
		super(handler, position, width, height, Assets.coin, new CollisionListener() {
			@Override
			public void onCollision(ScriptedStaticCollider self, Entity collider) {
				if (collider == handler.getWorld().getEntityManager().getPlayer()) {
					inc.run();
					self.remove();
				}
			}});
	}
	
	

}
