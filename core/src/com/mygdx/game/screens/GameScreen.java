package com.mygdx.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.controller.WorldController;
import com.mygdx.game.model.MyWorld;
import com.mygdx.game.view.WorldRenderer;

import java.util.HashMap;
import java.util.Map;

public class GameScreen implements Screen, InputProcessor {

	
	private WorldRenderer renderer;
	public MyWorld world;
	private SpriteBatch spriteBatch;
	Texture texture;
	public Map<String, TextureRegion> textureRegions = new HashMap<String, TextureRegion>();
	
	public int width;
	public int height;
	

	private WorldController controller;

    @Override
	public void show() {
		
		MyWorld.CAMERA_WIDTH =  MyWorld.CAMERA_HEIGHT* Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
		//this.cam = new OrthographicCamera(MyWorld.CAMERA_WIDTH, MyWorld.CAMERA_HEIGHT);
		//SetCamera(MyWorld.CAMERA_WIDTH / 2f, MyWorld.CAMERA_HEIGHT / 2f);
		spriteBatch = new SpriteBatch();
		loadTextures();
		world = new MyWorld();
	
		renderer = new WorldRenderer(world, MyWorld.CAMERA_WIDTH, MyWorld.CAMERA_HEIGHT,true);
		controller = new WorldController(world/*, renderer*/);
		Gdx.input.setInputProcessor(this);
		 
		
		
	}
	
	private void loadTextures() {
		
	}
	
	

	
	public boolean touchMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean mouseMoved(int x, int y) {
		return false;
	}
	
	@Override
	public boolean keyTyped(char character) {
		return false; 
	}
	
	/*public void SetCamera(float x, float y){
		this.cam.position.set(x, y,0);	
		this.cam.update();
	}*/
	
	@Override
	public void resize(int width, int height) {
		
		this.width = width;
		this.height = height;
		
		
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {		
		renderer.dispose();
		controller.dispose();
		Gdx.input.setInputProcessor(null);
	}


	@Override
	public boolean keyDown(int keycode) {
		
		return true;
	}
 
	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//world.update(delta);
		//world.draw();
		
		controller.update(delta);
		renderer.render(delta);
	}
	@Override
	public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.LEFT){

        }
        if (keycode == Input.Keys.DOWN){

        }

		
		return true;
	}
	
	private void ChangeNavigation(int x, int y){
		controller.resetWay();

			
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {

		if (!Gdx.app.getType().equals(Application.ApplicationType.Android))
			return false;
		ChangeNavigation(x,y);
		return true;
	} 
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (!Gdx.app.getType().equals(Application.ApplicationType.Android))
			return false;
	
		controller.resetWay();
		return true;
	}
	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		ChangeNavigation(x,y);
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
