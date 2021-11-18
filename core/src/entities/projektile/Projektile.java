package entities.projektile;


import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.Entity;
import funktions.KolisionCheck;
import entities.player.Player;


public class Projektile extends Entity {
    KolisionCheck kolisionCheck = new KolisionCheck();
    ProjektileSprites spriteGenerator = new ProjektileSprites();
    Player player;
    float speed = 5f;
    int direction;
    private Sprite sprite;
    boolean deleteble=false;

    public Projektile(int direction, float x, float y,Player player) {
        spriteGenerator.Sprite(this);
        setX(x);
        setY(y);
        this.direction = direction;
        this.player=player;
    }

    @Override
    public void update() {
        float projektileXY = 0f;
        if (direction == 0) {
            setX(getX() + speed);
            projektileXY = getX();
        }
        //Nach oben gehen
        if (direction == 1) {
            setY(getY() + speed);
            projektileXY = getY();
        }
        //Nach links gehen
        if (direction == 2) {
            setX(getX() - speed);
            projektileXY = getX();
        }
        //Nach unten gehen
        if (direction == 3) {
            setY(getY() - speed);
            projektileXY = getY();
        }
        if (kolisionCheck.wallCheck(projektileXY)) {
        deleteble=true;
        }

    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    public boolean isDeleteble() {
        return deleteble;
    }

    public void setDeleteble(boolean deleteble) {
        this.deleteble = deleteble;
    }

    public Player getPlayer() {
        return player;
    }
}
