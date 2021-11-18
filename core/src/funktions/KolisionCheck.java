package funktions;

import entities.player.Player;
import entities.projektile.Projektile;

import java.util.ArrayList;

public class KolisionCheck {


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
        if (projektile.getX() >= player.getX() - 68f) {
            if (projektile.getX() <= player.getX() + 68) {
                if (projektile.getY() >= player.getY() - 68) {
                    if (projektile.getY() <= player.getY() + 68) {
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
        if (X >= enemyplayer.getX() - 68f) {
            if (X <= enemyplayer.getX() + 68) {
                if (Y <= enemyplayer.getY() + 68) {
                    if (Y >= enemyplayer.getY() - 68) {
                        return false;
                    }
                }

            }
        }
        return true;
    }
}
