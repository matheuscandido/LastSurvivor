/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author matheuscandido
 */
public class Arma extends Fixo{
    private float fireRate;
    private float range;
    private boolean picked;
    private TipoArma tipoArma;
    
    public Arma(float largura, float altura, TipoArma tipoArma, Texture texture, int x, int y) {
        super(largura, altura);
        this.tipoArma = tipoArma;
        this.sprite = new Sprite(texture);
        this.sprite.setPosition(x, y);
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
