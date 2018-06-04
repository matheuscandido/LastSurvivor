/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import static br.edu.unifei.sd.Constantes.NUM_ARMAS;
import static br.edu.unifei.sd.TipoArma.FUZIL;
import static br.edu.unifei.sd.TipoArma.PISTOLA;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author matheuscandido
 */
public class PlayState extends State {

    private Texture characterTexture, pistolaTexture, fuzilTexture;
    private Rectangle character;
    // private List<Rectangle> pistolas = new ArrayList<Rectangle>();
    private Mapa mapa;
    float tempo = 0;
    Random rn = new Random();
    private List<Arma> armas = new ArrayList<Arma>();
    private Cliente cliente = new Cliente();
    
    Jogador jogador;

    public PlayState(GameStateManager gsm) throws IOException {
        super(gsm);
        character = new Rectangle(10, 10, 200, 200);


        character.x = 0;
        character.y = 0;
        characterTexture = new Texture(Gdx.files.internal("survivor-knife.png"));
        pistolaTexture = new Texture(Gdx.files.internal("pistol.png"));
        fuzilTexture = new Texture(Gdx.files.internal("SVT-40.png"));
      
        mapa = new Mapa();
        mapa.setElementosGraficos(new LinkedList());

        jogador = new Jogador(character.x, character.y);
        cliente.descobreServidor();
        cliente.conectaServidor(jogador);//conectaServidor envia jogador para o servidor
        System.out.println("Jogador enviado");
        mapa.addElementoGrafico(jogador);
        for(Jogador player : cliente.jogadores){
        //acrescenta lista atualizada no mapa
         mapa.addElementoGrafico(player);
        
        
        }
        
        camera.setToOrtho(false, LastSurvivor.WIDTH/2, LastSurvivor.HEIGHT/2);

        for (int i = 0; i < NUM_ARMAS; i++) {

            if (rn.nextInt(2) == 0) {
                armas.add(new Arma(5, 5, PISTOLA));

            } else {
                armas.add(new Arma(5, 5, FUZIL));
            }

        }

        for (Arma arma : armas) {


            arma.x = rn.nextInt(1200);
            arma.y = rn.nextInt(700);

            if (arma.getTipoArma() == PISTOLA) {
                arma.setRetangulo(new Rectangle(arma.x, arma.y, 20, 20));
                mapa.addElementoGrafico(arma);
            } else {
                if (arma.getTipoArma() == FUZIL) {

                    arma.setRetangulo(new Rectangle(arma.x, arma.y, 40, 40));
                    mapa.addElementoGrafico(arma);
                }

            }


        }

    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            character.x -= 200 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            character.x += 200 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            character.y += 200 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            character.y -= 200 * Gdx.graphics.getDeltaTime();
        }
    }

    @Override
    public void update(float dt){
        //implementa refresh, atualiza lista de jogadores
        
        for(Jogador player : cliente.jogadores){
        //acrescenta lista atualizada no mapa
         mapa.addElementoGrafico(player);
        
        
        }
        
        
        
    }
    
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        handleInput();
        sb.begin();


       //sb.draw(characterTexture, character.getX(), character.getY());

       Iterator<ElementoGrafico> iter = mapa.getElementosGraficos().iterator();
        while (iter.hasNext()) {
            // aqui ficarao os ifs pertinentes a processamento grafico e logico
            ElementoGrafico eg = iter.next();
           if(eg instanceof Arma){ 
            if (((Arma) eg).getTipoArma()== PISTOLA) {
                sb.draw(pistolaTexture, eg.x, eg.y);
            }


            if (((Arma) eg).getTipoArma() == FUZIL) {
               sb.draw(fuzilTexture, eg.x, eg.y);
            }


            if (character.overlaps(((Arma) eg).getRetangulo())) {

                jogador.setArma(((Arma) eg));
                iter.remove();
            }
            
           }
           
           if(eg instanceof Jogador){
           
                sb.draw(characterTexture, eg.x, eg.y);
                
           }
        }
        sb.end();
    }

    @Override
    public void dispose() {

        characterTexture.dispose();
        pistolaTexture.dispose();
        fuzilTexture.dispose();

    }

}
