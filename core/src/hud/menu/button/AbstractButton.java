package hud.menu.button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class AbstractButton {
    protected Texture background;
    protected Label nameLabel;
    protected int xPosition;
    protected int yPosition;
    protected int width;
    protected int height;

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


