package com.alexngai.jellyjump.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.SoundLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	
	public static Sound crunch, jump, bubblePop, descend, laser;
	public static Music beach;
	
    public static TextureRegion blue_ball, orange_ball, gray_ball;
    public static TextureRegion blue_flag, orange_flag;
    public static TextureRegion icon_blank;
    public static TextureRegion bubble, bubbleShield;
    
    public static TextureRegion jelly1, jelly2, jelly3;
    public static TextureRegion background;
    public static TextureRegion bluefish, bluefish2, bluefishflip, bluefish2flip;
    public static TextureRegion sardine, sardine2, sardineflip, sardine2flip;
    public static TextureRegion bass, bass2, bassflip, bass2flip;
    public static TextureRegion seabream, seabream2, seabreamflip, seabream2flip;
    public static TextureRegion tuna, tuna2, tunaflip, tuna2flip;
    public static TextureRegion swordfish, swordfish2, swordfishflip, swordfish2flip;
    public static TextureRegion carp, carp2, carpflip, carp2flip;
    
    public static TextureRegion menuButton, modButton, volumeButton, volumeButtonOn, plusButton;
    
    //loads all game assets
    public static void load() {
    	icon_blank = new TextureRegion(new Texture(Gdx.files.internal("data/icon_blank.png")));
    	
    	jelly1 = new TextureRegion(new Texture(Gdx.files.internal("data/jelly_1.png")));
    	jelly2 = new TextureRegion(new Texture(Gdx.files.internal("data/jelly_2.png")));
    	jelly3 = new TextureRegion(new Texture(Gdx.files.internal("data/jelly_3.png")));
    	
    	background = new TextureRegion(new Texture(Gdx.files.internal("data/jelly_bg.png")));
    	
    	bubble = new TextureRegion(new Texture(Gdx.files.internal("data/bubble.png")));
    	bubbleShield = new TextureRegion(new Texture(Gdx.files.internal("data/bubble_shield.png")));
    	blue_ball = new TextureRegion(new Texture(Gdx.files.internal("data/ball_blue.png")));

    	bluefish = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/bluefish.png")));
    	bluefish2 = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/bluefish2.png")));
    	bluefishflip = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/bluefish_flip.png")));
    	bluefish2flip = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/bluefish2_flip.png")));
    	
    	bass = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/bass.png")));
    	bass2 = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/bass2.png")));
    	bassflip = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/bass_flip.png")));
    	bass2flip = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/bass2_flip.png")));
    	
    	carp = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/carp.png")));
    	carp2 = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/carp2.png")));
    	carpflip = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/carp_flip.png")));
    	carp2flip = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/carp2_flip.png")));
    	
    	seabream = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/seabream.png")));
    	seabream2 = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/seabream2.png")));
    	seabreamflip = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/seabream_flip.png")));
    	seabream2flip = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/seabream2_flip.png")));
    	
    	sardine = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/sardine.png")));
    	sardine2 = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/sardine2.png")));
    	sardineflip = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/sardine_flip.png")));
    	sardine2flip = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/sardine2_flip.png")));
    	
    	swordfish = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/swordfish.png")));
    	swordfish2 = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/swordfish2.png")));
    	swordfishflip = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/swordfish_flip.png")));
    	swordfish2flip = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/swordfish2_flip.png")));
    	
    	tuna = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/tuna.png")));
    	tuna2 = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/tuna2.png")));
    	tunaflip = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/tuna_flip.png")));
    	tuna2flip = new TextureRegion(new Texture(Gdx.files.internal("data/fish_files/tuna2_flip.png")));
    	
    	crunch = Gdx.audio.newSound(Gdx.files.internal("data/crunch.mp3"));
    	jump = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
    	bubblePop = Gdx.audio.newSound(Gdx.files.internal("data/pop.mp3"));
    	descend = Gdx.audio.newSound(Gdx.files.internal("data/explosion.mp3"));
    	laser = Gdx.audio.newSound(Gdx.files.internal("data/laser.mp3"));
    	
    	beach = Gdx.audio.newMusic(Gdx.files.internal("data/ocean_waves.mp3"));
    	
    	menuButton = new TextureRegion(new Texture(Gdx.files.internal("data/button_menu.png")));
    	modButton = new TextureRegion(new Texture(Gdx.files.internal("data/wrench_icon.png")));
    	volumeButton = new TextureRegion(new Texture(Gdx.files.internal("data/ic_volume_off.png")));
    	volumeButtonOn = new TextureRegion(new Texture(Gdx.files.internal("data/ic_volume_on.png")));
    	plusButton = new TextureRegion(new Texture(Gdx.files.internal("data/ic_plus.png")));
    }

    public static void dispose() {

    }
}
