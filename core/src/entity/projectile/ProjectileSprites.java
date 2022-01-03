package entity.projectile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ProjectileSprites {

    private Sprite sprite;

    public void Sprite(Projectile projectile) {
        sprite = new Sprite(new Texture("projektil/PistolenSchuss.png"));
        sprite.setScale(0.3f);

        projectile.setSprite(sprite);
    }
}
