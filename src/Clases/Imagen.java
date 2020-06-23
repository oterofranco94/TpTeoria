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

    public Vector<Integer> getSimbolos() {
        Vector<SimboloProbabilidad> distribucion = new Vector<SimboloProbabilidad>();
        int[] suma = new int[colores];
        for (int i = 0; i < colores; i++) {
            suma[i] = 0;
        }
        Vector<Integer> simbolos = new Vector<Integer>();
        for (Integer i : pixeles) {

            if (!simbolos.contains(i)) {
                  simbolos.add(i);
           
            if (simbolos.size() == colores)
                return simbolos;
            
        }}
        return simbolos;
}
    
    public Vector<SimboloProbabilidad> getProbabilidades(Vector<Integer> simbolos, int [] suma){
                
        Vector<SimboloProbabilidad> distribucion = new Vector<SimboloProbabilidad>();
        for (int i = 0; i < colores; i++) {
            suma[i] = 0;
        }
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
    
    public Vector<SimboloProbabilidad> getProbabilidades(){
        
        int suma []  = new int[colores];
        Vector<SimboloProbabilidad> distribucion = new Vector<SimboloProbabilidad>();
        Vector<Integer>simbolos = new Vector<Integer>();
        for (int i = 0; i < colores; i++) {
            suma[i] = 0;
        }
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
    

}


