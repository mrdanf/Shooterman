package entity.object.obstacle;

import com.badlogic.gdx.graphics.Texture;
import entity.VisualEntity;

import java.util.ArrayList;

public class DestructibleBox extends VisualEntity {
    private int health = 100;

    public DestructibleBox(Texture texture) {
        super(0, 0, texture, 100, 80);
    }

    public DestructibleBox(Texture texture, float height) {
        super(0, 0, texture, 100,  height);
    }

    /**
     * Setzt eine zerstörbare Blockade an eine random position auf der Karte.
     *
     * @param boxes Ist eine Liste aller Blockaden, die es im Spiel gibt
     * @param destructibleBoxes Ist eine Liste aller zerstörbaren Blockaden, die es im Spiel gibt
     */
    public void randomPosition(ArrayList<Box> boxes, ArrayList<DestructibleBox> destructibleBoxes) {
        int max = 736;
        int min = 156;
        float x = 0;
        float y = 0;
        boolean possible = true;
        while (possible) {
            x = min + (int) (Math.random() * ((max - min) + 1));
            y = min + (int) (Math.random() * ((max - min) + 1));
            if (placementPossible(boxes, x, y, destructibleBoxes)) {
                possible = false;
            }
        }
        setX(x);
        setY(y);
    }


    /**
     *Prüft, ob das Platzieren überhaupt möglich ist und keine andere Blockade, die
     *  vorher platziert, wurde im weg ist,
     *  um ein Überlappen der Objekte zu verhindern
     *
     * @param boxes Ist eine Liste aller Blockaden, die es im Spiel gibt
     * @param destructibleBoxes Ist eine Liste aller zerstörbaren Blockaden,
     *                          die es im Spiel gibt
     * @param x Ist der Punkt auf der X Achse des zu prüfenden Objektes
     * @param y Ist der Punkt auf der Y Achse des zu prüfenden Objektes
     * @return
     * true = wenn alle Überprüfungen nicht zutreffen sind/
     * false = wenn eine Blockade schon an der position ist
     *
     *
     */
    public boolean placementPossible(ArrayList<Box> boxes, float x, float y, ArrayList<DestructibleBox> destructibleBoxes) {
        for (Box box : boxes) {
            if (box.getSprite().getBoundingRectangle().contains(x, y)) {
                return false;
            }
        }
        for (DestructibleBox destructibleBox : destructibleBoxes) {
            if (destructibleBox != this) {
                if (destructibleBox.getSprite().getBoundingRectangle().contains(x, y)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     *Setzt die Hitbox für die Zerstörbare box
     */
    @Override
    protected void updateHitbox() {
        hitbox.setPosition(x, y + 10);
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

    public void receiveDamage(int power) {
        this.health -= power;
    }
}

