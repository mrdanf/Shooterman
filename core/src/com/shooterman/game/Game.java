package com.shooterman.game;


import entities.objects.destructable.Box;
import entities.objects.destructable.DestructableBox;
import entities.objects.ground.Ammunition;
import entities.objects.ground.HealthOrb;
import entities.objects.weapons.Assaultrifle;
import entities.objects.weapons.Shotgun;
import entities.objects.weapons.Sniperrifle;
import entities.objects.weapons.Weapon;
import entities.player.Player;
import entities.projektile.Projektile;
import funktions.KolisionCheck;

import java.util.ArrayList;

public class Game {
    Player player1;
    Player player2;
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Box> boxes = new ArrayList<>();
    ArrayList<DestructableBox> paletten = new ArrayList<>();
    ArrayList<Weapon> weapons = new ArrayList<>();
    ArrayList<Ammunition> ammunitions = new ArrayList<>();
    ArrayList<HealthOrb> healthOrbs = new ArrayList<>();
    KolisionCheck kolisionCheck = new KolisionCheck();

    public Game() {
        player1 = new Player(100, 1, 100f, 800f);
        player2 = new Player(100, 2, 800f, 100f);
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

    public void update() {
        Projektile delete = null;
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
        DestructableBox deletpalette = null;
        for (DestructableBox palette:paletten) {
            if (palette.getHealth()<=0){
                deletpalette=palette;
            }
        }
        if (deletpalette != null) {
            paletten.remove(deletpalette);}

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

    public ArrayList<DestructableBox> getPaletten() {
        return paletten;
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
