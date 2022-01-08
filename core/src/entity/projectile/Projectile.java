package entity.projectile;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import entity.VisualEntity;
import entity.object.weapon.Shotgun;
import entity.object.weapon.Weapon;
import function.CollisionCheck;
import entity.player.Player;


public class Projectile extends VisualEntity {
    private CollisionCheck collisionCheck = new CollisionCheck();
    private Player player;
    private float speed = 5f;
    private int direction;
    private boolean deletable = false;
    private int shotgunbullet;

    public Projectile(int direction, float rotation, float x, float y, Player player, Texture texture, int shotgunbullet) {
        super(x, y, texture, 11, 11);
        setX(x);
        setY(y);
        this.direction = direction;
        this.player = player;
        sprite.setRotation(rotation);
        sprite.setScale(0.4f);
        this.shotgunbullet = shotgunbullet;
    }

    /**
     * Ruft die Methoden für die unterschiedlichen Schüsse auf
     */
    @Override
    public void update() {
        if (shotgunbullet == 1) {
            shotgun1();
        } else if (shotgunbullet == 2) {
            shotgun2();
        } else {
            normalGun();
        }
    }

    /**
     * Setzt die Position pro Frame neu, um die Projektile an der richtigen Position zu zeichnen
     */
    public void normalGun() {
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
            setY(getY() + (float) (speed * (Math.sin(Math.toRadians(45)))));
            setX(getX() - (float) (speed * (Math.cos(Math.toRadians(45)))));
            projectileX = getX();
            projectileY = getY();
        }
        // Unten Links
        if (direction == ProjectileDirection.DOWN_LEFT) {
            setY(getY() - (float) (speed * (Math.sin(Math.toRadians(45)))));
            setX(getX() - (float) (speed * (Math.cos(Math.toRadians(45)))));
            projectileX = getX();
            projectileY = getY();

        }
        // Unten Rechts
        if (direction == ProjectileDirection.DOWN_RIGHT) {
            setY(getY() - (float) (speed * (Math.sin(Math.toRadians(45)))));
            setX(getX() + (float) (speed * (Math.cos(Math.toRadians(45)))));
            projectileX = getX();
            projectileY = getY();

        }
        //Oben Rechts
        if (direction == ProjectileDirection.UP_RIGHT) {
            setY(getY() + (float) (speed * (Math.sin(Math.toRadians(45)))));
            setX(getX() + (float) (speed * (Math.cos(Math.toRadians(45)))));
            projectileX = getX();
            projectileY = getY();
        }

        if (!collisionCheck.outerWallCheck(projectileX, projectileY)) {
            deletable = true;
        }

    }

    /**
     * Setzt die Position pro Frame neu, um die Projektile für die 2. Schrotflinten kugel an der richtigen Position zu zeichnen.
     */
    public void shotgun1() {
        float projectileX = getX();
        float projectileY = getY();
        //Nach oben gehen
        if (direction == ProjectileDirection.UP) {
            setY(getY() + (float) (speed * (Math.sin(Math.toRadians(80)))));
            setX(getX() + (float) (speed * (Math.cos(Math.toRadians(80)))));
            projectileX = getX();
            projectileY = getY();
        }
        //Nach links gehen
        if (direction == ProjectileDirection.LEFT) {
            setY(getY() + (float) (speed * (Math.sin(Math.toRadians(10)))));
            setX(getX() - (float) (speed * (Math.cos(Math.toRadians(10)))));
            projectileX = getX();
            projectileY = getY();
        }
        //Nach unten gehen
        if (direction == ProjectileDirection.DOWN) {
            setY(getY() - (float) (speed * (Math.sin(Math.toRadians(80)))));
            setX(getX() - (float) (speed * (Math.cos(Math.toRadians(80)))));
            projectileX = getX();
            projectileY = getY();
        }
        //Nach rechts gehen
        if (direction == ProjectileDirection.RIGHT) {
            setY(getY() - (float) (speed * (Math.sin(Math.toRadians(10)))));
            setX(getX() + (float) (speed * (Math.cos(Math.toRadians(10)))));
            projectileX = getX();
            projectileY = getY();
        }
        //Oben Links
        if (direction == ProjectileDirection.UP_LEFT) {
            setY(getY() + (float) (speed * (Math.sin(Math.toRadians(35)))));
            setX(getX() - (float) (speed * (Math.cos(Math.toRadians(35)))));
            projectileX = getX();
            projectileY = getY();
        }
        // Unten Links
        if (direction == ProjectileDirection.DOWN_LEFT) {
            setY(getY() - (float) (speed * (Math.sin(Math.toRadians(35)))));
            setX(getX() - (float) (speed * (Math.cos(Math.toRadians(35)))));
            projectileX = getX();
            projectileY = getY();

        }
        // Unten Rechts
        if (direction == ProjectileDirection.DOWN_RIGHT) {
            setY(getY() - (float) (speed * (Math.sin(Math.toRadians(35)))));
            setX(getX() + (float) (speed * (Math.cos(Math.toRadians(35)))));
            projectileX = getX();
            projectileY = getY();

        }
        //Oben Rechts
        if (direction == ProjectileDirection.UP_RIGHT) {
            setY(getY() + (float) (speed * (Math.sin(Math.toRadians(35)))));
            setX(getX() + (float) (speed * (Math.cos(Math.toRadians(35)))));
            projectileX = getX();
            projectileY = getY();
        }

        if (!collisionCheck.outerWallCheck(projectileX, projectileY)) {
            deletable = true;
        }

    }

    /**
     * Setzt die Position pro Frame neu, um die Projektile für die 3. Schrotflinten kugel an der richtigen Position zu zeichnen.
     */
    public void shotgun2() {
        float projectileX = getX();
        float projectileY = getY();
        //Nach oben gehen
        if (direction == ProjectileDirection.UP) {
            setY(getY() + (float) (speed * (Math.sin(Math.toRadians(100)))));
            setX(getX() + (float) (speed * (Math.cos(Math.toRadians(100)))));
            projectileX = getX();
            projectileY = getY();
        }
        //Nach links gehen
        if (direction == ProjectileDirection.LEFT) {

            setY(getY() + (float) (speed * (Math.sin(Math.toRadians(350)))));
            setX(getX() - (float) (speed * (Math.cos(Math.toRadians(350)))));
            projectileX = getX();
            projectileY = getY();
        }
        //Nach unten gehen
        if (direction == ProjectileDirection.DOWN) {

            setY(getY() - (float) (speed * (Math.sin(Math.toRadians(100)))));
            setX(getX() - (float) (speed * (Math.cos(Math.toRadians(100)))));
            projectileX = getX();
            projectileY = getY();
        }
        //Nach rechts gehen
        if (direction == ProjectileDirection.RIGHT) {
            setY(getY() - (float) (speed * (Math.sin(Math.toRadians(350)))));
            setX(getX() + (float) (speed * (Math.cos(Math.toRadians(350)))));
            projectileX = getX();
            projectileY = getY();
        }
        //Oben Links
        if (direction == ProjectileDirection.UP_LEFT) {
            setY(getY() + (float) (speed * (Math.sin(Math.toRadians(55)))));
            setX(getX() - (float) (speed * (Math.cos(Math.toRadians(55)))));
            projectileX = getX();
            projectileY = getY();
        }
        // Unten Links
        if (direction == ProjectileDirection.DOWN_LEFT) {
            setY(getY() - (float) (speed * (Math.sin(Math.toRadians(55)))));
            setX(getX() - (float) (speed * (Math.cos(Math.toRadians(55)))));
            projectileX = getX();
            projectileY = getY();

        }
        // Unten Rechts
        if (direction == ProjectileDirection.DOWN_RIGHT) {
            setY(getY() - (float) (speed * (Math.sin(Math.toRadians(55)))));
            setX(getX() + (float) (speed * (Math.cos(Math.toRadians(55)))));
            projectileX = getX();
            projectileY = getY();

        }
        //Oben Rechts
        if (direction == ProjectileDirection.UP_RIGHT) {
            setY(getY() + (float) (speed * (Math.sin(Math.toRadians(55)))));
            setX(getX() + (float) (speed * (Math.cos(Math.toRadians(55)))));
            projectileX = getX();
            projectileY = getY();
        }

        if (!collisionCheck.outerWallCheck(projectileX, projectileY)) {
            deletable = true;
        }

    }


    /**
     * Erzeugt eine Hitbox für das Projektile
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
