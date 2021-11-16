package entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import entities.Entities;


public class Player extends Entities {
    PlayerSprits spritegenerat = new PlayerSprits();
    private int health;
    private float Movement = 1f;
    private int player;
    private Sprite sprite;


    public Player(int health, int player, float startx, float starty) {
        this.health = health;
        this.player = player;
        setX(startx);
        setY(starty);
        spritegenerat.Sprite(this);
    }

    public void update() {
        move();
    }

    public void move() {
        if (player == 1) {
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                setX(getX() + Movement);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                setY(getY() + Movement);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                setX(getX() - Movement);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                setY(getY() - Movement);
            }
        }
        if (player == 2) {
            if (Gdx.input.isKeyPressed(Input.Keys.L)) {
                setX(getX() + Movement);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.I)) {
                setY(getY() + Movement);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.J)) {
                setX(getX() - Movement);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.K)) {
                setY(getY() - Movement);
            }
        }
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


}

