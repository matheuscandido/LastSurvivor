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
import java.util.LinkedList;

/**
 *
 * @author matheuscandido
 */
public class Servidor {
    
    private InetAddress endereco;
    private static final int TCP = 6660, UDP = 6661, TIMEOUT = 5000;

    public static void main(String[] args) throws Exception {
        // TODO code application logic here
         final LinkedList<Jogador> jogadoresServidor = new LinkedList();  

        Server kryonetServer;
        System.out.println("Iniciando...");
        kryonetServer = new Server();
        kryonetServer.start();
        kryonetServer.bind(TCP, UDP);

        System.out.println("Registrando...");
        Kryo kryo = kryonetServer.getKryo();
        kryo.register(Jogador.class);

        System.out.println("Subiu");
        kryonetServer.addListener(new Listener() {
            public void received(Connection connection, Object object) {
               // Thread t = new Thread(() -> {
                    if (object instanceof Jogador) {
                   
                       jogadoresServidor.add((Jogador) object);
                       
                       for( Jogador player : jogadoresServidor){
                           System.out.println("Enviando Trolha");
                        connection.sendTCP(player);
                       System.out.println("Trolha enviada");
                       }
                    }
              //  });
              //  t.start();
            }
        });
    }
    
    
    
//    public void iniciaPartida() throws IOException{
//        System.out.println("Iniciando...");
//        kryonetServer = new Server();
//        kryonetServer.start();
//        kryonetServer.bind(TCP, UDP);
//    }
//    
//    public void esperaClientes(){
//        
//    }
//    
//    public void adicionaCliente(){
//        
//    }
//    
//    public void listener(Connection connection, Object object){
//        
//        
//        
//        
//    }
    
}
