package entities.player;

import com.badlogic.gdx.Gdx;

public class PlayerMovement {


    public void move(Player player) {
        boolean up, left, down, right;
        up = left = down = right = false;

        //Nach rechts gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(0))) {
            player.setX(player.getX() + player.getMovement());
            player.getSprite().setRotation(270f);
            right = true;
        }
        //Nach links gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(2))) {
            player.setX(player.getX() - player.getMovement());
            player.getSprite().setRotation(90f);
            left = true;
        }
        //Nach oben gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(1))) {
            player.setY(player.getY() + player.getMovement());
            player.getSprite().setRotation(0f);
            up = true;
        }
        //Nach unten gehen
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(3))) {
            player.setY(player.getY() - player.getMovement());
            player.getSprite().setRotation(180f);
            down = true;
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
    }
}
