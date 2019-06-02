import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	private JButton backButton = new JButton(new ImageIcon ("Images/backGallery.png")) ;
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
	
	/* Variable qui represente l'index du bouton (de l'ArrayList buttonPicture) surlequel on vient de cliquer */
	private int indexSearch;
	
	 
/**
 * Construction de la galerie
 */

	public Gallery(){
		/* Parametrage du GridBagLayout herite de BaseFrame*/
		par.gridx = 0;
		par.gridy = 1;

		/* Ajout du panel de fond via sa methode*/
		configurateScreenPanel();
		
		/* Ajout d'un panel Nord via sa methode*/
		configurateNorthPanel();
		
		/* Ajout d'un panel reserve a la galerie photo au Centre via sa methode*/
		configurateGalleryPanel();
		
		/* Ajout d'un panel Sud pour les boutons via sa methode*/
		configurateSouthPanel();
		
		/* Ajout du titre avec modification de la police et taille via sa methode */
		addTitelGalleryPanel();
    
		/* Ajout et modification du bouton Add via sa methode*/
		configurateAddButton();
		
		/* Ajout et modification du bouton Delete via sa methode*/
		//configurateDeleteButton();
		 
		/* Configuration du MigLayout */
		galleryPanel.setLayout(layout);
		
		/* Configuration du ScrollBar via sa methode */
		configurateScrollBar();
		
		/* Creation du tableau d'image depuis le dossier Galerie */
		initializeGallery();
		
	/* Ajout du Panel Screen et Par dans Gallery pour fixer le tout */	
	 add(screen,par);
	 }
	/* Methode pour configurer le Screen */
	public void configurateScreenPanel() {
			screen.setLayout(new BorderLayout());
			screen.setPreferredSize(new Dimension(LARGEUR,700));
			screen.setBackground(Color.WHITE);
}	
	/* Methode pour configurer le NorthPanel */
	public void configurateNorthPanel() {
			screen.add(northPanel, BorderLayout.NORTH);
			northPanel.setPreferredSize(new Dimension(LARGEUR,40));
			northPanel.setBackground(Color.WHITE);
	}
	/*Methode pour configurer le GalleryPanel */
	public void configurateGalleryPanel() {
			screen.add(galleryPanel, BorderLayout.CENTER);
			galleryPanel.setBackground(Color.WHITE);
	}
	/* Methode pour configurer le SouthPanel */
	public void configurateSouthPanel() {
			screen.add(southPanel, BorderLayout.SOUTH);
			southPanel.setPreferredSize(new Dimension(LARGEUR,50));
			southPanel.setBackground(Color.WHITE);
	}
	/* Methode pour ajouter le titre NorthPanel avec modification de la police et taille */
	public void addTitelGalleryPanel() {
			northPanel.add(galleryLabel, BorderLayout.CENTER);
			Font font = new Font("Arial",Font.BOLD,32);
			galleryLabel.setFont(font);
	}
	/* Configuration du bouton AddPictureButton */
	public void configurateAddButton() {
			southPanel.add(addPictureButton);
			addPictureButton.setPreferredSize(new Dimension(40, 40));
			addPictureButton.setContentAreaFilled(false);
			addPictureButton.setBorderPainted(false);
			addPictureButton.setRolloverEnabled(false);
			/* Ajout de l'ActionListener au bouton */
			addPictureButton.addActionListener(new AddClick());
	}
	/* Configuration du bouton DeletePictureButton */
	public void configurateDeleteButton() {
			southPanel.add(deletePictureButton);
			deletePictureButton.setPreferredSize(new Dimension(40, 40));
			deletePictureButton.setContentAreaFilled(false);
			deletePictureButton.setBorderPainted(false);
			deletePictureButton.setRolloverEnabled(false);
			/* Ajout d'un ActionListener au bouton */
			deletePictureButton.addActionListener(new DeleteClick());
	}
	/* Configuration du ScrollBar */
	public void configurateScrollBar() {
			scrollBar = new JScrollPane(galleryPanel);
			scrollBar.getVerticalScrollBar().setUnitIncrement(10);
			scrollBar.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
			scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			screen.add(scrollBar);
	}
	/* Configuration du bouton BackButton */
	public void configurateBackButton() {
			northPanel.add(backButton, BorderLayout.WEST);
			backButton.setPreferredSize(new Dimension(40, 40));
			backButton.setContentAreaFilled(false);
			backButton.setBorderPainted(false);
			backButton.setRolloverEnabled(false);
			/* Ajout d'un ActionListener au bouton */
			backButton.addActionListener(new BackClick());

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
						screen.removeAll();
						//Recuperation du bouton sur lequel on a clique
						JButton b = (JButton)mouseEvent.getSource();

						//Recuperation de l'image et insertion dans enlargedPicture
						enlargedPicture = (ImageIcon)b.getIcon();
						Image image = enlargedPicture.getImage();
						Image newimg2 = image.getScaledInstance(400,  500, java.awt.Image.SCALE_SMOOTH);
						enlargedPicture = new ImageIcon(newimg2);
						enlargedPictureButton = new JButton(enlargedPicture);
						enlargedPictureButton.setPreferredSize(new Dimension(400, 500));

						//Creation de la fenetre qui va afficher l'image en grand
						GalleryImageDisplayer gid = new GalleryImageDisplayer();
						gid.setVisible(true);
					}

					//Si un seul clic
					if(mouseEvent.getClickCount() == 1) {
						//Recuperation du bouton sur lequel on a clique
						JButton b = (JButton)mouseEvent.getSource();
						//Recuperation de l'index du bouton qui se trouve dans l'ArrayList
						indexSearch = buttonPicture.indexOf(b);
						//Ajout d'un ActionListener sur le bouton supprimer
						deletePictureButton.addActionListener(new DeleteClick());
					}
				}
			});
		}
	}
		
	public void refreshUI() {
		
		revalidate();
		repaint();
	}

	class GalleryImageDisplayer extends BaseFrame {

		public GalleryImageDisplayer() {
			par.gridx = 0;
			par.gridy = 1;
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			configurateScreenPanel();
			configurateNorthPanel();
			northPanel.setLayout(new BorderLayout(117,0));
			configurateGalleryPanel();
			galleryPanel.setLayout(new FlowLayout());
			configurateSouthPanel();
			configurateDeleteButton();
			configurateBackButton();
			addTitelGalleryPanel();
			configurateScrollBar();
			
			//configurateEditButtonPanel();
			southPanel.remove(addPictureButton);
			galleryPanel.removeAll();
			enlargedPictureButton.setBackground(Color.WHITE);
			//Ajout du bouton selectionne dans la fenetre
			galleryPanel.add(enlargedPictureButton);
			
		 add(screen,par);
		}
	}
	class DeleteClick implements ActionListener {
		public void actionPerformed(ActionEvent e){

			int num = indexSearch+1;

			
			if(num < 10) {
				File f = new File("Images/Galerie/numero0" + num + ".png"); 
				f.delete();
			}
			else {
				File f = new File("Images/Galerie/numero" + num + ".png"); 
				f.delete();
			}
		}
	}
	class AddClick implements ActionListener {
		
		
		public void actionPerformed(ActionEvent e){		
			
			if(e.getSource() == addPictureButton) {
				//JFileChoose permettant d'avoir acces a l'explorateur windows
				JFileChooser chooser = new JFileChooser();
				//FileNameExtensionFilter qui permet de selectionner uniquement les fichiers .jpeg, .png & .pg 
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG, PNG & JPG", "jpeg", "png", "jpg" );
				chooser.setFileFilter(filter);
				chooser.setSize(400, 400);
				chooser.setPreferredSize(new Dimension(350, 400));
				//Fichier selectionne dans l'explorateur windows
				File selectedFile;
				//Contient la valeur que retourne le JFileChooser
				int returnVal = chooser.showOpenDialog(galleryPanel);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					//Stockage du fichier selectionne dans la variable selectedFile
					selectedFile = chooser.getSelectedFile();

					//Stockage du nom et de l'extension du fichier selectionne
					String namePicture = selectedFile.getName();

					//Creation d'un fichier sous Images au meme nom que le nom du fichier selectionne
					File f = new File("./Images/Galerie/" + namePicture);

					//Renomme le chemin du selectedFile dans le but de le deplacer sous le dossier Images
					selectedFile.renameTo(f);
					
					
				}
			}
		}
	}
	class BackClick implements ActionListener {
		public void actionPerformed(ActionEvent e){

			if(e.getSource() == backButton) {
				Gallery frame = new Gallery();
				frame.setVisible(true);
			}
		}
	}
}

			


		

			
	
	
	
	
	
		
		 

 



	
	    
	


