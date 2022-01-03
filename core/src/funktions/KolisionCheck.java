package funktions;

import entities.objects.destructable.Box;
import entities.objects.destructable.DestructibleBox;
import entities.objects.weapons.Weapon;
import entities.player.Player;
import entities.projektile.Projektile;

import java.util.ArrayList;

public class KolisionCheck {

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
    public Projektile hitCheck(ArrayList<Player> players) {
        for (Player player : players) {
            for (Projektile projektile : player.getProjektileArrayList()) {
                for (Player playerhit : players) {
                    if (projektile.getPlayer() != playerhit) {
                        if (mathCheckProjektiles(playerhit, projektile)) {
                            int currentHealth = playerhit.getHealth() - player.getActiveWeapon().getPower();
                            if (currentHealth <= 0) {
                                playerhit.kill();
                            }
                            playerhit.setHealth(currentHealth);
                            return projektile;
                        }
                    }
                }
            }
        }
        return null;
    }

    public Player hitCheck(ArrayList<Player> players, Projektile projektile) {
        for (Player playerhit : players) {
            if (projektile.getPlayer() != playerhit) {
                if (mathCheckProjektiles(playerhit, projektile)) {
                    return playerhit;
                }
            }
        }
        return null;
    }

    public boolean mathCheckProjektiles(Player player, Projektile projektile) {
        if (projektile.getX() >= player.getX() - collisionBoxSize) {
            if (projektile.getX() <= player.getX() + collisionBoxSize) {
                if (projektile.getY() >= player.getY() - collisionBoxSize) {
                    if (projektile.getY() <= player.getY() + collisionBoxSize) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public boolean playerCheck(float X, float Y, Player player, ArrayList<Player> players) {
        for (Player enemyplayer : players) {
            if (player != enemyplayer) {
                if (mathCheckPlayers(X, Y, enemyplayer)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean mathCheckPlayers(float X, float Y, Player enemyplayer) {
        if (X >= enemyplayer.getX() - collisionBoxSize) {
            if (X <= enemyplayer.getX() + collisionBoxSize) {
                if (Y <= enemyplayer.getY() + collisionBoxSize) {
                    if (Y >= enemyplayer.getY() - collisionBoxSize) {
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
    public boolean playerCheckbox(float playerX, float playerY, ArrayList<Box> boxes) {
        for (Box box : boxes) {
            if (mathCheckbox(playerX, playerY, box)) {
                return false;
            }
        }
        return true;
    }


    public boolean mathCheckbox(float playerX, float playerY, Box box) {
        float boxXLeft = box.getX();
        float boxXRight = box.getX() + box.getWidth();
        float boxYBottom = box.getY();
        float boxYTop = box.getY() + box.getHeight();

        /*
        if (playerX >= boxXLeft && playerX <= boxXRight && playerY >= boxYBottom && playerY <= boxYTop) {
        // TODO
            System.out.println("-------START-------");
            System.out.println("PlayerX: " + playerX);
            System.out.println("PlayerY: " + playerY);

            System.out.println("BoxXLeft: " + boxXLeft);
            System.out.println("BoxXRight: " + boxXRight);
            System.out.println("BoxYBottom: " + boxYBottom);
            System.out.println("BoxYTop: " + boxYTop);
            System.out.println("-------END-------");
            // TODO END
            return true;
        }


        return false;
        */


        if (playerX >= box.getX() - box.getSprite().getWidth()) {
            if (playerX <= box.getX() + box.getSprite().getWidth()) {
                if (playerY >= box.getY() - box.getSprite().getHeight()) {
                    if (playerY <= box.getY() + box.getSprite().getHeight() / 2) {
                        return true;
                    }
                }

            }
        }
        return false;


    }

    public Projektile hitCheck(ArrayList<Box> boxes, ArrayList<Player> players) {
        for (Player player : players) {
            for (Projektile projektile : player.getProjektileArrayList()) {
                for (Box box : boxes) {
                    if (mathCheckProjektilesbox(box, projektile)) {

                        return projektile;
                    }
                }
            }
        }
        return null;
    }

    public boolean mathCheckProjektilesbox(Box box, Projektile projektile) {
        if (projektile.getX() >= box.getX() - box.getSprite().getWidth()) {
            if (projektile.getX() <= box.getX() + box.getSprite().getWidth()) {
                if (projektile.getY() >= box.getY() - box.getSprite().getHeight()) {
                    if (projektile.getY() <= box.getY() + box.getSprite().getHeight() / 2) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    /**
     * Paletten
     *
     * @param X
     * @param Y
     * @param
     * @return
     */
    public boolean playerCheckpallette(float X, float Y, ArrayList<DestructibleBox> paletten) {
        for (DestructibleBox palette : paletten) {
            if (mathCheckpalette(X, Y, palette)) {
                return false;
            }
        }
        return true;
    }


    public boolean mathCheckpalette(float X, float Y, DestructibleBox palette) {
        if (X >= palette.getX() - palette.getSprite().getWidth()) {
            if (X <= palette.getX() + palette.getSprite().getWidth()) {
                if (Y >= palette.getY() - palette.getSprite().getHeight()) {
                    if (Y <= palette.getY() + palette.getSprite().getHeight() / 2) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public Projektile hitCheckpalette(ArrayList<DestructibleBox> paletten, ArrayList<Player> players) {
        for (Player player : players) {
            for (Projektile projektile : player.getProjektileArrayList()) {
                for (DestructibleBox palette : paletten) {
                    if (mathCheckProjektilespalette(palette, projektile)) {
                        palette.setHealth(palette.getHealth() - player.getActiveWeapon().getPower());
                        return projektile;

                    }
                }
            }
        }
        return null;
    }

    public boolean mathCheckProjektilespalette(DestructibleBox palette, Projektile projektile) {
        if (projektile.getX() >= palette.getX() - palette.getSprite().getWidth()) {
            if (projektile.getX() <= palette.getX() + palette.getSprite().getWidth()) {
                if (projektile.getY() >= palette.getY() - palette.getSprite().getHeight()) {
                    if (projektile.getY() <= palette.getY() + palette.getSprite().getHeight() / 2) {
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
                    if (Y <= weapon.getY() + weapon.getSprite().getHeight() / 2) {
                        return true;
                    }
                }

            }
        }
        return false;
    }
}
