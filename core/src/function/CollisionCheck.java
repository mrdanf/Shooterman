package function;

import entity.VisualEntity;
import entity.object.obstacle.Box;
import entity.object.obstacle.DestructibleBox;
import entity.object.weapon.Weapon;
import entity.player.Player;
import entity.projectile.Projectile;

import java.util.ArrayList;
import java.util.List;

public class CollisionCheck {

    private float collisionBoxSize = 42f;

    public boolean wallCheck(float XY) {
        if (XY <= 56f || XY >= 836f) {
            return false;
        }
        return true;
    }

    /**
     * @param players
     * @return
     */
    public Projectile hitCheck(ArrayList<Player> players) {
        for (Player player : players) {
            for (Projectile projectile : player.getProjectiles()) {
                for (Player playerHit : players) {
                    if (projectile.getPlayer() != playerHit) {
                        if (mathCheckProjectile(playerHit, projectile)) {
                            int currentHealth = playerHit.getHealth() - player.getActiveWeapon().getPower();
                            if (currentHealth <= 0) {
                                playerHit.kill();
                            }
                            playerHit.setHealth(currentHealth);
                            return projectile;
                        }
                    }
                }
            }
        }
        return null;
    }

    public Player hitCheck(ArrayList<Player> players, Projectile projectile) {
        for (Player playerHit : players) {
            if (projectile.getPlayer() != playerHit) {
                if (mathCheckProjectile(playerHit, projectile)) {
                    return playerHit;
                }
            }
        }
        return null;
    }

    public boolean mathCheckProjectile(Player player, Projectile projectile) {
        if (projectile.getX() >= player.getX() - collisionBoxSize) {
            if (projectile.getX() <= player.getX() + collisionBoxSize) {
                if (projectile.getY() >= player.getY() - collisionBoxSize) {
                    if (projectile.getY() <= player.getY() + collisionBoxSize) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public boolean playerCheckPlayer(float X, float Y, Player player, ArrayList<Player> players) {
        for (Player enemyPlayer : players) {
            if (player != enemyPlayer) {
                if (mathCheckPlayers(X, Y, enemyPlayer)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean mathCheckPlayers(float X, float Y, Player enemyPlayer) {
        if (X >= enemyPlayer.getX() - collisionBoxSize) {
            if (X <= enemyPlayer.getX() + collisionBoxSize) {
                if (Y <= enemyPlayer.getY() + collisionBoxSize) {
                    if (Y >= enemyPlayer.getY() - collisionBoxSize) {
                        return false;
                    }
                }

            }
        }
        return true;
    }

    /**
     * Wand
     *
     * @param playerX
     * @param playerY
     * @param boxes
     * @return
     */
    public boolean playerCollidesEntity(Player player, List<VisualEntity> entities) {
        for (VisualEntity entity : entities) {
            if (entity.isColliding(player)) {
                return false;
            }
        }
        return true;
    }

    public Projectile hitCheck(ArrayList<Box> boxes, ArrayList<Player> players) {
        for (Player player : players) {
            for (Projectile projectile : player.getProjectiles()) {
                for (Box box : boxes) {
                    if (mathCheckProjectileBox(box, projectile)) {

                        return projectile;
                    }
                }
            }
        }
        return null;
    }

    public boolean mathCheckProjectileBox(Box box, Projectile projectile) {
        if (projectile.getX() >= box.getX() - box.getSprite().getWidth()) {
            if (projectile.getX() <= box.getX() + box.getSprite().getWidth()) {
                if (projectile.getY() >= box.getY() - box.getSprite().getHeight()) {
                    if (projectile.getY() <= box.getY() + box.getSprite().getHeight()) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public Projectile hitCheckDestructibleBox(ArrayList<DestructibleBox> destructibleBoxes, ArrayList<Player> players) {
        for (Player player : players) {
            for (Projectile projectile : player.getProjectiles()) {
                for (DestructibleBox destructibleBox : destructibleBoxes) {
                    if (mathCheckProjectileDestructibleBox(destructibleBox, projectile)) {
                        destructibleBox.setHealth(destructibleBox.getHealth() - player.getActiveWeapon().getPower());
                        return projectile;

                    }
                }
            }
        }
        return null;
    }

    public boolean mathCheckProjectileDestructibleBox(DestructibleBox destructibleBox, Projectile projectile) {
        if (projectile.getX() >= destructibleBox.getX() - destructibleBox.getSprite().getWidth()) {
            if (projectile.getX() <= destructibleBox.getX() + destructibleBox.getSprite().getWidth()) {
                if (projectile.getY() >= destructibleBox.getY() - destructibleBox.getSprite().getHeight()) {
                    if (projectile.getY() <= destructibleBox.getY() + destructibleBox.getSprite().getHeight()) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public boolean playerCheckWeapon(float X, float Y, Weapon weapon) {
        if (mathCheckWeapon(X, Y, weapon)) {
            return true;
        }

        return false;
    }

    public boolean mathCheckWeapon(float X, float Y, Weapon weapon) {
        if (X >= weapon.getX() - weapon.getSprite().getWidth()) {
            if (X <= weapon.getX() + weapon.getSprite().getWidth()) {
                if (Y >= weapon.getY() - weapon.getSprite().getHeight()) {
                    if (Y <= weapon.getY() + weapon.getSprite().getHeight()) {
                        return true;
                    }
                }

            }
        }
        return false;
    }
}
