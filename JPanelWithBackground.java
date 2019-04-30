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
