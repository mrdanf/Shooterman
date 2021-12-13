package entities.objects.weapons;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Timer;
import entities.Entity;
import entities.objects.destructable.Box;
import entities.objects.destructable.DestructableBox;
import entities.objects.ground.Ammunition;
import entities.objects.ground.HealthOrb;

import java.util.ArrayList;
import java.util.Random;

public abstract class Weapon extends Entity {

    private int power;
    private int totalAmmunition;
    protected int currentAmmunition;
    private int magazineSize;
    protected int roundsPerMinute;
    protected int cooldown;
    private float reloadTime;
    private boolean insideReload;
    private float spritegrößex = 60f;
    private float spritegrößey = 60f;

    private boolean isOnGround = true;

    private WeaponSprite sprite;


    public Weapon(int power, int totalAmmunition, int magazineSize, int roundsPerMinute, float reloadTime,
                  String spritePath) {
        this.power = power;
        this.totalAmmunition = totalAmmunition;
        this.magazineSize = magazineSize;
        this.currentAmmunition = magazineSize;
        this.roundsPerMinute = roundsPerMinute;
        this.reloadTime = reloadTime;
        insideReload = false;

        if (spritePath != null) {
            this.sprite = new WeaponSprite(spritePath);
        }
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

    public float getSpritegrößex() {
        return spritegrößex;
    }

    public float getSpritegrößey() {
        return spritegrößey;
    }

    public Sprite getSprite() {
        return this.sprite.getSprite();
    }

    public void randomposition(ArrayList<Box> boxes, ArrayList<DestructableBox> paletten, ArrayList<Weapon> weapons) {
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


    public boolean PlacmentPossible(ArrayList<Box> boxes, float x, float y,ArrayList<DestructableBox> paletten,
                                    ArrayList<Weapon> weapons) {
        boolean boxok=false;
        for (Box box : boxes) {
            if (x <= box.getX() + (box.getSpritegrößex()/2 )) {
                if (x >= box.getX() - (box.getSpritegrößex()/2)) {
                    if (y <= box.getY() + (box.getSpritegrößex()/2 )) {
                        if (y >= box.getY() - (box.getSpritegrößex()/2 )) {
                            return false;
                        }
                    }
                }
            }
        }
        for (DestructableBox palette : paletten) {
            if (x <= palette.getX() + (palette.getSpritegrößex()/2 )) {
                if (x >= palette.getX() - (palette.getSpritegrößex()/2 )) {
                    if (y <= palette.getY() + (palette.getSpritegrößex()/2 )) {
                        if (y >= palette.getY() - (palette.getSpritegrößex()/2 )) {
                            return false;
                        }
                    }
                }
            }
        }
        for (Weapon weapon : weapons) {
            if (x <= weapon.getX() + (spritegrößex/2 )) {
                if (x >= weapon.getX() - (spritegrößex/2 )) {
                    if (y <= weapon.getY() + (spritegrößey/2 )) {
                        if (y >= weapon.getY() - (spritegrößey/2 )) {
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
