package entity.object.weapon;

import com.badlogic.gdx.graphics.Texture;

public class Shotgun extends Weapon {

    public static final String spriteName = "item/PumpgunKiste30x60.png";
    public static final String projectileName = "projektil/PumpgunSchuss.png";

    /**
     * Initialisiert der Schrotflinte
     */
    public Shotgun() {
        super(35, 20, 10, 15, 3f, new Texture(spriteName), WeaponType.SHOTGUN);
    }

    @Override
    public String getProjectileName() {
        return projectileName;
    }
}
