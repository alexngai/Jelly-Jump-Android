package com.alexngai.jellyjump.gameobjects;

import com.alexngai.jellyjump.GameRenderer;
import com.badlogic.gdx.math.Vector2;

public class EnemyGenerator {

	EnemyPool enemypool;
	private double spawnChance = 0.03;
	private int numTypes = 1;
	private final int maxNumTypes = 7;
	private double enemySpeed = 2.5;
	private double minEnemySpeed = .5;
	
	public EnemyGenerator(EnemyPool enemypool){
		this.enemypool=enemypool;
	}
	
	public void update(){
		float x = (float) Math.random();
		if (x < spawnChance){
			float direction = (float) Math.random();
			float speed = (float) (enemySpeed*Math.random());
			float spawnPosition = (float) (4*GameRenderer.width*Math.random());
			int typeID = (int) Math.floor(numTypes*Math.random());
			
			//limit minimum speed
			speed = (float) ((speed < minEnemySpeed) ? minEnemySpeed:speed);
			
			float posX; float velX;
			if (direction < 0.5){
				//right
				posX = 0;
				velX = speed;
			} else{
				//left
				posX = GameRenderer.width;
				velX = -speed;
			}
			
			if (spawnPosition > GameRenderer.height) posX = (float) (Math.random() * GameRenderer.width);
			
			Vector2 position = new Vector2(posX, GameRenderer.height/3 + spawnPosition);
			Vector2 velocity = new Vector2(velX, 0);
			
			enemypool.addEnemy(typeID, position, velocity);
			//Log.d("Enemy", "created enemy " + created + ", pos:" + posX);
		}
	}

	public double getSpawnChance() {
		return spawnChance;
	}

	public double getEnemySpeed() {
		return enemySpeed;
	}

	public double getMinEnemySpeed() {
		return minEnemySpeed;
	}

	public void setSpawnChance(double spawnChance) {
		this.spawnChance = spawnChance;
	}

	public void setEnemySpeed(double enemySpeed) {
		this.enemySpeed = enemySpeed;
	}

	public void setMinEnemySpeed(double minEnemySpeed) {
		this.minEnemySpeed = minEnemySpeed;
	}

	public int getNumTypes() {
		return numTypes;
	}

	public void setNumTypes(int numTypes) {
		if (numTypes <= maxNumTypes) this.numTypes = numTypes;
	}
	
	
}
