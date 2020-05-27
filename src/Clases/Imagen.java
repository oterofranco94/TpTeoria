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
    
    public Imagen(String path) {
    try 
    {
      BufferedImage image = ImageIO.read(this.getClass().getResource(path));
      getSecuencia(image);
    } 
    catch (IOException e) 
    {
      System.err.println(e.getMessage());
    }
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
}
