package entities.objects.weapons;

public class Assaultrifle extends Weapon {

    public static String name = "Sturmgewehr";

    public final static String spriteName = "AK47Kiste30x60";

    public Assaultrifle(int power, int totalAmmunition, int magazineSize, int roundsPerMinute, int reloadTime) {
        super(power, totalAmmunition, magazineSize, roundsPerMinute, reloadTime);
    }
}
