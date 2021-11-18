package entities.objects.weapons;

import entities.Entity;

public abstract class Weapon extends Entity {

    private int power;
    private int totalAmmunition;
    private int magazineSize;
    private int roundsPerMinute;
    private int reloadTime;

    public Weapon(int power, int totalAmmunition, int magazineSize, int roundsPerMinute, int reloadTime) {
        this.power = power;
        this.totalAmmunition = totalAmmunition;
        this.magazineSize = magazineSize;
        this.roundsPerMinute = roundsPerMinute;
        this.reloadTime = reloadTime;
    }

    @Override
    public void update() {

    }
}
