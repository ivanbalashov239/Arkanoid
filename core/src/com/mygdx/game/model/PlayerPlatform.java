package com.mygdx.game.model;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class PlayerPlatform {
	Body platform;
	Vector2 pos = new Vector2();
	Vector2 dir = new Vector2();
	float dist = 0;
	float maxDist = 0;		
	public float width;
	public float height;
	public PlayerPlatform(World world, float x, float y, float width, float height, float dx, float dy, float maxDist) {
		platform = createBox(world, BodyDef.BodyType.KinematicBody, width, height, 1);
		pos.x = x;
		pos.y = y;
		dir.x = dx;
		dir.y = dy;
		this.width = width*2;
		this.height = height*2;
		this.maxDist = maxDist;
		platform.setTransform(pos, 0);
		platform.getFixtureList().get(0).setUserData("p");
		platform.setUserData(this);
	}

	public Body getBody(){
		return platform;
	}
	public void update(float deltaTime) {
		dist += dir.len() * deltaTime;
		if(dist > maxDist) {
			dir.scl(-1);
			dist = 0;
		}

		platform.setLinearVelocity(dir);		 
		
	}
	
	private Body createBox(World world,BodyDef.BodyType type, float width, float height, float density) {
		BodyDef def = new BodyDef();
		def.type = type;
		Body box = world.createBody(def);
 
		PolygonShape poly = new PolygonShape();
		poly.setAsBox(width, height);
		box.createFixture(poly, density);
		poly.dispose();
 
		return box;
	}
}
