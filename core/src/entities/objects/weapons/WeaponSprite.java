package entities.objects.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class WeaponSprite {

    private Sprite sprite;

    public WeaponSprite(String spritePath) {
        sprite = new Sprite(new Texture(spritePath));
        sprite.setScale(0.9f);
    }

    public Sprite getSprite() {
        return sprite;
    }
}