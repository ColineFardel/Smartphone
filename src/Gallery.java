import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;


/**
 Parametre pour la galerie photo
 
 *@author Ismaël Moreno
 */

public class Gallery extends BaseFrame{
	
	/* Declaration du MigLayout */
	MigLayout layout = new MigLayout("wrap 4");
	
	/* Declaration des panels */
	private JPanel screen = new JPanel();
	private JPanel galleryPanel = new JPanel();
	private JPanel northPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	
	/* Declaration de la barre de defilement */
	private JScrollPane scrollBar;
	 
	/* Declaration des boutons add/delete */
	private JButton addPictureButton = new JButton(new ImageIcon ("Images/add.png"));
	private JButton deletePictureButton = new JButton(new ImageIcon ("Images/delete.png"));
	 
	/* Declaration des labels */
	private JLabel galleryLabel = new JLabel("Ma Galerie");
	
	/* Declaration du fichier des images */
	private File filePictures = new File("./Images/Galerie");
	
	/* Tableau qui contient tous les chemins des images*/
	private String [] listPathPictures = filePictures.list();
	
	/* ImageIcon qui contient l'image du bouton selectionne */
	private ImageIcon enlargedPicture;
	
	/* Bouton qui contient l'ImageIcon du bouton selectionne*/
	private JButton enlargedPictureButton;
	
	/* ImageIcon qui represente chaque image dans la galerie */
	private ImageIcon eachPicture;
	
	/* Dimension des JButton contenant les ImageIcon */
	private Dimension size = new Dimension(113, 113);
	
	/* ArrayList de boutons qui est de la meme longueur que le tableau listPathPictures */
	private List<JButton> buttonPicture = new ArrayList<JButton>(listPathPictures.length);
	 
/**
 * Construction de la galerie
 */

	public Gallery(){
		/* Parametrage du GridBagLayout herite de BaseFrame*/
		par.gridx = 0;
		par.gridy = 1;

		/* Ajout du panel de fond */
		screen.setLayout(new BorderLayout());
		screen.setPreferredSize(new Dimension(LARGEUR,700));
		screen.setBackground(Color.WHITE);
		
		/* Ajout d'un panel Nord */
		screen.add(northPanel, BorderLayout.NORTH);
		northPanel.setPreferredSize(new Dimension(LARGEUR,40));
		northPanel.setBackground(Color.WHITE);
		
		/* Ajout d'un panel reserve a la galerie photo au Centre */
		screen.add(galleryPanel, BorderLayout.CENTER);
		//galleryPanel.setPreferredSize(new Dimension(LARGEUR,300));
		galleryPanel.setBackground(Color.WHITE);
		
		/* Ajout d'un panel Sud pour les boutons */
		screen.add(southPanel, BorderLayout.SOUTH);
		southPanel.setPreferredSize(new Dimension(LARGEUR,50));
		southPanel.setBackground(Color.WHITE);
		
		/* Ajout du titre avec modification de la police et taille */
		northPanel.add(galleryLabel, BorderLayout.PAGE_START);
		Font font = new Font("Arial",Font.BOLD,32);
		galleryLabel.setFont(font);
    
		/* Ajout et modification du bouton Add */
		southPanel.add(addPictureButton);
		addPictureButton.setPreferredSize(new Dimension(40, 40));
		addPictureButton.setContentAreaFilled(false);
		addPictureButton.setBorderPainted(false);
		addPictureButton.setRolloverEnabled(false);
		
		/* Ajout et modification du bouton Delete */
		southPanel.add(deletePictureButton);
		deletePictureButton.setPreferredSize(new Dimension(40, 40));
		deletePictureButton.setContentAreaFilled(false);
		deletePictureButton.setBorderPainted(false);
		deletePictureButton.setRolloverEnabled(false);
		 
		/* Ajout du Listener pour Add */
		//addPictureButton.addActionListener(new buttonAdd());
		
		/* Configuration du MigLayout */
		galleryPanel.setLayout(layout);
		
		/* Configuration du ScrollBar */
		scrollBar = new JScrollPane(galleryPanel);
	    scrollBar.getVerticalScrollBar().setUnitIncrement(10);
	    scrollBar.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
	    scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//scrollBar.setPreferredSize(new Dimension(LARGEUR,500));
	    screen.add(scrollBar);
		
		initializeGallery();
		
	 add(screen,par);
	 }

	 
		
	public void initializeGallery() {

		for(int i=0; i<listPathPictures.length; i++){

			//Defini l'image qu'il faut prendre dans le dossier Images
			eachPicture = new ImageIcon(filePictures + "/" + listPathPictures[i]);
			//Prend l'image
			Image pic = eachPicture.getImage();
			//Reglement des dimensions l'image
			Image newPic = pic.getScaledInstance(113, 113,java.awt.Image.SCALE_SMOOTH);
			//Met l'image dans l'ImageIcon
			eachPicture = new ImageIcon(newPic);
			//Creation d'un bouton avec l'ImageIcon a l'interieur
			buttonPicture.add(new JButton(eachPicture));
			//Reglement de la dimension du bouton
			buttonPicture.get(i).setMaximumSize(size);
			

			//Ajout du bouton dans le panel qui a comme layout le MigLayout
			galleryPanel.add(buttonPicture.get(i));

			//Ajout de MouseListener sur chaque bouton
			buttonPicture.get(i).addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent mouseEvent) {
					//Si double-clic
					if(mouseEvent.getClickCount() == 2) {
						//Recuperation du bouton sur lequel on a clique
						JButton b = (JButton)mouseEvent.getSource();

						//Recuperation de l'image et insertion dans imageAgrandie
						enlargedPicture = (ImageIcon)b.getIcon();
						Image pic = enlargedPicture.getImage();
						Image newPic2 = pic.getScaledInstance(400,  633, java.awt.Image.SCALE_SMOOTH);
						enlargedPicture = new ImageIcon(newPic2);
						enlargedPictureButton = new JButton(enlargedPicture);

						//Creation de la fenetre qui va afficher l'image en grand
						/* ImageAgrandie ia = new ImageAgrandie();
						ia.setVisible(true); */
					}

					//Si un seul clic
					if(mouseEvent.getClickCount() == 1) {
						//Recuperation du bouton sur lequel on a clique
						JButton b = (JButton)mouseEvent.getSource();
						//Recuperation de l'index du bouton qui se trouve dans l'ArrayList
						//indexRecherche = buttonPicture.indexOf(b);
						//Ajout d'un ActionListener sur le bouton supprimer
						//supprimerPhoto.addActionListener(new SupprimerClick());
							}
						}
					});
				}
		}
	
	
	
	
	
	}
		
		 

 



	
	    
	


