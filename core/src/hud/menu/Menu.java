package hud.menu;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Menu {
    public static final int OFFSET = 125;
    private Texture background;
    private Texture menuScreen;
    private Button newGame;
    private Button showHelp;
    private Button exit;

    public Menu() {
        Pixmap pixmap = new Pixmap(1000, 1000, Pixmap.Format.RGBA8888);
        pixmap.setColor(0,0,0,0.5f);
        pixmap.fill();
        this.background = new Texture(pixmap);

        pixmap = new Pixmap(750, 750, Pixmap.Format.RGBA8888);
        pixmap.setColor(0.6f, 0.6f, 0.6f, 1);
        pixmap.fill();
        this.menuScreen = new Texture(pixmap);

        pixmap.dispose();

        this.newGame = new Button("Neues Spiel", 100);
        this.showHelp = new Button("Hilfe", 0);
        this.exit = new Button("Beenden", -100);
    }

    public Texture getBackground() {
        return background;
    }

    public Texture getMenuScreen() {
        return menuScreen;
    }

    public Button[] getButtons() {
        return new Button[]{newGame, showHelp, exit};
    }
}
