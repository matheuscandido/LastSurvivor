/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import com.esotericsoftware.kryonet.Connection;
import java.net.InetAddress;
import java.util.ArrayList;

import com.esotericsoftware.kryonet.Client;

/**
 *
 * @author matheuscandido
 */
public class Cliente {
    private Jogador jogador;
    private InetAddress endereco;
    private Client kryonetClient;
    
    public ArrayList<Servidor> descobreServidor(){
        return null;
    }
    
    public boolean conectaServidor(Servidor servidor){
        return false;
    }
    
    public void listener(Connection connection, Object object){
        
    }
    
    public void refresh(){
        
    }
}
