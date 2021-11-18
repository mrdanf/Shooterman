package entities.objects.weapons;

public class Pistol extends Weapon {

    private final int totalAmmunition = 999;

    public static String name = "Pistole";

    public Pistol() {
        super(3, 999, 10, 5, 2f);
    }

}
