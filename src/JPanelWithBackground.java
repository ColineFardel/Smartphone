/*
 * Project POO Smartphone
 * Author: Coline Fardel
 * Date creation: 23.04.2019
 * Date last modification: 07.06.2019
 */
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * Class for to put the wallpaper in the JPanel
 * @author Coline Fardel
 */
public class JPanelWithBackground extends JPanel{
	private Image img;
	/**
	 * Read an image
	 * @param file path of the image
	 * @throws IOException
	 */
	public JPanelWithBackground(String file) throws IOException{
		img = ImageIO.read(new File(file));
	}
	/**
	 * Paint the image in the JPanel
	 */
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	  }
}
