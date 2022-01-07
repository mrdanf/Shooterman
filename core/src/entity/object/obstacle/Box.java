package entity.object.obstacle;

import com.badlogic.gdx.graphics.Texture;
import entity.VisualEntity;

import java.util.ArrayList;

public class Box extends VisualEntity {

    public Box() {
        super(0, 0, new Texture("hindernis/Sandsacke.png"), 80, 80);
    }


    /**
     * Setzt die Box an eine random position auf der karte
     */
    public void randomPosition(ArrayList<Box> boxes) {
        int max = 736;
        int min = 156;
        float x = 0;
        float y = 0;
        boolean possible = false;
        while (!possible) {
            x = min + (int) (Math.random() * ((max - min) + 1));
            y = min + (int) (Math.random() * ((max - min) + 1));
            if (placementPossible(boxes, x, y)) {
                possible = true;
            }
        }
        setX(x);
        setY(y);
    }

    /**
     *Prüft ob das platzieren überhaubt möglich ist und keine anderen objekt im weg sind
     */
    public boolean placementPossible(ArrayList<Box> boxes, float x, float y) {
        for (Box box : boxes) {
            if (box != this) {
                if (box.getSprite().getBoundingRectangle().contains(x, y)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void update() {

    }

}
