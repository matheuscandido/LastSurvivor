/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import br.edu.unifei.sd.rede.JogadorAtirou;
import br.edu.unifei.sd.rede.JogadorMorreu;
import br.edu.unifei.sd.rede.JogadorMoveu;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import java.net.InetAddress;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import java.io.IOException;
import java.util.Iterator;

/**
 *
 * @author matheuscandido
 */
public class Cliente {
    
    private Jogador jogador;
    private InetAddress enderecoServidor, meuEndereco;
    private Client kryonetClient; 
    private PlayState playstate;
    
    
    public Cliente(PlayState playstate) {
        this.playstate = playstate;
        
        kryonetClient = new Client();
        
        kryonetClient.addListener(new Listener() {
            
            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof JogadorMoveu) {
                    handleJogadorMoveu(connection, object);
                } else if(object instanceof JogadorAtirou){
                    handleJogadorAtirou(connection, object);
                } else if(object instanceof JogadorMorreu){
                    handleJogadorMorreu(connection,object);
                }
            }
        });
        
        // Registrando classes do Kryonet com método estático acessório
        rede.register(kryonetClient);
        
        enderecoServidor = kryonetClient.discoverHost(Constantes.UDP, Constantes.TIMEOUT);
    }
    
    public void conectaServidor() throws IOException {
        System.out.println("Conectando...");
        kryonetClient.start();
        kryonetClient.connect(Constantes.TIMEOUT, enderecoServidor, Constantes.TCP, Constantes.UDP);
        System.out.println("Registrando...");
        Kryo kryo = kryonetClient.getKryo();
        //  kryo.register(Jogador.class);
        System.err.println("REgistrado... Enviando - cliente");
        // kryonetClient.sendTCP(jogador);
        System.err.println("Enviado cliente");
        meuEndereco = InetAddress.getLocalHost();
    }
    
    protected synchronized void handleJogadorMoveu(Connection connection, Object object){
        boolean temJogador = false;
                    
        for (Jogador player : playstate.getJogadores()) {
            if (player.getId() == ((JogadorMoveu) object).playerId) {
                player.getSprite().setX(((JogadorMoveu) object).x);
                player.getSprite().setY(((JogadorMoveu) object).y);
                player.getSprite().setRotation(((JogadorMoveu) object).angulo);
                System.out.println("RECEBI ALGO DO SERVIDOR: ID " + ((JogadorMoveu) object).playerId);
                temJogador = true;
                break;
            }   
        }

        if(!temJogador){
            System.out.println("JOGADOR ADICIONADO AO VETOR DE JGOADORES");
            this.playstate.getJogadores().add(
                    new Jogador(
                            ((JogadorMoveu) object).playerId, 
                            Constantes.JOGADOR_WIDTH, 
                            Constantes.JOGADOR_HEIGHT, 
                            this.playstate.getCharacterTexture(), 
                            ((JogadorMoveu) object).x, 
                            ((JogadorMoveu) object).y
                    )
            );
        }
    }
    
    // Quando recebo um evento JogadorAtirou, adiciono um tiro na lista de tiros do PlayState
    protected synchronized void handleJogadorAtirou(Connection connection, Object object){
        
        
        boolean isFuzil = ((JogadorAtirou)object).isFuzil;
        float x = ((JogadorAtirou)object).x;
        float y = ((JogadorAtirou)object).y;
        float angulo = ((JogadorAtirou)object).angulo;
        System.out.println("RECEBENDO TIROS");
        
        if(isFuzil)
             playstate.getTirosDosOutros().add(new Tiro(x, y, angulo, TipoArma.FUZIL,playstate.getTiroTexture()));
        else
             playstate.getTirosDosOutros().add(new Tiro(x, y, angulo, TipoArma.PISTOLA,playstate.getTiroTexture()));
    }
    
    protected synchronized void handleJogadorMorreu(Connection connection, Object object){
            Iterator<Jogador> iter = playstate.getJogadores().iterator();
            JogadorMorreu morto = (JogadorMorreu) object;
            while(iter.hasNext()){
                    
                if(connection.getID() == morto.playerId){
                    System.out.println("JOGADOR REMOVIDO");
                    iter.remove();
                }
            }
            
    }
    public Client getKryonetClient() {
        return kryonetClient;
    }
    
    public Jogador getJogador() {
        return jogador;
    }
    
    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }
    
    public InetAddress getEnderecoServidor() {
        return enderecoServidor;
    }
    
    public InetAddress getMeuEndereco() {
        return meuEndereco;
    }
    
}
