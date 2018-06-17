/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

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
    private PlayState playstate;
    
    public Tiro(float x, float y, float angulo, TipoArma tipo,Texture textura) {
        super(17, 11);
        
        this.x = x;
        //System.out.println(this.x);
        this.y = y;
        this.angulo = angulo; 
        this.tipo = tipo;
        this.distanciaPercorrida = 0;
        
        sprite = new Sprite(textura);
        //this.sprite.setTexture(textura);
        this.sprite.setSize(12, 12);
        this.sprite.setOriginCenter();
        //this.sprite.setPosition(x,y);
        //this.sprite.setOrigin(x, y);
        //this.sprite.setOrigin(x-sprite.getWidth()/2, y-sprite.getHeight()/2);
        //this.sprite.setOriginBasedPosition(x, y);
       
        this.sprite.setPosition(x+20,y+15);
        this.sprite.setRotation(angulo);
        
        
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
      //float newX = this.sprite.getX() + (float) Math.cos(Math.toRadians((double)this.sprite.getRotation()%360)) * velocidade * dt;
      //  float newY = this.sprite.getY() + (float) Math.sin(Math.toRadians((double)this.sprite.getRotation()%360)) * velocidade * dt;
       // this.sprite.setRotation(angulo);
        float newX = this.sprite.getX() +(float) Math.cos(Math.toRadians((double)angulo%360)) * velocidade * dt;
        float newY = this.sprite.getY() +(float) Math.sin(Math.toRadians((double)angulo%360)) * velocidade * dt;
        System.out.println("IMPRIMINDO NEWX E NEWY");
        System.out.println(newX);
        System.out.println(newY);
        float dist = (float) Math.sqrt((newX*newX)+(newY*newY));
        this.distanciaPercorrida += dist;
        System.out.println("IMPRIMINDO ORIGEM DA SPRITE");
        System.out.println(this.sprite.getOriginX());
        System.out.println(this.sprite.getOriginY());
        this.sprite.setPosition(newX, newY);
        //this.sprite.setPosition(x,y);
 
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
