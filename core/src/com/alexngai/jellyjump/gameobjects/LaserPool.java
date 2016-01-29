package com.alexngai.jellyjump.gameobjects;


import box2dLight.Light;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class LaserPool {

	private Laser[] objarray;
	private final int maxNumObjects = 2;
	
	
	public LaserPool(){
		objarray = new Laser[maxNumObjects]; 
		for (int i=0; i<maxNumObjects; i++){
			objarray[i] = new Laser();
		}
	}
	
	public void render(SpriteBatch batch){
		for (int i=0; i<maxNumObjects; i++){
			if(objarray[i].isInUse()){
				objarray[i].render(batch);
			}
		}
	}
	
	public void update(float deltaTime){
		for (int i=0; i<maxNumObjects; i++){
			if(objarray[i].isInUse()){
				objarray[i].update(deltaTime);
			}
		}
	}
	
	public int addLaser(Vector2 position, Vector2 size, Vector2 velocity, Light light){

		for (int i=0; i<maxNumObjects; i++){
			if (!objarray[i].isInUse()){
				objarray[i].setInUse(true);
				objarray[i].reconstruct(position, size, velocity, light);
				return i;
			}
		}	
		return -1;
	}
	
	public Laser[] getObjarray(){
		return objarray;
	}
	
	public void clearAllObjects(){
		for (int i=0; i<maxNumObjects; i++){
			objarray[i].unuse();
		}
	}
}
