/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 *
 * @author matheuscandido
 */
public abstract class ScreenCore implements Screen{
    
    Game game;
    
    public ScreenCore(Game game){
        this.game = game;
    }
    
    public void resize(int width, int height){
        
    }
    
    public void show(){
        
    }
    
    public void hide(){
        
    }
    
    public void pause(){
        
    }
    
    public void resume(){
        
    }
    
    public void dispose(){
        
    }
    
}
