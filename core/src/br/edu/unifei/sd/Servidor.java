/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Random;

/**
 *
 * @author matheuscandido
 */
public class Servidor {
    private Server server;
    private PlayState playState;
    private Random random = new Random();
    
    public Servidor() throws IOException {
        server = new Server(){
            protected Connection newConnection(){
                return new SurvivorConnection(); 
            }
        };
        
        Kryo kryo = server.getKryo();
        kryo.register(ElementoGrafico.class);
        kryo.register(Fixo.class);
        kryo.register(Arma.class);
        kryo.register(Movel.class);
        kryo.register(TipoArma.class);
        kryo.register(Jogador.class);
        kryo.register(Tiro.class);
        
        server.addListener(new Listener(){
            public void received(Connection c, Object message){
                SurvivorConnection connection = (SurvivorConnection) c;
                
            }
        });
    }
    
    private InetAddress endereco;
    
    public void iniciaPartida(){
        
    }
    
    public void esperaClientes(){
        
    }
    
    public void adicionaCliente(){
        
    }
    
    public void listener(Connection connection, Object object){
        
    }
    
    static class SurvivorConnection extends Connection{
        public String name;
    }
    
}
