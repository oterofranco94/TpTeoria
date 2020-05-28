package Clases;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;


public class Imagen implements Comparable <Imagen> {
    public  String name;
    private static int width = 1310;
    private static int height = 1700;
    public Vector<Integer> pixeles = new Vector<Integer>();
    public Double correlacionConOriginal = 0.0;
    
    public Imagen(String name, String path, Imagen original) {
    try 
    {
      this.name = name;
      BufferedImage image = ImageIO.read(this.getClass().getResource(path));
      pixeles = getSecuencia(image);
      if (original != null)
        correlacionConOriginal = getFactorCorrelacion(original);
      else 
          correlacionConOriginal = 0d;
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
    
    public Vector<Integer> getSecuencia(BufferedImage image) {
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
        double suma = 0;
        for (int i=0;i<pixeles.size();i++){
            suma+=pixeles.elementAt(i);}
        return suma / pixeles.size();
    }
    
    public double getDesvioEstandar(){
        double media = getMedia();
        double varianza = 0;
        for (int i=0;i<pixeles.size();i++)
            varianza += Math.pow((pixeles.elementAt(i)-media), 2)/pixeles.size();
        
        return Math.sqrt(varianza);
    }
    
    public double getCovarianza (Imagen imagenB){
        Vector<Integer> secuenciaB = imagenB.pixeles;
        double mediaA = getMedia();
        double mediaB = imagenB.getMedia();
        double covarianza = 0;
        for (int i=0;i<pixeles.size();i++){
            covarianza+= ( (pixeles.elementAt(i)-mediaA)*(secuenciaB.elementAt(i)-mediaB))/pixeles.size();
        }
        return covarianza;
    }
    
    public double getFactorCorrelacion(Imagen original){
        return (getCovarianza(original)/(getDesvioEstandar()*original.getDesvioEstandar()));
    }
     
    @Override
    public int compareTo(Imagen img){
        return img.correlacionConOriginal.compareTo(this.correlacionConOriginal);
   }
    
    
}
