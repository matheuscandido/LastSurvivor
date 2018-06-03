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

    public PlayState(GameStateManager gsm) {
        super(gsm);
        character = new Rectangle();

        character.x = 0;
        character.y = 0;
        characterTexture = new Texture(Gdx.files.internal("survivor-knife.png"));
        pistolaTexture = new Texture(Gdx.files.internal("pistol.png"));
        fuzilTexture = new Texture(Gdx.files.internal("SVT-40.png"));
        mapa = new Mapa();

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
    public void render(SpriteBatch sb, float dt) {
        handleInput();
        sb.begin();
        sb.draw(characterTexture, character.x, character.y);
        for (Arma arma : armas) {
            if (arma.getTipoArma() == PISTOLA) {
                sb.draw(pistolaTexture, arma.x, arma.y);
            }
            if (arma.getTipoArma() == FUZIL) {
                sb.draw(fuzilTexture, arma.x, arma.y);
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
