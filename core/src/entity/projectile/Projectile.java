package entity.projectile;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import entity.VisualEntity;
import function.CollisionCheck;
import entity.player.Player;


public class Projectile extends VisualEntity {
    private CollisionCheck collisionCheck = new CollisionCheck();
    private Player player;
    private float speed = 5f;
    private int direction;
    private boolean deletable = false;

    public Projectile(int direction, float rotation, float x, float y, Player player, Texture texture) {
        super(x, y, texture, 11, 11);
        setX(x);
        setY(y);
        this.direction = direction;
        this.player = player;
        sprite.setRotation(rotation);
        sprite.setScale(0.4f);
    }

    /**
     * Setzt die position pro Frame neu, um die Projektile an der richtigen position zu zeichnen
     */
    @Override
    public void update() {
        float projectileX = getX();
        float projectileY = getY();
        //Nach oben gehen
        if (direction == ProjectileDirection.UP) {
            setY(getY() + speed);
            projectileY = getY();
        }
        //Nach links gehen
        if (direction == ProjectileDirection.LEFT) {
            setX(getX() - speed);
            projectileX = getX();
        }
        //Nach unten gehen
        if (direction == ProjectileDirection.DOWN) {
            setY(getY() - speed);
            projectileY = getY();
        }
        //Nach rechts gehen
        if (direction == ProjectileDirection.RIGHT) {
            setX(getX() + speed);
            projectileX = getX();
        }
        //Oben Links
        if (direction == ProjectileDirection.UP_LEFT) {
            setY(getY() + speed);
            setX(getX() - speed);
            projectileX = getX();
            projectileY = getY();
        }
        // Unten Links
        if (direction == ProjectileDirection.DOWN_LEFT) {
            setY(getY() - speed);
            setX(getX() - speed);
            projectileX = getX();
            projectileY = getY();

        }
        // Unten Rechts
        if (direction == ProjectileDirection.DOWN_RIGHT) {
            setY(getY() - speed);
            setX(getX() + speed);
            projectileX = getX();
            projectileY = getY();

        }
        //Oben Rechts
        if (direction == ProjectileDirection.UP_RIGHT) {
            setY(getY() + speed);
            setX(getX() + speed);
            projectileX = getX();
            projectileY = getY();
        }

        if (!collisionCheck.outerWallCheck(projectileX, projectileY)) {
            deletable = true;
        }

    }

    /**
     * Erzeugt eine Hitbox für die Projektile
     */
    @Override
    protected void updateHitbox() {
        hitbox.setPosition(x + 22, y + 22);
    }

    @Override
    public Rectangle getHitbox() {
        return super.getHitbox();
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
