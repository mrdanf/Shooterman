package entity.object.ground;

import com.badlogic.gdx.graphics.Texture;
import entity.player.Player;

public class Ammunition extends Item {

    private int ammunitionAmount = 20;

    public Ammunition() {
        super(0, 0, new Texture("item/AmmoBox.png"));
    }

    /**
     * Weist dem Spieler die aktuelle Anzahl an Munition zu.
     */
    @Override
    public void doEffect(Player player) {
        player.giveAmmo(ammunitionAmount);
    }


    @Override
    public void update() {

    }

}
