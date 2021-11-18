package hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class HealthBar {
    Pixmap hbPixmap;
    Texture background;

    public HealthBar(int health) {
        hbPixmap = new Pixmap( health, 10, Pixmap.Format.RGBA8888 );
        hbPixmap.setColor( 0, 1, 0, 0.75f );
        hbPixmap.fill();

        Pixmap pixmap2 = new Pixmap( health, 10, Pixmap.Format.RGBA8888 );
        pixmap2.setColor(0,0,0,1);
        pixmap2.fill();
        this.background = new Texture(pixmap2);
        pixmap2.dispose();
    }

    public Texture[] getHBTexture(int health) {
        hbPixmap.setColor(Color.CLEAR);
        hbPixmap.fill();
        hbPixmap.setColor(Color.GREEN);
        hbPixmap.fillRectangle(0,0,health,20);
        return new Texture[]{new Texture(hbPixmap), background};
    }
}
