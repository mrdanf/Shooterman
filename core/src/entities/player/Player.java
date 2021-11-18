package entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.Entity;
import entities.projektile.Projektile;

import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class Player extends Entity {
    PlayerMovement move = new PlayerMovement();
    PlayerSprites spriteGenerator = new PlayerSprites();
    private int health;
    private float movement = 1f;
    private int player;
    private Sprite sprite;
    private ArrayList<Integer> playerInput = new ArrayList();
    private int viewDirection = 0;
    ArrayList<Projektile> projektileArrayList = new ArrayList<>();


    public Player(int health, int player, float startx, float starty) {
        this.health = health;
        this.player = player;
        setX(startx);
        setY(starty);
        spriteGenerator.Sprite(this);
        input();

    }

    /**
     * Weist  dem Player die richtigen tasten zu um das spiel spielen zu können
     * 0.add Nach Rechts bewegen
     * 1.add Nach Rechts bewegen
     * 2.add Nach Rechts bewegen
     * 3.add Nach Rechts bewegen
     * 4.add Schießen
     *
     * @Author Marcel Sander
     */
    public void input() {
        if (player == 1) {
            //Nach rechts gehen
            playerInput.add(Input.Keys.D);
            //Nach oben gehen
            playerInput.add(Input.Keys.W);
            //Nach links gehen
            playerInput.add(Input.Keys.A);
            //Nach unten gehen
            playerInput.add(Input.Keys.S);
            //Schießen
            playerInput.add(Input.Keys.CONTROL_LEFT);
        } else if (player == 2) {
            playerInput.add(Input.Keys.L);
            playerInput.add(Input.Keys.I);
            playerInput.add(Input.Keys.J);
            playerInput.add(Input.Keys.K);
            playerInput.add(Input.Keys.SHIFT_RIGHT);
        }
    }

    @Override
    public void update() {
        move.move(this);
    }

    public void Shoot() {
        Projektile projektile = new Projektile(viewDirection, getX(), getY(),this);
        projektileArrayList.add(projektile);
    }

    public int getPlayer() {
        return player;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public float getMovement() {
        return movement;
    }

    public ArrayList<Integer> getPlayerInput() {
        return playerInput;
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


}

