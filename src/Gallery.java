
/*
 * Project POO Smartphone
 * Author: Ismaël Moreno
 * Date creation: 06.05.2019
 * Date last modification: 08.06.2019
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.miginfocom.swing.MigLayout;

/**
 * Parametre pour la galerie photo
 **/

public class Gallery extends BaseFrame {

	/* Declaration du MigLayout */
	private MigLayout layout = new MigLayout("wrap 4");

	/* Declaration des panels */
	private JPanel screen = new JPanel();
	private JPanel galleryPanel = new JPanel();
	private JPanel northPanel = new JPanel();
	private JPanel southPanel = new JPanel();

	/* Declaration de la barre de defilement */
	private JScrollPane scrollBar;

	/* Declaration des boutons add/delete/back */
	private JButton addPictureButton = new JButton(new ImageIcon("Images/add.png"));
	private JButton deletePictureButton = new JButton(new ImageIcon("Images/delete.png"));
	private JButton backButton = new JButton(new ImageIcon("Images/backGallery.png"));

	/* Declaration des labels */
	private JLabel galleryLabel = new JLabel("Ma Galerie");

	/* Declaration du fichier des images */
	private File filePictures = new File("./Images/Galerie");

	/* Tableau qui contient tous les chemins des images */
	private String[] listPathPictures = filePictures.list();

	/* ImageIcon qui represente chaque image dans la galerie */
	private ImageIcon eachPicture;

	/* Dimension des JButton contenant les ImageIcon */
	private Dimension size = new Dimension(113, 113);

	/* Tableau de boutons qui est de la meme longueur que le tableau listPathPictures */
	private JButton[] buttonPicture = new JButton[listPathPictures.length];

	/* Variable qui stock le nombre d'images dans le dossier */
	private int numberOfPictures;

	/**
	 * Construction de la galerie
	 */

	public Gallery() {
		/* Parametrage du GridBagLayout herite de BaseFrame */
		par.gridx = 0;
		par.gridy = 1;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		/* Ajout du panel de fond via sa methode */
		configurateScreenPanel();

		/* Ajout d'un panel Nord via sa methode */
		configurateNorthPanel();

		/* Ajout d'un panel reserve a la galerie photo au Centre via sa methode */
		configurateGalleryPanel();

		/* Ajout d'un panel Sud pour les boutons via sa methode */
		configurateSouthPanel();

		/* Ajout du titre avec modification de la police et taille via sa methode */
		addTitelGalleryPanel();

		/* Ajout et modification du bouton Add via sa methode */
		configurateAddButton();

		/* Configuration du MigLayout */
		galleryPanel.setLayout(layout);

		/* Configuration du ScrollBar via sa methode */
		configurateScrollBar();

		/* Creation du tableau d'image depuis le dossier Galerie */
		initializeGallery();

		/* Ajout du Panel Screen et Par dans Gallery pour fixer le tout */
		add(screen, par);
	}

	/* Methode pour configurer le Screen */
	private void configurateScreenPanel() {
		screen.setLayout(new BorderLayout());
		screen.setPreferredSize(new Dimension(LARGEUR, 700));
		screen.setBackground(Color.WHITE);
	}

	/* Methode pour configurer le NorthPanel */
	private void configurateNorthPanel() {
		screen.add(northPanel, BorderLayout.NORTH);
		northPanel.setPreferredSize(new Dimension(LARGEUR, 40));
		northPanel.setBackground(Color.WHITE);
	}

	/* Methode pour configurer le GalleryPanel */
	private void configurateGalleryPanel() {
		screen.add(galleryPanel, BorderLayout.CENTER);
		galleryPanel.setBackground(Color.WHITE);
	}

	/* Methode pour configurer le SouthPanel */
	private void configurateSouthPanel() {
		screen.add(southPanel, BorderLayout.SOUTH);
		southPanel.setPreferredSize(new Dimension(LARGEUR, 50));
		southPanel.setBackground(Color.WHITE);
	}

	/* Methode pour ajouter le titre NorthPanel avec modification de la police et taille */
	private void addTitelGalleryPanel() {
		northPanel.add(galleryLabel, BorderLayout.CENTER);
		Font font = new Font("Arial", Font.BOLD, 32);
		galleryLabel.setFont(font);
	}

	/* Configuration du bouton AddPictureButton */
	private void configurateAddButton() {
		southPanel.add(addPictureButton);
		addPictureButton.setPreferredSize(new Dimension(40, 40));
		addPictureButton.setContentAreaFilled(true);
		addPictureButton.setBackground(Color.WHITE);
		addPictureButton.setBorderPainted(false);
		addPictureButton.setRolloverEnabled(false);
		/* Ajout de l'ActionListener au bouton */
		addPictureButton.addActionListener(new AddClick());
	}

	/* Configuration du ScrollBar */
	private void configurateScrollBar() {
		scrollBar = new JScrollPane(galleryPanel);
		scrollBar.getVerticalScrollBar().setUnitIncrement(20);
		scrollBar.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		screen.add(scrollBar);
	}

	public void initializeGallery() {

		for (int i = 0; i < listPathPictures.length; i++) {

			/* Defini le nombre d'image dans la galerie */
			numberOfPictures = i;
			/* Defini les images qu'il faut prendre dans le dossier Images */
			eachPicture = new ImageIcon(filePictures + "/" + listPathPictures[i]);
			/* Prend les images */
			Image pic = eachPicture.getImage();
			/* Regle les dimensions des images */
			Image newPic = pic.getScaledInstance(113, 113, java.awt.Image.SCALE_SMOOTH);
			/* Met chaque image dans un ImageIcon */
			eachPicture = new ImageIcon(newPic);
			/* Creation des boutons avec les ImageIcon a l'interieur */
			buttonPicture[i] = new JButton(eachPicture);
			/* Reglement de la dimension des boutons */
			buttonPicture[i].setMaximumSize(size);
			/* Ajout des boutons dans le panel qui a comme layout le MigLayout */
			galleryPanel.add(buttonPicture[i]);
			/* Récupere le chemin des images */
			String imagePath = "./Images/Galerie/"+listPathPictures[i];
			/* Ajout de MouseListener sur chaque bouton */
			buttonPicture[i].addActionListener(new PictureButtonListener(imagePath));
		}
	}

	class PictureButtonListener implements ActionListener {
		private String imagePath;
		
		public PictureButtonListener(String imagePath) {
			this.imagePath = imagePath;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			screen.removeAll();
			GalleryImageDisplayer gid = new GalleryImageDisplayer(imagePath);
			gid.setVisible(true);
		}
		
	}
	private class GalleryImageDisplayer extends BaseFrame {

		public GalleryImageDisplayer(String imagePath) {
			
			/* Creer une ImageIcon depuis le chemin de l'image et la redimensionne */
			ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(450, 595, Image.SCALE_SMOOTH));
			/* Creer un label et ajoute l'ImageIcon dedans */
			JLabel imageLabel = new JLabel(icon);
			par.gridx = 0;
			par.gridy = 1;
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			configurateScreenPanel();
			configurateNorthPanel();
			northPanel.setLayout(new BorderLayout(117, 0));
			configurateGalleryPanel();
			galleryPanel.setLayout(new FlowLayout());
			configurateSouthPanel();
			configurateDeleteButton(imagePath);
			configurateBackButton();
			addTitelGalleryPanel();
			configurateScrollBar();
			southPanel.remove(addPictureButton);
			/* Renitialise la galerie d'image */
			galleryPanel.removeAll();
			/* Ajout du panel dans la fenetre */
			galleryPanel.add(imageLabel);
			add(screen, par);
		}

		class BackClick implements ActionListener {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == backButton) {
					Gallery frame = new Gallery();
					frame.setVisible(true);
					dispose();
				}
			}
		}

		private class DeleteClick implements ActionListener {
			String imagePath;

			public DeleteClick(String imagePath) {
				this.imagePath = imagePath;
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == deletePictureButton) {
					/* Affiche un Popup pour demander une confirmation de suppression */
					Object[] options = { "OK", "Annuler" };
					int choice = JOptionPane.showOptionDialog(null, "Voulez-vous vraiment supprimer cette image ?",
							"Attention", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
							options[0]);

					if (choice == 0) {

						File f = new File(imagePath);
						f.delete();
						Gallery frame = new Gallery();
						frame.setVisible(true);
						dispose();
					}
				}
			}
		}

		/* Configuration du bouton BackButton */
		private void configurateBackButton() {
			northPanel.add(backButton, BorderLayout.WEST);
			backButton.setPreferredSize(new Dimension(40, 40));
			backButton.setContentAreaFilled(true);
			backButton.setBackground(Color.WHITE);
			backButton.setBorderPainted(false);
			backButton.setRolloverEnabled(false);
			/* Ajout d'un ActionListener au bouton */
			backButton.addActionListener(new BackClick());
		}

		/* Configuration du bouton DeletePictureButton */
		private void configurateDeleteButton(String imagePath) {
			southPanel.add(deletePictureButton);
			deletePictureButton.setPreferredSize(new Dimension(40, 40));
			deletePictureButton.setContentAreaFilled(true);
			deletePictureButton.setBackground(Color.WHITE);
			deletePictureButton.setBorderPainted(false);
			deletePictureButton.setRolloverEnabled(false);
			/* Ajout d'un ActionListener au bouton */
			deletePictureButton.addActionListener(new DeleteClick(imagePath));
		}
	}

	private class AddClick implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			/* Variable qui attribue un nouveau nom aux images ajoutées */
			int newName;

			if (e.getSource() == addPictureButton) {
				/* JFileChoose permettant d'avoir acces a l'explorateur windows */
				JFileChooser chooser = new JFileChooser();
				/* FileNameExtensionFilter qui permet de selectionner uniquement les fichiers .jpeg, .png & .jpg */
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG, PNG & JPG", "jpeg", "png", "jpg");
				chooser.setDialogTitle("Choisissez une image");
				chooser.setFileFilter(filter);
				chooser.setSize(400, 400);
				chooser.setPreferredSize(new Dimension(350, 400));
				/* Fichier selectionne dans l'explorateur windows */
				File selectedFile;
				/* Contient la valeur que retourne le JFileChooser */
				int returnVal = chooser.showOpenDialog(galleryPanel);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					/* Stockage du fichier selectionne dans la variable selectedFile */
					selectedFile = chooser.getSelectedFile();
					/* Reprend le nombre d'image et ajoute 2 pour que la nouvelle image soit en dernière position */
					newName = numberOfPictures + 2;
					/* Creation d'un fichier sous Images au meme nom que le nom du fichier selectionne*/
					if (newName < 10) {
						File f = new File("Images/Galerie/000" + newName + ".png");
						/* Renomme le chemin du selectedFile dans le but de le deplacer sous le dossier Images */
						selectedFile.renameTo(f);
						/* Rafraichi la frame */
						Gallery frame = new Gallery();
						frame.setVisible(true);
						dispose();
					} else {
						File f = new File("Images/Galerie/00" + newName + ".png");
						selectedFile.renameTo(f);
						Gallery frame = new Gallery();
						frame.setVisible(true);
						dispose();
					}
				}
			}
		}
	}
}
