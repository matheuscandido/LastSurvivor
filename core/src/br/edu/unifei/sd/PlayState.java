/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author matheuscandido
 */
public class PlayState extends State{
    
    private Texture characterTexture;
    private Rectangle character;
    private Mapa mapa;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        character = new Rectangle();
        character.x = 0;
        character.y = 0;
        characterTexture = new Texture(Gdx.files.internal("survivor-knife.png"));
        
        mapa = new Mapa();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) character.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) character.x += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) character.y += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) character.y -= 200 * Gdx.graphics.getDeltaTime();
    }

    @Override
    public void render(SpriteBatch sb, float dt) {
        handleInput();
        sb.begin();
        sb.draw(characterTexture, character.x, character.y);
        sb.end();
    }

    @Override
    public void dispose() {
        
    }
    
}
