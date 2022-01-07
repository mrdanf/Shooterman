package hud.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import hud.menu.AbstractWindow;

/**
 * Hilfefenster in dem die Steuerung angezeigt wird
 * Einfachhaltshalber wird einfach ein Label mit einem String erzeugt und angezeigt
 * Die Position des Label muss manuel gesetzt werden, da es kein Objekt des AbtractWindow ist
 */
public class HelpWindow extends AbstractWindow {
    private Label textLabel;
    private static final String text = "Hilfe\n " +
            "                 Spieler 1     Spieler 2\n" +
            "Laufen:      WASD             IJKL  \n" +
            "Feuern:      LStrg            RStrg \n" +
            "Aufheben:     E                   O\n" +
            "Wechseln:     Q                  U\n";

    public HelpWindow() {
        super(800, 800, new Color(0.6f, 0.6f, 0.6f, 1));

        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = new BitmapFont(Gdx.files.internal("msJhengHei.fnt"));
        textLabel = new Label(text, ls);
        textLabel.setPosition(this.xOffset + 30, this.yOffset + 350);
    }

    public Label getTextLabel() {
        return textLabel;
    }
}
