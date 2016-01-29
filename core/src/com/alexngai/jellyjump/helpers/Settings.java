package com.alexngai.jellyjump.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Settings {

	public static int bubbles;
	public static boolean muted;
	public static float highScore;
	public static int timesPlayed;

	public static int brightness;
	public static int extralives;
	public static int headStart;
	public static int lasers;
	
	public static int brightCost;
	public static int livesCost;
	public static int headStartCost;
	public static int lasersCost;
	
	public static boolean endlessMode;
	
	public static void load(){
		Preferences prefs = Gdx.app.getPreferences("jellyprefs");
		bubbles = prefs.getInteger("bubbles", 0);
		muted = prefs.getBoolean("muted", false);
		highScore = prefs.getFloat("highScore", 0);
		timesPlayed = prefs.getInteger("timesPlayed", 0);
		brightness = prefs.getInteger("brightness", 400);
		extralives = prefs.getInteger("extralives", 0);
		headStart = prefs.getInteger("headStart", 0);
		lasers = prefs.getInteger("lasers", 0);
		brightCost = prefs.getInteger("brightCost", 0);
		livesCost = prefs.getInteger("livesCost", 25);
		headStartCost = prefs.getInteger("headStartCost", 50);
		lasersCost = prefs.getInteger("lasersCost", 350);
		endlessMode = prefs.getBoolean("endlessMode", false);
		
	}

	public static void save(){
		Preferences prefs = Gdx.app.getPreferences("jellyprefs");
		prefs.putInteger("bubbles", bubbles);
		prefs.putBoolean("muted", muted);
		prefs.putFloat("highScore", highScore);
		prefs.putInteger("timesPlayed", timesPlayed);
		prefs.putInteger("brightness", brightness);
		prefs.putInteger("extralives", extralives);
		prefs.putInteger("headStart", headStart);
		prefs.putInteger("lasers", lasers);
		prefs.putInteger("brightCost", brightCost);
		prefs.putInteger("livesCost", livesCost);
		prefs.putInteger("headStartCost", headStartCost);
		prefs.putInteger("lasersCost", lasersCost);
		prefs.putBoolean("endlessMode", endlessMode);
		prefs.flush();
		
	}

	public static void buyBright(){
		if (bubbles - brightCost >= 0){
			bubbles = bubbles - brightCost;
			brightCost = brightCost + 25;
			brightness = brightness + 50;

			Preferences prefs = Gdx.app.getPreferences("jellyprefs");
			prefs.putInteger("bubbles", bubbles);
			prefs.putInteger("brightness", brightness);
			prefs.putInteger("brightCost", brightCost);
			prefs.flush();
		}
	}
	
	public static void buyHeadStart(){
		if (bubbles - headStartCost >= 0){
			bubbles = bubbles - headStartCost;
			headStartCost = headStartCost + 50;
			headStart = headStart + 100;

			Preferences prefs = Gdx.app.getPreferences("jellyprefs");
			prefs.putInteger("bubbles", bubbles);
			prefs.putInteger("headStart", headStart);
			prefs.putInteger("headStartCost", headStartCost);
			prefs.flush();
		}
	}
	
	public static void buyLasers(){
		if (bubbles - lasersCost >= 0){
			bubbles = bubbles - lasersCost;
			lasersCost = lasersCost + 350;
			lasers = lasers + 1;

			Preferences prefs = Gdx.app.getPreferences("jellyprefs");
			prefs.putInteger("bubbles", bubbles);
			prefs.putInteger("lasers", lasers);
			prefs.putInteger("lasersCost", lasersCost);
			prefs.flush();
		}
	}


	public static void buyLife(){
		if (bubbles - livesCost >= 0){
			bubbles = bubbles - livesCost;
			livesCost = livesCost + 25;
			extralives = extralives + 1;

			Preferences prefs = Gdx.app.getPreferences("jellyprefs");
			prefs.putInteger("bubbles", bubbles);
			prefs.putInteger("extralives", extralives);
			prefs.putInteger("livesCost", livesCost);
			prefs.flush();
		}
	}

	public static void loseLife(){
		if (extralives - 1 >= 0){
			extralives = extralives - 1;
			livesCost = livesCost - 25;

			Preferences prefs = Gdx.app.getPreferences("jellyprefs");
			prefs.putInteger("extralives", extralives);
			prefs.putInteger("livesCost", livesCost);
			prefs.flush();
		}
	}

	public static void delete(){
		Preferences prefs = Gdx.app.getPreferences("jellyprefs");
		prefs.putInteger("bubbles", 0);
		prefs.putBoolean("muted", false);
		prefs.putFloat("highScore", 0);
		prefs.putInteger("timesPlayed", 0);
		prefs.putInteger("brightness", 400);
		prefs.putInteger("extralives", 0);
		prefs.putInteger("brightCost", 0);
		prefs.putInteger("livesCost", 25);
		prefs.putBoolean("endlessMode", false);
		prefs.putInteger("headStart", 0);
		prefs.putInteger("lasers", 0);
		prefs.putInteger("headStartCost", 50);
		prefs.putInteger("lasersCost", 350);
		bubbles = 0;
		muted = false;
		highScore = 0;
		timesPlayed = 0;
		brightness = 400;
		extralives = 0;
		brightCost = 0;
		livesCost = 25;
		lasers = 0;
		headStart = 0;
		lasersCost = 350;
		headStartCost = 50;
		endlessMode = false;
		prefs.flush();
	}

	public static boolean upgradesAvailable(){
		if (bubbles >= brightCost) return true;
		if (bubbles >= livesCost) return true;
		
		return false;
	}
}
