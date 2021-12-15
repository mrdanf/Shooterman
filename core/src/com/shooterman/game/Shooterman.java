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
import entities.objects.destructable.DestructableBox;
import entities.objects.ground.Ammunition;
import entities.objects.ground.HealthOrb;
import entities.objects.weapons.Assaultrifle;
import entities.objects.weapons.Shotgun;
import entities.objects.weapons.Sniperrifle;
import entities.objects.weapons.Weapon;
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
    KolisionCheck kolisionCheck = new KolisionCheck();
    ArrayList<Box> boxes = new ArrayList<>();
    ArrayList<DestructableBox> paletten = new ArrayList<>();
    ArrayList<Weapon> weapons = new ArrayList<>();
    ArrayList<Ammunition> ammunitions = new ArrayList<>();
    ArrayList<HealthOrb> healthOrbs = new ArrayList<>();

    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(1, h / w);
        batch = new SpriteBatch();
        map = new Texture("Spielfeld.png");
        sprite = new Sprite(TextureRegion.split(map, map.getWidth(), map.getHeight())[0][0]);
        player1 = new Player(100, 1, 100f, 800f, new Texture("player1WalkAnimation.png"));
        player2 = new Player(100, 2, 800f, 100f, new Texture("player2WalkAnimation.png"));
        camera.position.x = sprite.getX() + sprite.getOriginX();
        camera.position.y = sprite.getY() + sprite.getOriginY();
        camera.zoom = 1000f; // Je größer der Zoom, desto weiterweg ist die Kamera
        players.add(player1);
        players.add(player2);
        for (int i = 0; i <3; i++) {
            boxes.add(new Box());
        }
        for (int i = 0; i < 6; i++) {
            paletten.add(new DestructableBox(i));
        }
        for (Box box : boxes) {
            box.randomposition(boxes);
        }
        for (DestructableBox palette : paletten) {
            palette.randomposition(boxes,paletten);
        }

        // Create weapons on start
        weapons.add(new Assaultrifle());
        weapons.add(new Shotgun());
        weapons.add(new Sniperrifle());
        ammunitions.add(new Ammunition());
        healthOrbs.add(new HealthOrb());

        for (Weapon weapon : weapons) {
            weapon.randomposition(boxes, paletten, weapons);
        }
        for (Ammunition ammunition : ammunitions) {
            ammunition.randomposition(boxes, paletten,weapons,ammunitions);
        }
        for (HealthOrb healthOrb : healthOrbs) {
            healthOrb.randomposition(boxes, paletten,weapons,ammunitions,healthOrbs);
        }


        for (Player player : players) {
            player.setPlayers(players);
            player.setBoxes(boxes);
            player.setPaletten(paletten);
            player.setWeapons(weapons);
            player.setAmmoBoxes(ammunitions);
            player.setHealthBoxes(healthOrbs);
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
            status.getAmmoLabel().draw(batch,1 );
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
        for (DestructableBox palette : paletten) {
            Sprite sprite = palette.getSprite();
            sprite.setX(palette.getX());
            sprite.setY(palette.getY());
            sprite.draw(batch);}

        for (Weapon weapon : weapons) {
            Sprite sprite = weapon.getSprite();
            sprite.setX(weapon.getX());
            sprite.setY(weapon.getY());
            sprite.draw(batch);
        }
        for (Ammunition ammunition : ammunitions) {
            Sprite sprite = ammunition.getSprite();
            sprite.setX(ammunition.getX());
            sprite.setY(ammunition.getY());
            sprite.draw(batch);
        }
        for (HealthOrb healthOrb : healthOrbs) {
            Sprite sprite = healthOrb.getSprite();
            sprite.setX(healthOrb.getX());
            sprite.setY(healthOrb.getY());
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
            }
            if (delete != null) {
                player.getProjektileArrayList().remove(delete);
            }
            delete = kolisionCheck.hitCheck(players);
            if (delete != null) {
                player.getProjektileArrayList().remove(delete);
            }
            delete = kolisionCheck.hitCheck(boxes, players);
            if (delete != null) {
                player.getProjektileArrayList().remove(delete);
            }
            delete = kolisionCheck.hitCheckpalette(paletten, players);
            if (delete != null) {
                player.getProjektileArrayList().remove(delete);
            }

        }
        DestructableBox deletpalette=null;
        for (DestructableBox palette:paletten) {
            if (palette.getHealth()<=0){
                deletpalette=palette;
            }
        }
        if (deletpalette != null) {
            paletten.remove(deletpalette);}

        Weapon deleteWeapon = null;
        for (Weapon weapon : weapons) {
            if (weapon.isOnGround() == false) {
                deleteWeapon = weapon;
            }
        }

        weapons.remove(deleteWeapon);

    }

    @Override
    public void dispose() {
        batch.dispose();
        map.dispose();
    }
}
