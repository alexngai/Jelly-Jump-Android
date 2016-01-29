package com.alexngai.jellyjump.helpers;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.alexngai.jellyjump.gameobjects.Enemy;
import com.alexngai.jellyjump.gameobjects.EnemyPool;
import com.alexngai.jellyjump.gameobjects.Laser;
import com.alexngai.jellyjump.gameobjects.LaserPool;
import com.alexngai.jellyjump.gameobjects.Mote;
import com.alexngai.jellyjump.gameobjects.MoteGenerator;
import com.alexngai.jellyjump.gameobjects.MotePool;
import com.alexngai.jellyjump.gameobjects.PlayerCharacter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class CollisionDetector {
	
	public static float scale = .75f;
	
	public static Mote checkMoteCollision(PlayerCharacter c, MotePool motePool){
		for (Mote mote : motePool.getObjarray()){
			if (mote.isInUse()){
				if (c.getPosition().dst(mote.getPosition()) < (c.getSize().x/4+mote.getSize().x)){
					return mote;
				}
			}
		}
		return null;
	}

	public static void checkLaserCollision(LaserPool l, EnemyPool enemyPool, MotePool motePool, RayHandler handler){
		for (Laser laser : l.getObjarray()){
			for (Enemy enemy : enemyPool.getObjarray()){
				if (laser.isInUse() && enemy.isInUse() && overlap(laser, enemy)){
					laser.unuse();
					enemy.unuse();
					
					Color color = Color.YELLOW;
					Light light = new PointLight(handler, 100, color, 150, enemy.getPosition().x, enemy.getPosition().y);
					light.setSoft(false);
					
					motePool.addSpecialMote(new Vector2(enemy.getPosition().x, enemy.getPosition().y), 
							new Vector2(35,35), new Vector2(0,0), light);
				}
			}
		}
	}
	
	//compare collision between game character and all members of enemy list
	public static Enemy checkCollision(PlayerCharacter c, EnemyPool enemypool){
		for (Enemy enemy : enemypool.getObjarray()){
			if (enemy.isInUse() && overlap(c, enemy)){
				//if (collides(c, enemy)){
					return enemy;
				//}
			}
		}
		return null;
	}
	
	private static boolean overlap(PlayerCharacter c, Enemy e){
		//check for collisions between two objects

/*		int right1 = (int) (c.getPosition().x + scale*c.getSize().x/2);
		int left1 = (int) (c.getPosition().x - scale*c.getSize().x/2);
		int top1 = (int) (c.getPosition().y - scale*c.getSize().y/2);
		int bottom1 = (int) (c.getPosition().y + scale*c.getSize().y/2);
		
		int right2 = (int) (e.getPosition().x + scale*e.getSize().x/2);
		int left2 = (int) (e.getPosition().x - scale*e.getSize().x/2);
		int top2 = (int) (e.getPosition().y - scale*e.getSize().y/2);
		int bottom2 = (int) (e.getPosition().y + scale*e.getSize().y/2);
		
		Rectangle r1 = new Rectangle(left1, top1, right1, bottom1);
		Rectangle r2 = new Rectangle(left2, top2, right2, bottom2);*/
		
		//Rectangle(int x, int y, int width, int height)
		Vector2 size1 = new Vector2(c.getSize().x*scale, c.getSize().y*scale);
		int x1 = (int) (c.getPosition().x - size1.x/2);
		int y1 =(int) (c.getPosition().y - size1.y/2);
		int width1 = (int) size1.x;
		int height1 = (int) size1.y;
		
		Vector2 size2 = new Vector2(e.getSize().x*scale, e.getSize().y*scale);
		int x2 = (int) (e.getPosition().x - size2.x/2);
		int y2 =(int) (e.getPosition().y - size2.y/2);
		int width2 = (int) size2.x;
		int height2 = (int) size2.y;
		
		Rectangle r1 = new Rectangle(x1,y1,width1,height1);
		Rectangle r2 = new Rectangle(x2,y2,width2,height2);
		
		return r1.overlaps(r2);

        
	}
	
	private static boolean overlap(Laser c, Enemy e){
		//check for collisions between two objects

/*		int right1 = (int) (c.getPosition().x + scale*c.getSize().x/2);
		int left1 = (int) (c.getPosition().x - scale*c.getSize().x/2);
		int top1 = (int) (c.getPosition().y - scale*c.getSize().y/2);
		int bottom1 = (int) (c.getPosition().y + scale*c.getSize().y/2);
		
		int right2 = (int) (e.getPosition().x + scale*e.getSize().x/2);
		int left2 = (int) (e.getPosition().x - scale*e.getSize().x/2);
		int top2 = (int) (e.getPosition().y - scale*e.getSize().y/2);
		int bottom2 = (int) (e.getPosition().y + scale*e.getSize().y/2);
		
		Rectangle r1 = new Rectangle(left1, top1, right1, bottom1);
		Rectangle r2 = new Rectangle(left2, top2, right2, bottom2);*/
		
		//Rectangle(int x, int y, int width, int height)
		Vector2 size1 = new Vector2(10,10);
		int x1 = (int) (c.getPosition().x - size1.x/2);
		int y1 =(int) (c.getPosition().y - size1.y/2);
		int width1 = (int) size1.x;
		int height1 = (int) size1.y;
		
		Vector2 size2 = new Vector2(e.getSize().x*scale, e.getSize().y*scale);
		int x2 = (int) (e.getPosition().x - size2.x/2);
		int y2 =(int) (e.getPosition().y - size2.y/2);
		int width2 = (int) size2.x;
		int height2 = (int) size2.y;
		
		Rectangle r1 = new Rectangle(x1,y1,width1,height1);
		Rectangle r2 = new Rectangle(x2,y2,width2,height2);
		
		return r1.overlaps(r2);  
	}
	
	//compare collision between two objects
	/*private static boolean collides(PlayerCharacter c, Enemy e){
		//check for collisions between two objects
		int x1 = (int) (c.getPosition().x - c.getSize().x/2) ; int y1 = (int) (c.getPosition().y + c.getSize().y/2) ;
		//scaled bitmap
		
		Image b1 = new Image(new TextureRegionDrawable(c.getTexture()));
		
		//Bitmap b1 = c.getCurImage().scaleBitmap(c.getScale());
		
		int x2 = (int) (e.getPosition().x - e.getSize().x/2) ; int y2 = (int) (e.getPosition().y + e.getSize().y/2) ;
		//Bitmap b2 = e.getCurImage().scaleBitmap(e.getScale());
		Image b2 = new Image(new TextureRegionDrawable(e.getTexture()));
		//Log.d("Collision", "b1:" + b1.getWidth() + ", width:" + c.getIm_width());
		

		  double width1 = x1 + c.getSize().x -1,
		         height1 = y1 + c.getIm_height() -1,
		         width2 = x2 + e.getIm_width() -1,
		         height2 = y2 + e.getIm_height() -1;
		  
		  //find intersecting box
		  int xstart = (int) Math.max(x1, x2),
		      ystart = (int) Math.max(y1, y2),
		      xend   = (int) Math.min(width1, width2),
		      yend   = (int) Math.min(height1, height2);

		  // intersection rect
		  int recty = Math.abs(yend - ystart);
		  int rectx = Math.abs(xend - xstart);

		  //get index of each bitmap
		  for (int y=1;y < recty-1;y++){
		    int ny1 = Math.abs(ystart - (int) y1) + y;
		    int ny2 = Math.abs(ystart - (int) y2) + y;

		    for (int x=1;x < rectx-1;x++) {
		      
		      int nx1 = Math.abs(xstart - (int) x1) + x;
		      int nx2 = Math.abs(xstart - (int) x2) + x;
		      try {
		    	  if (((b1.getPixel(nx1,ny1) & 0xFF000000) != 0x00) &&
		    			  ((b2.getPixel(nx2,ny2) & 0xFF000000) != 0x00)) {
		    		  // collision detected
		    		  return true;
		    	  }
		      } catch (Exception error){
		    	  //Log.d("Collision", "s1:"+nx1+","+ny1+","+b1.getWidth()+","+b1.getHeight());
		      }
		    }
		  }
		  return false;
	}*/

	
}
