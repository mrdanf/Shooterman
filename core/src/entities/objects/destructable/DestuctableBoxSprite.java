package entities.objects.destructable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import entities.player.Player;

public class DestuctableBoxSprite {
    private Sprite sprite;

    public void Sprite(DestructableBox palette, int i) {
        if (i % 2 == 0) {
            sprite = new Sprite(new Texture("palette.png"));
            sprite.setScale(2f);
        }
        else{
            sprite = new Sprite(new Texture("Palettemitkartons.png"));
            sprite.setScale(1f);
        }
    }


    public Sprite getSprite() {
        return sprite;
    }
}
