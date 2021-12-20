package hud.menu.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class MenuButton extends AbstractButton {

    public MenuButton(String label, int offset) {
        this.width  = 300;
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
        nameLabel.setPosition(this.xPosition+10, this.yPosition+25);
    }
}
