/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import br.edu.unifei.sd.LastSurvivor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MenuState extends State{
    
    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("menu-bg.jpg");
        playButton = new Texture("playButton.png");
        
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            try {
                gsm.set(new PlayState(gsm));
            } catch (IOException ex) {
                Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispose();
        }
    }

    @Override
    public void update(float dt){
        handleInput();
    }
    
    @Override
    public void render(SpriteBatch sb) { //tirar o float dt
        sb.begin();
        sb.draw(background, 0, 0, LastSurvivor.WIDTH, LastSurvivor.HEIGHT);
        sb.draw(playButton, (LastSurvivor.WIDTH/2)-(playButton.getWidth()/2), (LastSurvivor.HEIGHT/2));
        sb.end();
        //-(playButton.getHeight()/2)
    }
    
    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
    
}
