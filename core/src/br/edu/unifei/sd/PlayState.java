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
    private Mapa mapa;
    float tempo = 0;
    Random rn = new Random();
    private List<Arma> armas = new ArrayList<Arma>();

    Jogador jogador;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        
        // Inicializando as coisas
        characterTexture = new Texture(Gdx.files.internal("survivor-knife.png"));
        pistolaTexture = new Texture(Gdx.files.internal("pistol.png"));
        fuzilTexture = new Texture(Gdx.files.internal("SVT-40.png"));
        
        mapa = new Mapa();

        jogador = new Jogador(
                0, 
                0,
                characterTexture,
                220,
                220
        );
        
        // Configura a camera para ?
        camera.setToOrtho(false, Constantes.MAPA_WIDTH/2, Constantes.MAPA_HEIGHT/2);

        
        // Adiciona novas armas ao vetor de armas
        for (int i = 0; i < Constantes.NUM_ARMAS; i++) {
            int posInicialX = rn.nextInt(Constantes.MAPA_WIDTH);
            int posInicialY = rn.nextInt(Constantes.MAPA_HEIGHT);
            if (rn.nextInt(2) == 0) {
                armas.add(new Arma(32, 32, PISTOLA, pistolaTexture, posInicialX, posInicialY));
            } else {
                armas.add(new Arma(64, 13, FUZIL, fuzilTexture, posInicialX, posInicialY));
            }
        }
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            //jogador.x -= 200 * Gdx.graphics.getDeltaTime();
            jogador.rotacionar(-20 * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            jogador.rotacionar(20 * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            jogador.andar(200 * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            jogador.andar(-200 * Gdx.graphics.getDeltaTime());
        }
    }

    @Override
    public void update(float dt){
        System.out.println("Jogador: x = " + 
                jogador.sprite.getX() + 
                " y =  " + 
                jogador.sprite.getY() + 
                " theta = " + 
                jogador.sprite.getRotation());
    }
    
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        handleInput();
        sb.begin();


        jogador.sprite.draw(sb);

        Iterator<Arma> iter = armas.iterator();//Aqui podemos percorrer a lista de elementos graficos
        while (iter.hasNext()) {
            // aqui ficarao os ifs pertinentes a processamento grafico e logico
            Arma arma = iter.next();

            arma.getSprite().draw(sb);

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
