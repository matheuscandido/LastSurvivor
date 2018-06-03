/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author matheuscandido
 */
public abstract class ElementoGrafico {
    protected float  x;
    protected float y;
    protected float altura;
    protected float largura;
    protected Sprite sprite;
    
    public ElementoGrafico(float largura, float altura){
        this.altura = altura;
        this.largura = largura;
    }

    public float getAltura() {
        return altura;
    }

    public float getLargura() {
        return largura;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    
    
}
