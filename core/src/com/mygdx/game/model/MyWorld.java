package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.controller.MyContactListener;

public class MyWorld{



	World world;
	public PlayerPlatform groundedPlatform = null;
	public static float CAMERA_WIDTH = 12f;
	public static  float CAMERA_HEIGHT = 8f;
	
	public int width;
	public int height;

	public World getWorld(){
		return world;
	}
	public MyWorld(){
		width = 30;
		height = 8;
		world = new World(new Vector2(0, -20), true);
		world.setContactListener(new MyContactListener(world));
		createWorld();
	}
	
	public void setPP(float x, float y){
	}
	
	private void createWorld(){
		
		BodyDef def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		Body boxP = world.createBody(def);


		for(int i=0;i<width; ++i){
			Body boxGround = createBox(BodyDef.BodyType.StaticBody, 0.5F, 0.5F, 2);
			boxGround.setTransform(i,0,0);
			boxGround.getFixtureList().get(0).setUserData("bd");
			boxGround = createBox(BodyDef.BodyType.StaticBody, 0.5F, 0.5F, 0);
			boxGround.setTransform(i,height-1,0);
			boxGround.getFixtureList().get(0).setUserData("b");
		}
	}
	
	private Body createBox(BodyDef.BodyType type, float width, float height, float density) {
		BodyDef def = new BodyDef();
		def.type = type;
		Body box = world.createBody(def);
 
		PolygonShape poly = new PolygonShape();
		poly.setAsBox(width, height);
		
		box.createFixture(poly, density);
		poly.dispose();
 
		return box;
	}

	public void dispose(){
		world.dispose();
	}
}
