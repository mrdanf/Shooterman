package entities.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.Entity;

import java.util.ArrayList;


public class Player extends Entity {
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

    /**
     * Weist  dem Player die richtigen tasten zu um das spiel spielen zu können
     * 1.add Nach Rechts bewegen
     * 2.add Nach Rechts bewegen
     * 3.add Nach Rechts bewegen
     * 4.add Nach Rechts bewegen
     *
     * @Author Marcel SAnder
     */
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

    @Override
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

