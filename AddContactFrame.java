import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

public class AddContactFrame extends BaseFrame{
	private JPanel screen = new JPanel();
	
	private JLabel nom = new JLabel("Nom : ");
	private JLabel prenom = new JLabel("Prénom : ");
	private JLabel numero = new JLabel("Numéro : ");
	
	private JTextField nomField=new JTextField(20);
	private JTextField prenomField=new JTextField(20);
	private JTextField numField=new JTextField(20);
	
	private JButton cancel = new JButton("Cancel");
	private JButton save = new JButton("Save");
	
	public AddContactFrame() {
		screen.setLayout(new GridLayout(4, 2,10,10));
		
		screen.add(nom);
		screen.add(nomField);
		screen.add(prenom);
		screen.add(prenomField);
		screen.add(numero);
		screen.add(numField);
		
		save.addActionListener(new SaveListener());
		cancel.addActionListener(new CancelListener());
		
		screen.add(save);
		screen.add(cancel);
		
		par.gridx = 0;
		par.gridy = 1;
		
		screen.setPreferredSize(new Dimension(LARGEUR,700));
		screen.setBackground(Color.WHITE);
		add(screen,par);
	}
	
	class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			ArrayList<Contact> contacts= readContacts();
			
			Contact contact = new Contact(nomField.getText(),prenomField.getText(),numField.getText());
			contacts.add(contact);
			
			write(contacts);
			
			JFrame frame = new ContactFrame();
			frame.setVisible(true);
			dispose();
		}
	}
	class CancelListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new ContactFrame();
			frame.setVisible(true);
			dispose();
		}
	}
	public void write(ArrayList<Contact> contacts){
		try {
			FileOutputStream out = new FileOutputStream("contacts.ser");
			ObjectOutputStream oos = new ObjectOutputStream( out );
			oos.writeObject(contacts);
			oos.close();
			}
			catch(Exception e) {
				System.out.println("Prout");
			}
	}
}
