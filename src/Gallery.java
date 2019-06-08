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
 * This frame contain all the logic for the gallery
 * @author ismor
 *
 */
public class Gallery extends BaseFrame {

	private MigLayout layout = new MigLayout("wrap 4");
	
	private JPanel screen = new JPanel();
	private JPanel galleryPanel = new JPanel();
	private JPanel northPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	
	private JScrollPane scrollBar;
	
	private JButton addPictureButton = new JButton(new ImageIcon("Images/add.png"));
	private JButton deletePictureButton = new JButton(new ImageIcon("Images/delete.png"));
	private JButton backButton = new JButton(new ImageIcon("Images/backGallery.png"));

	private JLabel galleryLabel = new JLabel("Ma Galerie");

	private File filePictures = new File("./Images/Galerie");

	private String[] listPathPictures = filePictures.list();

	private ImageIcon eachPicture;

	private Dimension size = new Dimension(113, 113);

	private JButton[] buttonPicture = new JButton[listPathPictures.length];

	private int numberOfPictures;

	/**
     * Builds the gallery screen panel
     *
     * @param frame which contains the gridbaglayout and all methods 
     */
	public Gallery() {
		par.gridx = 0;
		par.gridy = 1;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		configurateScreenPanel();
		configurateNorthPanel();
		configurateGalleryPanel();
		configurateSouthPanel();
		addTitelGalleryPanel();
		configurateAddButton();
		galleryPanel.setLayout(layout);
		configurateScrollBar();
		initializeGallery();
		add(screen, par);
	}
	/**
	 * Method to configure the screen
	 */
	private void configurateScreenPanel() {
		screen.setLayout(new BorderLayout());
		screen.setPreferredSize(new Dimension(LARGEUR, 700));
		screen.setBackground(Color.WHITE);
	}
	/**
	 * Method to configure the NorthPanel
	 */
	private void configurateNorthPanel() {
		screen.add(northPanel, BorderLayout.NORTH);
		northPanel.setPreferredSize(new Dimension(LARGEUR, 40));
		northPanel.setBackground(Color.WHITE);
	}
	/**
	 * Method to configure the GalleryPanel
	 */
	protected void configurateGalleryPanel() {
		screen.add(galleryPanel, BorderLayout.CENTER);
		galleryPanel.setBackground(Color.WHITE);
	}
	/**
	 * Method to configure the SouthPanel
	 */
	private void configurateSouthPanel() {
		screen.add(southPanel, BorderLayout.SOUTH);
		southPanel.setPreferredSize(new Dimension(LARGEUR, 50));
		southPanel.setBackground(Color.WHITE);
	}
	/**
	 * Method to add the NorthPanel title with font modification and size
	 */
	private void addTitelGalleryPanel() {
		northPanel.add(galleryLabel, BorderLayout.CENTER);
		Font font = new Font("Arial", Font.BOLD, 32);
		galleryLabel.setFont(font);
	}
	/**
	 * Method to configure the AddPictureButton 
	 */
	private void configurateAddButton() {
		southPanel.add(addPictureButton);
		addPictureButton.setPreferredSize(new Dimension(40, 40));
		addPictureButton.setContentAreaFilled(true);
		addPictureButton.setBackground(Color.WHITE);
		addPictureButton.setBorderPainted(false);
		addPictureButton.setRolloverEnabled(false);
		addPictureButton.addActionListener(new AddClick());
	}
	/**
	 * Method to configure the ScroolBar
	 */
	private void configurateScrollBar() {
		scrollBar = new JScrollPane(galleryPanel);
		scrollBar.getVerticalScrollBar().setUnitIncrement(20);
		scrollBar.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		screen.add(scrollBar);
	}
	/**
	 * Retrieves images from the folder and creates an array of buttons 
	 */
	public void initializeGallery() {
		for (int i = 0; i < listPathPictures.length; i++) {
			numberOfPictures = i;
			eachPicture = new ImageIcon(filePictures + "/" + listPathPictures[i]);
			Image pic = eachPicture.getImage();
			Image newPic = pic.getScaledInstance(113, 113, java.awt.Image.SCALE_SMOOTH);
			eachPicture = new ImageIcon(newPic);
			buttonPicture[i] = new JButton(eachPicture);
			buttonPicture[i].setMaximumSize(size);
			galleryPanel.add(buttonPicture[i]);
			String imagePath = "./Images/Galerie/"+listPathPictures[i];
			buttonPicture[i].addActionListener(new PictureButtonListener(imagePath));
			}
		}
	/**
	 * Builds the button listener of the enlarged image
	 * @author Ismaël Moreno
	 *
	 */
	class PictureButtonListener implements ActionListener {
		private String imagePath;
		
		/**
		 * Method that retrieves the path of the images
		 * @param imagePath Retrieves the original path of the selected image
		 */
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
	/**
	 * Class that will recreate a gallery but with an enlarged image
	 * @author Ismaël Moreno
	 *
	 */
	class GalleryImageDisplayer extends BaseFrame {
		/**
		 * Builds the new frame for the enlarged image 
		 * @param imagePath Retrieves the original path of the selected image
		 */
		public GalleryImageDisplayer(String imagePath) {
			ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(450, 595, Image.SCALE_SMOOTH));
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
			//Reset the image gallery
			galleryPanel.removeAll();
			galleryPanel.add(imageLabel);
			add(screen, par);
		}
		/**
		 * Class that allows you to close the frame of the enlarged image to return to the gallery
		 * @author Ismaël Moreno
		 *
		 */
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
		/**
		 * Class that allows you to delete an image
		 * @author Ismaël Moreno
		 *
		 */
		class DeleteClick implements ActionListener {
			String imagePath;
			
			/**
			 * Deletes the image and refreshes the gallery
			 * @param imagePath Retrieves the original path of the selected image
			 */
			public DeleteClick(String imagePath) {
				this.imagePath = imagePath;
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == deletePictureButton) {
					//Displays a Popup to request confirmation of deletion
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
		/**
		 * Method to configure the BackButton
		 */
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
		/**
		 * Method to configure the DeleteButton
		 * @param imagePath Retrieves the original path of the selected image
		 */
		private void configurateDeleteButton(String imagePath) {
			southPanel.add(deletePictureButton);
			deletePictureButton.setPreferredSize(new Dimension(40, 40));
			deletePictureButton.setContentAreaFilled(true);
			deletePictureButton.setBackground(Color.WHITE);
			deletePictureButton.setBorderPainted(false);
			deletePictureButton.setRolloverEnabled(false);
			deletePictureButton.addActionListener(new DeleteClick(imagePath));
		}
	}
	/**
	 * Class that allows you to add an image to the gallery
	 * @author Ismaël Moreno
	 *
	 */
	class AddClick implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int newName;
			if (e.getSource() == addPictureButton) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG, PNG & JPG", "jpeg", "png", "jpg");
				chooser.setDialogTitle("Choisissez une image");
				chooser.setFileFilter(filter);
				chooser.setSize(400, 400);
				chooser.setPreferredSize(new Dimension(350, 400));
				File selectedFile;
				int returnVal = chooser.showOpenDialog(galleryPanel);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					selectedFile = chooser.getSelectedFile();
					newName = numberOfPictures + 2;
					if (newName < 10) {
						File f = new File("Images/Galerie/000" + newName + ".png");
						selectedFile.renameTo(f);
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
