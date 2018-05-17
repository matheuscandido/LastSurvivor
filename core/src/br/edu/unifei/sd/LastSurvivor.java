package br.edu.unifei.sd;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LastSurvivor extends ApplicationAdapter {
	
    public static int WINDOW_WIDTH;
    public static int WINDOW_HEIGHT;
    public static int MAP_WIDTH;
    public static int MAP_HEIGHT;
    
    public static OrthographicCamera camera;
    
    SpriteBatch batch;
    Texture img;
    
    
    

    @Override
    public void create () {
        
        WINDOW_WIDTH = Gdx.graphics.getWidth();
        WINDOW_HEIGHT = Gdx.graphics.getHeight();
        
        camera = new OrthographicCamera(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void dispose () {
            batch.dispose();
            img.dispose();
    }
}
