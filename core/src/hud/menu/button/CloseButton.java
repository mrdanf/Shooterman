package hud.menu.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Button um das Fenster zu schließen, wird in AbstractWindow eingebunden
 */
public class CloseButton extends AbstractButton {

    public CloseButton(int xTopRight, int yTopRight) {
        this.width = 75;
        this.height = 25;
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(0.5f, 0.1f, 0.1f, 1);
        pixmap.fill();
        this.background = new Texture(pixmap);
        pixmap.dispose();

        this.xPosition = xTopRight - width;
        this.yPosition = yTopRight - height;

        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = new BitmapFont(Gdx.files.internal("msJhengHei.fnt"));
        ls.fontColor = Color.BLACK;
        nameLabel = new Label("x", ls);
        nameLabel.setPosition(this.xPosition + 30, this.yPosition - 3);
    }

}
