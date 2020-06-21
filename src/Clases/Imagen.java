package Clases;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import org.jfree.data.category.DefaultCategoryDataset;

public class Imagen implements Comparable<Imagen> {

    public String name;
    private static int width = 1310;
    private static int height = 1700;
    private static int colores = 16;
    public Vector<Integer> pixeles = new Vector<Integer>();
    public Double correlacionConOriginal = 0.0;

    public Imagen(String name, String path, Imagen original) {
        try {
            this.name = name;
            BufferedImage image = ImageIO.read(this.getClass().getResource(path));
            pixeles = getSecuencia(image);
            if (original != null) {
                correlacionConOriginal = getFactorCorrelacion(original);
            } else {
                correlacionConOriginal = 0d;
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private static Vector<Integer> getSecuencia(BufferedImage image) {
        Vector<Integer> secuencia = new Vector<Integer>();
        int rgb;
        int codigo;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                rgb = image.getRGB(x, y);
                Color color = new Color(rgb, true);
                codigo = color.getRed();
                secuencia.add(codigo);
            }
        }
        return secuencia;
    }

    public Vector<Integer> getSecuencia() {
        return pixeles;
    }

    public double[] devolverEnArreglo() {
        double[] aux = new double[pixeles.size()];
        for (int i = 0; i < pixeles.size(); i++) {
            aux[i] = (double) pixeles.elementAt(i);
        }
        return aux;
    }

    public double getMedia() {
        double suma = 0;
        for (int i = 0; i < pixeles.size(); i++) {
            suma += pixeles.elementAt(i);
        }
        return suma / pixeles.size();
    }

    public double getDesvioEstandar() {
        double media = getMedia();
        double varianza = 0;
        for (int i = 0; i < pixeles.size(); i++) {
            varianza += Math.pow((pixeles.elementAt(i) - media), 2) / pixeles.size();
        }

        return Math.sqrt(varianza);
    }

    public double getCovarianza(Imagen imagenB) {
        Vector<Integer> secuenciaB = imagenB.pixeles;
        double mediaA = getMedia();
        double mediaB = imagenB.getMedia();
        double covarianza = 0;
        for (int i = 0; i < pixeles.size(); i++) {
            covarianza += ((pixeles.elementAt(i) - mediaA) * (secuenciaB.elementAt(i) - mediaB)) / pixeles.size();
        }
        return covarianza;
    }

    public double getFactorCorrelacion(Imagen original) {
        return (getCovarianza(original) / (getDesvioEstandar() * original.getDesvioEstandar()));
    }

    @Override
    public int compareTo(Imagen img) {
        return img.correlacionConOriginal.compareTo(this.correlacionConOriginal);
    }

    public double cantidadDePixeles() {
        return pixeles.size();
    }

    public Vector<SimboloProbabilidad> getProbabilidades() {
        Vector<SimboloProbabilidad> distribucion = new Vector<SimboloProbabilidad>();
        int[] suma = new int[colores];
        for (int i = 0; i < colores; i++) {
            suma[i] = 0;
        }
        Vector<Integer> simbolos = new Vector<Integer>();
        for (Integer i : pixeles) {

            if (simbolos.contains(i)) {
                suma[simbolos.indexOf(i)]++;
            } else {
                simbolos.add(i);
                suma[simbolos.indexOf(i)]++;
            }
        }

        for (int i = 0; i < colores; i++) {
            SimboloProbabilidad cp = new SimboloProbabilidad(simbolos.elementAt(i), (double) suma[i] / pixeles.size());
            distribucion.add(cp);
        }
        return distribucion;
    }
    
    public Vector<SimboloProbabilidad> getProbabilidades(int [] suma){
                
        Vector<SimboloProbabilidad> distribucion = new Vector<SimboloProbabilidad>();
        for (int i = 0; i < colores; i++) {
            suma[i] = 0;
        }
        Vector<Integer> simbolos = new Vector<Integer>();
        for (Integer i : pixeles) {

            if (simbolos.contains(i)) {
                suma[simbolos.indexOf(i)]++;
            } else {
                simbolos.add(i);
                suma[simbolos.indexOf(i)]++;
            }
        }

        for (int i = 0; i < colores; i++) {
            SimboloProbabilidad cp = new SimboloProbabilidad(simbolos.elementAt(i), (double) suma[i] / pixeles.size());
            distribucion.add(cp);
        }
        return distribucion;
    }
    
    public int getColores(){
        return colores;
    }
    
    public Vector<NodoArbol> arbolDeHuffmann(Vector<SimboloProbabilidad> probabilidades) {
        Collections.sort(probabilidades);
        Vector<NodoArbol> arbolHuffmann = new Vector<NodoArbol>();
        NodoArbol nodo1 = new NodoArbol(null, null, probabilidades.get(0));
        NodoArbol nodo2 = new NodoArbol(null, null, probabilidades.get(1));
        SimboloProbabilidad cpinicial = new SimboloProbabilidad(probabilidades.get(0).getProbabilidad() + probabilidades.get(1).getProbabilidad());
        NodoArbol inicial = new NodoArbol(nodo1, nodo2, cpinicial);
        arbolHuffmann.add(inicial);
        probabilidades.remove(0);
        probabilidades.remove(0);
        while (arbolHuffmann.size() > 1 || probabilidades.size() > 0) {
            if (!probabilidades.isEmpty()) {
                if (probabilidades.get(0).getProbabilidad() < arbolHuffmann.get(0).getDato().getProbabilidad()) {
                    if ((probabilidades.size() > 1) && (probabilidades.get(1).getProbabilidad() < arbolHuffmann.get(0).getDato().getProbabilidad())) {
                        NodoArbol n1 = new NodoArbol(null, null, probabilidades.get(0));
                        NodoArbol n2 = new NodoArbol(null, null, probabilidades.get(1));
                        SimboloProbabilidad cp = new SimboloProbabilidad(probabilidades.get(0).getProbabilidad() + probabilidades.get(1).getProbabilidad());
                        NodoArbol n12 = new NodoArbol(n1, n2, cp);
                        probabilidades.remove(0);
                        probabilidades.remove(0);
                        arbolHuffmann.add(n12);
                    } else {
                        NodoArbol n1 = new NodoArbol(null, null, probabilidades.get(0));
                        SimboloProbabilidad cp = new SimboloProbabilidad(probabilidades.get(0).getProbabilidad() + arbolHuffmann.get(0).getDato().getProbabilidad());
                        NodoArbol n2 = new NodoArbol(n1, arbolHuffmann.get(0), cp);
                        probabilidades.remove(0);
                        arbolHuffmann.remove(0);
                        arbolHuffmann.add(n2);
                    }
                } else {
                    if (arbolHuffmann.size() > 1) {
                        SimboloProbabilidad cp = new SimboloProbabilidad(arbolHuffmann.get(0).getDato().getProbabilidad() + arbolHuffmann.get(1).getDato().getProbabilidad());
                        NodoArbol n1 = new NodoArbol(arbolHuffmann.get(0), arbolHuffmann.get(1), cp);
                        arbolHuffmann.remove(0);
                        arbolHuffmann.remove(0);
                        arbolHuffmann.add(n1);
                    } else {
                        NodoArbol n1 = new NodoArbol(null, null, probabilidades.get(0));
                        SimboloProbabilidad cp = new SimboloProbabilidad(arbolHuffmann.get(0).getDato().getProbabilidad() + probabilidades.get(0).getProbabilidad());
                        NodoArbol n2 = new NodoArbol(n1, arbolHuffmann.get(0), cp);
                        arbolHuffmann.remove(0);
                        probabilidades.remove(0);
                        arbolHuffmann.add(n2);
                    }
                }
            } else {
                SimboloProbabilidad cp = new SimboloProbabilidad(arbolHuffmann.get(0).getDato().getProbabilidad() + arbolHuffmann.get(1).getDato().getProbabilidad());
                NodoArbol n1 = new NodoArbol(arbolHuffmann.get(0), arbolHuffmann.get(1), cp);
                arbolHuffmann.remove(0);
                arbolHuffmann.remove(0);
                arbolHuffmann.add(n1);
            }
        }

        return arbolHuffmann;
    }

    public Vector<Codificacion> getSimbolosCodificados(Vector<NodoArbol> arbolHuff) {

        Vector<Codificacion> simbolosCodificados = new Vector<Codificacion>();
        getSimbolo(arbolHuff.get(0), "", simbolosCodificados);

        return simbolosCodificados;
    }

    private void getSimbolo(NodoArbol arbol, String codigo, Vector<Codificacion> codificacion) {

        if (arbol != null) {
            getSimbolo(arbol.getIzq(), codigo + '0', codificacion);
            if (arbol.esHoja()) {
                Codificacion c = new Codificacion(arbol.getDato().getSimbolo(), arbol.getDato().getProbabilidad(), codigo.length(), codigo);
                codificacion.add(c);
            }
            getSimbolo(arbol.getDer(), codigo + "1", codificacion);

        }
    }
}


