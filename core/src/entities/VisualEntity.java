package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class VisualEntity extends NonVisualEntity {

    private Sprite sprite;

    public VisualEntity(Texture texture) {
        if (texture != null) {
            sprite = new Sprite(texture);
        }
    }

    public VisualEntity(float x, float y, Texture texture) {
        super(x, y);
        if (texture != null) {
            sprite = new Sprite(texture);
        }
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
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
}
