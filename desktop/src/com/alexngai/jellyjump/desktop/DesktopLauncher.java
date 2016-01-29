package com.alexngai.jellyjump.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.alexngai.jellyjump.JellyJump;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 360;//1080/2;
        config.height = 640;//1920/2;
		new LwjglApplication(new JellyJump(), config);
	}
}
