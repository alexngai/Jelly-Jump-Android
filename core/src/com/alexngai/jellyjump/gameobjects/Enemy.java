package com.alexngai.jellyjump.gameobjects;

import com.alexngai.jellyjump.GameRenderer;
import com.alexngai.jellyjump.charactertypes.Bass;
import com.alexngai.jellyjump.charactertypes.Bluefish;
import com.alexngai.jellyjump.charactertypes.Carp;
import com.alexngai.jellyjump.charactertypes.Sardine;
import com.alexngai.jellyjump.charactertypes.SeaBream;
import com.alexngai.jellyjump.charactertypes.Swordfish;
import com.alexngai.jellyjump.charactertypes.Tuna;
import com.alexngai.jellyjump.helpers.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


public class Enemy extends GameObject{

	public int id;
	protected float animTime = 0;
	protected float totalDuration = .75f;
	private float scaleMultiplier = 1;
	
    public static final int RIGHT = 0;
    public static final int RIGHT2 = 1;
    public static final int LEFT = 2;
	public static final int LEFT2 = 3;
	private TextureRegion[] sprites;
	
	private int enemyID;
	
	private final int sardineID = 0;
	private final int bluefishID = 1;
	private final int carpID = 2;
	private final int seabreamID = 3;
	private final int bassID = 4;
	private final int tunaID = 5;
	private final int swordfishID = 6;
	
	public Enemy(){
		super();
	}
	
	public void update(float deltaTime){
		super.update(deltaTime);
		checkLifetime();
		animate(deltaTime);
	}
	
	public void render(SpriteBatch batch){
		super.render(batch);
	}

	public void unuse(){
		setInUse(false);
		Gdx.app.log("Enemy", "unused");
	}
	
	private void checkLifetime(){
		if (getPosition().x < (0-getSize().x) || getPosition().x > (GameRenderer.width+getSize().x)) unuse();
		if (getPosition().y < (0-getSize().y)) unuse();
	}
	
	private void animate(float deltaTime){
		if (sprites == null) return;
		
		animTime += deltaTime;
		animTime = animTime % totalDuration;		
		if (getVelocity().x > 0){
			if (animTime < totalDuration/2) setTexture(sprites[RIGHT]);
			else setTexture(sprites[RIGHT2]);
		}
		else{
			if (animTime < totalDuration/2) setTexture(sprites[LEFT]);
			else setTexture(sprites[LEFT2]);
		}	
	}
	
	public void reconstruct(int enemyID, Vector2 position, Vector2 velocity) {
		setVelocity(velocity);
		setEnemyType(enemyID);
		setPosition(position);
		TextureRegion texture = (velocity.x > 0) ? sprites[RIGHT]:sprites[LEFT]; 
		setTexture(texture);
		
		if (position.x == 0) position.x = 0 - getSize().x/2;
		else if (position.x == GameRenderer.width) position.x = GameRenderer.width + getSize().x/2;
	
	}
	
	private void setEnemyType(int enemyID){
		this.enemyID = enemyID; 
		switch (enemyID) {
		case bluefishID:
			sprites = Bluefish.sprites;
			setSize(Bluefish.scale);
			getVelocity().x = getVelocity().x * Bluefish.speed;
			break;

		case sardineID:
			sprites = Sardine.sprites;
			setSize(Sardine.scale);
			getVelocity().x = getVelocity().x * Sardine.speed;
			break;
			
		case carpID:
			sprites = Carp.sprites;
			setSize(Carp.scale);
			getVelocity().x = getVelocity().x * Carp.speed;
			break;
			
		case bassID:
			sprites = Bass.sprites;
			setSize(Bass.scale);
			getVelocity().x = getVelocity().x * Bass.speed;
			break;
			
		case seabreamID:
			sprites = SeaBream.sprites;
			setSize(SeaBream.scale);
			getVelocity().x = getVelocity().x * SeaBream.speed;
			break;
			
		case tunaID:
			sprites = Tuna.sprites;
			setSize(Tuna.scale);
			getVelocity().x = getVelocity().x * Tuna.speed;
			break;
			
		case swordfishID:
			sprites = Swordfish.sprites;
			setSize(Swordfish.scale);
			getVelocity().x = getVelocity().x * Swordfish.speed;
			break;
			
		default:
			break;
		}
	}
	
}
