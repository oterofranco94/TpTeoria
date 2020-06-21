package tpteoria;

import Clases.*;
import UI.Menu;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;



public class TPTeoria {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Imagen willOriginal = new Imagen("Will Original", "/Imagenes/Will(Original).bmp", null);
        Imagen will1 = new Imagen("Will 1", "/Imagenes/Will_1.bmp", willOriginal);
        Imagen will2 = new Imagen("Will 2", "/Imagenes/Will_2.bmp", willOriginal);
        Imagen will3 = new Imagen("Will 3", "/Imagenes/Will_3.bmp", willOriginal);
        Imagen will4 = new Imagen("Will 4", "/Imagenes/Will_4.bmp", willOriginal);
        Imagen will5 = new Imagen("Will 5", "/Imagenes/Will_5.bmp", willOriginal); 
        Imagen willEj2 = new Imagen("Will Ej2", "/Imagenes/Will_ej2.bmp", null); 
        Vector<Imagen> imagenes1 = new Vector<Imagen>();
        imagenes1.add(will1);
        imagenes1.add(will2);
        imagenes1.add(will3);
        imagenes1.add(will4);
        imagenes1.add(will5);
        Collections.sort(imagenes1);
        
        Vector<Imagen> imagenes2 = new Vector<Imagen>();
        imagenes2.add(willOriginal);
        imagenes2.add(imagenes1.elementAt(0));
        imagenes2.add(willEj2);       
        
        Vector<Imagen> imagenes3 = new Vector<Imagen>();
        imagenes3.add(willOriginal);
        imagenes3.add(willEj2);
        
        Vector<Imagen> imagenes4 = new Vector<Imagen>();
        
        Menu ui = new Menu(imagenes1, imagenes2, imagenes3, imagenes4);
        ui.setVisible (true);
        
        
        
}
}