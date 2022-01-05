package function;

import entity.VisualEntity;
import entity.object.ground.Item;
import entity.object.obstacle.DestructibleBox;
import entity.object.weapon.Weapon;
import entity.player.Player;
import entity.projectile.Projectile;

import java.util.ArrayList;

public class CollisionCheck {

    public boolean outerWallCheck(float x, float y) {
        return x >= 100f && x <= 860f && y >= 95f && y <= 835f;
    }

    public boolean playerCollidesPlayer(Player player, ArrayList<Player> players) {
        for (Player enemyPlayer : players) {
            if (player != enemyPlayer) {
                if (!enemyPlayer.isColliding(player) || !enemyPlayer.isAlive()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean playerCollidesEntity(Player player, ArrayList<VisualEntity> entities) {
        for (VisualEntity entity : entities) {
            if (entity.isColliding(player)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param players
     * @return
     */
    public Projectile projectileHitsPlayer(ArrayList<Player> players) {
        for (Player player : players) {
            for (Projectile projectile : player.getProjectiles()) {
                for (Player playerHit : players) {
                    if (projectile.getPlayer() != playerHit) {
                        if (projectile.isColliding(playerHit)) {
                            playerHit.receiveDamage(player.getActiveWeapon().getPower());
                            return projectile;
                        }
                    }
                }
            }
        }
        return null;
    }

    public Projectile projectileHitsObstacle(ArrayList<VisualEntity> obstacles, ArrayList<Player> players) {
        for (Player player : players) {
            for (Projectile projectile : player.getProjectiles()) {
                for (VisualEntity obstacle : obstacles) {
                    if (projectile.isColliding(obstacle)) {

                        if (obstacle instanceof DestructibleBox) {
                            // Nur wenn das Hindernis zerstörbar ist, soll es Schaden erhalten
                            ((DestructibleBox) obstacle).receiveDamage(player.getActiveWeapon().getPower());
                        }

                        return projectile;
                    }
                }
            }
        }
        return null;
    }

    public boolean playerInWeaponRange(Player player, Weapon weapon) {
        if (weapon.isColliding(player)) {
            return true;
        }

        return false;
    }


    public boolean playerInPickUpRange(Player player, Item item) {
        if (item.isColliding(player)) {
            return true;
        }

        return false;
    }
}
