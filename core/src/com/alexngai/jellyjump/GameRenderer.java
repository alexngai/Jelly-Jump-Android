package com.alexngai.jellyjump;

import box2dLight.RayHandler;

import com.alexngai.jellyjump.gameobjects.PlayerCharacter;
import com.alexngai.jellyjump.helpers.Assets;
import com.alexngai.jellyjump.helpers.LightHelper;
import com.alexngai.jellyjump.helpers.Settings;
import com.alexngai.jellyjump.helpers.UIHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

//contains all render objects
public class GameRenderer {

	public static final int Menu = 0;
	public static final int Running = 1;
	public static final int GameOver = 2;
	public static final int Paused = 3;
	public static final int Upgrade = 4;
	public int state = Menu;

	public boolean lights_enabled = true;
	public static float PIXELS_PER_METER = 10f;

	public static float height;
	public static float width = 1000;
	OrthographicCamera camera;

	//Sprite background = new Sprite(Assets.background);
	TextureRegion background = Assets.background;
	Vector2 bg_size = new Vector2(2000,4000);

	Viewport viewport;
	SpriteBatch batch = new SpriteBatch();
	FPSLogger logger;
	RayHandler handler;
	LightHelper lightHelper;
	UIHelper uiHelper;
	Stage stage;
	ShapeRenderer shapeRenderer;

	GameWorld gameInstance;
	World world;

	private BitmapFont font;

	public GameRenderer(GameWorld gameInstance, UIHelper uihandler){
		this.gameInstance = gameInstance;
		world = gameInstance.getWorld();
		uiHelper = uihandler;
		float ppu = Gdx.graphics.getWidth() / width;
		height = Gdx.graphics.getHeight() / ppu;
		Gdx.app.log("Screen", "width: " + width + ", height: " + height);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		viewport = new FitViewport(width, height, camera);

		stage = uihandler.getStage();
		camera.update();

		logger = new FPSLogger();
		batch = new SpriteBatch();

		lightHelper = new LightHelper(world, camera);
		handler = lightHelper.getRayHandler();

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);

		font = new BitmapFont(Gdx.files.internal("data/fonts/neuropol200.fnt"));

	}

	public void render(float delta){
		camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_BLEND);

		//Color lightColor = new Color(r1,g1,b1,.5f);
		//Color darkColor = new Color(r2,g2,b2,.5f);
		//shapeRenderer.setProjectionMatrix(camera.combined);
		//shapeRenderer.begin(ShapeType.Filled);
		//shapeRenderer.rect(0, 0, width, height, darkColor, darkColor, lightColor, lightColor);
		//shapeRenderer.end();


		batch.begin();
		batch.enableBlending();

		Color color = batch.getColor();
		float oldAlpha = color.a; 
		float scale = .3f;
		color.a = oldAlpha*scale; 
		batch.setColor(color); 
		if (GameWorld.depth*2 < 1000) batch.draw(background, 0, -GameWorld.depth*2, bg_size.x/2, bg_size.y, bg_size.x, bg_size.y, 1, 1, 0);
		else batch.draw(background, 0, -1000, bg_size.x/2, bg_size.y, bg_size.x, bg_size.y, 1, 1, 0);
		color.a = oldAlpha;
		batch.setColor(color);

		gameInstance.render(batch);
		batch.end();

		batch.setProjectionMatrix(camera.combined);
		if (lights_enabled){
			/** BOX2D LIGHT STUFF BEGIN */
			handler.setCombinedMatrix(camera.combined, camera.position.x,
					camera.position.y, camera.viewportWidth * camera.zoom,
					camera.viewportHeight * camera.zoom);
			handler.updateAndRender();
		}
		lightHelper.update();

		logger.log();

		if (gameInstance.isGameOver() && state != GameOver) setGameOver();

		switch (state) {
		case Menu:
			renderMenu();
			break;
		case Running:
			renderRunning();
			break;
		case GameOver:
			renderGameOver();
			break;
		case Paused:
			renderPaused();
			break;
		case Upgrade:
			renderUpgrade();
		}

	}

	private void renderPaused() {
		// TODO Auto-generated method stub

	}

	private void renderGameOver() {

		batch.begin();
		font.setColor(Color.BLACK);

		if (gameInstance.isGameWin()){
			Gdx.gl.glClearColor(1, 1, 1, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			font.setScale(.6f);
			String winString = "The Surface";
			font.draw(batch, winString, width/2 - font.getBounds(winString).width/2, height*3/4);

			font.setScale(.3f);
			String temp = "To be continued...";
			font.draw(batch, temp, width/2 - font.getBounds(temp).width/2, height*3/4 - 150);

			font.setScale(.3f);
			temp = "Endless Mode Unlocked";
			font.draw(batch, temp, width/2 - font.getBounds(temp).width/2, height/4 + 220);
			
			temp = "+" + (gameInstance.getBubbles()+(int)(GameWorld.depth-Settings.headStart)/10);
			font.draw(batch, temp, width/2, height/4 + 300 );
			
			Vector2 imSize = new Vector2(50, 50); 
			batch.draw(Assets.bubble, width/2 - 80, height/4 + 280 - font.getBounds(temp).height/2, 
					imSize.x/2, imSize.y/2, imSize.x, imSize.y, 1, 1, 0);


		}else {
			font.setColor(Color.WHITE);
			font.setScale(.6f);
			String gameOverString = "Game Over";
			font.draw(batch, gameOverString, width/2 - font.getBounds(gameOverString).width/2, height*2/3);

			font.setScale(.3f);
			String temp = "Try Some Upgrades?";
			font.draw(batch, temp, width/2 - font.getBounds(temp).width/2, height/4 + 220);
			
			temp = "+" + (gameInstance.getBubbles()+(int)(GameWorld.depth-Settings.headStart)/10);
			font.draw(batch, temp, width/2, height/4 + 300 );
			
			Vector2 imSize = new Vector2(50, 50); 
			batch.draw(Assets.bubble, width/2 - 80, height/4 + 280 - font.getBounds(temp).height/2, 
					imSize.x/2, imSize.y/2, imSize.x, imSize.y, 1, 1, 0);
		}

		font.setScale(.25f);
		String scoreStr = "You reached " + (int)gameInstance.depth + "m";
		font.draw(batch, scoreStr, width/2 - font.getBounds(scoreStr).width/2, height*2/3 - 200);

		batch.end();

		stage.act(Gdx.graphics.getDeltaTime()); 
		stage.draw();
	}

	private void renderRunning() {
		batch.begin();
		font.setColor(Color.WHITE);
		font.setScale(.6f);

		int depth;
		String depthString;
		if (Settings.endlessMode){
			depth = (int) (gameInstance.depth);
			depthString = depth + "m";
		} else{
			depth = (int) (1000 - gameInstance.depth);
			depthString = "-" + depth + "m";
		}

		font.draw(batch, depthString, width/2 - font.getBounds(depthString).width/2, height - 50);

		font.setScale(.25f);
		String bubbleStr = "" + gameInstance.getBubbles();
		font.draw(batch, bubbleStr, width - 200 - font.getBounds(bubbleStr).width/2, height - 200);

		Vector2 imSize = new Vector2(50, 50); 
		batch.draw(Assets.bubble, width - 50 - imSize.x, height-240, 
				imSize.x/2, imSize.y/2, imSize.x, imSize.y, 1, 1, 0);

		String livesStr = "x" + Settings.extralives;
		font.draw(batch, livesStr, 100, height - 200);

		batch.end();


	}

	private void renderMenu() {
		batch.begin();
		font.setColor(Color.WHITE);
		font.setScale(.7f);
		String titleString = "Jelly Quest";
		font.draw(batch, titleString, width/2 - font.getBounds(titleString).width/2, height*5/6);

		font.setScale(.25f);
		String tapString = "TAP TO JUMP";
		font.draw(batch, tapString, width/2 - font.getBounds(tapString).width/2, 200);

		font.setScale(.25f);
		String scoreString = "HIGH SCORE: " + (int)Settings.highScore + "m";
		font.draw(batch, scoreString, width/2 - font.getBounds(scoreString).width/2, height*5/6 - 280);

		//		font.setScale(.25f);
		//		String jumpString = "Tap Left/Right to jump";
		//		font.draw(batch, jumpString, width/2 - font.getBounds(jumpString).width/2, 150);

		font.setScale(.25f);
		String bubbleStr = "" + Settings.bubbles;
		font.draw(batch, bubbleStr, width/2 - font.getBounds(bubbleStr).width/2, height*5/6 - 200);

		Vector2 imSize = new Vector2(50, 50); 
		batch.draw(Assets.bubble, width/2 - font.getBounds(bubbleStr).width/2 - 100 - imSize.x/2, height*5/6-245, 
				imSize.x/2, imSize.y/2, imSize.x, imSize.y, 1, 1, 0);

		if (Settings.upgradesAvailable()){
			font.setScale(.2f);
			String temp = "Upgrades";
			font.draw(batch, temp, width/2 - 350 - font.getBounds(temp).width/2,  350);
			temp = "Available";
			font.draw(batch, temp, width/2 -350 - font.getBounds(temp).width/2, 300);
		}
		font.setColor(Color.GRAY);
		font.setScale(.15f);
		String creditString = "Game by Alex Ngai";
		String creditString2 = "SFX from FreeSFX";
		font.draw(batch, creditString, width/2 - font.getBounds(creditString).width/2, 75);
		font.draw(batch, creditString2, width/2 - font.getBounds(creditString2).width/2, 40);

		batch.end();

		stage.act(Gdx.graphics.getDeltaTime()); 
		stage.draw();

	}

	private void renderUpgrade(){
		batch.begin();
		font.setColor(Color.WHITE);
		font.setScale(.6f);
		String titleString = "Upgrades";
		font.draw(batch, titleString, width/2 - font.getBounds(titleString).width/2, height*5/6);

		font.setScale(.25f);
		String bubbleStr = "" + Settings.bubbles;
		font.draw(batch, bubbleStr, width/2 - font.getBounds(bubbleStr).width/2, height*5/6 - 200);

		Vector2 imSize = new Vector2(50, 50); 
		batch.draw(Assets.bubble, width/2 - font.getBounds(bubbleStr).width/2 - 100 - imSize.x/2, height*5/6-245, 
				imSize.x/2, imSize.y/2, imSize.x, imSize.y, 1, 1, 0);

		font.setScale(.25f);
		String temp = "Glow: " + Settings.brightness;
		font.draw(batch, temp, 50, height*5/6 - 300 - font.getBounds(temp).height/2);

		temp = "Extra Lives: " + Settings.extralives;
		font.draw(batch, temp, 50, height*5/6 - 400 - font.getBounds(temp).height/2);
		
		temp = "Head Start: " + Settings.headStart + "m";
		font.draw(batch, temp, 50, height*5/6 - 500 - font.getBounds(temp).height/2);
		
		temp = "Lasers: " + Settings.lasers;
		font.draw(batch, temp, 50, height*5/6 - 600 - font.getBounds(temp).height/2);


		font.setColor(Color.GRAY);
		temp = "" + Settings.brightCost;
		font.draw(batch, temp, width - 200 - font.getBounds(temp).width, height*5/6 - 300 - font.getBounds(temp).height/2);
		if (Settings.bubbles < Settings.brightCost) uiHelper.removeBrightButton();

		if (Settings.extralives < 3){
		temp = "" + Settings.livesCost;
		font.draw(batch, temp, width - 200 - font.getBounds(temp).width, height*5/6 - 400 - font.getBounds(temp).height/2);
		}
		if (Settings.bubbles < Settings.livesCost || Settings.extralives >= 3) uiHelper.removeLivesButton();
		
		if (Settings.headStart < 500){
		temp = "" + Settings.headStartCost;
		font.draw(batch, temp, width - 200 - font.getBounds(temp).width, height*5/6 - 500 - font.getBounds(temp).height/2);
		}
		if (Settings.bubbles < Settings.headStartCost || Settings.headStart >= 500) uiHelper.removeHeadStartButton();
		
		if (Settings.lasers < 2){
		temp = "" + Settings.lasersCost;
		font.draw(batch, temp, width - 200 - font.getBounds(temp).width, height*5/6 - 600 - font.getBounds(temp).height/2);
		}
		if (Settings.bubbles < Settings.lasersCost || Settings.lasers >= 2) uiHelper.removeLasersButton();
		
		batch.end();

		stage.act(Gdx.graphics.getDeltaTime()); 
		stage.draw();
	}

	public RayHandler getRayHandler(){
		return handler;
	}

	public OrthographicCamera getCamera(){
		return camera;
	}

	public float getHeight() {
		return height;
	}

	public float getWidth() {
		return width;
	}

	public Stage getStage(){
		return stage;
	}

	public Viewport getViewport(){
		return viewport;
	}

	public void setRunning(){
		state = Running;
		lights_enabled = true;
		uiHelper.hideButtons();
		Gdx.app.log("game state", "running");
	}

	public void setMenu(){
		state = Menu;
		lights_enabled = true;
		uiHelper.showButtons();
		uiHelper.removeGameOverButton();
		//uiHelper.removeUpgradeButtons();
		gameInstance.reset();
		Gdx.app.log("game state", "menu");
	}

	public void setGameOver(){
		state = GameOver;
		Gdx.app.log("game state", "game over");
		//uiHelper.removeUpgradeButtons();
		uiHelper.showGameOverButton();
	}

	public void setUpgrade(){
		state = Upgrade;
		Gdx.app.log("game state", "upgrade");
		uiHelper.hideButtons();
		uiHelper.showUpgradeButtons();
	}

}
