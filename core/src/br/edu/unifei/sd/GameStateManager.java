/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Stack;

/**
 *
 * @author matheuscandido
 */
public class GameStateManager {
    
    private Stack<State> states;
    
    public GameStateManager(){
        states = new Stack<State>();
    }
    
    public void push(State state){
        states.push(state);
    }
    
    public void pop(){
        states.pop();
    }
    
    public void set(State state){
        states.pop();
        states.push(state);
    }
    
    public synchronized void  update(float dt){ // adicionado
        states.peek().update(dt);
    }
    
    public synchronized  void render(SpriteBatch sb){
        states.peek().render(sb);
    }
    
}
