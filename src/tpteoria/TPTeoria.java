package tpteoria;

import Clases.Imagen;
import UI.Menu;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;


public class TPTeoria {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Menu ui = new Menu();
        //ui.setVisible (true);
        Imagen willOriginal = new Imagen("../Imagenes/Will(Original).bmp");
        Imagen will1 = new Imagen("../Imagenes/Will_1.bmp");
        //Imagen will2 = new Imagen("../Imagenes/Will_2.bmp");
        //Imagen will3 = new Imagen("../Imagenes/Will_3.bmp");
        //Imagen will4 = new Imagen("../Imagenes/Will_4.bmp");
        //Imagen will5 = new Imagen("../Imagenes/Will_5.bmp");
    }
}