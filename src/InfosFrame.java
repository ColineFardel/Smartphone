/*
 * Project POO Smartphone
 * Author: Coline Fardel
 * Date creation: 13.05.2019
 * Date last modification: 09.06.2019
 */
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
/**
 * Class that show all the infos of a contact
 * @author Coline Fardel
 */
public class InfosFrame extends BaseFrame{
	private int index=0;
	
	private JPanel screen = new JPanel();
	private JPanel infos = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JPanel photoPanel = new JPanel();
	private JPanel blankPanel = new JPanel();
	
	private JTextArea firstName;
	private JTextArea lastName;
	private JTextArea number;
	
	private JLabel firstNameLabel = new JLabel("Prénom : ");
	private JLabel lastNameLabel = new JLabel("Nom : ");
	private JLabel numberLabel = new JLabel("Numéro : ");
	private JLabel noteLabel = new JLabel("Note : ");
	
	private Contact contact;
	
	private JButton save = new JButton("Sauvegarder");
	private JButton delete = new JButton("Supprimer");
	private JButton cancel = new JButton("Annuler");
	private JButton addNote = new JButton("Ajouter une note au contact");
	private JButton noteButton = new JButton("Note");
	private JButton image;
	
	private ArrayList<Contact> contacts;
	private String parameter;
	private String contactNote="";
	
	private Font base = new Font(Font.SANS_SERIF, Font.BOLD, 30);
	private Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
	
	public InfosFrame(String selectedContact) {
		contactNote=selectedContact;
		
		contacts = readContacts();
		parameter = readParameter();
		
		for(int i=0;i<contacts.size();i++) {
			if(parameter.equals("lastname")) {
				if(selectedContact.equals(contacts.get(i).getLastname()+" "+contacts.get(i).getFirstname())){
					contact = contacts.get(i);
					index=i;
				}
			}
			else {
				if(selectedContact.equals(contacts.get(i).getFirstname()+" "+contacts.get(i).getLastname())){
					contact = contacts.get(i);
					index=i;
				}
			}
		}
		
		image= new JButton(new ImageIcon(contact.getPhoto()));
		image.addActionListener(new ButtonListener());
		image.setPreferredSize(new Dimension(150,150));
		image.setOpaque(false);
		image.setContentAreaFilled(false);
		image.setBorderPainted(false);
		
		photoPanel.setPreferredSize(new Dimension(LARGEUR,200));
		photoPanel.setBackground(Color.WHITE);
		photoPanel.add(image);
		
		screen.add(photoPanel);
		
		firstName = new JTextArea(contact.getFirstname());
		firstName.setFont(base);
		firstNameLabel.setFont(base);
		
		lastName = new JTextArea(contact.getLastname());
		lastName.setFont(base);
		lastNameLabel.setFont(base);
		
		number = new JTextArea(contact.getNumber());
		number.setFont(base);
		numberLabel.setFont(base);
		
		noteLabel.setFont(base);
		noteButton.setFont(base);
		
		noteButton.addActionListener(new ButtonListener());
		addNote.addActionListener(new ButtonListener());
		
		infos.setLayout(new GridLayout(4, 2, 50, 50));
		infos.setBackground(Color.WHITE);
		
		infos.add(firstNameLabel);
		infos.add(firstName);
		infos.add(lastNameLabel);
		infos.add(lastName);
		infos.add(numberLabel);
		infos.add(number);
		infos.add(noteLabel);
		if(contact.getNote()== null) {
			infos.add(addNote);
		}
		else {
			infos.add(noteButton);
		}
		
		screen.add(infos);
		
		blankPanel.setPreferredSize(new Dimension(LARGEUR,100));
		blankPanel.setBackground(Color.WHITE);
		
		save.addActionListener(new ButtonListener());
		save.setFont(buttonFont);
		delete.addActionListener(new ButtonListener());
		delete.setFont(buttonFont);
		cancel.addActionListener(new ButtonListener());
		cancel.setFont(buttonFont);
		
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.add(save);
		buttonPanel.add(delete);
		buttonPanel.add(cancel);
		
		screen.add(buttonPanel);
		
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
			if(e.getSource()==noteButton) {
				JFrame frame = new NoteFrame(readTxt(contact.getNote()),contact,index);
				frame.setVisible(true);
				dispose();
			}
			if(e.getSource()==addNote) {
				JFrame frame = new NotesFrame(contact,index);
				frame.setVisible(true);
				dispose();
			}
			if(e.getSource()==save) {
				Contact c = new Contact(lastName.getText(),firstName.getText(),number.getText());
				c.setNote(contact.getNote());
				contacts.set(index, c);
				writeContacts(contacts);
				
				JFrame frame = new ContactFrame();
				frame.setVisible(true);
				dispose();
			}
			if(e.getSource()==delete) {
				contacts.remove(index);
				writeContacts(contacts);
				
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
