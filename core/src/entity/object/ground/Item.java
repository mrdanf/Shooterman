package entity.object.ground;

import com.badlogic.gdx.graphics.Texture;
import entity.VisualEntity;
import entity.object.obstacle.Box;
import entity.object.obstacle.DestructibleBox;
import entity.object.weapon.Weapon;
import entity.player.Player;

import java.util.ArrayList;

public abstract class Item extends VisualEntity {

    private boolean onGround = true;

    public Item(Texture texture, float width, float height) {
        super(texture, width, height);
    }

    public Item(float x, float y, Texture texture) {
        super(x, y, texture, 80, 80);
    }


    /**
     * Setzt die Größe der Hitbox auf die Spritegröße
     */
    @Override
    protected void updateHitbox() {
        float x = sprite.getX() - ((hitbox.width - sprite.getWidth()) / 2);
        float y = sprite.getY() - ((hitbox.height - sprite.getHeight()) / 2);
        hitbox.setPosition(x, y);
    }

    public abstract void doEffect(Player player);

    public void setOnGround(boolean b) {
        this.onGround = b;
    }

    public boolean isOnGround() {
        return onGround;
    }


    /**
     * Setzt das Objekt an eine zufällige Position auf der Karte.
     *
     * @param boxes             Ist eine Liste aller Boxen, die es im Spiel gibt
     * @param destructibleBoxes Ist eine Liste aller zerstörbaren Boxen, die es im Spiel gibt
     * @param items             Ist eine Liste aller Items, die es im Spiel gibt
     * @param weapons           Ist eine Liste aller Waffen, die es im Spiel gibt
     */
    public void randomPosition(ArrayList<Box> boxes, ArrayList<DestructibleBox> destructibleBoxes, ArrayList<Weapon> weapons,
                               ArrayList<Item> items) {
        int max = 736;
        int min = 156;
        float x = 0;
        float y = 0;
        boolean possible = true;
        while (possible) {
            x = min + (int) (Math.random() * ((max - min) + 1));
            y = min + (int) (Math.random() * ((max - min) + 1));
            if (placementPossible(boxes, x, y, destructibleBoxes, weapons, items)) {
                possible = false;
            }
        }
        setX(x);
        setY(y);
    }

    /**
     * Prüft, ob das Platzieren überhaupt möglich ist und kein anderes Objekt im Weg ist, um ein Überlappen der Objekte zu verhindern
     *
     * @param boxes             Ist eine Liste aller Boxen, die es im Spiel gibt
     * @param destructibleBoxes Ist eine Liste aller zerstörbaren Boxen, die es im Spiel gibt
     * @param items             Ist eine Liste aller Items, die es im Spiel gibt
     * @param weapons           Ist eine Liste aller Waffen, die es im Spiel gibt
     * @param x                 Ist der Punkt auf der X Achse des zu prüfenden Objektes
     * @param y                 Ist der Punkt auf der Y Achse des zu prüfenden Objektes
     * @return true, wenn alle Überprüfungen nicht zutreffen
     */
    public boolean placementPossible(ArrayList<Box> boxes, float x, float y, ArrayList<DestructibleBox> destructibleBoxes,
                                     ArrayList<Weapon> weapons, ArrayList<Item> items) {
        for (Box box : boxes) {
            if (box.getSprite().getBoundingRectangle().contains(x, y)) {
                return false;
            }
        }
        for (DestructibleBox destructibleBox : destructibleBoxes) {
            if (destructibleBox.getSprite().getBoundingRectangle().contains(x, y)) {
                return false;
            }
        }
        for (Weapon weapon : weapons) {
            if (weapon.getSprite().getBoundingRectangle().contains(x, y)) {
                return false;
            }
        }
        for (Item item : items) {
            if (item != this) {
                if (item.getSprite().getBoundingRectangle().contains(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }
}
