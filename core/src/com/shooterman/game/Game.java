package com.shooterman.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    private ArrayList<Ammunition> ammunitions = new ArrayList<>();
    private ArrayList<HealthOrb> healthOrbs = new ArrayList<>();
    private CollisionCheck collisionCheck = new CollisionCheck();

    // TODO TEST
    Sprite player1Position;
    Sprite player2Position;
    // TODO TEST END

    public Game() {
        player1 = new Player(100, 1, 100f, 800f, new Texture("spieler/Spieler1PistoleLaufenNeu.png"));
        player2 = new Player(100, 2, 800f, 100f, new Texture("spieler/Spieler2PistoleLaufenNeu.png"));
        players.add(player1);
        players.add(player2);

        for (int i = 0; i < 3; i++) {
            boxes.add(new Box());
        }
        for (int i = 0; i < 6; i++) {
            String texturePath;
            if (i % 2 == 0) {
                texturePath = "hindernis/palette.png";
                destructibleBoxes.add(new DestructibleBox(new Texture(texturePath)));
                destructibleBoxes.get(i).setScale(2f);
            } else {
                texturePath = "hindernis/Palettemitkartons.png";
                destructibleBoxes.add(new DestructibleBox(new Texture(texturePath)));
                destructibleBoxes.get(i).setScale(1f);
            }

            // TODO: Besser ist es beide Bilddateien gleich groß zu skalieren und nur noch eine Methode aufzurufen: paletten.add(new DestructibleBox(new Texture(texturePath)));
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
        ammunitions.add(new Ammunition());
        healthOrbs.add(new HealthOrb());

        for (Weapon weapon : weapons) {
            weapon.randomPosition(boxes, destructibleBoxes, weapons);
        }
        for (Ammunition ammunition : ammunitions) {
            ammunition.randomPosition(boxes, destructibleBoxes, weapons, this.ammunitions);
        }
        for (HealthOrb healthOrb : healthOrbs) {
            healthOrb.randomPosition(boxes, destructibleBoxes, weapons, ammunitions, healthOrbs);
        }

        for (Player player : players) {
            player.setPlayers(players);
            player.setBoxes(boxes);
            player.setDestructibleBoxes(destructibleBoxes);
            player.setWeapons(weapons);
            player.setAmmoBoxes(ammunitions);
            player.setHealthBoxes(healthOrbs);
        }

        player1Position = new Sprite(new Texture("roter_Punkt.png"));
        player2Position = new Sprite(new Texture("roter_Punkt.png"));
    }

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
            deleteProjectile = collisionCheck.hitCheck(players);
            if (deleteProjectile != null) {
                player.getProjectiles().remove(deleteProjectile);
            }
            deleteProjectile = collisionCheck.hitCheck(boxes, players);
            if (deleteProjectile != null) {
                player.getProjectiles().remove(deleteProjectile);
            }
            deleteProjectile = collisionCheck.hitCheckDestructibleBox(destructibleBoxes, players);
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

    public ArrayList<HealthOrb> getHealthOrbs() {
        return healthOrbs;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public ArrayList<Ammunition> getAmmunitions() {
        return ammunitions;
    }
}
