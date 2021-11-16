package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Player extends Entities {
    int health;
    float Movement = 1f;

    public Player(int health) {
        this.health = health;
    }

    public void update() {
       move();
    }
    public void move(){

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
}

