/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheuscandido
 */
public class Servidor {

    private Server kryonetServer;
    private InetAddress endereco;
    private static final int TCP = 6660, UDP = 6661, TIMEOUT = 500000;

    public Servidor() throws IOException {

        kryonetServer = new Server();
        kryonetServer.addListener(new Listener() {

            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof rede.JogadorMoveu) {

                    kryonetServer.sendToAllExceptUDP(connection.getID(), object);

                }
            }

        });

        kryonetServer.bind(TCP, UDP);
        kryonetServer.start();

    }

    public static void main(String[] args) {
        try {
            Servidor servidor = new Servidor();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
