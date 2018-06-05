/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import java.util.List;

/**
 *
 * @author matheuscandido
 */
public class Network {
    
    static public class MovementState{
        public int playerId;
        public int x;
        public int y;
        public float angle;
        
        public MovementState(){}
        public MovementState(int playerId, int x, int y, float angle){
            this.playerId = playerId;
            this.x = x;
            this.y = y;
            this.angle = angle;
        }
    }
    
    static public class PlayerSpawns{
        public int playerId;
        public MovementState movementState;
        
        public PlayerSpawns(){}
        public PlayerSpawns(int playerId, MovementState movementState){
            this.playerId = playerId;
            this.movementState = movementState;
        }
    }
    
    static public class PlayerWasShot{
        public int playerIdVictim;
        public int playerIdShooter;
        
        public PlayerWasShot(){}
        public PlayerWasShot(int playerIdVictim, int playerIdShooter){
            this.playerIdVictim = playerIdVictim;
            this.playerIdShooter = playerIdShooter;
        }
    }
    
    static public class PlayerShoots{
        public int playerId;
        public int x;
        public int y;
        public float angle;
        public Arma arma;
        
        public PlayerShoots(){}
        public PlayerShoots(int playerId, int x, int y, float angle, Arma arma){
            this.playerId = playerId;
            this.x = x;
            this.y = y;
            this.angle = angle;
            this.arma = arma;
        }
    }
    
    static public class ArmaData{
        public int x;
        public int y;
        public TipoArma tipoArma;
        public boolean picked;
        
        public ArmaData(){}
        public ArmaData(int x, int y, TipoArma tipoArma, boolean picked){
            this.x = x;
            this.y = y;
            this.tipoArma = tipoArma;
            this.picked = picked;
        }
    }
    
    static public class PlayStateData{
        public boolean roundOver;
        public List<ArmaData> armaDatas;
        
        public PlayStateData(){}
        public PlayStateData(List<ArmaData> armaDatas, boolean roundOver){
            this.armaDatas = armaDatas;
            this.roundOver = roundOver;
        }
    }
    
}
