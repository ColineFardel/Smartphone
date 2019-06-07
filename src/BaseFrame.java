/*
 * Project POO Smartphone
 * Author: Coline Fardel
 * Date creation: 30.04.2019
 * Date last modification: 07.06.2019
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
/**
 * Frame used by all of the other frames as a template
 * @author Coline Fardel
 */
public class BaseFrame extends JFrame{

	protected final int LARGEUR = 480;
	protected GridBagConstraints par = new GridBagConstraints();

	private JPanel topPanel= new JPanel();
	private JPanel botPannel= new JPanel();
	
	private ClockLabel clock = new ClockLabel();
	
	private JButton homeButton = new JButton(new ImageIcon("Images//home-icon-silhouette.png"));
	private JButton closeButton = new JButton(new ImageIcon("Images//close.png"));
	
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
		topPanel.setLayout(new GridLayout(1, 3));
		clock.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		clock.setForeground(Color.WHITE);
		topPanel.add(clock);
		add(topPanel,par);
		
		par.gridx = 0;
		par.gridy = 1;
		
		homeButton.addActionListener(new ButtonListener());
		homeButton.setPreferredSize(new Dimension(40,40));
		homeButton.setOpaque(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setBorderPainted(false);
		botPannel.add(homeButton);
		
		closeButton.addActionListener(new ButtonListener());
		closeButton.setPreferredSize(new Dimension(40,40));
		closeButton.setOpaque(false);
		closeButton.setContentAreaFilled(false);
		closeButton.setBorderPainted(false);
		closeButton.setBackground(Color.BLACK);
		botPannel.add(closeButton);
		
		par.gridx = 0;
		par.gridy = 2;
		
		botPannel.setPreferredSize(new Dimension(LARGEUR,50));
		botPannel.setOpaque(true);
		botPannel.setBackground(Color.BLACK);
		add(botPannel,par);
	}
	/**
	 * Listener for the buttons
	 * @author Coline Fardel
	 */
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==closeButton) {
				System.exit(0);
			}
			if(e.getSource()==homeButton) {
				JFrame frame = new HomeFrame();
				frame.setVisible(true);
				dispose();
			}
		}
	}
	/**
	 * Class for the time
	 * @author Coline Fardel
	 */
	class ClockLabel extends JLabel implements ActionListener 
  	{
		public ClockLabel( ) {
			super("" + Calendar.getInstance());
			Timer t = new Timer(1000, this);
			t.start( );
			}
		public void actionPerformed(ActionEvent ae) {
			setText(String.format("%tR", Calendar.getInstance()));
			}
		}
	/**
	 * Read the ser file where all the contacts are
	 * @return the list of contacts
	 */
	protected ArrayList<Contact> readContacts(){
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
	/**
	 * Write the contacts in the ser file
	 * @param contacts
	 */
	protected void writeContacts(ArrayList<Contact> contacts){
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
	/**
	 * Read the ser file where the parameter for the sorting is
	 * @return the parameter
	 */
	protected String readParameter() {
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
	/**
	 * Write the parameter in the ser file
	 * @param parameter
	 */
	protected void writeParameter(String parameter) {
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
	/**
	 * Sort the list of contacts by the first name
	 */
	protected void sortByFirstName() {
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
	/**
	 * Sort the list of contacts by the last name
	 */
	protected void sortByLastName() {
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
	/**
	 * Read a text file
	 * @param filePath the path of the file
	 * @return what is written in the file
	 */
	protected String readTxt(String filePath) {
		String sortie="";
		try (FileReader reader = new FileReader(filePath);
	             BufferedReader br = new BufferedReader(reader)) {

	            // read line by line
	            String line;
	            while ((line = br.readLine()) != null) {
	                sortie = sortie + line;
	            }

	        } catch (IOException e) {
	        	System.err.print("Erreur lors de la lecture du fichier texte");
	        }
		return sortie;
	}
	/**
	 * Create a text file
	 * @param text what we want to write in the file
	 * @param filePath the path of the file
	 */
	protected void writeTxt(String text,String filePath) {
		File f;
		PrintWriter printer;
		BufferedWriter writer;			
		try {
			printer = new PrintWriter(filePath, "UTF-8");
			printer.write(text);
			printer.close();
			} catch (Exception e2) {
				System.out.print("Une erreur s'est produite lors de la création du fichier texte");
				}
		try {
			writer = new BufferedWriter(new FileWriter(filePath));
			writer.write(text);
			writer.close();
			} catch (Exception e2) {
				System.out.print("Une erreur s'est produite lors de l'écriture dans le fichier texte");
				}
	}
}
