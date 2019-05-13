import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

/* Declaration de la barre de deroulement */
protected JScrollPane scrollBar;

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
    galleryPanel.setBackground(Color.RED);
    
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
}
