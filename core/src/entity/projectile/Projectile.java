package entity.projectile;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entity.VisualEntity;
import function.CollisionCheck;
import entity.player.Player;


public class Projectile extends VisualEntity {
    private CollisionCheck collisionCheck = new CollisionCheck();
    private Player player;
    private float speed = 5f;
    private int direction;
    private float rotation;
    private boolean deletable = false;

    public Projectile(int direction, float rotation, float x, float y, Player player, Texture texture) {
        super(x, y, texture, 5, 5);
        setX(x);
        setY(y);
        this.direction = direction;
        this.rotation = rotation;
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
            sprite.setRotation(0f);
        }
        //Nach links gehen
        if (direction == 1) {
            setX(getX() - speed);
            projectileX = getX();
            sprite.setRotation(90f);
        }
        //Nach unten gehen
        if (direction == 2) {
            setY(getY() - speed);
            projectileY = getY();
            sprite.setRotation(180f);
        }
        //Nach rechts gehen
        if (direction == 3) {
            setX(getX() + speed);
            projectileX = getX();
            sprite.setRotation(270f);
        }
        //Oben Links
        if (direction == 4) {
            setY(getY() + speed);
            setX(getX() - speed);
            projectileX = getX();
            projectileY = getY();
            sprite.setRotation(45f);
        }
        // Unten Links
        if (direction == 5) {
            setY(getY() - speed);
            setX(getX() - speed);
            projectileX = getX();
            projectileY = getY();
            sprite.setRotation(135f);

        }
        // Unten Rechts
        if (direction == 6) {
            setY(getY() - speed);
            setX(getX() + speed);
            projectileX = getX();
            projectileY = getY();
            sprite.setRotation(225f);

        }
        //Oben Rechts
        if (direction == 7) {
            setY(getY() + speed);
            setX(getX() + speed);
            projectileX = getX();
            projectileY = getY();
            sprite.setRotation(315f);
        }

        if (!collisionCheck.wallCheck(projectileX, projectileY)) {
            deletable = true;
        }

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
