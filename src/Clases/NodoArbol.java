/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author fs
 */
public class NodoArbol {
    private NodoArbol izq;
    private NodoArbol der;
    private SimboloProbabilidad nodo;
    
    public NodoArbol(NodoArbol i, NodoArbol d, SimboloProbabilidad nodo){
		izq=i;
		der=d;
		this.nodo=nodo;
	}
	public SimboloProbabilidad getDato() {
		return nodo;
	}
	public NodoArbol getDer() {
		return der;
	}
	public NodoArbol getIzq() {
		return izq;
	}
                  public boolean esHoja(){
                      return (izq == null && der == null);
                  }
    
}
