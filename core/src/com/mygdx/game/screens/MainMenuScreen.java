package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class MainMenuScreen implements Screen {
	Game game;

	OrthographicCamera guiCam;
	SpriteBatch batcher;
	Rectangle playBounds;
	Vector3 touchPoint;

	public MainMenuScreen (Game game) {
		this.game = game;

		guiCam = new OrthographicCamera(320, 480);
		guiCam.position.set(320 / 2, 480 / 2, 0);
		batcher = new SpriteBatch();
		playBounds = new Rectangle(160 - 150, 200 + 18, 300, 36);
		touchPoint = new Vector3();
	}

	public void update () {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (playBounds.contains(touchPoint.x, touchPoint.y)) {
				game.setScreen(new GameScreen());
				return;
			}
		}
	}

	long last = TimeUtils.nanoTime();

	public void draw () {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);

		batcher.disableBlending();
		batcher.begin();
		batcher.end();

		batcher.enableBlending();
		batcher.begin();
		batcher.end();

		if (TimeUtils.nanoTime() - last > 2000000000) {
			Gdx.app.log("Arkanoid",
				"version: " + Gdx.app.getVersion() + ", memory: " + Gdx.app.getJavaHeap() + ", " + Gdx.app.getNativeHeap()
					+ ", native orientation:" + Gdx.input.getNativeOrientation() + ", orientation: " + Gdx.input.getRotation()
					+ ", accel: " + (int)Gdx.input.getAccelerometerX() + ", " + (int)Gdx.input.getAccelerometerY() + ", "
					+ (int)Gdx.input.getAccelerometerZ() + ", apr: " + (int)Gdx.input.getAzimuth() + ", " + (int)Gdx.input.getPitch()
					+ ", " + (int)Gdx.input.getRoll());
			last = TimeUtils.nanoTime();
		}
	}

	@Override
	public void render (float delta) {
		update();
		draw();
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void show () {
	}

	@Override
	public void hide () {
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}
}
