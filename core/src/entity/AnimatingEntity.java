package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

public abstract class AnimatingEntity extends VisualEntity {

    private TextureRegion[][] regions;

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

    public void setSprite(Texture texture, final int columns, final int rows) {
        regions = TextureRegion.split(texture, texture.getWidth() / columns, texture.getHeight() / rows);
        sprite = new Sprite(regions[0][0]);
    }

    public void setSprite(Texture texture) {
        sprite = new Sprite(texture);
    }

}
