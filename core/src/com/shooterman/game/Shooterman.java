package com.shooterman.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import entities.Entity;
import entities.player.Player;

import java.util.ArrayList;


public class Shooterman extends ApplicationAdapter {
    SpriteBatch batch;
    Texture map;
    OrthographicCamera camera;
    Sprite sprite;
    Player player1;
    Player player2;
    ArrayList<Player> players = new ArrayList<>();

    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(1, h / w);
        batch = new SpriteBatch();
        map = new Texture("Spielfeld.png");
        sprite = new Sprite(TextureRegion.split(map, map.getWidth(), map.getHeight())[0][0]);
        player1 = new Player(100, 1, 100f, 800f);
        player2 = new Player(100, 2, 800f, 100f);
        camera.position.x = sprite.getX() + sprite.getOriginX();
        camera.position.y = sprite.getY() + sprite.getOriginY();
        camera.zoom = 1000f; // Je größer der Zoom, desto weiterweg ist die Kamera
        players.add(player1);
        players.add(player2);

    }

    @Override
    public void render() {
        updateAll();

        batch();
    }

    private void batch() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(map, 0, 0);
        for (Player player : players) {
            Sprite sprite = player.getSprite();
            sprite.setX(player.getX());
            sprite.setY(player.getY());
            sprite.draw(batch);
        }

        batch.end();
    }

    private void updateAll() {
        camera.update();
        for (Player player : players) {
            player.update();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        map.dispose();
    }


}
