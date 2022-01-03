package entity.object.ground;

import com.badlogic.gdx.graphics.Texture;
import entity.VisualEntity;
import entity.object.obstacle.Box;
import entity.object.obstacle.DestructibleBox;
import entity.object.weapon.Weapon;

import java.util.ArrayList;
import java.util.Random;

public class Ammunition extends VisualEntity {

    public Ammunition() {
        super(0, 0, new Texture("AmmoBox.png"));
    }

    public void randomPosition(ArrayList<Box> boxes, ArrayList<DestructibleBox> destructibleBoxes, ArrayList<Weapon> weapons,
                               ArrayList<Ammunition> ammunitions) {
        int max = 736;
        int min = 156;
        float x = 0;
        float y = 0;
        boolean possible = true;
        Random rn = new Random();
        while (possible) {
            x = min + (int) (Math.random() * ((max - min) + 1));
            y = min + (int) (Math.random() * ((max - min) + 1));
            if (placementPossible(boxes, x, y, destructibleBoxes, weapons, ammunitions)) {
                possible = false;
            }
        }
        setX(x);
        setY(y);
    }

    public boolean placementPossible(ArrayList<Box> boxes, float x, float y, ArrayList<DestructibleBox> destructibleBoxes,
                                     ArrayList<Weapon> weapons, ArrayList<Ammunition> ammunitions) {
        boolean boxok = false;
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
        for (DestructibleBox destructibleBox : destructibleBoxes) {
            if (x <= destructibleBox.getX() + (destructibleBox.getSprite().getWidth() / 2)) {
                if (x >= destructibleBox.getX() - (destructibleBox.getSprite().getWidth() / 2)) {
                    if (y <= destructibleBox.getY() + (destructibleBox.getSprite().getHeight() / 2)) {
                        if (y >= destructibleBox.getY() - (destructibleBox.getSprite().getHeight() / 2)) {
                            return false;
                        }
                    }
                }
            }
        }
        for (Weapon weapon : weapons) {
            if (x <= weapon.getX() + (weapon.getSprite().getWidth() / 2)) {
                if (x >= weapon.getX() - (weapon.getSprite().getWidth() / 2)) {
                    if (y <= weapon.getY() + (weapon.getSprite().getHeight() / 2)) {
                        if (y >= weapon.getY() - (weapon.getSprite().getHeight() / 2)) {
                            return false;
                        }
                    }
                }
            }
        }
        for (Ammunition ammunition : ammunitions) {
            if (x <= ammunition.getX() + (getSprite().getWidth() / 2)) {
                if (x >= ammunition.getX() - (getSprite().getWidth() / 2)) {
                    if (y <= ammunition.getY() + (getSprite().getHeight() / 2)) {
                        if (y >= ammunition.getY() - (getSprite().getHeight() / 2)) {
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

}
