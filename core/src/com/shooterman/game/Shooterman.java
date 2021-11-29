package com.shooterman.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import entities.player.Player;
import hud.Status;
import entities.projektile.Projektile;
import funktions.KolisionCheck;

import java.awt.*;
import java.util.ArrayList;


public class Shooterman extends ApplicationAdapter {
    SpriteBatch batch;
    Texture map;
    OrthographicCamera camera;
    Sprite sprite;
    Player player1;
    Player player2;
    ArrayList<Player> players = new ArrayList<>();
    KolisionCheck kolisionCheck=new KolisionCheck();

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
        for (Player player:players) {
           player.setPlayers(players);
        }

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

            Status status = player.getStatus();
            status.getPlayerLabel().draw(batch,1 );
            batch.draw(status.getHealthBar()[0], status.getxPostion(), status.getyPosition()-35);
            batch.draw(status.getHealthBar()[1], status.getxPostion(), status.getyPosition()-35);
        }
        for (Player player : players) {
            for (Projektile projektile : player.getProjektileArrayList()) {
                Sprite sprite = projektile.getSprite();
                sprite.setX(projektile.getX());
                sprite.setY(projektile.getY());
                sprite.draw(batch);
            }
        }
        batch.end();
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {

            System.out.println("X:" + player1.getX() + " Y:" + player1.getY());
        }
    }

    private void updateAll() {
        Projektile delete = null;
        camera.update();
        for (Player player : players) {
            player.update();
        }
        for (Player player : players) {
            for (Projektile projektile : player.getProjektileArrayList()) {
                projektile.update();
                if (projektile.isDeleteble()) {
                   delete = projektile;
                }
            }
            if (delete != null) {
                player.getProjektileArrayList().remove(delete);
            }
            delete=kolisionCheck.hitCheck(players);
            if (delete != null) {
                player.getProjektileArrayList().remove(delete);
            }
        }
    }


    @Override
    public void dispose() {
        batch.dispose();
        map.dispose();
    }
}
