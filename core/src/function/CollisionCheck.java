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


    /**
     * Diese Methode ist zur Überprüfung, dass Spieler nicht durch andere Spieler laufen können
     *
     * @param player  aktueller Spieler
     * @param players Liste aller Spieler
     * @return true = wenn alle Überprüfungen nicht zutreffen sind/
     * false = wenn eine Blockade schon an der Position ist.
     */
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

    /**
     * Diese Methode ist zur Überprüfung, dass Spieler nicht durch Objekte laufen können
     *
     * @param player   aktueller Spieler
     * @param entities Liste aller Blockaden und zerstörbaren Blockade
     * @return true = wenn alle Überprüfungen nicht zutreffen sind/
     * false = wenn eine Blockade schon an der Position ist.
     */
    public boolean playerCollidesEntity(Player player, ArrayList<VisualEntity> entities) {
        for (VisualEntity entity : entities) {
            if (entity.isColliding(player)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Überprüft, ob die ein Projektil einen Spieler trifft
     *
     * @param players Liste aller Spieler
     * @return projectile, wenn das Projektile ein Spieler trifft
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

    /**
     * Überprüft, ob ein Projektile eine Blockade trifft
     *
     * @param obstacles Liste aller Blockaden und zerstörbaren Blockade
     * @param players   Liste aller Spieler
     * @return projectile, wenn das Projektile eine Blockade oder zerstörbare Blockade trifft
     */
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

    /**
     * Überprüft, ob eine Waffe in Reichweite des Spielers ist
     *
     * @param player aktueller Spieler
     * @param weapon Waffe, die am Boden liegt
     * @return true = wenn die Überprüfungen zutrifft/
     * false = wenn die Überprüfungen nicht zutrifft.
     */
    public boolean playerInWeaponRange(Player player, Weapon weapon) {
        if (weapon.isColliding(player)) {
            return true;
        }

        return false;
    }

    /**
     * Überprüft, ob eine Waffe in Reichweite des Spielers ist
     *
     * @param player aktueller Spieler
     * @param item   Items, die am Boden liegt
     * @return true = wenn die Überprüfungen zutrifft/
     * false = wenn die Überprüfungen nicht zutrifft.
     */
    public boolean playerInPickUpRange(Player player, Item item) {
        if (item.isColliding(player)) {
            return true;
        }

        return false;
    }
}
