package com.shooterman.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    entities.Player player2;

	@Override
	public void create () {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		map = new Texture("Spielfeld.png");
		sprite = new Sprite(TextureRegion.split(map, map.getWidth(), map.getHeight())[0][0]);
		player1 = new Player();
       player2 = new entities.Player(100);
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
		Sprite p1 = player1.getSprite();
		batch.draw(p1, 100, 100, p1.getWidth()/4, p1.getHeight()/4);
        batch.draw(p1, player2.getX(), player2.getY(), p1.getWidth()/4, p1.getHeight()/4);
		batch.end();
        player2.update();

	}

	@Override
	public void dispose () {
		batch.dispose();
		map.dispose();
	}


}
