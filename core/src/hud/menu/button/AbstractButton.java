package hud.menu.button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Abstrakte Basis Klasse für Menüknöpfe
 *
 * Jeder Button soll eine Textur, einen Namen, Position und Größe haben
 */
public class AbstractButton {
    protected Texture background;
    protected Label nameLabel;
    protected int xPosition;
    protected int yPosition;
    protected int width;
    protected int height;

    /**
     * Checkt, ob gegebene Koordinaten mit in dem Button liegen
     * @return True, wenn der Button gedrückt wurde
     */
    public boolean isPressed(int x, int y) {
        if (x >= xPosition && x <= xPosition + width) {
            if (y >= yPosition && y <= yPosition + height) {
                return true;
            }
        }
        return false;
    }

    public Texture getBackground() {
        return background;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    @Override
    public String toString() {
        return nameLabel.getText().toString();
    }
}


