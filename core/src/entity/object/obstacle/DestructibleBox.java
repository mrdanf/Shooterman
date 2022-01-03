package entity.object.obstacle;

import com.badlogic.gdx.graphics.Texture;
import entity.VisualEntity;

import java.util.ArrayList;

public class DestructibleBox extends VisualEntity {
    private int health = 100;

    public DestructibleBox(Texture texture) {
        super(0, 0, texture);
    }

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


    public boolean placementPossible(ArrayList<Box> boxes, float x, float y, ArrayList<DestructibleBox> destructibleBoxes) {
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

