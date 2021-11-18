package entities.objects.weapons;

public class Sniperrifle extends Weapon {

    public static String name = "Scharfschützengewehr";

    public final static String spriteName = "AWPKiste30x70";

    public Sniperrifle(int power, int totalAmmunition, int magazineSize, int roundsPerMinute, int reloadTime) {
        super(power, totalAmmunition, magazineSize, roundsPerMinute, reloadTime);
    }

}
