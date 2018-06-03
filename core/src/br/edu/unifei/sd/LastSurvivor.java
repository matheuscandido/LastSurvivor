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
        gsm.push(new PlayState(gsm));
        
        Gdx.gl.glClearColor(1, 1, 1, 1);
    }

    @Override
    public void render () {        
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        gsm.render(batch, Gdx.graphics.getDeltaTime());
        
    }

    @Override
    public void dispose () {
            batch.dispose();
    }
}
