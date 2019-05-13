import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class InfosFrame extends BaseFrame{
	
	private JPanel screen = new JPanel();
	private JPanelWithBackground image;
	private JPanel infos = new JPanel();
	private JTextArea firstName = new JTextArea();
	private JTextArea lastName = new JTextArea();
	private JTextArea number = new JTextArea();
	private Contact contact;
	ImageIcon photo;
	
	public InfosFrame(String selectedContact) {
		ArrayList<Contact> contacts = readContacts();
		for(int i=0;i<contacts.size();i++) {
			if(selectedContact.equals(contacts.get(i).getFirstname()+" "+contacts.get(i).getLastname())){
				contact = contacts.get(i);
			}
		}
		
		if(contact.getPhoto()==null) {
			try {
				image = new JPanelWithBackground("contactnull.png");
				//<div>Icons made by <a href="https://www.freepik.com/" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" 			    title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" 			    title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
			else {
				try {
					image = new JPanelWithBackground(contact.getPhoto());
				} catch (Exception e) {
					// TODO: handle exception
				}
		}
		image.setPreferredSize(new Dimension(LARGEUR,200));
		screen.add(image);
		
		par.gridx = 0;
		par.gridy = 1;
		
		screen.setPreferredSize(new Dimension(LARGEUR,700));
		screen.setBackground(Color.WHITE);
		add(screen,par);
	}
}
