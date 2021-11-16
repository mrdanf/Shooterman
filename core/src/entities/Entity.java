package entities;

import com.badlogic.gdx.ApplicationAdapter;
import com.shooterman.game.Shooterman;

public abstract class Entity extends Shooterman {
    public float x;
    public float y;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    public abstract void update();
}
