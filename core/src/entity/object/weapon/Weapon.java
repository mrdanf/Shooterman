package entity.object.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import entity.VisualEntity;
import entity.object.obstacle.Box;
import entity.object.obstacle.DestructibleBox;

import java.util.ArrayList;

public abstract class Weapon extends VisualEntity {

    private int power;
    private int totalAmmunition;
    protected int currentAmmunition;
    private int magazineSize;
    protected int roundsPerMinute;
    protected int cooldown;
    private float reloadTime;
    private boolean insideReload;
    private int weaponType;

    private boolean isOnGround = true;

    public Weapon(int power, int totalAmmunition, int magazineSize, int roundsPerMinute, float reloadTime,
                  Texture texture, int weaponType) {
        super(texture, 150, 120);
        this.power = power;
        this.totalAmmunition = totalAmmunition;
        this.magazineSize = magazineSize;
        this.currentAmmunition = magazineSize;
        this.roundsPerMinute = roundsPerMinute;
        this.reloadTime = reloadTime;
        this.insideReload = false;
        this.weaponType = weaponType;
    }

    public abstract String getProjectileName();

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

    @Override
    protected void updateHitbox() {
        float x = sprite.getX() - ((hitbox.width - sprite.getWidth()) / 2);
        float y = sprite.getY() - ((hitbox.height - sprite.getHeight()) / 2);
        hitbox.setPosition(x, y);
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

    public int getWeaponType() {
        return weaponType;
    }

    public void randomPosition(ArrayList<Box> boxes, ArrayList<DestructibleBox> destructibleBoxes, ArrayList<Weapon> weapons) {
        int max = 736;
        int min = 156;
        float x = 0;
        float y = 0;
        boolean possible = true;
        while (possible) {
            x = min + (int) (Math.random() * ((max - min) + 1));
            y = min + (int) (Math.random() * ((max - min) + 1));
            if (placementPossible(boxes, x, y, destructibleBoxes, weapons)) {
                possible = false;
            }
        }
        setX(x);
        setY(y);
    }


    public boolean placementPossible(ArrayList<Box> boxes, float x, float y, ArrayList<DestructibleBox> destructibleBoxes,
                                     ArrayList<Weapon> weapons) {
        for (Box box : boxes) {
            if (box.getSprite().getBoundingRectangle().contains(x, y)) {
                return false;
            }
        }
        for (DestructibleBox destructibleBox : destructibleBoxes) {
            if (destructibleBox.getSprite().getBoundingRectangle().contains(x, y)) {
                return false;
            }
        }
        for (Weapon weapon : weapons) {
            if (weapon != this) {
                if (weapon.getSprite().getBoundingRectangle().contains(x, y)) {
                    return false;
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

    public void giveAmmo(int ammunitionAmount) {
        if (totalAmmunition == 0) {
            this.totalAmmunition += ammunitionAmount;
            currentAmmunition = totalAmmunition;
        } else {
            this.totalAmmunition += ammunitionAmount;
        }
    }
}
