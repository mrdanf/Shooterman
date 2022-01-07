package hud.menu;

import com.badlogic.gdx.graphics.Color;
import hud.menu.button.MenuButton;

public class MenuWindow extends AbstractWindow {

    /**
     * Einfache Implementierung des AbstractWindow mit 3 Buttons
     */
    public MenuWindow() {
        super(750, 750, new Color(0.6f, 0.6f, 0.6f, 1));

        buttons.add(new MenuButton("Neues Spiel", 100));
        buttons.add(new MenuButton("Hilfe", 0));
        buttons.add(new MenuButton("Beenden", -100));
    }


}
