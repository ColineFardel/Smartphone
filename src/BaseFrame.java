/*
 * Project POO Smartphone
 * Author: Coline Fardel
 * Date creation: 30.04.2019
 * Date last modification: 21.05.2019
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

public class BaseFrame extends JFrame{

	protected final int LARGEUR = 480;
	
	protected GridBagConstraints par = new GridBagConstraints();

	private JPanel topPanel= new JPanel();
	private JPanel botPannel= new JPanel();
	
	private JButton homeButton = new JButton(new ImageIcon("home-icon-silhouette.png"));
	//<div>Icons made by <a href="https://www.freepik.com/" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" 			    title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" 			    title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
	private JButton closeButton = new JButton(new ImageIcon("power2.png"));
	//<div>Icons made by <a href="https://www.flaticon.com/<?=_('authors').'/'?>freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" 		    title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" 		    title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
	
	public BaseFrame(){		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		setUndecorated(true);
		setSize(new Dimension(LARGEUR,800));
		setLocationRelativeTo(null);		
		
		par.gridx = 0;
		par.gridy = 0;
		
		topPanel.setPreferredSize(new Dimension(LARGEUR,50));
		topPanel.setOpaque(true);
		topPanel.setBackground(Color.BLACK);
		add(topPanel,par);
		
		par.gridx = 0;
		par.gridy = 1;
		
		homeButton.addActionListener(new HomeListener());
		homeButton.setPreferredSize(new Dimension(40,40));
		homeButton.setOpaque(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setBorderPainted(false);
		botPannel.add(homeButton);
		
		closeButton.addActionListener(new CloseListener());
		closeButton.setPreferredSize(new Dimension(40,40));
		closeButton.setBackground(Color.BLACK);
		botPannel.add(closeButton);
		
		//setBackground(new Color(0,0,0,0));
		par.gridx = 0;
		par.gridy = 2;
		
		botPannel.setPreferredSize(new Dimension(LARGEUR,50));
		botPannel.setOpaque(true);
		botPannel.setBackground(Color.BLACK);
		add(botPannel,par);
	}
	
	class HomeListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new HomeFrame();
			frame.setVisible(true);
			dispose();
		}
	}
	class CloseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	public ArrayList<Contact> readContacts(){
		ArrayList<Contact> contacts= new ArrayList<Contact>();
		
		try {
			FileInputStream in = new FileInputStream("contacts.ser");
			ObjectInputStream ois = new ObjectInputStream( in );
			contacts = (ArrayList<Contact>) ois.readObject();
			ois.close();
			} catch(Exception e) {
				System.out.println("Erreur dans la lecture des contacts");
			}
		return contacts;
	}
	
	public void writeContacts(ArrayList<Contact> contacts){
		try {
			FileOutputStream out = new FileOutputStream("contacts.ser");
			ObjectOutputStream oos = new ObjectOutputStream( out );
			oos.writeObject(contacts);
			oos.close();
			}
			catch(Exception e) {
				System.out.println("Erreur dans l'ecriture des contacts");
			}
	}
	public String readParameter() {
		String parameter="";
		
		try {
			FileInputStream in = new FileInputStream("parameter.ser");
			ObjectInputStream ois = new ObjectInputStream( in );
			parameter = (String) ois.readObject();
			ois.close();
			} catch(Exception e) {
				System.out.println("Erreur dans la lecture du parametre");
			}
		return parameter;
	}
	public void writeParameter(String parameter) {
		try {
			FileOutputStream out = new FileOutputStream("parameter.ser");
			ObjectOutputStream oos = new ObjectOutputStream( out );
			oos.writeObject(parameter);
			oos.close();
			}
			catch(Exception e) {
				System.out.println("Erreur dans l'ecriture du parametre");
			}
	}
	public void sortByFirstName() {
		ArrayList<Contact> contacts = readContacts();
    	ArrayList<String> sorted = new ArrayList<String>();
    	Contact tempContact = new Contact();
    	
    	for(int i=0;i<contacts.size();i++) {
    		sorted.add(contacts.get(i).getFirstname());
    	}
    	Collections.sort(sorted);
		
		for(int i=0; i<contacts.size();i++) {
			for(int j=0;j<contacts.size();j++) {
				if(sorted.get(i).equals(contacts.get(j).getFirstname())) {
					tempContact=contacts.get(j);
					contacts.remove(j);
					contacts.add(i,tempContact);
    			}
			}
		}
		writeContacts(contacts);
	}
	public void sortByLastName() {
		ArrayList<Contact> contacts = readContacts();
    	ArrayList<String> sorted = new ArrayList<String>();
    	Contact tempContact = new Contact();
    	
    	for(int i=0;i<contacts.size();i++) {
    		sorted.add(contacts.get(i).getLastname());
    	}
    	Collections.sort(sorted);
		
		for(int i=0; i<contacts.size();i++) {
			for(int j=0;j<contacts.size();j++) {
				if(sorted.get(i).equals(contacts.get(j).getLastname())) {
					tempContact=contacts.get(j);
					contacts.remove(j);
					contacts.add(i,tempContact);
    			}
			}
		}
		writeContacts(contacts);
	}
}
