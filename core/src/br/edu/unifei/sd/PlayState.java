/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import br.edu.unifei.sd.rede.JogadorMorreu;
import br.edu.unifei.sd.rede.JogadorMoveu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheuscandido
 */
public class PlayState extends State {

    private Texture characterTexture, characterPistolaTexture, characterFuzilTexture, pistolaTexture, fuzilTexture, mapTexture, tiroTexture;

    float tempo = 0;
    Random rn = new Random();
    private List<Jogador> jogadores;
    private List<ElementoGrafico> egs = new ArrayList<ElementoGrafico>();
    private List<Arma> armas = new ArrayList<Arma>();
    // private List<Tiro> tirosDosOutros = new ArrayList<Tiro>();
    private List<Tiro> tirosDosOutros = Collections.synchronizedList(new ArrayList<Tiro>());
    //private List<Tiro> meusTiros = new ArrayList<Tiro>();
    private List<Tiro> meusTiros = Collections.synchronizedList(new ArrayList<Tiro>());
    private Cliente cliente;

    /*
    Para que eu não tenha que carregar uma ID no JogadorAtirou, vou criar duas listas de tiros.
    A primeira é os tiros recebidos da rede, de outros players, que eu vou só mover pra frente e desenhar na tela andando.
    A segunda são os meus tiros, que vou além de mover e desenhar, verificar por intersects com outros players e caso afirmativo
    disparar um evento de JogadorMorreu para o servidor.
     */
    Jogador jogador;//jogador local
    Sprite mapSprite;

    ShapeRenderer sr;

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
        tiroTexture = new Texture(Gdx.files.internal("shot.png"));

        mapSprite = new Sprite(mapTexture);
        mapSprite.setPosition((-mapTexture.getWidth()) / 2, (-mapTexture.getHeight()) / 2);

        // Inicializando o jogador EU
        System.out.println("Criou jogador");
        jogador = new Jogador(
                Constantes.JOGADOR_WIDTH,
                Constantes.JOGADOR_HEIGHT,
                characterTexture,
                rn.nextInt(Constantes.MAPA_WIDTH),
                rn.nextInt(Constantes.MAPA_HEIGHT)
        );
        jogador.setId(cliente.getKryonetClient().getID());
        Arma arma = new Arma(Constantes.PISTOLA_WIDTH, Constantes.PISTOLA_HEIGHT, TipoArma.PISTOLA, pistolaTexture, 0, 0);
        jogador.setArma(arma);
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

        sr = new ShapeRenderer();

    }

    public Texture getTiroTexture() {
        return tiroTexture;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    @Override
    public synchronized void handleInput() {
        // Movimentação do jogador
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cliente.getKryonetClient().sendUDP(jogador.rotacionar(200 * Gdx.graphics.getDeltaTime()));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cliente.getKryonetClient().sendUDP(jogador.rotacionar(-200 * Gdx.graphics.getDeltaTime()));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            JogadorMoveu jogadorMoveu = jogador.andar(jogadores, 200 * Gdx.graphics.getDeltaTime());
            if (jogadorMoveu != null) {
                cliente.getKryonetClient().sendUDP(jogadorMoveu);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            JogadorMoveu jogadorMoveu = jogador.andar(jogadores, -200 * Gdx.graphics.getDeltaTime());
            if (jogadorMoveu != null) {
                cliente.getKryonetClient().sendUDP(jogadorMoveu);
            }
        }
        // Tiro
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (jogador.getArma() != null) {
                System.out.println("JOGADOR ATIRANDO");
                cliente.getKryonetClient().sendUDP(jogador.atirar());
                System.out.println("POSICAO DE JOGADOR AO ATIRAR");
                System.out.println(jogador.getSprite().getX());
                System.out.println(jogador.getSprite().getY());
                meusTiros.add(new Tiro(jogador.getSprite().getX(), jogador.getSprite().getY(), jogador.getSprite().getRotation(), jogador.getArma().getTipoArma(), this.tiroTexture));
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        calcularTiros(dt);
    }

    @Override
    public void render(SpriteBatch sb) {

        camera.position.set(jogador.sprite.getX(), jogador.sprite.getY(), 0);
        camera.update();

        sb.setProjectionMatrix(camera.combined);
        sb.begin();

        mapSprite.draw(sb);

        jogador.sprite.draw(sb);

        for (Jogador jogador : jogadores) {
            jogador.getSprite().setTexture(characterPistolaTexture);
            jogador.sprite.draw(sb);
        }
        synchronized (tirosDosOutros) {
            Iterator<Tiro> iterT = tirosDosOutros.iterator();
            while (iterT.hasNext()) {

                Tiro tdo = iterT.next();
                tdo.sprite.draw(sb);
            }
        }

        Iterator<Tiro> iterM = meusTiros.iterator();
        while (iterM.hasNext()) {

            Tiro mtr = iterM.next();
            mtr.sprite.draw(sb);

        }
        /*  for(Tiro tiro : tirosDosOutros){
        //tiro.sprite.setTexture(new Texture(Gdx.files.internal("shot.png")));
        System.out.println(" desenhando tiros ");
        System.out.println(" posicao X ");
        System.out.println( tiro.sprite.getX());
        System.out.println(" posicao Y ");
        System.out.println( tiro.sprite.getX());
        tiro.sprite.draw(sb);
        }*/
 /*        for(Tiro tiro : meusTiros){
        // tiro.sprite.setTexture(new Texture(Gdx.files.internal("shot.png")));
        System.out.println(" desenhando tiros ");
        System.out.println(" posicao X ");
        System.out.println( tiro.sprite.getX());
        System.out.println(" posicao Y ");
        System.out.println( tiro.sprite.getX());
        tiro.sprite.draw(sb);
        }*/

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
        if (jogador.getArma().getTipoArma() == TipoArma.PISTOLA) {
            jogador.getSprite().setTexture(characterPistolaTexture);
        }
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

        sr.setProjectionMatrix(camera.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(new Color(0, 0, 1, 0));
        for (Jogador jogador : jogadores) {
            sr.rect(jogador.sprite.getX(), jogador.sprite.getY(), jogador.sprite.getWidth(), jogador.sprite.getHeight());
            sr.circle(jogador.sprite.getX(), jogador.sprite.getY(), 5);
        }
        sr.rect(jogador.sprite.getX(), jogador.sprite.getY(), jogador.sprite.getWidth(), jogador.sprite.getHeight());
        sr.circle(jogador.sprite.getX(), jogador.sprite.getY(), 5);
        sr.end();

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

    public List<Tiro> getTirosDosOutros() {
        return tirosDosOutros;
    }

    public List<Tiro> getMeusTiros() {
        return meusTiros;
    }

    private synchronized void calcularTiros(float dt) {
        // Calculando tiros dos outros
        synchronized (tirosDosOutros) {
            Iterator<Tiro> iter = tirosDosOutros.iterator();
            while (iter.hasNext()) {
                if (!iter.next().mover(dt, Constantes.VELOCIDADE_TIRO)) {
                    iter.remove();
                }

            }
        }
        // Calculando meus tiros
        Iterator<Tiro> iter = meusTiros.iterator();
        iter = meusTiros.iterator();
        while (iter.hasNext()) {
            Tiro tir = iter.next();
            if (!tir.mover(dt, Constantes.VELOCIDADE_TIRO)) {
                System.out.println("IMPRIMEINDO ALTURA DO TIR");
                System.out.println(tir.altura);
                calculaMorte(tir);
                iter.remove();
            }
        }
    }

    private synchronized void calculaMorte(Tiro tiroMorte) {
        Iterator<Jogador> iterm = jogadores.iterator();
        System.out.println("ENTRANDO EM CALCULA MORTE");
        while (iterm.hasNext()) {
            Jogador mortoAtual = iterm.next();
            if (tiroMorte.sprite.getBoundingRectangle().overlaps(mortoAtual.sprite.getBoundingRectangle())) {
                JogadorMorreu morto = new JogadorMorreu();
                morto.playerId = mortoAtual.getId();
                System.out.println("ENVIANDO MORTE!!!!!!!!!");
                cliente.getKryonetClient().sendUDP(morto);
            }
        }
    }
}
