package entity.object.weapon;

import com.badlogic.gdx.graphics.Texture;

public class Shotgun extends Weapon {

    public static final String spriteName = "PumpgunKiste30x60.png";

    public Shotgun() {
        super(35, 20, 10, 15, 3f, new Texture(spriteName));
    }

}
