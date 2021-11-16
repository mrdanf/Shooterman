package com.shooterman.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import entities.player.Player;
import entities.player.PlayerSprits;

public class Shooterman extends ApplicationAdapter {
    SpriteBatch batch;
    Texture map;
    OrthographicCamera camera;
    Sprite sprite;
    Player player1;
    Player player2;

    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(1, h / w);
        batch = new SpriteBatch();
        map = new Texture("Spielfeld.png");
        sprite = new Sprite(TextureRegion.split(map, map.getWidth(), map.getHeight())[0][0]);
        player1 = new Player(100, 1, 100f, 850f);
        player2 = new Player(100, 2, 850f, 100f);
        camera.position.x = sprite.getX() + sprite.getOriginX();
        camera.position.y = sprite.getY() + sprite.getOriginY();
        camera.zoom = 1000f; // Je größer der Zoom, desto weiterweg ist die Kamera
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.1f, 0.35f, 0.1f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(map, 0, 0);
        batch.draw(player1.getSprite(), player1.getX(), player1.getY(), player1.getSprite().getWidth() / 4, player1.getSprite().getHeight() / 4);
        batch.draw(player2.getSprite(), player2.getX(), player2.getY(), player2.getSprite().getWidth() / 4, player2.getSprite().getHeight() / 4);
        batch.end();
        player1.update();
        player2.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
        map.dispose();
    }


}
