package entities.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;

public class Playermovment {


    public void move(Player player) {
        //Nach rechts gehen
        if (Gdx.input.isKeyPressed(player.playerinput.get(0))) {
            player.setX(player.getX() + player.getMovement());
        }
        //Nach oben gehen
        if (Gdx.input.isKeyPressed(player.playerinput.get(1))) {
            player.setY(player.getY() + player.getMovement());
        }
        //Nach links gehen
        if (Gdx.input.isKeyPressed(player.playerinput.get(2))) {
            player.setX(player.getX() - player.getMovement());
        }
        //Nach unten gehen
        if (Gdx.input.isKeyPressed(player.playerinput.get(3))) {
            player.setY(player.getY() - player.getMovement());
        }
    }
}
