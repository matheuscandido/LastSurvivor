/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

/**
 *
 * @author matheuscandido
 */
public class Arma extends Fixo{
    private float fireRate;
    private float range;
    private boolean picked;
    private TipoArma tipoArma;

    public Arma(float largura, float altura, TipoArma tipoArma) {
        super(largura, altura);
        this.tipoArma = tipoArma;
    }

    public TipoArma getTipoArma() {
        return tipoArma;
    }

    public void setTipoArma(TipoArma tipoArma) {
        this.tipoArma = tipoArma;
    }
}
