package br.edu.unifei.sd;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LastSurvivor extends Game {
	
    public static int WIDTH = 1200;
    public static int HEIGHT = 720;
    public static String TITLE = "Gamedev Unknownd's Last Survivor";
    
    private GameStateManager gsm;
    private SpriteBatch batch;

    @Override
    public void create () {
        batch = new SpriteBatch();        
        gsm = new GameStateManager();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        gsm.push(new MenuState(gsm));
//        gsm.push(new PlayState(gsm));       
 
    }

    @Override
    public void render () {        
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);       
    }

    @Override
    public void dispose () {
            batch.dispose();
    }
}
