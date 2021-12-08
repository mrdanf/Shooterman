package entities.objects.weapons;

import com.badlogic.gdx.utils.Timer;
import entities.Entity;

public abstract class Weapon extends Entity {

    private int power;
    private int totalAmmunition;
    protected int currentAmmunition;
    private int magazineSize;
    protected int roundsPerMinute;
    protected int cooldown;
    private float reloadTime;
    private boolean insideReload;

    public Weapon(int power, int totalAmmunition, int magazineSize, int roundsPerMinute, float reloadTime) {
        this.power = power;
        this.totalAmmunition = totalAmmunition;
        this.magazineSize = magazineSize;
        this.currentAmmunition = magazineSize;
        this.roundsPerMinute = roundsPerMinute;
        this.reloadTime = reloadTime;
        insideReload = false;
    }

    @Override
    public void update() {
        cooldown--;
    }

    public boolean shoot() {
        if (cooldown <= 0 && totalAmmunition > 0) {
            if (currentAmmunition > 0) {

                currentAmmunition--;
                totalAmmunition--;

                if (currentAmmunition == 0) {
                    reload();
                }

                cooldown = roundsPerMinute;
                return true;
            } else {
                // Can't shoot because of reloading
                return false;
            }
        } else {
            // Weapon on cooldown or no ammo left
            return false;
        }
    }

    protected void reload() {
        insideReload = true;

        Timer.schedule(new Timer.Task() {

            @Override
            public void run() {
                currentAmmunition = Math.min(magazineSize, totalAmmunition);
                insideReload = false;
            }
        }, reloadTime);
    }

    public int getPower() {
        return power;
    }

    /**
     * For visual representation.
     *
     * @return total ammo
     */
    public int getTotalAmmunition() {
        return totalAmmunition;
    }

    /**
     * For visual representation.
     *
     * @return current ammo in magazine
     */
    public int getCurrentAmmunition() {
        return currentAmmunition;
    }

    public int getRoundsPerMinute() {
        return roundsPerMinute;
    }

    /**
     * For visual representation of the reloading state.
     *
     * @return true - when inside reload
     * <br/>    false - when not reloading
     */
    public boolean isInsideReload() {
        return insideReload;
    }
}
