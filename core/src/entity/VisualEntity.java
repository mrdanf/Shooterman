package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class VisualEntity extends NonVisualEntity {

    protected Sprite sprite;
    protected Rectangle hitbox;

    public VisualEntity(Texture texture, float width, float height) {
        if (texture != null) {
            sprite = new Sprite(texture);
        }

    }

    public VisualEntity(float x, float y, Texture texture, float width, float height) {
        super(x, y);
        if (texture != null) {
            sprite = new Sprite(texture);
        }

        this.hitbox = new Rectangle(x, y, width, height);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getWidth() {
        return sprite.getWidth();
    }

    public float getHeight() {
        return sprite.getHeight();
    }

    public void setScale(float scale) {
        sprite.setScale(scale);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        sprite.setX(x);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        sprite.setY(y);
    }

    public float getHitboxX() {
        return sprite.getBoundingRectangle().getX();
    }

    public float getHitboxY() {
        return sprite.getBoundingRectangle().getY();
    }

    public Rectangle getHitbox() {
        updateHitbox();
        return hitbox;
    }

    protected void updateHitbox() {
        hitbox.setPosition(x, y);
    }

    public boolean isColliding(VisualEntity other) {
        //return this.sprite.getBoundingRectangle().overlaps(other.getHitbox());
        // TODO: Würde genau die Sprites umschließen, ist aber etwas zu clunky

        // Unsichtbares Viereck im Objekt, eigenständig angepasste Hitbox
        return this.getHitbox().overlaps(other.getHitbox());
    }

    @Override
    public void update() {

    }
}
