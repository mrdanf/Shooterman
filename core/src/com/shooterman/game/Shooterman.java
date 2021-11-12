package com.shooterman.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class Shooterman extends ApplicationAdapter {
	SpriteBatch batch;
	Texture map;
	OrthographicCamera camera;
	Sprite sprite;
	Player player1;

	@Override
	public void create () {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		map = new Texture("Spielfeld.png");
		sprite = new Sprite(TextureRegion.split(map, map.getWidth(), map.getHeight())[0][0]);
		player1 = new Player();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.1f, 0.35f, 0.1f, 1);
		camera.position.x = sprite.getX() + sprite.getOriginX();
		camera.position.y = sprite.getY() + sprite.getOriginY();
		camera.zoom = 1200f; // Je größer der Zoom, desto weiterweg ist die Kamera
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(map, 0, 0);
		player1.getSprite().draw(batch);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		map.dispose();
	}

}
