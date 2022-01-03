package hud.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import hud.menu.buttons.AbstractButton;
import hud.menu.buttons.CloseButton;
import hud.menu.buttons.MenuButton;

public class MenuWindow extends AbstractWindow {


    public MenuWindow() {
        super(750, 750, new Color(0.6f, 0.6f, 0.6f, 1));

        buttons.add(new MenuButton("Neues Spiel", 100));
        buttons.add(new MenuButton("Hilfe", 0));
        buttons.add(new MenuButton("Beenden", -100));
    }


}
