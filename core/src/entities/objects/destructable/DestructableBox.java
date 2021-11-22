package entities.objects.destructable;

import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.Entity;
import entities.objects.weapons.Weapon;

import java.util.ArrayList;
import java.util.Random;

public class DestructableBox extends Entity {
    DestuctableBoxSprite sprite = new DestuctableBoxSprite();
    float spritegrößex = 80f;
    float spritegrößey = 80f;
    int health=100;

    public DestructableBox(int i) {
        sprite.Sprite(this,i);
        setY(0);
        setX(0);
    }

    public void randomposition(ArrayList<Box> boxes,ArrayList<DestructableBox> paletten) {
        int max = 736;
        int min = 156;
        float x = 0;
        float y = 0;
        boolean possible = true;
        Random rn = new Random();
        while (possible) {
            x = min + (int) (Math.random() * ((max - min) + 1));
            y = min + (int) (Math.random() * ((max - min) + 1));
            if (PlacmentPossible(boxes, x, y,paletten)) {
                possible=false;
            }
        }
        setX(x);
        setY(y);
    }


    public boolean PlacmentPossible(ArrayList<Box> boxes, float x, float y,ArrayList<DestructableBox> paletten ) {
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
            if (x <= palette.getX() + (spritegrößex/2 )) {
                if (x >= palette.getX() - (spritegrößex/2 )) {
                    if (y <= palette.getY() + (spritegrößey/2 )) {
                        if (y >= palette.getY() - (spritegrößey/2 )) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    @Override
    public void update() {

    }

    @Override
    public Sprite getSprite() {
        return sprite.getSprite();
    }

    public float getSpritegrößex() {
        return spritegrößex;
    }

    public float getSpritegrößey() {
        return spritegrößey;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}

