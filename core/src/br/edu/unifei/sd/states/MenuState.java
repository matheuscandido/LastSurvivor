/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd.states;

import br.edu.unifei.sd.LastSurvivor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author matheuscandido
 */
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
        
    }

    @Override
    public void render(SpriteBatch sb, float dt) {
        sb.begin();
        sb.draw(background, 0, 0, LastSurvivor.WIDTH, LastSurvivor.HEIGHT);
        sb.draw(playButton, (LastSurvivor.WIDTH/2-playButton.getWidth()/2), (LastSurvivor.HEIGHT/2-playButton.getHeight()/2));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
    
}
