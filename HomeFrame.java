import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.Border;

public class HomeFrame extends BaseFrame{
	
	private JPanelWithBackground screen;
	private GridBagConstraints prout = new GridBagConstraints();
	private final int LARGEUR = 480;
	
	public HomeFrame() {
		try {
			screen = new JPanelWithBackground("C:\\Users\\colin\\Dropbox\\Mes Dossiers\\Semestre 2\\ProjetPOO\\wallpaper1.jpg");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		prout.gridx = 0;
		prout.gridy = 1;
		
		screen.setPreferredSize(new Dimension(LARGEUR,700));
		add(screen,prout);
	}
}
