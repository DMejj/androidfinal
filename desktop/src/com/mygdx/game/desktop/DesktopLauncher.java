package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import Arkanoid.Arkanoid;
import util.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		//LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//new LwjglApplication(new MyGdxGame(), config);

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.width = Constants.APP_WIDTH;
		//config.height = Constants.APP_HEIGHT;
		new LwjglApplication(new Arkanoid(), config);
	}
}
