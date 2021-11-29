package entities.projektile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import entities.player.Player;

public class ProjektileSprites {

    private int column = 0;
    private int row = 0;
    private Sprite sprite;
    private TextureRegion[][] regions;

    public void Sprite(Projektile projektile) {

        regions = TextureRegion.split(new Texture("player2WalkAnimation.png"), 77, 136);

        sprite = new Sprite(regions[row][column]);
        sprite.setScale(0.1f);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                column++;
                if (column > 5) {
                    column = 0;
                }
                sprite.setRegion(regions[row][column]);
            }
        }, 0, 1 / 10f);
        projektile.setSprite(sprite);
    }
}
