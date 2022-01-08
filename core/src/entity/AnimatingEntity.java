package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

public abstract class AnimatingEntity extends VisualEntity {

    private TextureRegion[][] regions;

    /**
     * Erzeugt die Animationen für den Spieler
     *
     * @param x       Aktuelle Position des Spielers auf der x-Achse
     * @param y       Aktuelle Position des Spielers auf der y-Achse
     * @param width   Weite des einzelnen Bildes
     * @param height  Höhe des einzelnen Bildes
     * @param texture Aktuelle Texture
     * @param columns Anzahl der Pixel horizontal in der das Bild geschnitten werden muss
     * @param rows    Anzahl der Pixel vertikal in der das Bild geschnitten werden muss
     */
    public AnimatingEntity(float x, float y, float width, float height, Texture texture, final int columns,
                           final int rows) {
        super(x, y, texture, width, height);
        regions = TextureRegion.split(texture, texture.getWidth() / columns, texture.getHeight() / rows);
        sprite = new Sprite(regions[0][0]);
        sprite.setX(this.x);
        sprite.setY(this.y);
        Timer.schedule(new Timer.Task() {
            int column = 0;
            int row = 0;

            @Override
            public void run() {
                column++;
                row++;
                if (column > columns - 1) {
                    column = 0;
                }
                if (row > rows - 1) {
                    row = 0;
                }
                sprite.setRegion(regions[row][column]);
            }

        }, 0, 1 / 10f);
    }

    /**
     * Zeigt eine Aneinanderreihung von Texturen
     *
     * @param texture Aktuelle Texturen
     * @param columns Anzahl der Pixel horizontal in der das Bild geschnitten werden muss
     * @param rows    Anzahl der Pixel vertikal in der das Bild geschnitten werden muss
     */
    public void setSprite(Texture texture, final int columns, final int rows) {
        regions = TextureRegion.split(texture, texture.getWidth() / columns, texture.getHeight() / rows);
        sprite = new Sprite(regions[0][0]);
    }

    /**
     * Zeigt ein einzelnes Bild an
     *
     * @param texture Aktuelle Texture die angezeigt werden soll
     */
    public void setSprite(Texture texture) {
        sprite = new Sprite(texture);
    }

}
