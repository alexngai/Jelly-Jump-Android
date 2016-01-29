package com.alexngai.jellyjump.helpers;

import java.util.Random;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.alexngai.jellyjump.GameRenderer;
import com.alexngai.jellyjump.GameWorld;
import com.alexngai.jellyjump.gameobjects.PlayerCharacter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

public class InputHandler implements InputProcessor {

	enum GameState {
		Menu, Running, Paused, GameOver
	}

	private static float height;
	private static float width = 1000;

	private OrthographicCamera camera;
	private RayHandler handler; 
	private UIHelper uihandler;
	private GameWorld gameWorld;
	private World world;
	private GameRenderer gameRenderer;

	private Touchpad touchpad;

	private PlayerCharacter gamechar;
	private Body circleBody;
	private Body groundBody;

	private MouseJoint mouseJoint = null;
	private MouseJoint joystickMouseJoint = null;

	private Vector3 testPoint = new Vector3();
	private Vector2 target = new Vector2();

	private boolean downPressed = false;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private boolean upPressed = false;

	private Vector2 leftCtrl = new Vector2(-8,25);
	private Vector2 rightCtrl = new Vector2(8,25);

	public InputHandler(GameWorld gameWorld, GameRenderer gameRenderer, UIHelper uihandler){
		camera = gameRenderer.getCamera();
		gamechar = gameWorld.getPlayerCharacter();
		this.gameRenderer = gameRenderer;
		float ppu = Gdx.graphics.getWidth() / width;
		height = Gdx.graphics.getHeight() / ppu;

		this.gameWorld = gameWorld;
		world = gameWorld.getWorld();
		handler = gameRenderer.getRayHandler();
		this.uihandler = uihandler;
	}

	public void update(float delta){
	}


	@Override
	public boolean keyDown(int keycode) {

		if (gameRenderer.state == GameRenderer.Menu){

		}
		else if (gameRenderer.state == GameRenderer.Running){
			if (keycode == Input.Keys.RIGHT){
				rightPressed = true;
				gamechar.jumpRight();
			}

			if (keycode == Input.Keys.LEFT){
				leftPressed = true;
				gamechar.jumpLeft();
			}

			if (keycode == Input.Keys.UP){
				gamechar.setVelocity(new Vector2(0, 50));
				gamechar.setAngle(0);
				upPressed = true;
			}

			if (keycode == Input.Keys.DOWN){
				downPressed = true;
			}
			
			if (!gamechar.isStarted()) gamechar.setStarted(true);
		}
		else if (gameRenderer.state == GameRenderer.GameOver){

		} else if (gameRenderer.state == GameRenderer.Paused){

		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.RIGHT){
			rightPressed = false;
		}
		if (keycode == Input.Keys.LEFT){
			leftPressed = false;
		}
		if (keycode == Input.Keys.UP){
			upPressed = false;
		}
		if (keycode == Input.Keys.DOWN){
			downPressed = false;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	
		testPoint.set(screenX, screenY,0);
		camera.unproject(testPoint);
		Gdx.app.log("Input", "touch down:" + screenX + "," + screenY + "/" + testPoint.x + "," + testPoint.y);
		
		if (gameRenderer.state == GameRenderer.Menu){
			if (testPoint.dst(new Vector3(gamechar.getPosition().x, gamechar.getPosition().y, 0)) < 200){
				gameRenderer.setRunning();
				if (testPoint.x < width/2){
					gamechar.jumpLeft();
				} else if (testPoint.x >= width/2){
					gamechar.jumpRight();

				}
				gamechar.setStarted(true);
				
			}
		}
		else if (gameRenderer.state == GameRenderer.Running){
			if (testPoint.x < width/2){
				gamechar.jumpLeft();
			} else if (testPoint.x >= width/2){
				gamechar.jumpRight();

			}
		}
		else if (gameRenderer.state == GameRenderer.GameOver){
			//gameRenderer.setMenu();
		} 
		else if (gameRenderer.state == GameRenderer.Paused){
		}
	

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		testPoint.set(screenX, screenY,0);
		camera.unproject(testPoint);

		//gamechar.setPosition(new Vector2(testPoint.x,testPoint.y));
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}


}
