package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.utils.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "The Project";
		cfg.resizable = false;
		cfg.height = Constants.DESKTOP_WINDOW_HEIGHT;
		cfg.width = 700;
		//LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MyGdxGame(), cfg);
	}
}
