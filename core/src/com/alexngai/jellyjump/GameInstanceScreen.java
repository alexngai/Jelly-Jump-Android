package com.alexngai.jellyjump;

import java.util.Random;

import box2dLight.ConeLight;
import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.alexngai.jellyjump.helpers.InputHandler;
import com.alexngai.jellyjump.helpers.Settings;
import com.alexngai.jellyjump.helpers.UIHelper;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameInstanceScreen implements Screen{

	JellyJump game;
	//controls game world, input handler, and renderer
	//also includes tilemap helper and 
	InputHandler inputHandler;
	GameRenderer gameRenderer;
	GameWorld gameWorld;
	UIHelper uiHandler;
	
	public GameInstanceScreen(JellyJump game) {
		Settings.load();
		//Settings.delete();
		this.game = game;
		uiHandler = new UIHelper(game);
		gameWorld = new GameWorld();
		gameRenderer = new GameRenderer(gameWorld, uiHandler);
		inputHandler = new InputHandler(gameWorld, gameRenderer, uiHandler);
		
		uiHandler.setRenderer(gameRenderer);
		uiHandler.setGameWorld(gameWorld);
		gameWorld.setHandler(gameRenderer.getRayHandler());
		gameWorld.postCreate();
		
		
	}

	public void dispose(){
		gameWorld.dispose();
	}

	@Override
	public void render(float delta){
		inputHandler.update(delta);
		gameWorld.update(delta);
		gameRenderer.render(delta);
	}
	
	public void resize(int width, int height){
		gameRenderer.getViewport().update(width, height);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	
	public InputProcessor getInputProcessor(){
		return inputHandler;
	}

	public Stage getStage(){
		return gameRenderer.getStage();
	}
	
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
