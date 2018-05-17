package br.edu.unifei.sd.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import br.edu.unifei.sd.LastSurvivor;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title = "Gamedev Unknowns Last Survivor";
                config.width = 1200;
                config.height = 720;
		new LwjglApplication(new LastSurvivor(), config);
	}
}
