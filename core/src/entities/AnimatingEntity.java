package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

public abstract class AnimatingEntity extends NonVisualEntity {

    private Sprite sprite;
    private TextureRegion[][] regions;

    public AnimatingEntity(float x, float y, Texture texture, final int columns, final int rows) {
        super(x, y);
        regions = TextureRegion.split(texture, texture.getWidth() / columns, texture.getHeight() / rows);
        sprite = new Sprite(regions[0][0]);
        // TODO: sprite.setScale(scale);
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

    public void setScale(float scale) {
        sprite.setScale(scale);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Texture texture, final int columns, final int rows) {
        regions = TextureRegion.split(texture, texture.getWidth() / columns, texture.getHeight() / rows);
        sprite = new Sprite(regions[0][0]);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        sprite.setX(x);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        sprite.setY(y);
    }
}
