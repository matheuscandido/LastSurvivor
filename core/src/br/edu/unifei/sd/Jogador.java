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
public class Jogador extends Movel{
    
    private String nickname;
    private Arma arma;
    private Cliente cliente;
    
    public Jogador(float largura, float altura, Texture texture, int posX, int posY) {
        super(largura, altura);
        sprite = new Sprite(texture);
        sprite.setScale(0.4f);
    }
    
    public void darComando(Comando comando){
        
    }
    
    public Tiro atirar(){
        return new Tiro(Constantes.TIRO_LARGURA, Constantes.TIRO_ALTURA, x, y, angulo, arma);
    }
    
    public void andar(float x, float y, float angulo){
        this.sprite.setPosition(this.sprite.getX() + x * Gdx.graphics.getDeltaTime(), this.sprite.getY() + y * Gdx.graphics.getDeltaTime() );
        this.sprite.setRotation(angulo);
    }
    
    public void andarEsferico(float r){
        float addX = (float) Math.cos((double)this.sprite.getRotation()) * r;
        float addY = (float) Math.sin((double)this.sprite.getRotation()) * r;
        this.sprite.setPosition(this.sprite.getX() + addX, this.sprite.getY() + addY);
    }
    
    public void rotacionar(float angulo){
        this.sprite.setRotation(this.sprite.getRotation() + (-angulo));
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }
    
    
    
}
