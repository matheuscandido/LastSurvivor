/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import br.edu.unifei.sd.Network.*;
import com.esotericsoftware.kryonet.Connection;
import java.util.ArrayList;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import java.util.Random;

/**
 *
 * @author matheuscandido
 */
public class Cliente {
    private Client kryonetClient;
    private PlayState playState;
    public int id;
    private String name;
    public String remoteIp;
    
    private Random random = new Random();
    
    public Cliente(String name, PlayState playState){
        this.playState = playState;
        // this.isClient = true;
        this.name = name;
        
        kryonetClient = new Client();
        kryonetClient.start();
        
        //Network.register(kryonetClient);
        
        kryonetClient.addListener(new Listener(){
            
            public void connected(Connection connection){
                handleConnected(connection);
            }
            
            public void received(Connection connection, Object object){
                handleReceived(connection.getID(), object);
            }
            
            public void disconnected(Connection connection){
                handleDisconnected(connection);
            }
            
        });
        
    }
    
    private void handleConnected(Connection connection){
        id = connection.getID();
        remoteIp = connection.getRemoteAddressTCP().toString();
        //kryonetClient.sendTCP(new PlayerSpawns());
        kryonetClient.updateReturnTripTime();
        
    }
    
    private void handleReceived(int id, Object object){
        if(object instanceof MovementState){
            // fazer algo
        } else if(object instanceof PlayerSpawns){
            
        } else if(object instanceof PlayerShoots){
            
        } else if(object instanceof PlayerWasShot){
            
        } else if(object instanceof ArmaData){
            
        } else if(object instanceof ArmaCollected){
            
        }
    }
    
    private void handleDisconnected(Connection connection){
        playState.onDisconnect();
    }
    
    public ArrayList<Servidor> descobreServidor(){
        return null;
    }
    
    public boolean contectaServidor(Servidor servidor){
        return false;
    }
    
    public void listener(Connection connection, Object object){
        
    }
    
    public void refresh(){
        
    }
}
