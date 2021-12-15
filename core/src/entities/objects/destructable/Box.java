package entities.objects.destructable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.VisualEntity;

import java.util.ArrayList;
import java.util.Random;

public class Box extends VisualEntity {

    public Box() {
        super(0, 0, new Texture("Sandsacke.png"));
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
                if (x <= box.getX() + (getSprite().getWidth())) {
                    if (x >= box.getX() - (getSprite().getWidth())) {
                        if (y <= box.getY() + (getSprite().getHeight())) {
                            if (y >= box.getY() - (getSprite().getHeight())) {
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

}
