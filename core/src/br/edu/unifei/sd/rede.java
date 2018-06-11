/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

/**
 *
 * @author user
 */
public class rede {

    public rede() {
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
