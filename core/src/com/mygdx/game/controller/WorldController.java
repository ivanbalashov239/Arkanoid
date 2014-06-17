package com.mygdx.game.controller;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.model.MyWorld;
import com.mygdx.game.model.PlayerPlatform;

import java.util.HashMap;
import java.util.Map;




public class WorldController {   
	
	
	private MyWorld world;
	
	  enum Keys {
	    LEFT, RIGHT, UP, DOWN
	  }
	  static Map<Keys, Boolean> keys = new HashMap<Keys, Boolean>();

	  static {
	    keys.put(Keys.LEFT, false);
	    keys.put(Keys.RIGHT, false);
	    keys.put(Keys.UP, false);
	    keys.put(Keys.DOWN, false);
	  };
	  
	public WorldController(MyWorld world/*, WorldRenderer renderer*/) {
		this.world = world;
		

	}
	  public void leftPressed() {
	
	    keys.get(keys.put(Keys.LEFT, true));
	  }

	  public void rightPressed() {
	    keys.get(keys.put(Keys.RIGHT, true));
	  }
	  
	  public void leftReleased() {
	    keys.get(keys.put(Keys.LEFT, false));
	  }	
	  public void rightReleased() {
	    keys.get(keys.put(Keys.RIGHT, false));
	  }
	  public void upPressed() {
	    keys.get(keys.put(Keys.UP, true));
	  }
	  
	  public void upReleased() {
		    keys.get(keys.put(Keys.UP, false));
		  }
	  
	  public void resetWay(){
		    rightReleased();
		    leftReleased();
		    upReleased();
		  }

	public void dispose(){

		//world.dispose();
	}
	
	
	
	
	public void update(float delta) {
		Array<PlayerPlatform> platforms = world.getPlatforms();
		for(int i = 0; i < platforms.size; i++) {
			PlayerPlatform platform = platforms.get(i);
			platform.update(Math.max(1/60.0f, delta));
		}

	
	}

	
	public static boolean grounded ;

}
