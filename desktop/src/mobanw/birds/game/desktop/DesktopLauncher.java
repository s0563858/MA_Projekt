package mobanw.birds.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import mobanw.birds.game.SuperBirdsMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = SuperBirdsMain.title;
		config.width = SuperBirdsMain.width;
		config.height = SuperBirdsMain.height;
		new LwjglApplication(new SuperBirdsMain(), config);
	}
}
