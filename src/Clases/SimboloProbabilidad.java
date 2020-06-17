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
public class SimboloProbabilidad implements Comparable<SimboloProbabilidad> {

    private Integer simbolo;
    private double probabilidad;

    public SimboloProbabilidad(double probabilidad) {
        this.probabilidad = probabilidad;
        this.simbolo = 0;
    }

    public SimboloProbabilidad(Integer simbolo, double probabilidad) {
        this.simbolo = simbolo;
        this.probabilidad = probabilidad;
    }

    public double getProbabilidad() {
        return probabilidad;
    }

    public Integer getSimbolo() {
        return simbolo;
    }

    public void setProbabilidad(double probabilidad) {
        this.probabilidad = probabilidad;
    }

    public void setSimbolo(Integer simbolo) {
        this.simbolo = simbolo;
    }

    @Override
    public int compareTo(SimboloProbabilidad t1) {
        if (probabilidad > t1.getProbabilidad()) {
            return 1;
        } else if (probabilidad < t1.getProbabilidad()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return ("Simbolo: " + simbolo + " - Probabilidad: " + probabilidad);
    }
}
