package entities.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

public class PlayerSprits {

    private int column = 0;
    private int row = 0;
    private Sprite sprite;
    private TextureRegion[][] regions;

    public void Sprite(Player player) {
        if (player.getPlayer() == 1) {
            regions = TextureRegion.split(new Texture("playerWalkAnimation.png"), 213, 390);
        }
        if (player.getPlayer() == 2) {
            regions = TextureRegion.split(new Texture("playerWalkAnimation.png"), 213, 390);
        }
        sprite = new Sprite(regions[row][column]);
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
        player.setSprite(sprite);
    }


}
