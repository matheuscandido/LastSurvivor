/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd.screens;

import br.edu.unifei.sd.GameMap;
import br.edu.unifei.sd.net.LocalClient;
import br.edu.unifei.sd.net.LocalServer;
import com.badlogic.gdx.Game;
import java.io.IOException;

/**
 *
 * @author matheuscandido
 */
public class ScreenGame extends ScreenCore{
    
    private GameMap gameMap;
    
    private LocalServer localServer;
    private LocalClient localClient;
    
    private final boolean isHost;
    private final String ip;
    private final String name;

    public ScreenGame(Game game, boolean isHost, String ip, String name) {
        super(game);
        this.isHost = isHost;
        if(!ip.isEmpty()){
            this.ip = ip;
        } else {
            this.ip = "localhsot";
        }
        this.name = name;
    }
    
    public void show(){
        localClient = new LocalClient(name);
        gameMap = localClient.getMap();
        
        if(isHost){
            try{
                localServer = new LocalServer();
                localClient.connectLocal();
            } catch(IOException e){
                e.printStackTrace();
                game.setScreen(new ScreenMenu(this));
            }
        } else {
            localClient.connect(ip);
        }
    }

    @Override
    public void render(float delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void resize(int width, int height){
        
    }
    
}
