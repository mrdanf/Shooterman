package entities.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import entities.AnimatingEntity;
import entities.objects.destructable.Box;
import entities.objects.ground.Ammunition;
import entities.objects.ground.HealthOrb;
import hud.Status;
import entities.objects.destructable.DestructibleBox;
import entities.objects.weapons.Pistol;
import entities.objects.weapons.Weapon;
import entities.projektile.Projektile;

import java.util.ArrayList;


public class Player extends AnimatingEntity {
    PlayerMovement move = new PlayerMovement();
    private int health;
    boolean alive;
    private float movement = 2f;
    private int playerNumber;
    private ArrayList<Integer> playerInput = new ArrayList();
    private Status status;
    private int viewDirection = 0;
    private ArrayList<Projektile> projektileArrayList = new ArrayList<>();
    private ArrayList<Player> players;
    private ArrayList<Box> boxes = new ArrayList<>();
    private ArrayList<DestructibleBox> paletten = new ArrayList<>();
    private ArrayList<Weapon> weapons;
    private ArrayList<Ammunition> ammunitions;
    private ArrayList<HealthOrb> healthOrbs;

    private Weapon pistol = new Pistol();
    private Weapon weapon2;
    private Weapon activeWeapon;


    public Player(int health, int playerNumber, float startX, float startY, Texture texture) {
        super(startX, startY, texture, 6, 1);
        this.health = health;
        this.alive = true;
        this.playerNumber = playerNumber;
        this.status = new Status(playerNumber);
        setX(startX);
        setY(startY);
        input();
        this.activeWeapon= this.pistol;
    }

    /**
     * Weist  dem Player die richtigen tasten zu um das spiel spielen zu können
     * 0.add Nach oben bewegen
     * 1.add Nach links bewegen
     * 2.add Nach unten bewegen
     * 3.add Nach rechts bewegen
     * 4.add Schießen
     * 5.add Aufheben
     * 6.add Waffe wechseln
     * @Author Marcel Sander
     */
    public void input() {
        if (playerNumber == 1) {
            //Nach oben gehen
            playerInput.add(Input.Keys.W);
            //Nach links gehen
            playerInput.add(Input.Keys.A);
            //Nach unten gehen
            playerInput.add(Input.Keys.S);
            //Nach rechts gehen
            playerInput.add(Input.Keys.D);
            //Schießen
            playerInput.add(Input.Keys.CONTROL_LEFT);
            //Aufheben
            playerInput.add(Input.Keys.E);
            //Waffenslot wechseln
            playerInput.add(Input.Keys.NUM_1);
        } else if (playerNumber == 2) {
            playerInput.add(Input.Keys.I);
            playerInput.add(Input.Keys.J);
            playerInput.add(Input.Keys.K);
            playerInput.add(Input.Keys.L);
            playerInput.add(Input.Keys.SHIFT_RIGHT);
            playerInput.add(Input.Keys.O);
            playerInput.add(Input.Keys.NUM_7);
        }
    }

    @Override
    public void update() {
        status.update(health, activeWeapon);
        activeWeapon.update();
        if (alive) {
            move.move(this, players, boxes, paletten, weapons);
        }
    }

    public void Shoot() {
        if (activeWeapon.shoot()) {
            Projektile projektile = new Projektile(viewDirection, getX(), getY(), this);
            projektileArrayList.add(projektile);
        }
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public float getMovement() {
        return movement;
    }

    public ArrayList<Integer> getPlayerInput() {
        return playerInput;
    }

    public Status getStatus() {
        return status;
    }
    public int getViewDirection() {
        return viewDirection;
    }

    public void setViewDirection(int viewDirection) {
        this.viewDirection = viewDirection;
    }

    public ArrayList<Projektile> getProjektileArrayList() {
        return projektileArrayList;
    }

    public void setProjektileArrayList(ArrayList<Projektile> projektileArrayList) {
        this.projektileArrayList = projektileArrayList;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    public void setAmmoBoxes(ArrayList<Ammunition> ammunitions) {
        this.ammunitions = ammunitions;
    }
    public void setHealthBoxes(ArrayList<HealthOrb> healthOrbs) {
        this.healthOrbs = healthOrbs;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setBoxes(ArrayList<Box> boxes) {
        this.boxes = boxes;
    }

    public void setPaletten(ArrayList<DestructibleBox> paletten) {
        this.paletten = paletten;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Weapon getActiveWeapon() {
        return activeWeapon;
    }

    public void kill() {
        this.alive = false;
    }

    public void switchWeapon() {
        if (weapon2 != null) {
            if (activeWeapon == pistol) {
                activeWeapon = weapon2;
            } else {
                activeWeapon = pistol;
            }
        }
    }

    public void pickUpWeapon(Weapon weapon) {
        this.weapon2 = weapon;
        this.activeWeapon = weapon;
    }
}

