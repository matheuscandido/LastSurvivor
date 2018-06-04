/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.sd;

import com.badlogic.gdx.Gdx;
import java.util.LinkedList;
import java.util.Timer;

/**
 *
 * @author matheuscandido
 */
public class Mapa {

    private float largura;
    private float altura;
    private Timer escalonadorReducao;
    private LinkedList<ElementoGrafico> elementosGraficos;

    public LinkedList<ElementoGrafico> getElementosGraficos() {
        return elementosGraficos;
    }

    public void setElementosGraficos(LinkedList<ElementoGrafico> elementosGraficos) {
        this.elementosGraficos = elementosGraficos;
    }
    private LinkedList<Cliente> clientes;
    private Servidor servidor;
   

    public void addElementoGrafico(ElementoGrafico eg) {

        elementosGraficos.add(eg);

    }

    public void addCliente(Cliente cliente) {

        clientes.add(cliente);
    }

}
