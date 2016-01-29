package com.alexngai.jellyjump.gameobjects;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.alexngai.jellyjump.GameRenderer;
import com.alexngai.jellyjump.helpers.Assets;
import com.alexngai.jellyjump.helpers.Settings;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer.Random;
import com.badlogic.gdx.math.Vector2;

public class LaserGenerator {

	LaserPool laserPool;
	private final double spawnChance = 0.02;
	private final double shieldChance = 0.02;

	float counter = 0;
	PlayerCharacter playerCharacter;
	RayHandler handler;

	public LaserGenerator(RayHandler handler, LaserPool laserPool, PlayerCharacter playerChar){
		this.laserPool = laserPool;
		this.handler = handler;
		this.playerCharacter = playerChar;
	}

	public void update(float delta){

		counter += delta;
		if (Settings.lasers>0 && counter > 5/Settings.lasers){
			counter = 0;
			Vector2 position = new Vector2(playerCharacter.getPosition().x, playerCharacter.getPosition().y);
			Vector2 velocity = new Vector2( 0, 15);
			float sizeVal = 20;
			Vector2 size = new Vector2( sizeVal, sizeVal);

			Color color = Color.CYAN;
			Light light = new PointLight(handler, 100, color, 100, position.x, position.y);
			light.setSoft(false);
			laserPool.addLaser(position, size, velocity, light);
			if (!Settings.muted) Assets.laser.play(.25f);
			//Log.d("Enemy", "created enemy " + created + ", pos:" + posX);
		}		
	}

}
