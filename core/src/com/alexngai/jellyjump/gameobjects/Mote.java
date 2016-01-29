package com.alexngai.jellyjump.gameobjects;

import box2dLight.Light;

import com.alexngai.jellyjump.GameRenderer;
import com.alexngai.jellyjump.helpers.Assets;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Mote extends GameObject{

	Light light;
	
	public Mote(){
		super();
	}

	public Mote(Vector2 position, Vector2 size, Vector2 velocity) {
		super(position, size);
		setTexture(Assets.bubble);
		setVelocity(velocity);
	}

	public void update(float deltaTime){
		super.update(deltaTime);
		if (light != null) light.setPosition(getPosition());
		checkLifetime();
	}

	public void render(SpriteBatch batch){
		super.render(batch);
	}

	public void reconstruct(Vector2 position, Vector2 size, Vector2 velocity, Light light) {
		setPosition(position);
		setSize(size);
		setTexture(Assets.bubble);
		setVelocity(velocity);
		setLight(light);
	}

	public void unuse(){
		setInUse(false);
		if (light != null) light.setActive(false);
		//super.setPosX(500);
		//Log.d("Enemy", "died" + id);
	}

	private void checkLifetime(){
		if (getPosition().y < 0) unuse();
	}
	
	public void setLight(Light light){
		this.light = light;
	}
	
	public Light getLight(){
		return light;
	}

}