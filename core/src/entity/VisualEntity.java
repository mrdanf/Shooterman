package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class VisualEntity extends NonVisualEntity {

    protected Sprite sprite;
    protected Rectangle hitbox;

    /**
     * Erzeugt die Animationen für Items und Waffen
     * @param width Weite des einzelnen Bildes
     * @param height Höhe des einzelnen Bildes
     * @param texture Aktuelle Texture
     */
    public VisualEntity(Texture texture, float width, float height) {
        if (texture != null) {
            sprite = new Sprite(texture);
        }

        this.hitbox = new Rectangle(0, 0, width, height);
    }

    /**
     * Erzeugt die Animationen für Items und Waffen
     * @param x Aktuelle Position des Items auf der x-Achse
     * @param y Aktuelle Position des Items auf der y-Achse
     * @param width Weite des einzelnen Bildes
     * @param height Höhe des einzelnen Bildes
     * @param texture Aktuelle Texture
     */
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

    public Rectangle getHitbox() {
        updateHitbox();
        return hitbox;
    }

    protected void updateHitbox() {
        hitbox.setPosition(x, y);
    }

    public boolean isColliding(VisualEntity other) {
        // Unsichtbares Viereck im Objekt, eigenständig angepasste Hitbox
        return this.getHitbox().overlaps(other.getHitbox());
    }

    @Override
    public void update() {

    }
}
