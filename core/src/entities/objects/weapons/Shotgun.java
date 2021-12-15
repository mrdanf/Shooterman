package entities.objects.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Shotgun extends Weapon {

    public static final String spriteName = "PumpgunKiste30x60.png";

    public Shotgun() {
        super(35, 20, 10, 15, 3f, new Texture(spriteName));
    }

}
