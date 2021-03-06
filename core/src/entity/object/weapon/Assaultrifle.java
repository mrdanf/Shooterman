package entity.object.weapon;

import com.badlogic.gdx.graphics.Texture;

public class Assaultrifle extends Weapon {

    public static final String spriteName = "item/AK47Kiste30x60.png";
    public static final String projectileName = "projektil/AKSchuss.png";

    @Override
    public String getProjectileName() {
        return projectileName;
    }

    /**
     * Initialisiert das Sturmgewehr
     */
    public Assaultrifle() {
        super(15, 40, 20, 40, 2f, new Texture(spriteName), WeaponType.ASSAULTRIFLE);
    }
}
