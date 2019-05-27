/*
 * Project POO Smartphone
 * Author: Coline Fardel
 * Date creation: 23.04.2019
 * Date last modification: 30.04.2019
 */
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class JPanelWithBackground extends JPanel{
	private Image img;
	
	public JPanelWithBackground(String file) throws IOException{
		img = ImageIO.read(new File(file));
	}
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	  }
}
