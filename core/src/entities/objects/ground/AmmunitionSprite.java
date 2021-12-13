package entities.objects.ground;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.objects.destructable.Box;

public class AmmunitionSprite {
    private Sprite sprite;

    public void Sprite( Ammunition ammunition) {
        sprite = new Sprite(new Texture("AmmoBox.png"));
    }

    public Sprite getSprite() {
        return sprite;
    }
}
