package atm.se.project.pacman.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import atm.se.project.pacman.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 900;
		config.height = 600;
		config.title = "PacMan";
		config.vSyncEnabled = true;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
