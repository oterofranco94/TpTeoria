package tpteoria;

import Clases.Imagen;
import UI.Menu;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;


public class TPTeoria {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Imagen willOriginal = new Imagen("Will Original", "../Imagenes/Will(Original).bmp", null);
        Imagen will1 = new Imagen("Will 1", "../Imagenes/Will_1.bmp", willOriginal);
        Imagen will2 = new Imagen("Will 2", "../Imagenes/Will_2.bmp", willOriginal);
        Imagen will3 = new Imagen("Will 3", "../Imagenes/Will_3.bmp", willOriginal);
        Imagen will4 = new Imagen("Will 4", "../Imagenes/Will_4.bmp", willOriginal);
        Imagen will5 = new Imagen("Will 5", "../Imagenes/Will_5.bmp", willOriginal); 
        Vector<Imagen> imagenes = new Vector<Imagen>();
        imagenes.add(willOriginal);
        imagenes.add(will1);
        imagenes.add(will2);
        imagenes.add(will3);
        imagenes.add(will4);
        imagenes.add(will5);
        Collections.sort(imagenes);
        
        Menu ui = new Menu(imagenes);
        ui.setVisible (true);
    }
}