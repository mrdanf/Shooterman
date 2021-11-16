package entities.player;

import com.badlogic.gdx.Gdx;

public class PlayerMovement {


    public void move(Player player) {
        //Nach rechts gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(0))) {
            player.setX(player.getX() + player.getMovement());
        }
        //Nach oben gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(1))) {
            player.setY(player.getY() + player.getMovement());
        }
        //Nach links gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(2))) {
            player.setX(player.getX() - player.getMovement());
        }
        //Nach unten gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(3))) {
            player.setY(player.getY() - player.getMovement());
        }
    }
}
