package entities.objects.weapons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Timer;
import entities.Entity;

public abstract class Weapon extends Entity {

    private int power;
    private int totalAmmunition;
    private int currentAmmunition;
    private int magazineSize;
    private int roundsPerMinute;
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

    }

    public boolean shoot() {
        if (totalAmmunition > 0) {
            if (currentAmmunition > 0) {

                currentAmmunition--;
                totalAmmunition--;

                if (currentAmmunition == 0) {
                    reload();
                }

                return true;
            }
        }

        return false;
    }

    private void reload() {
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
     * @return total ammo
     */
    public int getTotalAmmunition() {
        return totalAmmunition;
    }

    /**
     * For visual representation.
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
     * @return  true - when inside reload
     * <br/>    false - when not reloading
     */
    public boolean isInsideReload() {
        return insideReload;
    }
}
