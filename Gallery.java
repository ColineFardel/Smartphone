import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * @author ismor
 *
 *
 * Cette frame comporte les parametres de la galerie image
 */

public class Gallery extends BaseFrame{

/* Declaration des labels */
protected JLabel galleryLabel = new JLabel("Ma Galerie");

/* Declaration des boutons */
protected JButton addPictureButton = new JButton(new ImageIcon("C:\\Users\\ismor\\workspace\\ProjetSmartphoneOK\\src\\Images\\Icones\\AddButton.png"));


/* Creation de la liaison avec le repertoire des images */
protected File repertoire = new File ("Images/Galerie");

/* Creation du tableau d'image en utilisant le repertoire */
protected File[] maGalerie = repertoire.listFiles();

/* Compter le nombre d'image dans le repertoire */
protected int nbImages = maGalerie.length;



/* Declaration d'un tableau de boutons qui va acceuillir les images */
protected JButton[] pictureButton = new JButton[nbImages];




/* Declaration de la barre de deroulement */
//private JTextArea textPane = new JTextArea();
//private JScrollPane scrollBar = new JScrollPane(textPane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


/* Declaration des panels */
protected JPanel screen = new JPanel();
protected JPanel galleryPanel = new JPanel();


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
    
    /* Importation des photos dans les boutons */
   for(int i=0; i<nbImages;i++) {
    	pictureButton[i] = new GalleryButton();
    }
    
    
    /* Ajout de la barre de deroulement */
    
    //Scrollbar scrollBar = new Scrollbar();
	//scrollBar.setBounds(100, 100, 100, 100);
    //galleryPanel.add(scrollBar,BorderLayout.EAST);
    
    
    
    
   // galleryPanel.add(scrollBar, BorderLayout.CENTER);
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /* Parametrage du bouton Ajouter */
    // Supprime l'espacement
    addPictureButton.setMargin(new Insets(0, 0, 0, 0));
    // Supprime le fond 
    addPictureButton.setBackground(null);
    // Supprime les bords
    addPictureButton.setBorder(null);
    
    /* Ajout du bouton Ajouter dans la partie inférieure de l'ecran */
    screen.add(addPictureButton,BorderLayout.SOUTH);
 
    add(screen,par);
      

	}

	abstract class GalleryButton implements ActionListener {
		
	}
	
}

