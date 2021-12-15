package entities.objects.ground;

import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.NonVisualEntity;
import entities.objects.destructable.Box;
import entities.objects.destructable.DestructableBox;
import entities.objects.weapons.Weapon;

import java.util.ArrayList;
import java.util.Random;

public class HealthOrb extends NonVisualEntity {
    HealthOrbSprite healthOrbSprite = new HealthOrbSprite();
    float spriteSizeX = 50;
    float spriteSizeY = 50;

    public HealthOrb(){
        healthOrbSprite.Sprite(this);
        setY(0);
        setX(0);
    }



    public void randomposition(ArrayList<Box> boxes, ArrayList<DestructableBox> paletten, ArrayList<Weapon> weapons,
                               ArrayList<Ammunition> ammunitions, ArrayList<HealthOrb> healthOrbs) {
        int max = 736;
        int min = 156;
        float x = 0;
        float y = 0;
        boolean possible = true;
        Random rn = new Random();
        while (possible) {
            x = min + (int) (Math.random() * ((max - min) + 1));
            y = min + (int) (Math.random() * ((max - min) + 1));
            if (PlacmentPossible(boxes, x, y,paletten,weapons,ammunitions,healthOrbs)) {
                possible=false;
            }
        }
        setX(x);
        setY(y);
    }

    public boolean PlacmentPossible(ArrayList<Box> boxes, float x, float y,ArrayList<DestructableBox> paletten,
                                    ArrayList<Weapon> weapons, ArrayList<Ammunition> ammunitions,ArrayList<HealthOrb> healthOrbs) {
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
            if (x <= ammunition.getX() + (ammunition.getSpriteSizeX()/2 )) {
                if (x >= ammunition.getX() - (ammunition.getSpriteSizeX()/2 )) {
                    if (y <= ammunition.getY() + (ammunition.getSpriteSizeY()/2 )) {
                        if (y >= ammunition.getY() - (ammunition.getSpriteSizeY()/2 )) {
                            return false;
                        }
                    }
                }
            }
        }
        for (HealthOrb healthOrb : healthOrbs) {
            if (x <= healthOrb.getX() + (spriteSizeX/2 )) {
                if (x >= healthOrb.getX() - (spriteSizeX/2 )) {
                    if (y <= healthOrb.getY() + (spriteSizeY/2 )) {
                        if (y >= healthOrb.getY() - (spriteSizeY/2 )) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private int healthAmount;

    @Override
    public void update() {

    }

    @Override
    public Sprite getSprite() {
        return healthOrbSprite.getSprite();
    }

    public float getSpriteSizeX() {
        return spriteSizeX;
    }

    public float getSpriteSizeY() {
        return spriteSizeY;
    }
}
