package entity.object.weapon;

public class Pistol extends Weapon {

    public static final String projectileName = "projektil/PistolenSchuss.png";

    public Pistol() {
        super(15, 999, 10, 30, 2f, null, WeaponType.PISTOL);
    }

    @Override
    public String getProjectileName() {
        return projectileName;
    }

    @Override
    public boolean shoot() {
        if (cooldown <= 0 && currentAmmunition > 0) {

            currentAmmunition--;

            if (currentAmmunition == 0) {
                reload();
            }

            cooldown = Math.max(50 - roundsPerMinute, 0) + 1;
            return true;
        }

        return false;
    }

}
