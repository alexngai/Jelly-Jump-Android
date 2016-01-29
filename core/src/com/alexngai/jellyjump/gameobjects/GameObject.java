package com.alexngai.jellyjump.gameobjects;

import box2dLight.Light;

import com.alexngai.jellyjump.GameWorld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class GameObject {

	private boolean inUse = false;
	
	private static float height;
	private static float width = 1000;
	
	private Vector2 imSize;
	private Vector2 position;
	private Vector2 velocity = new Vector2(0,0);;
	private Vector2 acceleration = new Vector2(0,0);;
	private float angle = 0;
	
	private TextureRegion texture;
	
	public GameObject(){
		float ppu = Gdx.graphics.getWidth() / width;
		height = Gdx.graphics.getHeight() / ppu;
	}
	
	public GameObject(Vector2 position, Vector2 size){

		this.position = position;
		
		imSize = size;

		float ppu = Gdx.graphics.getWidth() / width;
		height = Gdx.graphics.getHeight() / ppu;
	}
	
	public void update(float delta){
		position.y = position.y - GameWorld.height * 60 * delta;
		//Vector2 vel = velocity.scl((float) (delta/0.01667));
		position.add(new Vector2(velocity.x, velocity.y).scl(60*delta));
		
	}
	
	public void render(SpriteBatch batch){
    	batch.draw(texture, position.x - imSize.x/2, position.y - imSize.y/2, 
    			imSize.x/2, imSize.y/2, imSize.x, imSize.y, 1, 1, angle);
	}

	public void setPosition(Vector2 position){
		this.position = position;
	}

	public void setVelocity(Vector2 velocity){
		this.velocity = velocity;
	}
	
	public void setSize(Vector2 size){
		imSize = size;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Vector2 getVelocity(){
		return velocity;
	}
	
	public Vector2 getAcceleration(){
		return acceleration;
	}
	
	public Vector2 getSize(){
		return imSize;
	}
	
	public boolean isInUse(){
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public TextureRegion getTexture() {
		return texture;
	}

	public void setTexture(TextureRegion texture) {
		this.texture = texture;
	}
	
}
