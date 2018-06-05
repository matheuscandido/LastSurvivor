/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import com.badlogic.gdx.math.Rectangle;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import java.net.InetAddress;
import java.util.ArrayList;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author matheuscandido
 */
public class Cliente {
    public  LinkedList<Jogador> jogadores = new LinkedList();
    private Jogador jogador;
    private InetAddress endereco;
    private Client kryonetClient = new Client();
    private static final int TCP = 6660, UDP = 6661, TIMEOUT = 500000;
    
    public void descobreServidor() throws IOException{
        
        System.out.println("Conectando...");
        kryonetClient.start();
        System.out.println("Procurando Server...");
        endereco = kryonetClient.discoverHost(UDP, TIMEOUT);
        System.out.println("Servidor encontrado em: " + endereco.getHostAddress());
        System.out.println("Registrando...");
        kryonetClient.connect(TIMEOUT, endereco, TCP, UDP);
        Kryo kryo = kryonetClient.getKryo();
        kryo.register(Jogador.class);
        kryo.register(Arma.class);
        kryo.register(Rectangle.class);
        kryo.register(TipoArma.class);
        

    }
    
    public void conectaServidor(Jogador jogador) throws IOException{
        
       
        //kryonetClient.start();
        
       
       
        System.err.println("Enviando - cliente");
        kryonetClient.sendTCP(jogador);
        System.err.println("Enviado cliente");
        
    }
   
    public void listener(Connection connection, Object object){
    
        System.out.println("Entrando no listener");
        kryonetClient.addListener(new Listener(){
            @Override
            public void received (Connection connection, Object object) {
          if (object instanceof Jogador) {
              System.out.println("Trolha em processo de recuperacao");
              jogadores.add((Jogador) object);
              System.out.println("Trolha recebida");
          }
       }
        });
      
    }
    
    public void refresh(){
        
        
        
    }
    
}
