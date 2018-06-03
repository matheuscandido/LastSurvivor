package br.edu.unifei.sd.desktop;

import br.edu.unifei.sd.Constantes;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import br.edu.unifei.sd.LastSurvivor;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title = "Gamedev Unknowns Last Survivor";
                config.width = Constantes.MAPA_WIDTH;
                config.height = Constantes.MAPA_HEIGHT;
                config.title = Constantes.TITLE;
		new LwjglApplication(new LastSurvivor(), config);
	}
}
