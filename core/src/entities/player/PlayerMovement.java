package entities.player;

import com.badlogic.gdx.Gdx;
import funktions.KolisionCheck;

import java.util.ArrayList;


public class PlayerMovement {
    KolisionCheck kolision = new KolisionCheck();

    public void move(Player player, ArrayList<Player> players) {
        float playerX = player.getX();
        float playerY = player.getY();

    public void move(Player player) {
        //Nach rechts gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(0))) {
            player.setViewdirection(0);
            playerX += player.getMovement();
            player.getSprite().setRotation(270f);
        }
        //Nach oben gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(1))) {
            player.setViewdirection(1);
            playerY += player.getMovement();
            player.getSprite().setRotation(0f);
        }
        //Nach links gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(2))) {
            player.setViewdirection(2);
            playerX -= player.getMovement();
            player.getSprite().setRotation(90f);
        }
        //Nach unten gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(3))) {
            player.setViewdirection(3);
            playerY -= player.getMovement();
            player.getSprite().setRotation(180f);
        }

        if (up && left) { //Nach oben links gehen
            player.getSprite().setRotation(45f);
        } else if (down && left) { // Nach unten links gehen
            player.getSprite().setRotation(135f);
        } else if (down && right) { // Nach unten rechts gehen
            player.getSprite().setRotation(225f);
        } else if (up && right) { // Nach oben rechts gehen
            player.getSprite().setRotation(315f);
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
