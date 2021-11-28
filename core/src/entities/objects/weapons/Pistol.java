package entities.objects.weapons;

public class Pistol extends Weapon {

    public Pistol() {
        super(3, 999, 10, 5, 2f);
    }

    @Override
    public boolean shoot() {
        if (currentAmmunition > 0) {

            currentAmmunition--;

            if (currentAmmunition == 0) {
                reload();
            }

            return true;
        }

        return false;
    }

}
