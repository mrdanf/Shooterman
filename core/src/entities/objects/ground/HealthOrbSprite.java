package entities.objects.ground;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class HealthOrbSprite {
    private Sprite sprite;

    public void Sprite( HealthOrb healthOrb) {
        sprite = new Sprite(new Texture("HealthBox.png"));
    }

    public Sprite getSprite() {
        return sprite;
    }
}
