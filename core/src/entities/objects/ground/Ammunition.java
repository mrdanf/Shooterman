package entities.objects.ground;

import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.Entity;
import entities.objects.destructable.Box;
import entities.objects.destructable.DestructableBox;
import entities.objects.weapons.Weapon;

import java.util.ArrayList;
import java.util.Random;

public class Ammunition extends Entity {
    AmmunitionSprite ammoSprite = new AmmunitionSprite();
    float spriteSizeX = 50;
    float spriteSizeY = 50;

    public Ammunition(){
        ammoSprite.Sprite(this);
        setY(0);
        setX(0);
    }

    public void randomposition(ArrayList<Box> boxes, ArrayList<DestructableBox> paletten,ArrayList<Weapon> weapons, ArrayList<Ammunition> ammunitions) {
        int max = 736;
        int min = 156;
        float x = 0;
        float y = 0;
        boolean possible = true;
        Random rn = new Random();
        while (possible) {
            x = min + (int) (Math.random() * ((max - min) + 1));
            y = min + (int) (Math.random() * ((max - min) + 1));
            if (PlacmentPossible(boxes, x, y,paletten,weapons,ammunitions)) {
                possible=false;
            }
        }
        setX(x);
        setY(y);
    }

    public boolean PlacmentPossible(ArrayList<Box> boxes, float x, float y,ArrayList<DestructableBox> paletten,
                                    ArrayList<Weapon> weapons, ArrayList<Ammunition> ammunitions) {
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
            if (x <= weapon.getX() + (weapon.getSpritegrößex()/2 )) {
                if (x >= weapon.getX() - (weapon.getSpritegrößex()/2 )) {
                    if (y <= weapon.getY() + (weapon.getSpritegrößey()/2 )) {
                        if (y >= weapon.getY() - (weapon.getSpritegrößey()/2 )) {
                            return false;
                        }
                    }
                }
            }
        }
        for (Ammunition ammunition : ammunitions) {
            if (x <= ammunition.getX() + (spriteSizeX/2 )) {
                if (x >= ammunition.getX() - (spriteSizeX/2 )) {
                    if (y <= ammunition.getY() + (spriteSizeY/2 )) {
                        if (y >= ammunition.getY() - (spriteSizeY/2 )) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private int ammunitionAmount;//TODO Anzahl der Munition die man bekommt hinzufügen

    @Override
    public void update() {

    }

    @Override
    public Sprite getSprite() {
        return ammoSprite.getSprite();
    }

    public float getSpriteSizeX() {
        return spriteSizeX;
    }

    public float getSpriteSizeY() {
        return spriteSizeY;
    }
}
