package entities.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.Entity;
import entities.objects.destructable.Box;
import hud.HealthBar;
import hud.Status;
import entities.objects.destructable.DestructableBox;
import entities.objects.weapons.Pistol;
import entities.objects.weapons.Weapon;
import entities.projektile.Projektile;

import java.util.ArrayList;


public class Player extends Entity {
    PlayerMovement move = new PlayerMovement();
    PlayerSprites spriteGenerator = new PlayerSprites();
    private int health;
    private float movement = 2f;
    private int player;
    private ArrayList<Integer> playerInput = new ArrayList();
    private Status status;
    private int viewDirection = 0;
    ArrayList<Projektile> projektileArrayList = new ArrayList<>();
    ArrayList<Player> players;
    ArrayList<Box> boxes = new ArrayList<>();
    ArrayList<DestructableBox> paletten = new ArrayList<>();

    private Weapon pistol = new Pistol();
    private Weapon weapon2;


    public Player(int health, int player, float startx, float starty) {
        this.health = health;
        this.player = player;
        this.status = new Status(player);
        setX(startx);
        setY(starty);
        spriteGenerator.Sprite(this);
        input();
    }

    /**
     * Weist  dem Player die richtigen tasten zu um das spiel spielen zu können
     * 0.add Nach oben bewegen
     * 1.add Nach links bewegen
     * 2.add Nach unten bewegen
     * 3.add Nach rechts bewegen
     * 4.add Schießen
     * 5.add Aufheben
     * 6.add Waffenslot1
     * 7.add Waffenslot2
     * @Author Marcel Sander
     */
    public void input() {
        if (player == 1) {
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
            //Waffenslot1
            playerInput.add(Input.Keys.NUM_1);
            //Waffenslot2
            playerInput.add(Input.Keys.NUM_2);
        } else if (player == 2) {
            playerInput.add(Input.Keys.I);
            playerInput.add(Input.Keys.J);
            playerInput.add(Input.Keys.K);
            playerInput.add(Input.Keys.L);
            playerInput.add(Input.Keys.SHIFT_RIGHT);
            playerInput.add(Input.Keys.O);
            playerInput.add(Input.Keys.NUM_7);
            playerInput.add(Input.Keys.NUM_8);
        }
    }

    @Override
    public void update() {
        move.move(this, players,boxes);
    }

    public void Shoot() {
        if (pistol.shoot()) { // testweise erst nur pistol shoot, später allgemein -> currentWeapon.shoot()
            Projektile projektile = new Projektile(viewDirection, getX(), getY(), this);
            projektileArrayList.add(projektile);
        }
    }

    public int getPlayer() {
        return player;
    }

    public Sprite getSprite() {
        return spriteGenerator.getSprite();
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
    public int getViewdirection() {
        return viewDirection;
    }

    public void setViewdirection(int viewdirection) {
        this.viewDirection = viewdirection;
    }

    public ArrayList<Projektile> getProjektileArrayList() {
        return projektileArrayList;
    }

    public void setProjektileArrayList(ArrayList<Projektile> projektileArrayList) {
        this.projektileArrayList = projektileArrayList;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void decreaseHeath (int amount) {
        this.health -= amount;
    }


    public void setBoxes(ArrayList<Box> boxes) {
        this.boxes = boxes;
    }

    public void setPaletten(ArrayList<DestructableBox> paletten) {
        this.paletten = paletten;
    }
}

