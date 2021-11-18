package entities.objects.weapons;

public class Pistol extends Weapon {

    private final int totalAmmunition = 999;

    public static String name = "Pistole";

    public Pistol(int power, int totalAmmunition, int magazineSize, int roundsPerMinute, int reloadTime) {
        super(power, 999, magazineSize, roundsPerMinute, reloadTime);
    }

}
