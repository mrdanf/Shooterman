package com.shooterman.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.objects.destructable.Box;
import entities.objects.destructable.DestructibleBox;
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
    ArrayList<DestructibleBox> paletten = new ArrayList<>();
    ArrayList<Weapon> weapons = new ArrayList<>();
    ArrayList<Ammunition> ammunition = new ArrayList<>();
    ArrayList<HealthOrb> healthOrbs = new ArrayList<>();
    KolisionCheck kolisionCheck = new KolisionCheck();

    // TODO TEST
    Sprite player1Position;
    Sprite player2Position;
    // TODO TEST END

    public Game() {
        player1 = new Player(100, 1, 100f, 800f, new Texture("player1WalkAnimation.png"));
        player1.setScale(0.5f);
        player2 = new Player(100, 2, 800f, 100f, new Texture("player2WalkAnimation.png"));
        player2.setScale(0.5f);
        players.add(player1);
        players.add(player2);

        for (int i = 0; i <3; i++) {
            boxes.add(new Box());
        }
        for (int i = 0; i < 6; i++) {
            String texturePath;
            if (i % 2 == 0) {
                texturePath = "palette.png";
                paletten.add(new DestructibleBox(new Texture(texturePath)));
                paletten.get(i).setScale(2f);
            } else {
                texturePath = "Palettemitkartons.png";
                paletten.add(new DestructibleBox(new Texture(texturePath)));
                paletten.get(i).setScale(1f);
            }

            // TODO: Besser ist es beide Bilddateien gleich groß zu skalieren und nur noch eine Methode aufzurufen: paletten.add(new DestructibleBox(new Texture(texturePath)));
        }
        for (Box box : boxes) {
            box.randomPosition(boxes);
        }
        for (DestructibleBox palette : paletten) {
            palette.randomPosition(boxes,paletten);
        }

        // Create weapons on start
        weapons.add(new Assaultrifle());
        weapons.add(new Shotgun());
        weapons.add(new Sniperrifle());
        ammunition.add(new Ammunition());
        healthOrbs.add(new HealthOrb());

        for (Weapon weapon : weapons) {
            weapon.randomPosition(boxes, paletten, weapons);
        }
        for (Ammunition ammunition : ammunition) {
            ammunition.randomPosition(boxes, paletten,weapons, this.ammunition);
        }
        for (HealthOrb healthOrb : healthOrbs) {
            healthOrb.randomPosition(boxes, paletten,weapons, ammunition,healthOrbs);
        }

        for (Player player : players) {
            player.setPlayers(players);
            player.setBoxes(boxes);
            player.setPaletten(paletten);
            player.setWeapons(weapons);
            player.setAmmoBoxes(ammunition);
            player.setHealthBoxes(healthOrbs);
        }

        player1Position = new Sprite(new Texture("roter_Punkt.png"));
        player2Position = new Sprite(new Texture("roter_Punkt.png"));
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
        DestructibleBox deletpalette = null;
        for (DestructibleBox palette:paletten) {
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

    public ArrayList<DestructibleBox> getPaletten() {
        return paletten;
    }

    public ArrayList<HealthOrb> getHealthOrbs() {
        return healthOrbs;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public ArrayList<Ammunition> getAmmunition() {
        return ammunition;
    }
}
