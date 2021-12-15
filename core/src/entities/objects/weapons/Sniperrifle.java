package entities.objects.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Sniperrifle extends Weapon {

    public static final String spriteName = "AWPKiste30x70.png";

    public Sniperrifle() {
        super(50, 10, 5, 5, 3f, new Texture(spriteName));
    }

}
