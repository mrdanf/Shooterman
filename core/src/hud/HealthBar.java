package hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class HealthBar {
    private Pixmap hbPixmap;
    private Texture background;

    public HealthBar(int health) {
        hbPixmap = new Pixmap(health, 15, Pixmap.Format.RGBA8888);
        hbPixmap.setColor(0, 1, 0, 0.75f);
        hbPixmap.fill();

        Pixmap pixmap2 = new Pixmap(health, 15, Pixmap.Format.RGBA8888);
        pixmap2.setColor(0, 0, 0, 1);
        pixmap2.fill();
        this.background = new Texture(pixmap2);
        pixmap2.dispose();
    }

    public Texture[] getHBTexture() {
        return new Texture[]{background, new Texture(hbPixmap),};
    }

    public void updateHealth(int health) {
        if (health > 0) {
            hbPixmap.setColor(Color.CLEAR);
            hbPixmap.fill();
            hbPixmap.setColor(Color.GREEN);
            hbPixmap.fillRectangle(0, 0, health, 20);
        } else {
            hbPixmap = new Pixmap(130, 50, Pixmap.Format.RGBA8888);
            hbPixmap.setColor(.5f, .1f, .1f, 1f);
            hbPixmap.fill();
        }

    }
}
