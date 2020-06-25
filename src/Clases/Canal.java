/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author fs
 */
public class Canal {

    //private Vector<Double> errorList = new Vector<Double>();
    private Vector<Integer> entrada;
    private Vector<Integer> salida;
    private Vector<Integer> simbolos;
    private static int colores = 16;
    private static double EPSILON = 0.00001d;
    private static int NUM_MIN_MUESTRAS = 5000;

    public Canal(Vector<Integer> entrada, Vector<Integer> salida, Vector<Integer> simbolos) {
        this.entrada = entrada;
        this.salida = salida;
        this.simbolos = simbolos;

    }

    public double[][] getMatrizConjunta() {

        double[][] matriz = new double[colores][colores];
        Integer x;
        Integer y;
        int columna;
        int fila;
        for (int i = 0; i < entrada.size(); i++) {
            x = entrada.get(i);
            y = salida.get(i);
            columna = simbolos.indexOf(x);
            fila = simbolos.indexOf(y);
            matriz[fila][columna]++;
        }
        for (int i = 0; i < colores; i++) {
            for (int j = 0; j < colores; j++) {
                matriz[i][j] = matriz[i][j] / entrada.size();
            }

        }
        return matriz;
    }

    public double[][] getMatrizdeTransicion(double[][] matrizConjunta, Vector<SimboloProbabilidad> probabilidadesX) {
        double[][] matrizTransicion = new double[colores][colores];
        Integer simbolo;
        for (int i = 0; i < colores; i++) {
            for (int j = 0; j < colores; j++) {
                simbolo = simbolos.get(j);
                for (SimboloProbabilidad sp : probabilidadesX) {
                    if (sp.getSimbolo().equals(simbolo)) {
                        matrizTransicion[i][j] = matrizConjunta[i][j] / sp.getProbabilidad();
                    }
                }
            }
        }

        return matrizTransicion;
    }

    private double[] probabilidadAcumulada(Vector<SimboloProbabilidad> probabilidadesX) {

        double[] probAcum = new double[colores];
        for (int i = 0; i < colores; i++) {
            probAcum[i] = 0;
        }
        boolean flag;
        int index;
        double parcial = 0;
        for (int i = 0; i < colores; i++) {
            flag = false;
            index = 0;
            while (!flag && index < colores) {

                if (probabilidadesX.get(index).getSimbolo().equals(simbolos.get(i))) {
                    parcial += probabilidadesX.get(index).getProbabilidad();
                    probAcum[i] = parcial;
                    flag = true;
                }
                index++;
            }

        }
        return probAcum;
    }

    private boolean converge(double probAnt, double probAct) {

        return (Math.abs(probAnt - probAct) < EPSILON);

    }

    private double calcularRuido(double[][] matrizConjunta) {
        double ruido = 0;
        for (int i = 0; i < colores; i++) {
            for (int j = 0; j < colores; j++) {
                if (matrizConjunta[i][j] != 0.0) {
                    ruido += Math.abs(matrizConjunta[i][j] * (Math.log10(matrizConjunta[i][j]) / Math.log10(2)));
                }
            }
        }
        return (ruido);
    }

    public double ruidoPorMuestreo(double[][] mTransicion, Vector<SimboloProbabilidad> probabilidadesX) {

        int muestras = 0;

        double[] probabilidadX = probabilidadAcumulada(probabilidadesX);

        double[][] mAcum = new double[colores][colores];

        double ruidoAct = 0;

        double ruidoAnt = -1;

        double[][] probConjunta = new double[colores][colores];

        int[][] apariciones = new int[colores][colores];

        // Calculo probabilidad acumulada
        for (int i = 0; i < colores; i++) {
            double parcial = 0;
            for (int j = 0; j < colores; j++) {
                parcial += mTransicion[j][i];
                mAcum[j][i] = parcial;
                probConjunta[i][j] = 0;
                apariciones[i][j] = 0;
            }
        }

        Integer x;
        Integer y;
        int fila;
        int columna;
        double[] error = new double[100];
        int index = 0;

        while (!converge(ruidoAct, ruidoAnt) || muestras < NUM_MIN_MUESTRAS) {
            x = generarXRandom(probabilidadX);
            y = generarYdadoX(mAcum, simbolos.indexOf(x));
            columna = simbolos.indexOf(x);
            fila = simbolos.indexOf(y);
            apariciones[fila][columna]++;
            muestras++;

            for (int i = 0; i < colores; i++) {
                for (int j = 0; j < colores; j++) {
                    probConjunta[i][j] = (double) apariciones[i][j] / muestras;
                }
            }
            ruidoAnt = ruidoAct;
            ruidoAct = calcularRuido(probConjunta);
        }

        return ruidoAct;

    }

    private Integer generarXRandom(double[] probabilidadX) {

        double r = Math.random();
        for (int i = 0; i < colores; i++) {
            if (r < probabilidadX[i]) {
                return simbolos.get(i);
            }
        }
        return -1;
    }

    private Integer generarYdadoX(double[][] mAcum, int columna) {
        double r = Math.random();
        for (int i = 0; i < colores; i++) {
            if (r < mAcum[i][columna]) {
                return simbolos.get(i);
            }
        }
        return -1;
    }

}
