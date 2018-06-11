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
    private Cliente cliente;
   
    
    public Jogador(float largura, float altura, Texture texture, int posX, int posY) {
        super(largura, altura);
        sprite = new Sprite(texture);
        sprite.setScale(0.4f);
        //MUITO IMPORTANTE!!!! Mandando referencia para cliente
        cliente = new Cliente();
        cliente.setJogador(this);
    }
    
    public void darComando(Comando comando){
        
    }
    
    public Tiro atirar(){
        return new Tiro(Constantes.TIRO_LARGURA, Constantes.TIRO_ALTURA, x, y, angulo, arma);
    }
    
    float addX,addY;
    public JogadorMoveu andar(float r){
        addX = (float) Math.cos(Math.toRadians((double)this.sprite.getRotation()%360)) * r;
        addY = (float) Math.sin(Math.toRadians((double)this.sprite.getRotation()%360)) * r;
        this.sprite.setPosition(this.sprite.getX() + addX, this.sprite.getY() + addY);
        return new JogadorMoveu(id,addX,addY, this.sprite.getRotation());
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

    public Cliente getCliente() {
        return cliente;
    }

//    public void setCliente(Cliente cliente) {
//        this.cliente = cliente;
//    }
    
    
    
}