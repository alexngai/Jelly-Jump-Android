package com.alexngai.jellyjump.gameobjects;


import box2dLight.Light;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MotePool {

	private Mote[] objarray;
	private final int maxNumObjects = 5;
	
	
	public MotePool(){
		objarray = new Mote[maxNumObjects]; 
		for (int i=0; i<maxNumObjects; i++){
			objarray[i] = new Mote();
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
	
	public int addMote(Vector2 position, Vector2 size, Vector2 velocity, Light light){

		for (int i=0; i<maxNumObjects-1; i++){
			if (!objarray[i].isInUse()){
				objarray[i].setInUse(true);
				objarray[i].reconstruct(position, size, velocity, light);
				return i;
			}
		}	
		return -1;
	}
	
	public void addSpecialMote(Vector2 position, Vector2 size, Vector2 velocity, Light light){

		if (!objarray[maxNumObjects-1].isInUse()){
			objarray[maxNumObjects-1].setInUse(true);
			objarray[maxNumObjects-1].reconstruct(position, size, velocity, light);
		} else light.setActive(false);
	}	
	
	public Mote[] getObjarray(){
		return objarray;
	}
	
	public void clearAllObjects(){
		for (int i=0; i<maxNumObjects; i++){
			objarray[i].unuse();
		}
	}
}
