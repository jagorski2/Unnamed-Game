package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGame;
import com.mygdx.game.screens.BoardScreen;
import com.mygdx.game.utils.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "The Project";
		cfg.resizable = false;
		//cfg.height = Constants.DESKTOP_WINDOW_HEIGHT;
		//cfg.width = Constants.DESKTOP_WINDOW_WIDTH;
		cfg.width=MyGame.WIDTH; // sets window width
        cfg.height=MyGame.HEIGHT;  // sets window height
        new LwjglApplication(new MyGame(), cfg);
	}
}
