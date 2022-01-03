package entity.projectile;


import com.badlogic.gdx.graphics.g2d.Sprite;
import entity.NonVisualEntity;
import function.CollisionCheck;
import entity.player.Player;


public class Projectile extends NonVisualEntity {
    CollisionCheck collisionCheck = new CollisionCheck();
    ProjectileSprites spriteGenerator = new ProjectileSprites();
    Player player;
    float speed = 5f;
    int direction;
    private Sprite sprite;
    boolean deletable = false;

    public Projectile(int direction, float x, float y, Player player) {
        spriteGenerator.Sprite(this);
        setX(x);
        setY(y);
        this.direction = direction;
        this.player = player;
    }

    @Override
    public void update() {
        float projectileX = getX();
        float projectileY = getY();
        //Nach oben gehen
        if (direction == 0) {
            setY(getY() + speed);
            projectileY = getY();
        }
        //Nach links gehen
        if (direction == 1) {
            setX(getX() - speed);
            projectileX = getX();
        }
        //Nach unten gehen
        if (direction == 2) {
            setY(getY() - speed);
            projectileY = getY();
        }
        //Nach rechts gehen
        if (direction == 3) {
            setX(getX() + speed);
            projectileX = getX();
        }
        //Oben Links
        if (direction == 4) {
            setY(getY() + speed);
            setX(getX() - speed);
            projectileX = getX();
            projectileY = getY();
        }
        // Unten Links
        if (direction == 5) {
            setY(getY() - speed);
            setX(getX() - speed);
            projectileX = getX();
            projectileY = getY();
        }
        // Unten Rechts
        if (direction == 6) {
            setY(getY() - speed);
            setX(getX() + speed);
            projectileX = getX();
            projectileY = getY();
        }
        //Oben Rechts
        if (direction == 7) {
            setY(getY() + speed);
            setX(getX() + speed);
            projectileX = getX();
            projectileY = getY();
        }


        if (!collisionCheck.wallCheck(projectileX) || !collisionCheck.wallCheck(projectileY)) {
            deletable = true;
        }

    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public boolean isDeletable() {
        return deletable;
    }

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }

    public Player getPlayer() {
        return player;
    }
}
