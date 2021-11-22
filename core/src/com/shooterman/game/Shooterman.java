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
import entities.objects.destructable.Box;
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
    Box box1;
    Box box2;
    Box box3;
    Box box4;
    Box box5;
    Box box6;
    ArrayList<Player> players = new ArrayList<>();
    KolisionCheck kolisionCheck = new KolisionCheck();
    ArrayList<Box> boxes = new ArrayList<>();

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
        box1 = new Box();
        box2 = new Box();
        box3 = new Box();
        box4 = new Box();
        box5 = new Box();
        box6 = new Box();
        camera.position.x = sprite.getX() + sprite.getOriginX();
        camera.position.y = sprite.getY() + sprite.getOriginY();
        camera.zoom = 1000f; // Je größer der Zoom, desto weiterweg ist die Kamera
        players.add(player1);
        players.add(player2);
        boxes.add(box1);
        boxes.add(box2);
        boxes.add(box3);
        boxes.add(box4);
        boxes.add(box5);
        boxes.add(box6);
        for (Box box : boxes) {
            box.randomposition(boxes);
        }
        for (Player player : players) {
            player.setPlayers(players);
            player.setBoxes(boxes);
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
        for (Box box : boxes) {
            Sprite sprite = box.getSprite();
            sprite.setX(box.getX());
            sprite.setY(box.getY());
            sprite.draw(batch);
        }
        batch.end();
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
                Player playerhit = kolisionCheck.hitCheck(players, projektile);
                if (playerhit != null) {
                    delete = projektile;
                    playerhit.decreaseHeath(10); // TODO Waffenschaden einfügen, anstatt 10
                }
            }
            if (delete != null) {
                player.getProjektileArrayList().remove(delete);
            }
            delete = kolisionCheck.hitCheck(players);
            if (delete != null) {
                player.getProjektileArrayList().remove(delete);
            }
            delete = kolisionCheck.hitCheck(boxes,players);
            if (delete != null) {
                player.getProjektileArrayList().remove(delete);
            }
        }
    }

/*
* Player playerhit = kolisionCheck.hitCheck(this.players , projektile);
                if (playerhit != null) {
                    player.getProjektileArrayList().remove(projektile);
                    playerhit.decreaseHeath(10); // TODO Waffenschaden einfügen, anstatt 10
                }
* */
    @Override
    public void dispose() {
        batch.dispose();
        map.dispose();
    }
}
