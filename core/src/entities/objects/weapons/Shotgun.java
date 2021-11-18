package entities.objects.weapons;

public class Shotgun extends Weapon {

    public static String name = "Schrotgewehr";

    public final static String spriteName = "PumpgunKiste30x60";

    public Shotgun(int power, int totalAmmunition, int magazineSize, int roundsPerMinute, int reloadTime) {
        super(power, totalAmmunition, magazineSize, roundsPerMinute, reloadTime);
    }

}
