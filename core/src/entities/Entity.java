package entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Entity {
    protected Sprite sprite;
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

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
