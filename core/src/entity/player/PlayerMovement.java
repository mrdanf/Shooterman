package entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import entity.object.obstacle.Box;
import entity.object.obstacle.DestructibleBox;
import entity.object.weapon.Weapon;
import function.CollisionCheck;

import java.util.ArrayList;


public class PlayerMovement {
    private CollisionCheck collisionCheck = new CollisionCheck();

    public void move(Player player, ArrayList<Player> players, ArrayList<Box> boxes,
                     ArrayList<DestructibleBox> destructibleBoxes, ArrayList<Weapon> weapons) {
        float playerX = player.getX();
        float playerY = player.getY();
        //Oben Links
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_UP)) && Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_LEFT))) {
            player.setViewDirection(4);
            playerY += player.getMovement();
            playerX -= player.getMovement();
            player.getSprite().setRotation(45f);
        }
        // Unten Links
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_DOWN)) && Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_LEFT))) {
            player.setViewDirection(5);
            playerY -= player.getMovement();
            playerX -= player.getMovement();
            player.getSprite().setRotation(135f);
        }
        // Unten Rechts
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_DOWN)) && Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_RIGHT))) {
            player.setViewDirection(6);
            playerY -= player.getMovement();
            playerX += player.getMovement();
            player.getSprite().setRotation(225f);
        }
        //Oben Rechts
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_UP)) && Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_RIGHT))) {
            player.setViewDirection(7);
            playerY += player.getMovement();
            playerX += player.getMovement();
            player.getSprite().setRotation(315f);
        }
        //Nach oben gehen
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_UP))) {
            player.setViewDirection(0);
            playerY += player.getMovement();
            player.getSprite().setRotation(0f);
        }
        //Nach links gehen
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_LEFT))) {
            player.setViewDirection(1);
            playerX -= player.getMovement();
            player.getSprite().setRotation(90f);
        }
        //Nach unten gehen
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_DOWN))) {
            player.setViewDirection(2);
            playerY -= player.getMovement();
            player.getSprite().setRotation(180f);
        }

        //Nach rechts gehen
        else if (Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.MOVE_RIGHT))) {
            player.setViewDirection(3);
            playerX += player.getMovement();
            player.getSprite().setRotation(270f);
        }
        if (Gdx.input.isKeyPressed(player.getPlayerInput().get(Input.SHOOT))) {
            player.Shoot();
        }
        if (collisionCheck.wallCheck(playerY) && collisionCheck.wallCheck(playerX) && collisionCheck.playerCheck(playerX,
                playerY, player,
                players)) {
            if (collisionCheck.playerCheckBox(playerX, playerY, boxes) && collisionCheck.playerCheckDestructibleBox(playerX, playerY, destructibleBoxes)) {
                player.setX(playerX);
                player.setY(playerY);
            }
        }

        // TODO: eventuell besser mit X / Y von BoundingRectangle arbeiten
        /*
        float pHitX = player.getHitboxX();
        float pHitY = player.getHitboxY();
        if (collisionCheck.wallCheck(pHitY) && collisionCheck.wallCheck(pHitX) && collisionCheck.playerCheck(pHitX, pHitY, player,
                players)) {
            if (collisionCheck.playerCheckBox(pHitX, pHitY, boxes) && collisionCheck.playerCheckDestructibleBox(pHitX, pHitY, destructibleBoxes)) {
                player.setX(playerX);
                player.setY(playerY);
            }
        }
        */

        // Waffe aufheben
        if (Gdx.input.isKeyJustPressed(player.getPlayerInput().get(Input.PICK_UP))) {
            // TODO setSprite wieder rausnehmen
            player.setSprite(new Texture("spieler/Spieler1AKLaufenNeu.png"), 6, 1);
            // TODO END
            for (Weapon weapon : weapons) {
                if (collisionCheck.playerCheckWeapon(playerX, playerY, weapon)) {
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

