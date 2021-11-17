package entities.projektile;


import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.Entity;


public class Projektile extends Entity {
    ProjektileSprites spriteGenerator = new ProjektileSprites();
    float speed = 5f;
    int direction;
    private Sprite sprite;

    public Projektile(int direction, float x, float y) {
        spriteGenerator.Sprite(this);
        setX(x);
        setY(y);
        this.direction = direction;

    }

    @Override
    public void update() {
        if (direction == 0) {
            setX(getX() + speed);
        }
        //Nach oben gehen
        if (direction == 1) {
            setY(getY() + speed);
        }
        //Nach links gehen
        if (direction == 2) {
            setX(getX() - speed);
        }
        //Nach unten gehen
        if (direction == 3) {
            setY(getY() - speed);
        }

    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
