package funktions;

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

    public Projektile hitCheck(ArrayList<Player> players) {
        for (Player player : players) {
            for (Projektile projektile : player.getProjektileArrayList()) {
                for (Player playerhit : players) {
                    if (projektile.getPlayer() != playerhit) {
                        if (mathCheckProjektiles(playerhit, projektile)) {
                            return projektile;
                        }
                    }
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

    public boolean playerCheck(float X ,float Y,Player player,ArrayList<Player> players){
        for (Player enemyplayer:players){
            if(player!=enemyplayer){
                if(mathCheckPlayers(X,Y,enemyplayer)){
                    return true;
                }
            }
        }
        return false;
    }


    public boolean mathCheckPlayers(float X ,float Y, Player enemyplayer) {
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
}
