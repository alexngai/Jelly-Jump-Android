package com.alexngai.jellyjump.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class EnemyPool {
	
	private Enemy[] objarray;
	private int maxNumObjects;
	private int numObjects;
	
	public EnemyPool(){	
	}
	
	public EnemyPool(int maxNumObjects, int numObjects){
		this.maxNumObjects = maxNumObjects;
		this.numObjects = numObjects;
		
		objarray = new Enemy[maxNumObjects]; 
		for (int i=0; i<maxNumObjects; i++){
			objarray[i] = new Enemy();
		}	
	}
	
	public void render(SpriteBatch batch){
		for (int i=0; i<numObjects; i++){
			if(objarray[i].isInUse()){
				objarray[i].render(batch);
			}
		}
	}
	
	public void update(float delta){
		for (int i=0; i<numObjects; i++){
			if(objarray[i].isInUse()){
				objarray[i].update(delta);
			}
		}
	}
	
	public int addEnemy(int enemyID, Vector2 position, Vector2 velocity){

		for (int i=0; i<numObjects; i++){
			if (!objarray[i].isInUse()){
				objarray[i].setInUse(true);
				objarray[i].reconstruct(enemyID, position, velocity);
				return i;
			}
		}	
		return -1;
	}
	
	public Enemy[] getObjarray(){
		return objarray;
	}

	public int getMaxNumObjects() {
		return maxNumObjects;
	}

	public void setNumObjects(int numObjects) {
		this.numObjects = numObjects;
	}
	
	public void clearAllObjects(){
		for (int i=0; i<maxNumObjects; i++){
			objarray[i].unuse();
		}
	}
	

}
