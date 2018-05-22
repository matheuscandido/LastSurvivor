package br.edu.unifei.sd;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
        camera.update();
        
        character = new Rectangle();
        character.x = 0;
        character.y = 0;
        character.width = 144;
        character.height = 122;
        
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        batch.draw(characterImage, character.x, character.y);
        batch.end();
        
        if(Gdx.input.isKeyPressed(Keys.LEFT)) character.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) character.x += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Keys.UP)) character.y += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Keys.DOWN)) character.y -= 200 * Gdx.graphics.getDeltaTime();
        
        camera.position.x = character.x;
        camera.position.y = character.y;
        camera.update();
        
        System.out.println(character.x + " - " + character.y);
    }

    @Override
    public void dispose () {
            batch.dispose();
            characterImage.dispose();
    }
}
