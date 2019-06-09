/*
 * Project POO Smartphone
 * Author: Coline Fardel
 * Date creation: 23.04.2019
 * Date last modification: 07.06.2019
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.Border;
/**
 * Frame for the home screen
 * @author Coline Fardel
 */
public class HomeFrame extends BaseFrame{
	
	private JPanelWithBackground screen;
	
	private JButton contactButton= new JButton(new ImageIcon("Images//phone-book.png"));
	private JButton galleryButton= new JButton(new ImageIcon("Images//landscape.png"));
	private JButton noteButton = new JButton(new ImageIcon("Images//notepad.png"));
	
	public HomeFrame() {
		try {
			screen = new JPanelWithBackground("Images//wallpaper.jpg");
		} catch (Exception e) {
			System.err.println("Erreur dans l'affichage du fond d'écran");
		}
		screen.setLayout(new FlowLayout(0,50,50));
		
		contactButton.addActionListener(new ButtonListener());
		contactButton.setPreferredSize(new Dimension(80,80));
		contactButton.setOpaque(false);
		contactButton.setContentAreaFilled(false);
		contactButton.setBorderPainted(false);
		screen.add(contactButton);
		
		galleryButton.addActionListener(new ButtonListener());
		galleryButton.setPreferredSize(new Dimension(80,80));
		galleryButton.setOpaque(false);
		galleryButton.setContentAreaFilled(false);
		galleryButton.setBorderPainted(false);
		screen.add(galleryButton);
		
		noteButton.addActionListener(new ButtonListener());
		noteButton.setPreferredSize(new Dimension(80,80));
		noteButton.setOpaque(false);
		noteButton.setContentAreaFilled(false);
		noteButton.setBorderPainted(false);
		screen.add(noteButton);
		
		par.gridx = 0;
		par.gridy = 1;
		
		screen.setPreferredSize(new Dimension(LARGEUR,700));
		add(screen,par);
	}
	/**
	 * Listener for the buttons
	 * @author Coline Fardel
	 */
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==contactButton) {
				JFrame frame = new ContactFrame();
				frame.setVisible(true);
				dispose();
			}
			if(e.getSource()==galleryButton) {
				JFrame frame = new Gallery();
				frame.setVisible(true);
				dispose();
			}
			if(e.getSource()==noteButton) {
				JFrame frame = new NotesFrame();
				frame.setVisible(true);
				dispose();
			}
		}
		
	}
}
