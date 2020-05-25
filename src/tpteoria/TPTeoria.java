package tpteoria;

import UI.Menu;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;


public class TPTeoria {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Menu ui = new Menu();
        ui.setVisible (true);
    }
}