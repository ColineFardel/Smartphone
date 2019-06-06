/*
 * Project POO Smartphone
 * Author: Coline Fardel
 * Date creation: 06.05.2019
 * Date last modification: 21.05.2019
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class AddContactFrame extends BaseFrame{
	private JPanel screen = new JPanel();
	private JPanel topPanel = new JPanel();
	
	private JLabel title = new JLabel("Ajouter un contact");
	private JLabel nom = new JLabel("Nom : ");
	private JLabel prenom = new JLabel("Prénom : ");
	private JLabel numero = new JLabel("Numéro : ");
	
	private JTextField nomField=new JTextField(20);
	private JTextField prenomField=new JTextField(20);
	private JTextField numField=new JTextField(20);
	
	private JButton cancel = new JButton("Annuler");
	private JButton save = new JButton("Sauvegarder");
	
	private Dimension dimension = new Dimension(200,150);
	private Font normalFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
	
	public AddContactFrame() {
		screen.setLayout(new GridBagLayout());
		
		title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
		title.setForeground(Color.WHITE);
		topPanel.setBackground(Color.GREEN);
		topPanel.setPreferredSize(new Dimension(LARGEUR,100));
		topPanel.add(title);
		
		par.gridx = 0;
		par.gridy = 0;
		par.fill = GridBagConstraints.HORIZONTAL;
		par.gridwidth = 2;
		
		screen.add(topPanel,par);
		
		par.gridx = 0;
		par.gridy = 1;
		par.gridwidth = 1;
		
		nom.setPreferredSize(dimension);
		nom.setFont(normalFont);
		screen.add(nom,par);
		
		par.gridx = 1;
		par.gridy = 1;
		
		screen.add(nomField,par);
		
		par.gridx = 0;
		par.gridy = 2;
		
		prenom.setPreferredSize(dimension);
		prenom.setFont(normalFont);
		screen.add(prenom,par);
		
		par.gridx = 1;
		par.gridy = 2;
		
		screen.add(prenomField,par);
		
		par.gridx = 0;
		par.gridy = 3;
		
		numero.setPreferredSize(dimension);
		numero.setFont(normalFont);
		screen.add(numero,par);
		
		par.gridx = 1;
		par.gridy = 3;
		
		screen.add(numField,par);
		
		save.addActionListener(new ButtonListener());
		cancel.addActionListener(new ButtonListener());
		
		par.gridx = 0;
		par.gridy = 4;
		
		screen.add(save,par);
		
		par.gridx = 1;
		par.gridy = 4;
		
		screen.add(cancel,par);
		
		par.gridx = 0;
		par.gridy = 1;
		
		screen.setPreferredSize(new Dimension(LARGEUR,700));
		screen.setBackground(Color.WHITE);
		add(screen,par);
	}
	/**
	 * Listener for the buttons
	 * @author Coline Fardel
	 */
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==save) {
				ArrayList<Contact> contacts= readContacts();
				String parameter = readParameter();
				
				Contact contact = new Contact(nomField.getText(),prenomField.getText(),numField.getText());
				contacts.add(contact);
				
				writeContacts(contacts);
				if(parameter.equals("lastname")) {
					sortByLastName();
				}
				else {
					sortByFirstName();
				}
				
				JFrame frame = new ContactFrame();
				frame.setVisible(true);
				dispose();
			}
			if(e.getSource()==cancel) {
				JFrame frame = new ContactFrame();
				frame.setVisible(true);
				dispose();
			}
		}
	}
}
