package entities.objects.destructable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import entities.player.Player;

public class DestuctableBoxSprite {

    private int column = 0;
    private int row = 0;
    private Sprite sprite;

    public void Sprite(Player player) {
        sprite = new Sprite(new Texture("palette.png"));
    }

    public Sprite getSprite() {
        return sprite;
    }
}
