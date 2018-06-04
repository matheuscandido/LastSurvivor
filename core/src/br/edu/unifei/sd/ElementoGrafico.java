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
public abstract class ElementoGrafico {
    protected float x;
    protected float y;
    private final float altura ; 
    private final float largura;
    
    public ElementoGrafico(){
        altura = 100; 
        largura = 100;
    }
    
    public ElementoGrafico(float largura, float altura){
        this.altura = altura;
        this.largura = largura;
    }
}
