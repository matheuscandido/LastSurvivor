/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author matheuscandido
 */
public abstract class State {
    
    protected OrthographicCamera camera;
    protected GameStateManager gsm;
    
    public State(GameStateManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
    }
    
    public abstract void handleInput();
    public abstract void render(SpriteBatch sb, float dt);
    public abstract void dispose();
}
