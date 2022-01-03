package com.shooterman.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.shooterman.game.Shooterman;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Shooterman";
		config.width = 950;
		config.height = 950;
		config.addIcon("logo/128x128.png", Files.FileType.Internal);
		config.addIcon("logo/32x32.png", Files.FileType.Internal);
		config.addIcon("logo/16x16.png", Files.FileType.Internal);
		new LwjglApplication(new Shooterman(), config);
	}
}
