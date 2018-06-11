/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheuscandido
 */
public class PlayState extends State {

    private Texture characterTexture, characterPistolaTexture, characterFuzilTexture, pistolaTexture, fuzilTexture, mapTexture;
    
    float tempo = 0;
    Random rn = new Random();
    private List<Jogador> jogadores;
    private List<ElementoGrafico> egs = new ArrayList<ElementoGrafico>();
    private List<Arma> armas = new ArrayList<Arma>();
    private Cliente cliente;
    Jogador jogador;//jogador local
    Sprite mapSprite;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        
        cliente = new Cliente(this);

        jogadores = new ArrayList<Jogador>();
        
        // Inicializando as texturas
        characterTexture = new Texture(Gdx.files.internal("survivor-knife.png"));
        characterPistolaTexture = new Texture(Gdx.files.internal("survivor-pistol.png"));
        characterFuzilTexture = new Texture(Gdx.files.internal("survivor-rifle.png"));
        pistolaTexture = new Texture(Gdx.files.internal("pistol.png"));
        fuzilTexture = new Texture(Gdx.files.internal("SVT-40.png"));
        mapTexture = new Texture(Gdx.files.internal("map.jpg"));
        
        mapSprite = new Sprite(mapTexture);
        mapSprite.setPosition((-mapTexture.getWidth()) / 2, (-mapTexture.getHeight()) / 2);

        // Inicializando o jogador EU
        System.out.println("Criou jogador");
        jogador = new Jogador(
                200,
                200,
                characterTexture,
                rn.nextInt(Constantes.MAPA_WIDTH),
                rn.nextInt(Constantes.MAPA_HEIGHT)
        );
        jogador.setId(cliente.getKryonetClient().getID());
        
        // Tenta conectar ao servidor
        try {
            cliente.conectaServidor();
        } catch (IOException ex) {
            Logger.getLogger(PlayState.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Configura a camera para ?
        camera.setToOrtho(false, Constantes.MAPA_WIDTH, Constantes.MAPA_HEIGHT);
        camera.position.set(jogador.sprite.getX(), jogador.sprite.getY(), 0);
        camera.update();

//        // Adiciona novas armas ao vetor de armas
//        for (int i = 0; i < Constantes.NUM_ARMAS; i++) {
//            int posInicialX = rn.nextInt(Constantes.MAPA_WIDTH);
//            int posInicialY = rn.nextInt(Constantes.MAPA_HEIGHT);
//            if (rn.nextInt(2) == 0) {
//                egs.add(new Arma(32, 32, PISTOLA, pistolaTexture, posInicialX, posInicialY));
//            } else {
//                egs.add(new Arma(64, 13, FUZIL, fuzilTexture, posInicialX, posInicialY));
//            }
//        }
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    @Override
    public void handleInput() {
        // Movimentação do jogador
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cliente.getKryonetClient().sendUDP(jogador.rotacionar(200 * Gdx.graphics.getDeltaTime()));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cliente.getKryonetClient().sendUDP(jogador.rotacionar(-200 * Gdx.graphics.getDeltaTime()));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cliente.getKryonetClient().sendUDP(jogador.andar(200 * Gdx.graphics.getDeltaTime()));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cliente.getKryonetClient().sendUDP(jogador.andar(-200 * Gdx.graphics.getDeltaTime()));
        }
        // Tiro
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            // fazer algo
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {

        camera.position.set(jogador.sprite.getX(), jogador.sprite.getY(), 0);
        camera.update();

        sb.setProjectionMatrix(camera.combined);
        sb.begin();

        mapSprite.draw(sb);
        
        jogador.sprite.draw(sb);
        
        for(Jogador jogador : jogadores){
            jogador.sprite.draw(sb);
        }

//        //Iterator<Arma> iter = armas.iterator();//Aqui podemos percorrer a lista de elementos graficos
//        Iterator<ElementoGrafico> iter = egs.iterator();
//        while (iter.hasNext()) {
//            // aqui ficarao os ifs pertinentes a processamento grafico e logico
//            ElementoGrafico eg = iter.next();
//
//            //Se o jogador passa por uma arma
//            if (eg instanceof Arma) {
//                if (jogador.getSprite().getBoundingRectangle().overlaps(eg.getSprite().getBoundingRectangle())) {
//
//                    if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
//
//                        jogador.setArma((Arma) eg);
//                        //Muda a textura do jogador de acordo com a arma
//                        if (jogador.getArma().getTipoArma() == null) {
//                            jogador.getSprite().setTexture(characterTexture);
//                        }
//                        if (jogador.getArma().getTipoArma() == TipoArma.PISTOLA) {
//                            jogador.getSprite().setTexture(characterPistolaTexture);
//                        }
//                        if (jogador.getArma().getTipoArma() == TipoArma.FUZIL) {
//                            jogador.getSprite().setTexture(characterFuzilTexture);
//                        }
//                        iter.remove();
//
//                    }
//                } else {
//                    eg.getSprite().draw(sb);
//                }
//            }
//        }

        //Implementar iterator de jogadores
        sb.end();
    }

    @Override
    public void dispose() {

        characterTexture.dispose();
        pistolaTexture.dispose();
        fuzilTexture.dispose();

    }

    public Jogador getJogador() {
        return jogador;
    }

    public Texture getCharacterTexture() {
        return characterTexture;
    }

    public Texture getCharacterPistolaTexture() {
        return characterPistolaTexture;
    }

    public Texture getCharacterFuzilTexture() {
        return characterFuzilTexture;
    }

    public List<Arma> getArmas() {
        return armas;
    }
    
    

}
