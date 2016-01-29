package com.alexngai.jellyjump;

import com.alexngai.jellyjump.helpers.InputHandler;
import com.alexngai.jellyjump.helpers.LightHelper;
import com.alexngai.jellyjump.helpers.Settings;
import com.alexngai.jellyjump.helpers.UIHelper;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class UpgradeScreen implements Screen{
	//controls game world, input handler, and renderer
	//also includes tilemap helper and 
	JellyJump game;

	public static float height;
	public static float width = 1000;
	OrthographicCamera camera;
	Viewport viewport;
	SpriteBatch batch = new SpriteBatch();
	private BitmapFont font;
	
	public UpgradeScreen(JellyJump game) {
		this.game = game;
		Settings.load();

		float ppu = Gdx.graphics.getWidth() / width;
		height = Gdx.graphics.getHeight() / ppu;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		viewport = new FitViewport(width, height, camera);

		batch = new SpriteBatch();
        
		font = new BitmapFont(Gdx.files.internal("data/fonts/neuropol200.fnt"));

	}

	public void dispose(){

	}

	@Override
	public void render(float delta){
		camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		
		batch.begin();
		batch.enableBlending();

		
		batch.end();
	}

	public void resize(int width, int height){
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

	@Override
	public void resume() {
		// TODO Auto-generated method stub	
	}


}
