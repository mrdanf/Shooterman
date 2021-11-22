package entities.objects.destructable;

import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.Entity;

import java.util.ArrayList;
import java.util.Random;

public class Box extends Entity {
    BoxSprite sprite = new BoxSprite();
    float spritegrößex = 81f;
    float spritegrößey = 85f;

    public Box() {
        sprite.Sprite(this);
        setY(0);
        setX(0);
    }

    public void randomposition(ArrayList<Box> boxes) {
        int max = 736;
        int min = 156;
        float x = 0;
        float y = 0;
        boolean possible = false;
        Random rn = new Random();
        while (!possible) {
            x = min + (int) (Math.random() * ((max - min) + 1));
            y = min + (int) (Math.random() * ((max - min) + 1));
            if (PlacmentPossible(boxes, x, y)) {
                possible=true;
            }
        }
        setX(x);
        setY(y);
    }

    public boolean PlacmentPossible(ArrayList<Box> boxes, float x, float y) {
        for (Box box : boxes) {
            if (box != this) {
                if (x <= box.getX() + (spritegrößex )) {
                    if (x >= box.getX() - (spritegrößex )) {
                        if (y <= box.getY() + (spritegrößey )) {
                            if (y >= box.getY() - (spritegrößey )) {
                                return false;
                            }
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
}
