/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author matheuscandido
 */
public class Arma extends Fixo{
    private float fireRate;
    private float range;
    private boolean picked;
    private TipoArma tipoArma;
    private Rectangle retangulo;

    public Rectangle getRetangulo() {
        return retangulo;
    }

    public void setRetangulo(Rectangle retangulo) {
        this.retangulo = retangulo;
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
    
    public Arma(float largura, float altura, TipoArma tipoArma) {
        super(largura, altura);
        this.tipoArma = tipoArma;
    }

    public TipoArma getTipoArma() {
        return tipoArma;
    }

    public float getFireRate() {
        return fireRate;
    }

    public void setFireRate(float fireRate) {
        this.fireRate = fireRate;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }

    

    public void setTipoArma(TipoArma tipoArma) {
        this.tipoArma = tipoArma;
    }
}
