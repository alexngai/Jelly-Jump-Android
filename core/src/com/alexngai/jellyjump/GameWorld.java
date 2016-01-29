package com.alexngai.jellyjump;

import java.awt.Font;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.alexngai.jellyjump.gameobjects.BubbleGenerator;
import com.alexngai.jellyjump.gameobjects.BubblePool;
import com.alexngai.jellyjump.gameobjects.Enemy;
import com.alexngai.jellyjump.gameobjects.EnemyGenerator;
import com.alexngai.jellyjump.gameobjects.EnemyPool;
import com.alexngai.jellyjump.gameobjects.LaserGenerator;
import com.alexngai.jellyjump.gameobjects.LaserPool;
import com.alexngai.jellyjump.gameobjects.Mote;
import com.alexngai.jellyjump.gameobjects.MoteGenerator;
import com.alexngai.jellyjump.gameobjects.MotePool;
import com.alexngai.jellyjump.gameobjects.PlayerCharacter;
import com.alexngai.jellyjump.helpers.Assets;
import com.alexngai.jellyjump.helpers.CollisionDetector;
import com.alexngai.jellyjump.helpers.Difficulty;
import com.alexngai.jellyjump.helpers.Settings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;


//contains all box2d physics objects
public class GameWorld {

	private final float endDepth = 1000f;
	public static float speed = 1.5f;
	public static float height = 0f;
	public static float depth = 0;

	private World world;
	private PlayerCharacter playerCharacter;

	private RayHandler handler;
	private BubbleGenerator bubbleGenerator;
	private BubblePool bubblePool;
	private EnemyPool enemyPool;
	private EnemyGenerator enemyGenerator;
	private MotePool motePool;
	private MoteGenerator moteGenerator;

	private LaserPool laserPool;
	private LaserGenerator laserGenerator;

	private BitmapFont font;

	private int bubbles;
	private boolean gameOver = false;
	private boolean gameWin = false;
	private int level = 1;

	public GameWorld(){
		world = new World(new Vector2(0f, 0f), false);
		playerCharacter = new PlayerCharacter(world, new Vector2(500, 500));
		//playerCharacter.setTexture(Assets.jelly1);
		bubblePool = new BubblePool();
		bubbleGenerator = new BubbleGenerator(bubblePool);
		enemyPool = new EnemyPool(30, 20);
		enemyGenerator = new EnemyGenerator(enemyPool);

		bubbles = 0;

	}

	public void postCreate(){
		Color color2 = new Color(0f, .7f, 1f, .8f);
		playerCharacter.setLight(new PointLight(handler, 400, color2, Settings.brightness, playerCharacter.getPosition().x, playerCharacter.getPosition().y));
		playerCharacter.getLight().setSoft(false);
		motePool = new MotePool();
		moteGenerator = new MoteGenerator(handler, motePool);
		laserPool = new LaserPool();
		laserGenerator = new LaserGenerator(handler, laserPool, playerCharacter);
	}

	public void update(float delta){
		playerCharacter.update(delta);
		bubbleGenerator.update();
		bubblePool.update(delta);

		if (playerCharacter.isStarted()){
			enemyGenerator.update();
			enemyPool.update(delta);
			moteGenerator.update();
			motePool.update(delta);
			if (!gameOver){ 
				laserGenerator.update(delta);
				laserPool.update(delta);
				depth = depth + delta*60*height/200;
			}
		}

		if (checkCollisions()){
			//enemy.unuse();
			if (playerCharacter.collided() && !gameOver) {
				setGameOver();
				height = 0;
			}
			Gdx.app.log("enemy", "collided");
		}

		if (playerCharacter.getPosition().y < 0 && !gameOver) setGameOver();
		if (depth > 1000 && !gameOver && !Settings.endlessMode) setGameWin();

		int temp = Difficulty.increaseLevel(level, depth);
		if (temp > 0){
			level = temp;
			enemyGenerator.setNumTypes(level);
		}

		if (gameOver) height = 0;
		else{ //increase scroll speed
			speed = (float) (1.5 + 5*(depth/1000));
			height = speed;
		}

		//increase enemies with depth
		if (depth/100 <= 10){
			enemyPool.setNumObjects((int) (20+depth/100));
		}

	}

	public void render(SpriteBatch batch){
		enemyPool.render(batch);
		bubblePool.render(batch);
		motePool.render(batch);
		playerCharacter.render(batch);
	}

	private boolean checkCollisions(){
		Mote mote = CollisionDetector.checkMoteCollision(playerCharacter, motePool);
		if (mote != null){
			Gdx.app.log("bubble", "collided");
			if (!Settings.muted) Assets.bubblePop.play(.4f);
			if (mote.getSize().x > 100){
				playerCharacter.activateShield();
			} else bubbles++;
			mote.unuse();
		}
		CollisionDetector.checkLaserCollision(laserPool, enemyPool, motePool, handler);

		Enemy enemy = CollisionDetector.checkCollision(playerCharacter, enemyPool);
		if (enemy != null) return true;
		return false;
	}

	public PlayerCharacter getPlayerCharacter(){
		return playerCharacter;
	}

	public World getWorld(){
		return world;
	}

	public void dispose(){
		world.dispose();
	}

	public RayHandler getHandler() {
		return handler;
	}

	public void setHandler(RayHandler handler) {
		this.handler = handler;
	}

	public int getBubbles(){
		return bubbles;
	}

	public boolean isGameOver(){
		return gameOver;
	}

	public boolean isGameWin(){
		return gameWin;
	}

	public void setGameOver(){
		gameOver = true;
		height = 0f;
		playerCharacter.setAngVelocity(20);
		Settings.bubbles = Settings.bubbles + bubbles + (int)(depth-Settings.headStart)/10;
		if (depth > Settings.highScore) Settings.highScore = depth;
		Settings.timesPlayed++;
		Settings.save();
		if (!Settings.muted)Assets.descend.play(.3f);
	}

	public void setGameWin(){
		gameOver = true;
		gameWin = true;
		height = 0f;
		Settings.bubbles = Settings.bubbles + bubbles + (int)(depth-Settings.headStart)/10;
		if (depth > Settings.highScore) Settings.highScore = depth;
		Settings.timesPlayed++;
		Settings.endlessMode = true;
		Settings.save();

		if (!Settings.muted)Assets.beach.play();
		Assets.beach.setVolume(.5f);
		
		
	}

	public void reset(){
		bubblePool.clearAllObjects();
		motePool.clearAllObjects();
		enemyPool.clearAllObjects();
		laserPool.clearAllObjects();
		gameOver = false;
		gameWin = false;
		playerCharacter.setPosition(new Vector2(500,500));
		playerCharacter.setStarted(false);
		playerCharacter.setAngVelocity(0);
		playerCharacter.setAngle(0);
		playerCharacter.removeShield();
		depth = Settings.headStart;
		height = speed;
		level = 1;
		enemyGenerator.setNumTypes(1);
		enemyPool.setNumObjects(20);
		bubbles = 0;
	}

}
