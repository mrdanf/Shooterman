package entity.player;

import com.badlogic.gdx.Gdx;
import entity.VisualEntity;
import entity.object.obstacle.Box;
import entity.object.obstacle.DestructibleBox;
import entity.object.weapon.Weapon;
import function.CollisionCheck;

import java.util.ArrayList;


public class PlayerMovement {
    private CollisionCheck collisionCheck = new CollisionCheck();

    public void move(Player player, ArrayList<Player> players, ArrayList<Box> boxes,
                     ArrayList<DestructibleBox> destructibleBoxes, ArrayList<Weapon> weapons) {
        float oldX = player.getX();
        float oldY = player.getY();
        float playerX = oldX;
        float playerY = oldY;
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
            player.shoot();
        }

        player.setX(playerX);
        player.setY(playerY);

        if (!movementCollisionFree(playerX, playerY, player, players, boxes, destructibleBoxes)) {
            // Spieler kollidiert gegen etwas, kann sich also nicht bewegen und gesetzte Koordinaten werden
            // zurückgesetzt
            player.setX(oldX);
            player.setY(oldY);
        }

        // Waffe aufheben
        if (Gdx.input.isKeyJustPressed(player.getPlayerInput().get(Input.PICK_UP))) {
            for (Weapon weapon : weapons) {
                if (collisionCheck.playerInWeaponRange(player, weapon)) {
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

    private boolean movementCollisionFree(float playerX, float playerY, Player player, ArrayList<Player> players,
                                          ArrayList<Box> boxes, ArrayList<DestructibleBox> destructibleBoxes) {
        return collisionCheck.outerWallCheck(playerX, playerY)
                && collisionCheck.playerCollidesPlayer(player, players)
                && collisionCheck.playerCollidesEntity(player, (ArrayList<VisualEntity>) (ArrayList<?>) boxes)
                && collisionCheck.playerCollidesEntity(player, (ArrayList<VisualEntity>) (ArrayList<?>) destructibleBoxes);
    }
}

