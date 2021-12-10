package funktions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import entities.objects.destructable.Box;
import entities.objects.destructable.DestructableBox;
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
     * @param X
     * @param Y
     * @param boxes
     * @return
     */
    public boolean playerCheckbox(float X, float Y, ArrayList<Box> boxes) {
        for (Box box : boxes) {
            if (mathCheckbox(X, Y, box)) {
                return false;
            }
        }
        return true;
    }


    public boolean mathCheckbox(float X, float Y, Box box) {
        if (X >= box.getX() - box.getSpritegrößex()) {
            if (X <= box.getX() + box.getSpritegrößex()) {
                if (Y >= box.getY() - box.getSpritegrößey()) {
                    if (Y <= box.getY() + box.getSpritegrößey() / 2) {
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
        if (projektile.getX() >= box.getX() - box.getSpritegrößex()) {
            if (projektile.getX() <= box.getX() + box.getSpritegrößex()) {
                if (projektile.getY() >= box.getY() - box.getSpritegrößey()) {
                    if (projektile.getY() <= box.getY() + box.getSpritegrößey() / 2) {
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
    public boolean playerCheckpallette(float X, float Y, ArrayList<DestructableBox> paletten) {
        for (DestructableBox palette : paletten) {
            if (mathCheckpalette(X, Y, palette)) {
                return false;
            }
        }
        return true;
    }


    public boolean mathCheckpalette(float X, float Y, DestructableBox palette) {
        if (X >= palette.getX() - palette.getSpritegrößex()) {
            if (X <= palette.getX() + palette.getSpritegrößex()) {
                if (Y >= palette.getY() - palette.getSpritegrößey()) {
                    if (Y <= palette.getY() + palette.getSpritegrößey() / 2) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public Projektile hitCheckpalette(ArrayList<DestructableBox> paletten, ArrayList<Player> players) {
        for (Player player : players) {
            for (Projektile projektile : player.getProjektileArrayList()) {
                for (DestructableBox palette : paletten) {
                    if (mathCheckProjektilespalette(palette, projektile)) {
                        palette.setHealth(palette.getHealth() - player.getActiveWeapon().getPower());
                        return projektile;

                    }
                }
            }
        }
        return null;
    }

    public boolean mathCheckProjektilespalette(DestructableBox palette, Projektile projektile) {
        if (projektile.getX() >= palette.getX() - palette.getSpritegrößex()) {
            if (projektile.getX() <= palette.getX() + palette.getSpritegrößex()) {
                if (projektile.getY() >= palette.getY() - palette.getSpritegrößey()) {
                    if (projektile.getY() <= palette.getY() + palette.getSpritegrößey() / 2) {
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
        if (X >= weapon.getX() - weapon.getSpritegrößex()) {
            if (X <= weapon.getX() + weapon.getSpritegrößex()) {
                if (Y >= weapon.getY() - weapon.getSpritegrößey()) {
                    if (Y <= weapon.getY() + weapon.getSpritegrößey() / 2) {
                        return true;
                    }
                }

            }
        }
        return false;
    }
}
