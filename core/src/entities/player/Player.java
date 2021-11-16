package entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import entities.Entities;

import java.util.ArrayList;


public class Player extends Entities {
    Playermovment move = new Playermovment();
    PlayerSprits spritegenerat = new PlayerSprits();
    private int health;
    private float movement = 1f;
    private int player;
    private Sprite sprite;
    ArrayList<Integer> playerinput = new ArrayList();


    public Player(int health, int player, float startx, float starty) {
        this.health = health;
        this.player = player;
        setX(startx);
        setY(starty);
        spritegenerat.Sprite(this);
        input();

    }

    public void input() {
        if (player == 1) {
            //Nach rechts gehen
            playerinput.add(Input.Keys.D);
            //Nach oben gehen
            playerinput.add(Input.Keys.W);
            //Nach links gehen
            playerinput.add(Input.Keys.A);
            //Nach unten gehen
            playerinput.add(Input.Keys.S);
        } else if (player == 2) {
            playerinput.add(Input.Keys.L);
            playerinput.add(Input.Keys.I);
            playerinput.add(Input.Keys.J);
            playerinput.add(Input.Keys.K);
        }
    }

    public void update() {
        move.move(this);
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

    public ArrayList<Integer> getPlayerinput() {
        return playerinput;
    }

}

