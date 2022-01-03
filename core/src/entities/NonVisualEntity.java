package entities;

public abstract class NonVisualEntity {
    protected float x;
    protected float y;

    public NonVisualEntity() {

    }

    public NonVisualEntity(float x, float y) {
        this.x = x;
        this.y = y;
    }

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
