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
        kryo.register(PosicaoArma.class);
        kryo.register(TipoArma.class);
        kryo.register(JogadorAtirou.class);
        kryo.register(JogadorMorreu.class);
    }
    
    static public class JogadorAtirou{
        public float x;
        public float y;
        public float angulo;
        public boolean isFuzil;

        public JogadorAtirou(){}
        public JogadorAtirou(float x, float y, float angulo, boolean isFuzil) {
            this.x = x;
            this.y = y;
            this.angulo = angulo;
            this.isFuzil = isFuzil;
        }   
    }
    
    static public class JogadorMorreu{
        public int playerId;
    }
    
    static public class PosicaoArma{
        public int idArma;
        public float x;
        public float y;
        public TipoArma tipo;
        
        public PosicaoArma(){}
        public PosicaoArma(int idArma, float x, float y, TipoArma tipo){
            this.idArma = idArma;
            this.x = x;
            this.y = y;
            this.tipo = tipo;
        }
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
