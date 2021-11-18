package entities.player;

import com.badlogic.gdx.Gdx;
import funktions.KolisionCheck;

import java.util.ArrayList;


public class PlayerMovement {
    KolisionCheck kolision = new KolisionCheck();

    public void move(Player player, ArrayList<Player> players) {
        float playerX = player.getX();
        float playerY = player.getY();

        //Nach rechts gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(0))) {
            player.setViewdirection(0);
            playerX += player.getMovement();

        }
        //Nach oben gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(1))) {
            player.setViewdirection(1);
            playerY += player.getMovement();
        }
        //Nach links gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(2))) {
            player.setViewdirection(2);
            playerX -= player.getMovement();
        }
        //Nach unten gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(3))) {
            player.setViewdirection(3);
            playerY -= player.getMovement();

        }
            if (kolision.wallCheck(playerY)&& kolision.wallCheck(playerX)&& kolision.playerCheck(playerX,playerY,player, players)) {
                player.setX(playerX);
                player.setY(playerY);
            }
        if (Gdx.input.isKeyJustPressed(player.getPlayerInput().get(4))) {
            player.Shoot();
        }
    }
}
