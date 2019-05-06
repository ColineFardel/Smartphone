import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.Border;

public class HomeFrame extends BaseFrame{
	
	private JPanelWithBackground screen;
	
	private JButton contactButton= new JButton(new ImageIcon("C:\\Users\\colin\\Dropbox\\Mes Dossiers\\Semestre 2\\ProjetPOO\\phone-book.png"));
	//<div>Icons made by <a href="https://www.flaticon.com/authors/chanut" title="Chanut">Chanut</a> from <a href="https://www.flaticon.com/" 			    title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" 			    title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
	private JButton galleryButton= new JButton(new ImageIcon("C:\\Users\\colin\\Dropbox\\Mes Dossiers\\Semestre 2\\ProjetPOO\\landscape.png"));
	//<div>Icons made by <a href="https://www.flaticon.com/authors/bqlqn" title="bqlqn">bqlqn</a> from <a href="https://www.flaticon.com/" 		    title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" 		    title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
	
	//private JButton contactButton= new JButton(new ImageIcon("C:\\Users\\colin\\Dropbox\\Mes Dossiers\\Semestre 2\\ProjetPOO\\power2.png"));
	
	private GridBagConstraints par = new GridBagConstraints();
	private final int LARGEUR = 480;
	
	public HomeFrame() {
		try {
			screen = new JPanelWithBackground("C:\\Users\\colin\\Dropbox\\Mes Dossiers\\Semestre 2\\ProjetPOO\\wallpaper1.jpg");
		} catch (Exception e) {
			// TODO: handle exception
		}
		screen.setLayout(new FlowLayout(0,50,50));
		
		contactButton.addActionListener(new ContactListener());
		contactButton.setPreferredSize(new Dimension(80,80));
		screen.add(contactButton);
		
		galleryButton.addActionListener(new GalleryListener());
		galleryButton.setPreferredSize(new Dimension(80,80));
		screen.add(galleryButton);
		
		par.gridx = 0;
		par.gridy = 1;
		
		screen.setPreferredSize(new Dimension(LARGEUR,700));
		add(screen,par);
	}
	class ContactListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new ContactFrame();
			frame.setVisible(true);
			dispose();
		}
	}
	class GalleryListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//JFrame frame = new Gallery();
			//frame.setVisible(true);
			//dispose();
		}
	}
}
