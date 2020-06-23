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
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import javax.imageio.ImageIO;

public class ByteEncoding {

    private static int colores = 16;
    private static int width = 1310;
    private static int height = 1700;
    private static int bufferLength = 8;

    private static String getCodigo(int intensidad, Vector<Codificacion> codificacion) {
        for (int i = 0; i < codificacion.size(); i++) {
            if ((int) codificacion.get(i).getSimbolo() == (intensidad)) {
                return codificacion.get(i).getCodigo();
            }
        }
        System.out.print("Codigo inexistente");
        return "";
    }

    public void encodeSequence( Vector<Integer> secuencia, Vector<Codificacion> codificacion, Vector<Integer> simbolos, int[] cantidades, String outputfilepath) {
        try {

            String secuenciaCodificada = "";
            char buffer = 0;
            int bufferPos = 0;
            int longSimbolo = 0;
            String codigo;
            for (Integer i : secuencia) {
                codigo = getCodigo((int) i, codificacion);
                longSimbolo = codigo.length();
                while (longSimbolo > 0) {
                    // La operación de corrimiento pone un '0'
                    buffer = (char) (buffer << 1);
                    if (codigo.charAt(0) == '1') {
                        buffer = (char) (buffer | '\u0001');
                    }

                    if (codigo.length() > 1) {
                        codigo = codigo.substring(1);
                    }

                    bufferPos++;
                    longSimbolo--;
                    if (bufferPos == bufferLength) {
                        secuenciaCodificada = secuenciaCodificada + buffer;
                        buffer = 0;
                        bufferPos = 0;
                    }
                }
            }
            int cerosSobrantes = 0;
            if ((bufferPos < bufferLength) && (bufferPos != 0)) {
                cerosSobrantes = (bufferLength - bufferPos);
                for (int k = 0; k < cerosSobrantes; k++) {
                    buffer = (char) (buffer << 1);
                }
                secuenciaCodificada = secuenciaCodificada + buffer;
            }

            FileOutputStream fos = new FileOutputStream(new File(outputfilepath));
            for (int i = 0; i < colores; i++) {

                int simbolo = (int) simbolos.get(i);
               //fos.write((char) simbolo);
                fos.write(simbolo);
            }

            char cod; // indica si el char está completo o sobran bits
            int cantidadocupada; //cantidad de chars que ocupa cantidades[i]
            int resto;
            int cantidadchars;

            for (int j = 0; j < colores; j++) {
                cantidadocupada = cantidades[j] / 255;
                resto = cantidades[j] - cantidadocupada * 255;
                if (cantidades[j] > 255) {
                    cod = 'c';
                    cantidadchars = cantidadocupada / 255;
                    fos.write(cod);
                    fos.write((char) cantidadchars);
                    fos.write((char) cantidadocupada - cantidadchars * 255);
                    fos.write((char) resto);
                } else {
                    cod = 's';
                    fos.write(cod);
                    fos.write((char) cantidadocupada); //agregamos cantidad de veces que entra cantidad[k] en un char
                    fos.write((char) resto);
                }

            }

            fos.write((char) cerosSobrantes);
            int cantidadwidth = width / 255;
            fos.write(cantidadwidth);
            fos.write(width - 255 * cantidadwidth);
            int cantidadheight = height / 255;
            fos.write(cantidadheight);
            fos.write(height - 255 * cantidadheight);
            for (int j = 0; j < secuenciaCodificada.length(); j++) {
                fos.write(secuenciaCodificada.charAt(j));
            }

            fos.close();
            

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void decodeSequence(String inputFilepath, String newFilepath) {

        try {
            FileInputStream fis = new FileInputStream(inputFilepath);
            Vector<Integer> simbolos = new Vector<Integer>();
            Vector<Integer> cantidades = new Vector<Integer>();

            for (int i = 0; i < colores; i++) {
                simbolos.add((int) fis.read());
            }
            int cantidad;
            char cod;
            for (int j = 0; j < colores; j++) {
                cod = (char) fis.read();
                if (cod == 'c') {
                    cantidad = (int) fis.read();
                    cantidad = cantidad * 255;
                    cantidad = cantidad + (int) fis.read();
                    cantidad = cantidad * 255;
                    cantidad = cantidad + (int) fis.read();
                    cantidades.add(cantidad);
                } else if (cod == 's') {
                    cantidad = (int) fis.read();
                    cantidad = cantidad * 255;
                    cantidad = cantidad + (int) fis.read();
                    cantidades.add(cantidad);
                }
            }
            int cerosAgregados = (int) fis.read();

            int ancho = (int) fis.read() * 255;
            ancho += (int) fis.read();

            int alto = (int) fis.read() * 255;
            alto += (int) fis.read();

            int cantidadPixeles = ancho * alto;

            Vector<SimboloProbabilidad> probabilidades = getProbabilidades(simbolos, cantidades, cantidadPixeles);

            Vector<NodoArbol> arbolHuffmann = arbolDeHuffmann(probabilidades);

            Vector<Codificacion> codificacion = getSimbolosCodificados(arbolHuffmann);

            Vector<Integer> secuenciaRestaurada = new Vector<Integer>();


            int bufferPos = 0;
            //   int i = 0;

            String secuencia = "";
            char byteActual;
            boolean verifica = false;
            String a;
            String aux;
            int sumados = 0;
            int bytesleidos = 0;

            while (fis.available() != 0) {
                byteActual = (char) fis.read();
                bytesleidos++;
                if (fis.available() == 0) {
                    for (int i = 0; i < bufferLength - cerosAgregados; i++) {
                        if ((byteActual & '\u0080') == '\u0080') {
                            secuencia = secuencia + '1';
                        } else {
                            secuencia = secuencia + '0';
                        }

                        byteActual = (char) (byteActual << 1);
                        int j = 0;
                        while ((j < codificacion.size()) && (verifica == false)) {
                            a = codificacion.get(j).getCodigo();
                            aux = secuencia;
                            if (a.equals(aux)) {
                                verifica = true;
                                sumados++;
                            } else {
                                j++;
                            }
                        }

                        if (verifica) {
                            secuenciaRestaurada.add(codificacion.get(j).getSimbolo());
                            secuencia = "";
                            verifica = false;
                        }
                    }
                } else {

                    for (int i = 0; i < bufferLength; i++) {
                        if ((byteActual & '\u0080') == '\u0080') {
                            secuencia = secuencia + '1';
                        } else {
                            secuencia = secuencia + '0';
                        }
                        byteActual = (char) (byteActual << 1);
                        int j = 0;

                        while ((j < codificacion.size()) && (verifica == false)) {
                            a = codificacion.get(j).getCodigo();
                            aux = secuencia;
                            if (a.equals(aux)) {
                                verifica = true;
                                sumados++;
                            } else {
                                j++;
                            }
                        }
                        if (verifica) {
                            secuenciaRestaurada.add(codificacion.get(j).getSimbolo());
                            secuencia = "";
                            verifica = false;
                        }
                    }
                }

            }

            BufferedImage bufferedImage = new BufferedImage(ancho, alto,BufferedImage.TYPE_BYTE_INDEXED); //BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = bufferedImage.createGraphics();
            //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
           
            g2.fillRect(0, 0, ancho, alto);

            int nodo = 0;
            int r = 0;
            //int g = 0;
            //int b = 0;
            
            for (int i = 0; i < ancho; i++) {
                for (int j = 0; j < alto; j++) {

                    r = (int)secuenciaRestaurada.elementAt(nodo);
                    nodo++;
                    
                    Color color = new Color(r, r, r);
                    
                    bufferedImage.setRGB(i, j, color.getRGB());
                   
                }
            }

            g2.dispose();

            ImageIO.write(bufferedImage, "bmp", new File(newFilepath));


        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static Vector<SimboloProbabilidad> getProbabilidades(Vector<Integer> simbolos, Vector<Integer> cantidades, int cantidadPixeles) {

        Vector<SimboloProbabilidad> probabilidades = new Vector<SimboloProbabilidad>();
        for (int i = 0; i < colores; i++) {
            SimboloProbabilidad sp = new SimboloProbabilidad(simbolos.get(i), (double) cantidades.get(i) / cantidadPixeles);
            probabilidades.add(sp);
        }

        return probabilidades;
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
