package com.alexngai.jellyjump.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ObjectPool {
	private PlayerCharacter[] playerArray;
	private int maxNumObjects = 8;

	public ObjectPool(){

		playerArray = new PlayerCharacter[maxNumObjects]; 
		for (int i=0; i<maxNumObjects; i++){
			playerArray[i] = new PlayerCharacter();
		}	
	}

	public void render(SpriteBatch batch){
//		for (int i=0; i<maxNumObjects; i++){
//			if (playerArray[i].isInUse()){
//				playerArray[i].render(batch);
//				//Gdx.app.log("Player", "updated" + i);
//			}
//		}
	}

	public void update(float delta){
//		for (int i=0; i<maxNumObjects; i++){
//			if (playerArray[i].isInUse()){
//				playerArray[i].update(delta);
//				//Gdx.app.log("Player", "updated" + i);
//			}
//		}
	}

	public void addPlayer(PlayerCharacter player){
//		for (int i=0; i<maxNumObjects; i++){
//			if (!playerArray[i].isInUse()){
//				playerArray[i] = player;
//				player.setInUse(true);
//				Gdx.app.log("Player", "in use " + i);
//				break;
//			}
//		}
	}

	public PlayerCharacter[] getplayerArray(){
		return playerArray;
	}

	public int getMaxNumObjects() {
		return maxNumObjects;
	}

	public void setMaxNumObjects(int maxNumObjects) {
		this.maxNumObjects = maxNumObjects;
		playerArray = new PlayerCharacter[maxNumObjects]; 
		for (int i=0; i<maxNumObjects; i++){
			playerArray[i] = new PlayerCharacter();
		}	
	}

	public void clearAllObjects(){
//		for (int i=0; i<maxNumObjects; i++){
//			playerArray[i].setInUse(false);
//		}
	}

}
