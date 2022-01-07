package hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import entity.object.weapon.Weapon;

public class Status {
    private final Label playerLabel;
    private final float xPostion;
    private float yPosition = 75;
    private final HealthBar healthBar;
    private Label ammoLabel;
    private static final float LABELSIZE = 220f;

    /**
     * Die Statusanzeige eines Spielers
     * Enthält alle Elemente eines Spielers, die am unteren Bildschrimrand
     * ausgegeben werden
     * @param player Spielernummer, wird genutzt, um Abhängig von der Gesamtanzahl der Spieler,
     *               den Status zu verschieben
     */
    public Status(int player) {
        // Screenwidth - (Labelsize*NumberOfPlayers)/2 + LabelSize*(playerNumber-1)
        // TODO Spieleranzahl ist Hardcoded
        this.xPostion = 570 - ((LABELSIZE * 2) / 2f) + LABELSIZE * (player - 1);

        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = new BitmapFont(Gdx.files.internal("msJhengHei.fnt"));

        playerLabel = new Label("Spieler " + player, ls);
        playerLabel.setSize(0, 0);
        playerLabel.setPosition(xPostion, yPosition);

        healthBar = new HealthBar(100);

        ammoLabel = new Label("0", ls);
        ammoLabel.setPosition(xPostion, yPosition - 50);
        ammoLabel.setSize(0, 0);
        ammoLabel.setFontScale(0.8f);

    }

    public Label getAmmoLabel() {
        return ammoLabel;
    }

    public Label getPlayerLabel() {
        return playerLabel;
    }

    public Texture[] getHealthBar() {
        return healthBar.getHBTexture();
    }

    /**
     * Aktualisiert den Lebensbalken und Munitionsanzeige
     * Wenn die Lebenspunkte unter 0 sinken, wird die Ammoanzeige zu einem 'DEAD' umgewandelt
     * @param health Setzt die Breite des Lebensbalkens
     * @param activeWeapon Hollt sich die Werte der aktiven Waffe des Spielers
     */
    public void update(int health, Weapon activeWeapon) {
        this.healthBar.updateHealth(health);
        String total = "error";
        if (activeWeapon.getTotalAmmunition() >= 999) {
            total = "INF";
        } else {
            total = String.valueOf(activeWeapon.getTotalAmmunition());
        }
        if (health > 0) {
            ammoLabel.setText(activeWeapon.getCurrentAmmunition() + " / " + total);
        } else {
            yPosition = 80 - 35;
            ammoLabel.setText("  DEAD");
        }
    }

    public float getXPostion() {
        return xPostion;
    }

    public float getYPosition() {
        return yPosition;
    }
}
