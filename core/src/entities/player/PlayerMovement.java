package entities.player;

import com.badlogic.gdx.Gdx;
import funktions.KolisionCheck;


public class PlayerMovement {
    KolisionCheck kolision = new KolisionCheck();

    public void move(Player player) {
        float playerXY ;

        //Nach rechts gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(0))) {
            player.setViewdirection(0);
            playerXY  =player.getX()+ player.getMovement();
            if (kolision.wallCheck(playerXY)) {
                player.setX(playerXY);
            }
        }
        //Nach oben gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(1))) {
            player.setViewdirection(1);
            playerXY=player.getY()+ player.getMovement();
            if (kolision.wallCheck(playerXY)) {
                player.setY(playerXY);
            }

        }
        //Nach links gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(2))) {
            player.setViewdirection(2);
            playerXY =player.getX()- player.getMovement();
            if (kolision.wallCheck(playerXY)) {
                player.setX(playerXY);
            }
        }
        //Nach unten gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(3))) {
            player.setViewdirection(3);
            playerXY=player.getY()- player.getMovement();
            if (kolision.wallCheck(playerXY)) {
                player.setY(playerXY);
            }
        }
        if (Gdx.input.isKeyJustPressed(player.getPlayerInput().get(4))) {
            player.Shoot();
        }
    }
}
