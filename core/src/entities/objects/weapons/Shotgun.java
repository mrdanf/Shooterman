package entities.objects.weapons;

public class Shotgun extends Weapon {

    public static String name = "Schrotgewehr";

    public final static String spriteName = "PumpgunKiste30x60";

    public Shotgun() {
        super(10, 30, 20, 10, 3f);
    }

}
