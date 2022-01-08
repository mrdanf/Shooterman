package entity.object.weapon;

import com.badlogic.gdx.graphics.Texture;

public class Sniperrifle extends Weapon {

    public static final String spriteName = "item/AWPKiste30x70.png";
    public static final String projectileName = "projektil/SniperSchuss.png";

    /**
     * Initialisiert das Scharfschützengewehr
     */
    public Sniperrifle() {
        super(50, 10, 5, 5, 4f, new Texture(spriteName), WeaponType.SNIPERRIFLE);
    }

    @Override
    public String getProjectileName() {
        return projectileName;
    }
}
