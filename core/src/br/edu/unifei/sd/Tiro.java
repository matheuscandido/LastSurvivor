/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author matheuscandido
 */
public class Tiro extends Movel{
    
    private float x;
    private float y;
    private float angulo;
    private float distanciaPercorrida;
    private TipoArma tipo;
    
    public Tiro(float x, float y, float angulo, TipoArma tipo) {
        super(17, 11);
        this.x = x;
        this.y = y;
        this.angulo = angulo;
        this.tipo = tipo;
        this.distanciaPercorrida = 0;
        this.sprite.setTexture(new Texture(Gdx.files.internal("shot.png")));
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getDistanciaPercorrida() {
        return distanciaPercorrida;
    }
    
    public boolean mover(float dt, float velocidade){
        float newX = (float) Math.cos(Math.toRadians((double)this.sprite.getRotation()%360)) * velocidade * dt;
        float newY = (float) Math.sin(Math.toRadians((double)this.sprite.getRotation()%360)) * velocidade * dt;
        float dist = (float) Math.sqrt((newX*newX)+(newY*newY));
        this.distanciaPercorrida += dist;
        this.sprite.setPosition(newX, newY);
        
        if(this.tipo == TipoArma.FUZIL){
            if(this.distanciaPercorrida < Constantes.ALCANCE_FUZIL){
                return true;
            } else {
                return false;
            }
        } else if(this.tipo == TipoArma.PISTOLA){
            if(this.distanciaPercorrida < Constantes.ALCANCE_PISTOLA){
                return true;
            } else {
                return false;
            }
        } else { // Se tipo não estiver setado, retorna falso e o tiro será destruído
            return false;
        }
    }
    
}
