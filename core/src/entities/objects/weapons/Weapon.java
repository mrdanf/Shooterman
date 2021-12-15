package entities.objects.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import entities.VisualEntity;
import entities.objects.destructable.Box;
import entities.objects.destructable.DestructibleBox;

import java.util.ArrayList;
import java.util.Random;

public abstract class Weapon extends VisualEntity {

    private int power;
    private int totalAmmunition;
    protected int currentAmmunition;
    private int magazineSize;
    protected int roundsPerMinute;
    protected int cooldown;
    private float reloadTime;
    private boolean insideReload;

    private boolean isOnGround = true;

    public Weapon(int power, int totalAmmunition, int magazineSize, int roundsPerMinute, float reloadTime,
                  Texture texture) {
        super(texture);
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

                cooldown = Math.max(50 - roundsPerMinute, 0) + 1;
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

    public void randomposition(ArrayList<Box> boxes, ArrayList<DestructibleBox> paletten, ArrayList<Weapon> weapons) {
        int max = 736;
        int min = 156;
        float x = 0;
        float y = 0;
        boolean possible = true;
        Random rn = new Random();
        while (possible) {
            x = min + (int) (Math.random() * ((max - min) + 1));
            y = min + (int) (Math.random() * ((max - min) + 1));
            if (PlacmentPossible(boxes, x, y,paletten, weapons)) {
                possible=false;
            }
        }
        setX(x);
        setY(y);
    }


    public boolean PlacmentPossible(ArrayList<Box> boxes, float x, float y,ArrayList<DestructibleBox> paletten,
                                    ArrayList<Weapon> weapons) {
        boolean boxok=false;
        for (Box box : boxes) {
            if (x <= box.getX() + (box.getSprite().getWidth() / 2)) {
                if (x >= box.getX() - (box.getSprite().getWidth() / 2)) {
                    if (y <= box.getY() + (box.getSprite().getHeight() / 2)) {
                        if (y >= box.getY() - (box.getSprite().getHeight() / 2)) {
                            return false;
                        }
                    }
                }
            }
        }
        for (DestructibleBox palette : paletten) {
            if (x <= palette.getX() + (palette.getSprite().getWidth() / 2)) {
                if (x >= palette.getX() - (palette.getSprite().getWidth() / 2)) {
                    if (y <= palette.getY() + (palette.getSprite().getHeight() / 2)) {
                        if (y >= palette.getY() - (palette.getSprite().getHeight() / 2)) {
                            return false;
                        }
                    }
                }
            }
        }
        for (Weapon weapon : weapons) {
            if (x <= weapon.getX() + (getSprite().getWidth() / 2)) {
                if (x >= weapon.getX() - (getSprite().getWidth() / 2)) {
                    if (y <= weapon.getY() + (getSprite().getHeight() / 2)) {
                        if (y >= weapon.getY() - (getSprite().getHeight() / 2)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isOnGround() {
        return isOnGround;
    }

    public void setOnGround(boolean onGround) {
        isOnGround = onGround;
    }
}
