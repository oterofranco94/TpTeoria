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
        InputStream input = new FileInputStream("/Users/francootero/Downloads/ImagenesWill/Will(Original).bmp");
        ImageInputStream imageInput = ImageIO.createImageInputStream(input);
        BufferedImage original = ImageIO.read(imageInput);
        System.out.println(original.getWidth() +"and"+ original.getHeight());
    public static void main(String[] args) {
           System.out.println('.'); 
    }
    
}
