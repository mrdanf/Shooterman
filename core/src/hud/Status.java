package hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Status {
    private final Label PlayerLabel;
    private final float xPostion;
    private final float yPosition = 80;
    private final  HealthBar healthBar;

    public Status(int player) {
        // Screenwidth - (Labelsize*NumberOfPlayers)/2 + LabelSize*(playerNumber-1)
        // TODO get number of players
        this.xPostion = 550 -((200f*2)/2f) + 200*(player-1);

        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = new BitmapFont(Gdx.files.internal("msJhengHei.fnt"));

        PlayerLabel = new Label("Spieler "+player, ls);
        PlayerLabel.setSize(0, 0);
        PlayerLabel.setPosition(xPostion, yPosition);

        healthBar = new HealthBar(100);

    }

    public Label getPlayerLabel() {
        return PlayerLabel;
    }

    public Texture[] getHealthBar() {
        return healthBar.getHBTexture();
    }

    public void update(int health) {
        this.healthBar.updateHealth(health);
    }

    public float getxPostion() {
        return xPostion;
    }

    public float getyPosition() {
        return yPosition;
    }
}
