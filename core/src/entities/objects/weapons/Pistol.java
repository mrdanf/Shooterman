package entities.objects.weapons;

public class Pistol extends Weapon {

    public Pistol() {
        super(3, 999, 10, 30, 2f);
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
