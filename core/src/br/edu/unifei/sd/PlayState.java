/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import static br.edu.unifei.sd.TipoArma.FUZIL;
import static br.edu.unifei.sd.TipoArma.PISTOLA;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author matheuscandido
 */
public class PlayState extends State {

    private Texture characterTexture, pistolaTexture, fuzilTexture, mapTexture;
    private Mapa mapa;
    float tempo = 0;
    Random rn = new Random();
    private List<Arma> armas = new ArrayList<Arma>();

    Jogador jogador;
    Sprite mapSprite;
    

    public PlayState(GameStateManager gsm) {
        super(gsm);
        
        // Inicializando as coisas
        characterTexture = new Texture(Gdx.files.internal("survivor-knife.png"));
        pistolaTexture = new Texture(Gdx.files.internal("pistol.png"));
        fuzilTexture = new Texture(Gdx.files.internal("SVT-40.png"));
        mapTexture = new Texture(Gdx.files.internal("map.jpg"));
        
        mapa = new Mapa();
        mapSprite = new Sprite(mapTexture);
        mapSprite.setPosition((-mapTexture.getWidth())/2, (-mapTexture.getHeight())/2);

        System.out.println("Criou jogador");
        jogador = new Jogador(
                rn.nextInt(Constantes.MAPA_WIDTH), 
                rn.nextInt(Constantes.MAPA_HEIGHT),
                characterTexture,
                200,
                200
        );
        System.out.println("Jogador ang" + jogador.sprite.getRotation());
        
        // Configura a camera para ?
        camera.setToOrtho(false, Constantes.MAPA_WIDTH, Constantes.MAPA_HEIGHT);
        camera.position.set(jogador.sprite.getX(), jogador.sprite.getY(), 0);
        camera.update();
        
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
            jogador.rotacionar(200 * Gdx.graphics.getDeltaTime());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            jogador.rotacionar(-200 * Gdx.graphics.getDeltaTime());
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
    }
    
    @Override
    public void render(SpriteBatch sb) {
        handleInput();
        
        camera.position.set(jogador.sprite.getX(), jogador.sprite.getY(), 0);
        camera.update();
        
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        
        mapSprite.draw(sb);
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
