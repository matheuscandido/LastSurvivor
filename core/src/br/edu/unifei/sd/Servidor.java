/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import static com.badlogic.gdx.Net.Protocol.TCP;
import static com.badlogic.gdx.utils.JsonValue.ValueType.object;
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

    private Server kryonetServer;
    private InetAddress endereco;
    private static final int TCP = 6660, UDP = 6661, TIMEOUT = 5000;
    //TODO: Passar essa linked list para ElementoGrafico para o servidor enviar
    //informações de todos os elementos gráficos para todos os clientes.
    //Será necessário para atualizar elementos como armas pegadas e tiros
    private LinkedList<Jogador> jogadoresServidor = new LinkedList();
    private ListenerEsperaClientes espera;

    public void subirServidor() throws IOException {
        Server kryonetServer;
        System.out.println("Iniciando...");
        kryonetServer = new Server();
        kryonetServer.start();
        kryonetServer.bind(TCP, UDP);

        System.out.println("Registrando...");
        Kryo kryo = kryonetServer.getKryo();
        kryo.register(Jogador.class);

        System.out.println("Subiu");
    }

    public void esperaClientes() {
        espera = new ListenerEsperaClientes();
        kryonetServer.addListener(espera);
    }

    private class ListenerEsperaClientes extends Listener {

        @Override
        public void received(final Connection connection, final Object object) {
            System.out.println("Entrada no listener servidor - esperaClientes");
            if (object instanceof Jogador) {
                jogadoresServidor.add((Jogador) object);

            }
        }
    }

    public void iniciaPartida() {
        kryonetServer.removeListener(espera);
    }

    private class ListenerPartida extends Listener {

        @Override
        public void received(final Connection connection, final Object object) {
            System.out.println("Entrada no listener servidor - ListenerPartida");
            //esta thread é para evitar overhead de processamento e para suportar multiplas requisições
            //A LISTA DE JOGADORES DEVE SER SINCRONIZED
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Entrada no listener servidor - ListenerPartida.run");
                    if (object instanceof Jogador) {
                        Jogador jogadorRecebido = (Jogador) object;
                        System.out.println("Entrada no listener servidor - ListenerPartida.instanceof");
                        
                        for(Jogador player : jogadoresServidor){
                            if(player.getCliente().getMeuEndereco().getHostAddress() ==
                                    jogadorRecebido.getCliente().getMeuEndereco().getHostAddress()){ 
                                //Achou seu proprio jogador na lista de jogadores
                            }
                        }
                        
                        jogadoresServidor.add((Jogador) object);

                        for (Jogador player : jogadoresServidor) {
                            System.out.println("Enviando Trolha");
                            connection.sendTCP(player);
                            System.out.println("Trolha enviada");
                        }
                    }
                }
            });

        }
    }

    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        final LinkedList<Jogador> jogadoresServidor = new LinkedList();

        /*kryonetServer.addListener(new Listener() {

            public void received(final Connection connection, final Object object) {
                System.out.println("antes do thread servidor");
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Entrada no listener servidor");
                        if (object instanceof Jogador) {

                            jogadoresServidor.add((Jogador) object);

                            for (Jogador player : jogadoresServidor) {
                                System.out.println("Enviando Trolha");
                                connection.sendTCP(player);
                                System.out.println("Trolha enviada");
                            }
                        }

                    }
                });
                t.start();
            }

        });
         */
    }
}
