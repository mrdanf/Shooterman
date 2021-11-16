package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;




public class Player extends Entities {
    int health;
    float Movement = 1f;
    int player;
    private int column = 0;
    private int row = 0;

    private Sprite sprite;
    private TextureRegion[][] regions;

    public Player(int health, int player,float startx, float starty) {
        this.health = health;
        this.player = player;
        setX(startx);
        setY(starty);
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


    public void Sprite() {
        regions = TextureRegion.split(new Texture("playerWalkAnimation.png"), 213, 390);
        sprite = new Sprite(regions[row][column]);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                column++;
                if (column > 5) {
                    column = 0;
                }
                sprite.setRegion(regions[row][column]);
            }
        }, 0, 1 / 10f);
    }

    public Sprite getSprite() {
        return sprite;
    }
}

