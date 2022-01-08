package entity.object.obstacle;

import com.badlogic.gdx.graphics.Texture;
import entity.VisualEntity;

import java.util.ArrayList;

public class Box extends VisualEntity {

    public Box() {
        super(0, 0, new Texture("hindernis/Sandsacke.png"), 80, 80);
    }


    /**
     * Setzt eine Blockade an eine zufällige Position auf der Karte.
     *
     * @param boxes Ist eine Liste aller Blockaden, die es im Spiel gibt
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
     * Prüft, ob das Platzieren überhaupt möglich ist und keine andere Blockade, die vorher platziert, wurde im Weg ist,
     * um ein Überlappen der Objekte zu verhinder
     *
     * @param boxes Ist eine Liste aller Blockaden, die es im Spiel gibt
     * @param x     Ist der Punkt auf der X Achse des zu prüfenden Objektes
     * @param y     Ist der Punkt auf der Y Achse des zu prüfenden Objektes
     * @return true = wenn alle Überprüfungen nicht zutreffen sind/
     * false = wenn eine Blockade schon an der position ist
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
