package entities.player;

import com.badlogic.gdx.Gdx;
import funktions.KolisionCheck;

import java.util.ArrayList;


public class PlayerMovement {
    KolisionCheck kolision = new KolisionCheck();

    public void move(Player player, ArrayList<Player> players) {
        float playerX = player.getX();
        float playerY = player.getY();
        //Oben Links
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(0)) && Gdx.input.isKeyPressed(player.getPlayerInput().get(1))) {
            player.setViewdirection(4);
            playerY += player.getMovement();
            playerX -= player.getMovement();
            player.getSprite().setRotation(45f);
        }
        // Unten Links
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(2)) && Gdx.input.isKeyPressed(player.getPlayerInput().get(1))) {
            player.setViewdirection(5);
            playerY -= player.getMovement();
            playerX -= player.getMovement();
            player.getSprite().setRotation(135f);
        }
        // Unten Rechts
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(2)) && Gdx.input.isKeyPressed(player.getPlayerInput().get(3))) {
            player.setViewdirection(6);
            playerY -= player.getMovement();
            playerX += player.getMovement();
            player.getSprite().setRotation(225f);
        }
        //Oben Rechts
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(0)) && Gdx.input.isKeyPressed(player.getPlayerInput().get(3))) {
            player.setViewdirection(7);
            playerY += player.getMovement();
            playerX += player.getMovement();
            player.getSprite().setRotation(315f);
        }
        //Nach oben gehen
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(0))) {
            player.setViewdirection(0);
            playerY += player.getMovement();
            player.getSprite().setRotation(0f);
        }
        //Nach links gehen
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(1))) {
            player.setViewdirection(1);
            playerX -= player.getMovement();
            player.getSprite().setRotation(90f);
        }
        //Nach unten gehen
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(2))) {
            player.setViewdirection(2);
            playerY -= player.getMovement();
            player.getSprite().setRotation(180f);
        }

        //Nach rechts gehen
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(3))) {
            player.setViewdirection(3);
            playerX += player.getMovement();
            player.getSprite().setRotation(270f);
        }
        if (Gdx.input.isKeyJustPressed(player.getPlayerInput().get(4))) {
            player.Shoot();
        }
        if (kolision.wallCheck(playerY) && kolision.wallCheck(playerX) && kolision.playerCheck(playerX, playerY, player, players)) {
            player.setX(playerX);
            player.setY(playerY);
        }
    }
}

