package com.shooterman.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entity.VisualEntity;
import entity.object.ground.Item;
import entity.object.obstacle.Box;
import entity.object.obstacle.DestructibleBox;
import entity.object.ground.Ammunition;
import entity.object.ground.HealthOrb;
import entity.object.weapon.Assaultrifle;
import entity.object.weapon.Shotgun;
import entity.object.weapon.Sniperrifle;
import entity.object.weapon.Weapon;
import entity.player.Player;
import entity.projectile.Projectile;
import function.CollisionCheck;

import java.util.ArrayList;

public class Game {
    Player player1; // TODO: jetzt nicht private wegen debugging
    Player player2;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Box> boxes = new ArrayList<>();
    private ArrayList<DestructibleBox> destructibleBoxes = new ArrayList<>();
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private CollisionCheck collisionCheck = new CollisionCheck();

    // TODO TEST
    Sprite player1Position;
    Sprite player2Position;
    // TODO TEST END

    /**
     *Initialiesiert alle Objecte und Fügt sie der entsprechenden arraylist hinzu um sie dann
     * später ordentlich aufrufen zu können und abfragen zu können
     */
    public Game() {
        ArrayList<Texture> texturesP1 = new ArrayList<>();
        texturesP1.add(new Texture("spieler/Spieler1PistoleLaufenNeu.png"));
        texturesP1.add(new Texture("spieler/Spieler1AKLaufenNeu.png"));
        texturesP1.add(new Texture("spieler/Spieler1AWPLaufenNeu.png"));
        texturesP1.add(new Texture("spieler/Spieler1PumpgunLaufenNeu.png"));
        texturesP1.add(new Texture("spieler/Spieler1Tot.png"));
        player1 = new Player(100, 1, 150f, 800f, new Texture("spieler/Spieler1PistoleLaufenNeu.png"), texturesP1);

        ArrayList<Texture> texturesP2 = new ArrayList<>();
        texturesP2.add(new Texture("spieler/Spieler2PistoleLaufenNeu.png"));
        texturesP2.add(new Texture("spieler/Spieler2AKLaufenNeu.png"));
        texturesP2.add(new Texture("spieler/Spieler2AWPLaufenNeu.png"));
        texturesP2.add(new Texture("spieler/Spieler2PumpgunLaufenNeu.png"));
        texturesP2.add(new Texture("spieler/Spieler2Tot.png"));
        player2 = new Player(100, 2, 800f, 150f, new Texture("spieler/Spieler2PistoleLaufenNeu.png"), texturesP2);
        players.add(player1);
        players.add(player2);

        for (int i = 0; i < 3; i++) {
            boxes.add(new Box());
        }
        for (int i = 0; i < 6; i++) {
            String texturePath;
            float height;
            if (i % 2 == 0) {
                texturePath = "hindernis/palette.png";
                height = 60;
            } else {
                texturePath = "hindernis/Palettemitkartons.png";
                height = 80;
            }
            destructibleBoxes.add(new DestructibleBox(new Texture(texturePath), height));
        }
        for (Box box : boxes) {
            box.randomPosition(boxes);
        }
        for (DestructibleBox destructibleBox : destructibleBoxes) {
            destructibleBox.randomPosition(boxes, destructibleBoxes);
        }

        // Create weapons on start
        weapons.add(new Assaultrifle());
        weapons.add(new Shotgun());
        weapons.add(new Sniperrifle());
        items.add(new Ammunition());
        items.add(new HealthOrb());

        for (Weapon weapon : weapons) {
            weapon.randomPosition(boxes, destructibleBoxes, weapons);
        }
        for (Item item : items) {
            item.randomPosition(boxes, destructibleBoxes, weapons, items);
        }

        for (Player player : players) {
            player.setPlayers(players);
            player.setBoxes(boxes);
            player.setDestructibleBoxes(destructibleBoxes);
            player.setWeapons(weapons);
            player.setItems(items);
        }

        player1Position = new Sprite(new Texture("roter_Punkt.png"));
        player2Position = new Sprite(new Texture("roter_Punkt.png"));
    }

    /**
     * Fragt jeden Frame ab ob es eine änderung gibt für die aktieven objekte auf der karte
     */
    public void update() {
        for (Player player : players) {
            player.update();
        }

        Projectile deleteProjectile = null;
        for (Player player : players) {
            for (Projectile projectile : player.getProjectiles()) {
                projectile.update();
                if (projectile.isDeletable()) {
                    deleteProjectile = projectile;
                }
            }
            if (deleteProjectile != null) {
                player.getProjectiles().remove(deleteProjectile);
            }
            deleteProjectile = collisionCheck.projectileHitsPlayer(players);
            if (deleteProjectile != null) {
                player.getProjectiles().remove(deleteProjectile);
            }
            deleteProjectile = collisionCheck.projectileHitsObstacle((ArrayList< VisualEntity>) (ArrayList<?>) boxes,
                    players);
            if (deleteProjectile != null) {
                player.getProjectiles().remove(deleteProjectile);
            }
            deleteProjectile =
                    collisionCheck.projectileHitsObstacle((ArrayList< VisualEntity>) (ArrayList<?>) destructibleBoxes, players);
            if (deleteProjectile != null) {
                player.getProjectiles().remove(deleteProjectile);
            }

        }

        DestructibleBox deleteDestructibleBox = null;
        for (DestructibleBox destructibleBox : destructibleBoxes) {
            if (destructibleBox.getHealth() <= 0) {
                deleteDestructibleBox = destructibleBox;
            }
        }
        if (deleteDestructibleBox != null) {
            destructibleBoxes.remove(deleteDestructibleBox);
        }

        Weapon deleteWeapon = null;
        for (Weapon weapon : weapons) {
            if (!weapon.isOnGround()) {
                deleteWeapon = weapon;
            }
        }

        weapons.remove(deleteWeapon);

        Item deleteItem = null;
        for (Item item : items) {
            if (!item.isOnGround()) {
                deleteItem = item;
            }
        }

        items.remove(deleteItem);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public ArrayList<DestructibleBox> getDestructibleBoxes() {
        return destructibleBoxes;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

}
