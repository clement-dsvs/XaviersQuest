package fr.promeo.xaviersquest;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import fr.promeo.xaviersquest.MyGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Xavier's Quest");
		config.setWindowedMode(1280, 720);
		config.setWindowIcon("./icon.png");
		new Lwjgl3Application(new MyGame(), config);
	}
}
