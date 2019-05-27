import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;




/**
 parametres de la galerie image
 */

public class Gallery extends BaseFrame{
	
	 protected JScrollPane scrollBar;

	 /* Declaration des panels */
	 protected JPanel screen = new JPanel();
	 protected JPanel galleryPanel = new JPanel();
	 protected JPanel northPanel= new JPanel();
	 
	 protected GridLayout innerPanelGridLayout;
	 
	 protected int numberOfImages;
	 protected int numberOfEmptyFiller;
	 protected int numberTotalObject;
	 
	 /* Declaration des boutons */
	 protected JButton addPictureButton = new JButton(new ImageIcon ("src/Images/Icones/AddButton.png"));
	 /* Declaration des labels */
	 protected JLabel galleryLabel = new JLabel("Ma Galerie");
	 protected MigLayout layout;
	
	 
	 private JButton button1 = new JButton("1)");
	 private JButton button2 = new JButton("2)");
	 private JButton button3 = new JButton("3)");
	 private JButton button4 = new JButton("4)");  

/**
 * Construction de la galerie
 */

	 public Gallery(){
		 /* Parametrage du GridBagLayout herite de BaseFrame*/
		 par.gridx = 0;
		 par.gridy = 1;

		 /* Ajout du panel de fond */
		 screen.setPreferredSize(new Dimension(LARGEUR,700));
		 screen.setBackground(Color.WHITE);
    
		 /* Ajout du titre avec modification de la police et taille */
		 screen.add(galleryLabel);
		 Font font = new Font("Arial",Font.BOLD,14);
		 galleryLabel.setFont(font);
    
		 /* Ajout d'un panel reserve a la galerie photo */
		 screen.add(galleryPanel, BorderLayout.CENTER);
		 galleryPanel.setPreferredSize(new Dimension(LARGEUR,625));
		 galleryPanel.setBackground(Color.GRAY);
		 
		 /* Initialisation des int */
		
		 
		 /* Ajout et modification du bouton Ajouter */
		 screen.add(addPictureButton, BorderLayout.CENTER);
		 addPictureButton.setPreferredSize(new Dimension(40, 40));
		 addPictureButton.setContentAreaFilled(false);
		 addPictureButton.setBorderPainted(false);
		 addPictureButton.setRolloverEnabled(false);
		 
		 /* Ajout du Listener */
		/* listener = new GalleryScreenListener();
		 addPictureButton.addActionListener(listener);*/
		 
		  
		 /* Configuration du MigLayout */
		galleryPanel.setLayout(new MigLayout("wrap 4"));
		galleryPanel.add(button1);
		button1.setPreferredSize(new Dimension(115, 115));
		galleryPanel.add(button2);
		button2.setPreferredSize(new Dimension(115, 115));
		galleryPanel.add(button3);
		button3.setPreferredSize(new Dimension(115, 115));
		galleryPanel.add(button4);
		button4.setPreferredSize(new Dimension(115, 115));
		
		
		
	     /* Création du tableau */
	    // pictureButton = new HashMap<>();
	     
		configureScrollBar();
	     
		/*numberOfImages = 0;
		
		fillGridSpace();
		setLayout(new BorderLayout());
		add(scrollBar, BorderLayout.CENTER);
		numberTotalObject = numberOfImages + numberOfEmptyFiller;
		*/
	 add(screen,par);
	 }
	/* private void configureScrollBar() {
	        scrollBar = new JScrollPane(galleryPanel);
	        scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    }*/

}

	
	    
	


