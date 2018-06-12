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
    
    
    
}
