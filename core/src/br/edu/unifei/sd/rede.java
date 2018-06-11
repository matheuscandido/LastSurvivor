/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import br.edu.unifei.sd.rede.JogadorMoveu;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

/**
 *
 * @author user
 */
public class rede {

    static public void register(EndPoint endPoint){
        Kryo kryo = endPoint.getKryo();
        kryo.register(JogadorMoveu.class);
    }

    static public class JogadorMoveu {

        public int playerId;
        public float x;
        public float y;
        public float angulo;

        public JogadorMoveu() {
        }

        public JogadorMoveu(int playerId, float x, float y, float angulo) {
            this.playerId = playerId;
            this.x = x;
            this.y = y;
            this.angulo = angulo;
        }

    };

}
