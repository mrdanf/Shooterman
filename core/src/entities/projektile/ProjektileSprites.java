package entities.projektile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import entities.player.Player;

public class ProjektileSprites {

    private Sprite sprite;

    public void Sprite(Projektile projektile) {
        sprite = new Sprite(new Texture("PistolenSchuss.png"));
        sprite.setScale(0.3f);

        projektile.setSprite(sprite);
    }
}
