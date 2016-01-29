package com.alexngai.jellyjump.gameobjects;

import com.alexngai.jellyjump.GameRenderer;
import com.alexngai.jellyjump.helpers.Assets;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;



public class Bubble extends GameObject{
	
	public Bubble(){
		super();
	}
	
	public Bubble(Vector2 position, Vector2 size, Vector2 velocity) {
		super(position, size);
		setTexture(Assets.bubble);
		setVelocity(velocity);
	}
	
	public void update(float deltaTime){
		super.update(deltaTime);
		checkLifetime();
	}
	
	public void render(SpriteBatch batch){
		super.render(batch);
	}
	
	public void reconstruct(Vector2 position, Vector2 size, Vector2 velocity) {
		setPosition(position);
		setSize(size);
		setTexture(Assets.bubble);
		setVelocity(velocity);
	}
	
	public void unuse(){
		setInUse(false);
		//super.setPosX(500);
		//Log.d("Enemy", "died" + id);
	}
	
	private void checkLifetime(){
		if (getPosition().y > GameRenderer.height || getPosition().y < -200) unuse();
	}
}