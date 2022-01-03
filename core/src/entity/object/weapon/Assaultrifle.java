package entity.object.weapon;

import com.badlogic.gdx.graphics.Texture;

public class Assaultrifle extends Weapon {

    public static final String spriteName = "AK47Kiste30x60.png";

    public Assaultrifle() {
        super(15, 40, 20, 45, 3f, new Texture(spriteName));
    }
}
