package Clases;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;


public class Imagen {
    private static int width = 1310;
    private static int height = 1700;
    public Vector<Integer> pixeles = new Vector<Integer>();
    
    public Imagen(String path) {
    try 
    {
      BufferedImage image = ImageIO.read(this.getClass().getResource(path));
      pixeles = getSecuencia(image);
    } 
    catch (IOException e) 
    {
      System.err.println(e.getMessage());
    }
  }
    
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public Vector<Integer> getSecuencia() {
        Vector<Integer> secuencia = new Vector<Integer>();
	  for (int x = 0; x < width; x++) {
		  for (int y = 0; y < height; y++) {
			  int rgb = image.getRGB(x, y);
			  Color color = new Color(rgb, true);
			  int codigo= color.getRed();
			  secuencia.add(codigo);
		  }
	  }
          return secuencia;
  }
    
    public double getMedia (){
        Vector<Integer> aux = getSecuencia();
        double suma = 0;
        for (int i=0;i<aux.size();i++){
            suma+=aux.elementAt(i);}
           
        
        return suma / aux.size();
    }
    
    public double getDesvioEstandar(){
        Vector<Integer> aux = getSecuencia();
        double media = getMedia();
        double varianza = 0;
        for (int i=0;i<aux.size();i++)
            varianza += Math.pow((aux.elementAt(i)-media), 2);
        
        return Math.sqrt(varianza/aux.size());
    }
    
    public double getCovarianza (Imagen imagenB){
        
        Vector<Integer> secuenciaA = getSecuencia();
        Vector<Integer> secuenciaB = imagenB.getSecuencia();
        double mediaA = getMedia();
        double mediaB = imagenB.getMedia();
        double covarianza = 0;
        for (int i=0;i<secuenciaA.size();i++){
            covarianza+= ( (secuenciaA.elementAt(i)-mediaA)*(secuenciaB.elementAt(i)-mediaB))/secuenciaA.size();
        }
        
        return covarianza;
    }
    
    public double getFactorCorrelacion(Imagen imagenB){
        
        double factor = (getCovarianza(imagenB)/(getDesvioEstandar()*imagenB.getDesvioEstandar()));
        
        return factor;
    }
    
    
 
}
