package funktions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import entities.objects.destructable.Box;
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

    /**Wand
     *
     * @param X
     * @param Y
     * @param boxes
     * @return
     */
    public boolean playerCheckbox(float X ,float Y,ArrayList<Box> boxes){
        for (Box box:boxes){
                if(mathCheckbox(X,Y,box)){
                    return false;
                }
            }
        return true;
    }


    public boolean mathCheckbox(float X ,float Y, Box box) {

        System.out.println("X:"+X+" Y:"+Y+" BoxX:"+box.getX()+" BoxY:"+box.getY()+" Spritegrößex:"+box.getSpritegrößex()+" Spritegrößey:"+box.getSpritegrößey());
        if (X >= box.getX() - box.getSpritegrößex()) {
            if (X <= box.getX() + box.getSpritegrößex()) {
                if (Y >= box.getY() - box.getSpritegrößey()) {
                    if (Y <= box.getY() + box.getSpritegrößey()/2) {
                        return true;
                    }
                }

            }
        }
        return false;
    }
    public Projektile hitCheck(ArrayList<Box> boxes,ArrayList<Player> players) {
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
                    if (projektile.getY() <= box.getY() + box.getSpritegrößey()/2) {
                        return true;
                    }
                }

            }
        }
        return false;
    }
}
