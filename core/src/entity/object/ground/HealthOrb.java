package entity.object.ground;

import com.badlogic.gdx.graphics.Texture;
import entity.object.obstacle.Box;
import entity.object.obstacle.DestructibleBox;
import entity.object.weapon.Weapon;
import entity.player.Player;

import java.util.ArrayList;
import java.util.Random;

public class HealthOrb extends Item {

    private int healthAmount;

    public HealthOrb() {
        super(0, 0, new Texture("item/HealthBox.png"));
    }

    /**
     * Setzt das Leben des Spielers auf 100 % bei Aktivierung
     **/
    @Override
    public void doEffect(Player player) {
        player.setHealth(100);
    }

    @Override
    public void update() {

    }

}
