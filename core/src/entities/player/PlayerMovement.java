package entities.player;

import com.badlogic.gdx.Gdx;
import entities.objects.destructable.Box;
import entities.objects.destructable.DestructibleBox;
import entities.objects.weapons.Weapon;
import funktions.KolisionCheck;

import java.util.ArrayList;


public class PlayerMovement {
    KolisionCheck kolision = new KolisionCheck();

    public void move(Player player, ArrayList<Player> players, ArrayList<Box> boxes,
                     ArrayList<DestructibleBox> paletten, ArrayList<Weapon> weapons) {
        float playerX = player.getX();
        float playerY = player.getY();
        //Oben Links
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_UP)) && Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_LEFT))) {
            player.setViewdirection(4);
            playerY += player.getMovement();
            playerX -= player.getMovement();
            player.getSprite().setRotation(45f);
        }
        // Unten Links
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_DOWN)) && Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_LEFT))) {
            player.setViewdirection(5);
            playerY -= player.getMovement();
            playerX -= player.getMovement();
            player.getSprite().setRotation(135f);
        }
        // Unten Rechts
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_DOWN)) && Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_RIGHT))) {
            player.setViewdirection(6);
            playerY -= player.getMovement();
            playerX += player.getMovement();
            player.getSprite().setRotation(225f);
        }
        //Oben Rechts
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_UP)) && Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_RIGHT))) {
            player.setViewdirection(7);
            playerY += player.getMovement();
            playerX += player.getMovement();
            player.getSprite().setRotation(315f);
        }
        //Nach oben gehen
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_UP))) {
            player.setViewdirection(0);
            playerY += player.getMovement();
            player.getSprite().setRotation(0f);
        }
        //Nach links gehen
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_LEFT))) {
            player.setViewdirection(1);
            playerX -= player.getMovement();
            player.getSprite().setRotation(90f);
        }
        //Nach unten gehen
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_DOWN))) {
            player.setViewdirection(2);
            playerY -= player.getMovement();
            player.getSprite().setRotation(180f);
        }

        //Nach rechts gehen
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_RIGHT))) {
            player.setViewdirection(3);
            playerX += player.getMovement();
            player.getSprite().setRotation(270f);
        }
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.SHOOT))) {
            player.Shoot();
        }
        if (kolision.wallCheck(playerY) && kolision.wallCheck(playerX) && kolision.playerCheck(playerX, playerY, player, players)) {
            if (kolision.playerCheckbox(playerX, playerY, boxes)&&kolision.playerCheckpallette(playerX, playerY,paletten)) {
                player.setX(playerX);
                player.setY(playerY);
            }
        }

        // Waffe aufheben
        if (Gdx.input.isKeyJustPressed(player.getPlayerInput().get(Input.PICK_UP))) {
            for (Weapon weapon : weapons) {
                if (kolision.playerCheckWeapon(playerX, playerY, weapon)) {
                    // Waffe wird aufgehoben
                    player.pickUpWeapon(weapon);
                    weapon.setOnGround(false);
                }
            }
        }

        // Slot wechseln
        if (Gdx.input.isKeyJustPressed(player.getPlayerInput().get(Input.CHANGE_WEAPON))) {
            player.switchWeapon();
        }
    }
}

