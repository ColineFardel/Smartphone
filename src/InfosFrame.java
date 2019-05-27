/*
 * Project POO Smartphone
 * Author: Coline Fardel
 * Date creation: 13.04.2019
 * Date last modification: 14.04.2019
 */
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class InfosFrame extends BaseFrame{
	private int index=0;
	
	private JPanel screen = new JPanel();
	private JPanel infos = new JPanel();
	private JPanel firstNamePanel = new JPanel();
	private JPanel lastNamePanel = new JPanel();
	private JPanel numberPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JPanelWithBackground image;
	
	private JTextArea firstName;
	private JTextArea lastName = new JTextArea();
	private JTextArea number = new JTextArea();
	
	private Contact contact;
	private ImageIcon photo;
	
	private JButton save = new JButton("Save");
	private JButton delete = new JButton("Delete");
	private JButton cancel = new JButton("Cancel");
	
	private ArrayList<Contact> contacts;
	
	private Font base = new Font(Font.SANS_SERIF, Font.BOLD, 50);
	
	public InfosFrame(String selectedContact) {
		contacts = readContacts();
		for(int i=0;i<contacts.size();i++) {
			if(selectedContact.equals(contacts.get(i).getFirstname()+" "+contacts.get(i).getLastname())){
				contact = contacts.get(i);
				index=i;
			}
		}
		
		if(contact.getPhoto()==null) {
			try {
				image = new JPanelWithBackground("contactnull.png");
				//<div>Icons made by <a href="https://www.freepik.com/" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" 			    title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" 			    title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
			}
			catch (Exception e) {
				System.err.println("Image not found");
			}
		}
			else {
				try {
					image = new JPanelWithBackground(contact.getPhoto());
				} catch (Exception e) {
					System.err.println("Image not found");
				}
		}
		image.setPreferredSize(new Dimension(LARGEUR,200));
		screen.add(image);
		
		firstName = new JTextArea(contact.getFirstname());
		firstName.setFont(base);
		lastName = new JTextArea(contact.getLastname());
		lastName.setFont(base);
		number = new JTextArea(contact.getNumber());
		number.setFont(base);
		
		firstNamePanel.add(firstName);
		lastNamePanel.add(lastName);
		numberPanel.add(number);
		
		infos.setLayout(new GridLayout(3, 1, 50, 50));
		//infos.setPreferredSize(new Dimension(LARGEUR,150));
		infos.add(firstNamePanel);
		infos.add(lastNamePanel);
		infos.add(numberPanel);
		
		
		screen.add(infos);
		
		save.addActionListener(new ButtonListener());
		delete.addActionListener(new ButtonListener());
		cancel.addActionListener(new ButtonListener());
		
		buttonPanel.setPreferredSize(new Dimension(LARGEUR,100));
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
	class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			//contacts=readContacts();
			if(e.getSource()==save) {
				Contact c = new Contact(lastName.getText(),firstName.getText(),number.getText());
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
