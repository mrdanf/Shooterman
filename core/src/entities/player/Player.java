package entities.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.Entity;

import java.util.ArrayList;


public class Player extends Entity {
    PlayerMovement move = new PlayerMovement();
    PlayerSprites spriteGenerator = new PlayerSprites();
    private int health;
    private float movement = 1f;
    private int player;
    private ArrayList<Integer> playerInput = new ArrayList();


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
     * 1.add Nach Rechts bewegen
     * 2.add Nach Rechts bewegen
     * 3.add Nach Rechts bewegen
     * 4.add Nach Rechts bewegen
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
        } else if (player == 2) {
            playerInput.add(Input.Keys.L);
            playerInput.add(Input.Keys.I);
            playerInput.add(Input.Keys.J);
            playerInput.add(Input.Keys.K);
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
        return spriteGenerator.getSprite();
    }

    public float getMovement() {
        return movement;
    }

    public ArrayList<Integer> getPlayerInput() {
        return playerInput;
    }

}

