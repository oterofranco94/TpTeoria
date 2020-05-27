package tpteoria;

import Clases.Imagen;
import UI.Menu;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;


public class TPTeoria {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //Menu ui = new Menu();
        //ui.setVisible (true);
        Imagen willOriginal = new Imagen("../Imagenes/Will(Original).bmp");
        Imagen will1 = new Imagen("../Imagenes/Will_1.bmp");
        Imagen will2 = new Imagen("../Imagenes/Will_2.bmp");
        Imagen will3 = new Imagen("../Imagenes/Will_3.bmp");
        Imagen will4 = new Imagen("../Imagenes/Will_4.bmp");
        Imagen will5 = new Imagen("../Imagenes/Will_5.bmp");
        
        System.out.print("El factor de correlacion entre la foto original y will1 es : "+willOriginal.getFactorCorrelacion(will1)+"\n");
        System.out.print("El factor de correlacion entre la foto original y will2 es : "+willOriginal.getFactorCorrelacion(will2)+"\n");
        System.out.print("El factor de correlacion entre la foto original y will3 es : "+willOriginal.getFactorCorrelacion(will3)+"\n");
        System.out.print("El factor de correlacion entre la foto original y will4 es : "+willOriginal.getFactorCorrelacion(will4)+"\n");
        System.out.print("El factor de correlacion entre la foto original y will5 es : "+willOriginal.getFactorCorrelacion(will5)+"\n");

        Imagen [] parecidos = {will1,will2,will3,will4,will5};
       // ejercicio1(willOriginal, parecidos);
    }
    
    public static void ejercicio1 (Imagen original, Imagen[] parecidos){
        
        
        
        
    }
    
    
    
}