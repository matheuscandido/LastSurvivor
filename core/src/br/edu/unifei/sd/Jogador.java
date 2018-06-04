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
    
    float addX,addY;
    public void andar(float r){
        addX = (float) Math.cos(Math.toRadians((double)this.sprite.getRotation()%360)) * r;
        addY = (float) Math.sin(Math.toRadians((double)this.sprite.getRotation()%360)) * r;
        
        System.err.println("(x: " + sprite.getX() + ", dx: " + addX + "\t" + 
               "(y: " + sprite.getY() + ", dy: " + addY + "\t" +
                "deg: " +sprite.getRotation()+ ", dd: 0" + ")");
        this.sprite.setPosition(this.sprite.getX() + addX, this.sprite.getY() + addY);
    }
    
    public void rotacionar(float angulo){
        float deg = (this.sprite.getRotation() + angulo)%360;
        this.sprite.setRotation(deg);
        
        System.err.println("(x: " + sprite.getX() + ", dx: " + addX + ")\t" + 
               "(y: " + sprite.getY() + ", dy: " + addY + ")\t" +
                "(deg: " +sprite.getRotation()+ ", dd: " + angulo +")");
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
