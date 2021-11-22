package entities.objects.destructable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import entities.player.Player;

public class BoxSprite {
    private Sprite sprite;

    public void Sprite( Box box) {
        sprite = new Sprite(new Texture("Sandsacke.png"));
    }

    public Sprite getSprite() {
        return sprite;
    }
}
