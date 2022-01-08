package hud.menu.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Klasse für Buttons im Hauptmenü
 */
public class MenuButton extends AbstractButton {

    /**
     * Beim Erstellen sind Größe und Farbe vorgegeben
     * @param label angezeigter Text auf dem Button
     * @param offset bei einem Offset von 0 wird der Button in der Fenstermitte dargestellt,
     *               wenn man einen positiven Wert eingibt, wird dieser um so viele Pixel nach oben verschoben
     *               und mit einem negativen Wert nach unten
     */
    public MenuButton(String label, int offset) {
        this.width = 300;
        this.height = 50;

        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(0.5f, 0.5f, 0.5f, 1);
        pixmap.fill();
        this.background = new Texture(pixmap);
        pixmap.dispose();

        // Calculation position to put it in the center of the screen:
        // (ScreenWidth / 2) - (ButtonWidth / 2)
        this.xPosition = 350;
        // (ScreenHeight / 2) - (ButtonHeight / 2) + offset
        this.yPosition = 475 + offset;

        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = new BitmapFont(Gdx.files.internal("msJhengHei.fnt"));
        nameLabel = new Label(label, ls);
        nameLabel.setSize(0, 0);
        nameLabel.setPosition(this.xPosition + 10, this.yPosition + 25);
    }
}
