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
            gsm.set(new PlayState(gsm));
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
        sb.draw(background, 0, 0, Constantes.MAPA_WIDTH, Constantes.MAPA_HEIGHT);
        sb.draw(playButton, (Constantes.MAPA_WIDTH/2)-(playButton.getWidth()/2), (Constantes.MAPA_HEIGHT/2));
        sb.end();
        //-(playButton.getHeight()/2)
    }
    
    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
    
}
