package entities.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

public class PlayerSprites {

    private int column = 0;
    private int row = 0;
    private Sprite sprite;
    private TextureRegion[][] regions;

    public void Sprite(Player player) {
        if (player.getPlayer() == 1) {
            regions = TextureRegion.split(new Texture("player1WalkAnimation.png"), 77, 136);
        }
        if (player.getPlayer() == 2) {
            regions = TextureRegion.split(new Texture("player2WalkAnimation.png"), 77, 136);
        }
        sprite = new Sprite(regions[row][column]);
        sprite.setScale(0.5f);
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
