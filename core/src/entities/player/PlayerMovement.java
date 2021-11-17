package entities.player;

import com.badlogic.gdx.Gdx;


public class PlayerMovement {


    public void move(Player player) {
        //Nach rechts gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(0))) {
            player.setX(player.getX() + player.getMovement());
            player.setViewdirection(0);
        }
        //Nach oben gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(1))) {
            player.setY(player.getY() + player.getMovement());
            player.setViewdirection(1);
        }
        //Nach links gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(2))) {
            player.setX(player.getX() - player.getMovement());
            player.setViewdirection(2);
        }
        //Nach unten gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(3))) {
            player.setY(player.getY() - player.getMovement());
            player.setViewdirection(3);
        }
        if (Gdx.input.isKeyJustPressed(player.getPlayerInput().get(4))) {
            player.Shoot();
        }
    }
}
