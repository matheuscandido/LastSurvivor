/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import br.edu.unifei.sd.rede.JogadorMoveu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author matheuscandido
 */
public class Jogador extends Movel{
    
    private String nickname;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private Arma arma;
    
    public Jogador(int largura, int altura, Texture texture, float posX, float posY) {
        super(largura, altura);
        sprite = new Sprite(texture);
        sprite.setScale(0.4f);
        sprite.setPosition(posX, posY);
    }
    
    public Jogador(int id, int largura, int altura, Texture texture, float posX, float posY) {
        super(largura, altura);
        
        this.id = id;
        
        sprite = new Sprite(texture);
        sprite.setScale(0.4f);
        sprite.setPosition(posX, posY);
    }
    
    public void darComando(Comando comando){
        
    }
    
    public Tiro atirar(){
        return new Tiro(Constantes.TIRO_LARGURA, Constantes.TIRO_ALTURA, x, y, angulo, arma);
    }
   
    public JogadorMoveu andar(float r){
        float addX = this.sprite.getX() + (float) Math.cos(Math.toRadians((double)this.sprite.getRotation()%360)) * r;
        float addY = this.sprite.getY() + (float) Math.sin(Math.toRadians((double)this.sprite.getRotation()%360)) * r;
        this.sprite.setPosition(addX, addY);
        return new JogadorMoveu(id, addX, addY, this.sprite.getRotation());
    }
    
    public JogadorMoveu rotacionar(float angulo){
        float deg = (this.sprite.getRotation() + angulo)%360;
        this.sprite.setRotation(deg);
        return new JogadorMoveu(id, sprite.getX(), sprite.getY(), deg);
       
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

//    public void setCliente(Cliente cliente) {
//        this.cliente = cliente;
//    }
    
    
    
}