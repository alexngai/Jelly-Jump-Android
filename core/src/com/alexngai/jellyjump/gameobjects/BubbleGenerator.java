package com.alexngai.jellyjump.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class BubbleGenerator {
	
	BubblePool bubblepool;
	private final double spawnChance = 0.01;
	private final float bubbleSpeed = 2;
	
	public BubbleGenerator(BubblePool bubblepool){
		this.bubblepool=bubblepool;
	}
	
	public void update(){

		Vector2 position = new Vector2((float) (Math.random()*1000), 0f);
		Vector2 velocity = new Vector2( 0, (float) (4*Math.random()+bubbleSpeed));
		float sizeVal = (float) (Math.random()*50+20);
		Vector2 size = new Vector2( sizeVal, sizeVal);
		
		if (Math.random() < spawnChance){
			bubblepool.addBubble(position, size, velocity);
			//Log.d("Enemy", "created enemy " + created + ", pos:" + posX);
		}		
	}
	
}
