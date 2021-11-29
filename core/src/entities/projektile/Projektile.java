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
    boolean deleteble = false;

    public Projektile(int direction, float x, float y, Player player) {
        spriteGenerator.Sprite(this);
        setX(x);
        setY(y);
        this.direction = direction;
        this.player = player;
    }

    @Override
    public void update() {
        float projektileX = getX();
        float projektileY = getY();
        //Nach oben gehen
        if (direction == 0) {
            setY(getY() + speed);
            projektileY = getY();
        }
        //Nach links gehen
        if (direction == 1) {
            setX(getX() - speed);
            projektileX = getX();
        }
        //Nach unten gehen
        if (direction == 2) {
            setY(getY() - speed);
            projektileY = getY();
        }
        //Nach rechts gehen
        if (direction == 3) {
            setX(getX() + speed);
            projektileX = getX();
        }
        //Oben Links
        if (direction == 4) {
            setY(getY() + speed);
            setX(getX() - speed);
            projektileX = getX();
            projektileY = getY();
        }
        // Unten Links
        if (direction == 5) {
            setY(getY() - speed);
            setX(getX() - speed);
            projektileX = getX();
            projektileY = getY();
        }
        // Unten Rechts
        if (direction == 6) {
            setY(getY() - speed);
            setX(getX() + speed);
            projektileX = getX();
            projektileY = getY();
        }
        //Oben Rechts
        if (direction == 7) {
            setY(getY() + speed);
            setX(getX() + speed);
            projektileX = getX();
            projektileY = getY();
        }


        if (!kolisionCheck.wallCheck(projektileX) || !kolisionCheck.wallCheck(projektileY)) {
            deleteble = true;
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
