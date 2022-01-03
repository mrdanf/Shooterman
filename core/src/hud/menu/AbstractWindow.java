package hud.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import hud.menu.button.AbstractButton;
import hud.menu.button.CloseButton;

import java.util.ArrayList;

public abstract class AbstractWindow {
    protected Texture window;
    protected Texture background;
    private CloseButton close;
    protected int xOffset;
    protected int yOffset;
    protected ArrayList<AbstractButton> buttons;

    public AbstractWindow(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        this.window = new Texture(pixmap);

        pixmap = new Pixmap(1000, 1000, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 0.5f);
        pixmap.fill();
        this.background = new Texture(pixmap);

        pixmap.dispose();

        this.xOffset = (1000 - width) / 2;
        this.yOffset = (1000 - height) / 2;

        this.close = new CloseButton(xOffset + width, yOffset + height);

        buttons = new ArrayList<>();
        buttons.add(this.close);
    }

    public String update() {
        if (Gdx.input.justTouched()) {
            // Converting the actual coordinates to our wacky 1000x1000 Map coordinates
            int x = (int) (((float) Gdx.input.getX() / (float) Gdx.graphics.getWidth()) * 1000f);
            int y = (int) (((float) Gdx.input.getY() / (float) Gdx.graphics.getHeight()) * 1000f);
            y = Math.abs(y - 1000); // inverting y, because origin for mouse input is top left, not bottom left
            for (AbstractButton b : buttons) {
                if (b.isPressed(x, y)) {
                    return b.toString();
                }
            }
        }
        return null;
    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public Texture getWindow() {
        return window;
    }

    public Texture getBackground() {
        return background;
    }

    public ArrayList<AbstractButton> getButtons() {
        return buttons;
    }
}
