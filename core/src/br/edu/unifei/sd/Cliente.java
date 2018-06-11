/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import br.edu.unifei.sd.rede.JogadorMoveu;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import java.net.InetAddress;
import java.util.ArrayList;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author matheuscandido
 */
public class Cliente {
    
    private Jogador jogador;
    private InetAddress enderecoServidor, meuEndereco;
    private Client kryonetClient = new Client();
    private static final int TCP = 6660, UDP = 6661, TIMEOUT = 500000;
    private PlayState playstate;
    
    public Cliente() {
        
    }
    
    public Cliente(PlayState playstate) {
        this.playstate = playstate;
    }
    
    public ArrayList<Servidor> descobreServidor() {
        System.out.println("Procurando Server...");
        enderecoServidor = kryonetClient.discoverHost(UDP, TIMEOUT);
        System.out.println("Servidor encontrado em: " + enderecoServidor.getHostAddress());
        return null;
    }
    
    public void conectaServidor() throws IOException {
        
        System.out.println("Conectando...");
        kryonetClient.start();
        kryonetClient.connect(TIMEOUT, enderecoServidor, TCP, UDP);
        System.out.println("Registrando...");
        Kryo kryo = kryonetClient.getKryo();
        //  kryo.register(Jogador.class);
        System.err.println("REgistrado... Enviando - cliente");
        // kryonetClient.sendTCP(jogador);
        System.err.println("Enviado cliente");
        meuEndereco = InetAddress.getLocalHost();
        
    }
    
    public Client getKryonetClient() {
        return kryonetClient;
    }
    
    public void listener(Connection connection, Object object) {
        // Implementar recebimento de objetos do servidor
        System.out.println("Entrando no listener");
        kryonetClient.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof JogadorMoveu) {
                    
                    for (Jogador player : playstate.getJogadores()) {
                        if (player.getId() == ((JogadorMoveu) object).playerId) {
                            
                            player.getSprite().setX(((JogadorMoveu) object).x);
                            player.getSprite().setY(((JogadorMoveu) object).y);
                            player.getSprite().setRotation(((JogadorMoveu) object).angulo);
                            break;
                        }
                        
                        
                    }
                }
            }
        });
        
    }
    
    public Jogador getJogador() {
        return jogador;
    }
    
    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }
    
    public void refresh() {
        
    }
    
    public InetAddress getEnderecoServidor() {
        return enderecoServidor;
    }
    
    public InetAddress getMeuEndereco() {
        return meuEndereco;
    }
    
}
