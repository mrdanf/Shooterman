package entities.objects.destructable;

import com.badlogic.gdx.graphics.Texture;
import entities.VisualEntity;

import java.util.ArrayList;
import java.util.Random;

public class DestructibleBox extends VisualEntity {
    private int health = 100;

    public DestructibleBox(Texture texture) {
        super(0, 0, texture);
    }

    public void randomposition(ArrayList<Box> boxes,ArrayList<DestructibleBox> paletten) {
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


    public boolean PlacmentPossible(ArrayList<Box> boxes, float x, float y,ArrayList<DestructibleBox> paletten ) {
        boolean boxok=false;
        for (Box box : boxes) {
                if (x <= box.getX() + (box.getSprite().getWidth() / 2)) {
                    if (x >= box.getX() - (box.getSprite().getWidth() / 2)) {
                        if (y <= box.getY() + (box.getSprite().getWidth() / 2)) {
                            if (y >= box.getY() - (box.getSprite().getWidth() / 2)) {
                                return false;
                            }
                    }
                }
            }
        }
        for (DestructibleBox palette : paletten) {
            if (x <= palette.getX() + (getSprite().getWidth() / 2)) {
                if (x >= palette.getX() - (getSprite().getWidth() / 2)) {
                    if (y <= palette.getY() + (getSprite().getHeight() / 2)) {
                        if (y >= palette.getY() - (getSprite().getHeight() / 2)) {
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}

