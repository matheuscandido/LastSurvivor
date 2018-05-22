package br.edu.unifei.sd;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class LastSurvivor extends ApplicationAdapter {
	
    public static int WINDOW_WIDTH;
    public static int WINDOW_HEIGHT;
    public static int MAP_WIDTH;
    public static int MAP_HEIGHT;
    
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture characterImage;
    private Texture weaponImage;
    private Rectangle character;
    

    @Override
    public void create () {
        
        WINDOW_WIDTH = Gdx.graphics.getWidth();
        WINDOW_HEIGHT = Gdx.graphics.getHeight();
        
        batch = new SpriteBatch();
        characterImage = new Texture(Gdx.files.internal("survivor-knife.png"));
        weaponImage = new Texture(Gdx.files.internal("pistol.png"));
        
        camera = new OrthographicCamera(WINDOW_WIDTH, WINDOW_HEIGHT);
        camera.setToOrtho(false, 800, 480);
        
        character = new Rectangle();
        character.x = 800/2-64/2;
        character.y = 20;
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
