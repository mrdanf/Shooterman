package hud.menu.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import hud.menu.AbstractWindow;

public class HelpWindow extends AbstractWindow {
    private Label textLabel;
    private static final String text = "Hilfe\n Spieler 1 WASD \t Spieler 2 IJKL";
    public HelpWindow() {
        super(800, 800, new Color(0.6f, 0.6f, 0.6f, 1));

        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = new BitmapFont(Gdx.files.internal("msJhengHei.fnt"));
        textLabel = new Label(text, ls);
        textLabel.setPosition(this.xOffset+30, this.yOffset+700);
    }

    public Label getTextLabel() {
        return textLabel;
    }
}
