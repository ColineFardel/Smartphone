/*
 * Project POO Smartphone
 * Author: Coline Fardel
 * Date creation: 06.05.2019
 * Date last modification: 07.06.2019
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * Class that show all the contacts
 * @author Coline Fardel
 *
 */
public class ContactFrame extends BaseFrame{
	
	private JList list;
	private DefaultListModel listModel= new DefaultListModel();
	
	private JButton plusButton = new JButton(new ImageIcon("Images//add.png"));
	private JButton moreButton = new JButton(new ImageIcon("Images//threedots.png"));
	
	private JPanel screen = new JPanel();
	private JPanel topJPanel = new JPanel();
	private JPanel mainJPanel = new JPanel();
	
	private JLabel title = new JLabel("Contacts");
	
	private JScrollPane scrollPane;
	
	public ContactFrame() {
		screen.setLayout(new GridBagLayout());
		
		ArrayList<Contact> contacts = readContacts();
		String parameter = readParameter();
		
		//Put the contacts in the model for the Jlist
		if(parameter.equals("firstname")) {
			for(int i=0; i<contacts.size();i++) {
				listModel.addElement(contacts.get(i).getFirstname()+" "+contacts.get(i).getLastname());
			}
		}
		else {
			for(int i=0; i<contacts.size();i++) {
				listModel.addElement(contacts.get(i).getLastname()+" "+contacts.get(i).getFirstname());
			}
		}
		
		list=  new JList(listModel);
		
		plusButton.setOpaque(false);
		plusButton.setContentAreaFilled(false);
		plusButton.setBorderPainted(false);
		plusButton.setPreferredSize(new Dimension(40,40));
		plusButton.addActionListener(new ButtonListener());
		
		moreButton.setOpaque(false);
		moreButton.setContentAreaFilled(false);
		moreButton.setBorderPainted(false);
		moreButton.setPreferredSize(new Dimension(40,40));
		moreButton.addActionListener(new ButtonListener());
		
		title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
		
		topJPanel.setLayout(new FlowLayout());
		topJPanel.setPreferredSize(new Dimension(LARGEUR,80));
		topJPanel.setBackground(Color.WHITE);
		topJPanel.add(title);
		topJPanel.add(plusButton);
		topJPanel.add(moreButton);
		
		par.gridx = 0;
		par.gridy = 0;
		
		screen.add(topJPanel,par);
		
		list.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		
		//When you click it open the info frame without having to click on another button
		list.addListSelectionListener(new ListSelectionListener()
	    {
	      public void valueChanged(ListSelectionEvent evt) 
	      {
	        // To avoid double value selected
	        if (evt.getValueIsAdjusting())
	                  return;
	        JFrame frame = new InfosFrame((String)list.getSelectedValue());
			frame.setVisible(true);
			dispose();
	      }
	    });

		scrollPane= new JScrollPane(list);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		scrollPane.setPreferredSize(new Dimension(LARGEUR,600));
		
		mainJPanel.setLayout(new FlowLayout());
		mainJPanel.setPreferredSize(new Dimension(LARGEUR,600));
		mainJPanel.setBackground(Color.WHITE);
		mainJPanel.add(scrollPane);
		
		par.gridx = 0;
		par.gridy = 1;
		
		screen.add(mainJPanel,par);
		
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
			if(e.getSource()==plusButton) {
				JFrame frame = new AddContactFrame();
				frame.setVisible(true);
				dispose();
			}
			if(e.getSource()==moreButton) {
				JFrame frame = new ParameterFrame();
				frame.setVisible(true);
			}
		}
	}
	/**
	 * Class to select the parameter of the sorting
	 * @author Coline Fardel
	 */
	class ParameterFrame extends JFrame{
		private JList list;
		private DefaultListModel listModel= new DefaultListModel();
		private JPanel parameterPanel = new JPanel();
		
		public ParameterFrame() {
			setLayout(new FlowLayout());
			setSize(new Dimension(100,50));
			setBackground(Color.WHITE);
			setLocation(850, 150);
			setUndecorated(true);
			
			listModel.addElement("Sort by firstname");
			listModel.addElement("Sort by lastname");
			
			list = new JList(listModel);
			
			//When you click it open the info frame without having to click on another button
			list.addListSelectionListener(new ListSelectionListener()
		    {
		      public void valueChanged(ListSelectionEvent evt) 
		      {
		        // To avoid double value selected
		        if (evt.getValueIsAdjusting())
		                  return;
		        
		        ArrayList<Contact> contacts = readContacts();
	        	
	        	ArrayList<String> sorted = new ArrayList<String>();
	        	
	        	Contact tempContact = new Contact();
	        	
		        if(list.getSelectedValue().equals("Sort by firstname")) {
		        	sortByFirstName();
		        	writeParameter("firstname");
		        }
		        else {
		        	sortByLastName();
		        	writeParameter("lastname");
		        }
		        JFrame frame = new ContactFrame();
				frame.setVisible(true);
				dispose();
		      }
		    });
			parameterPanel.add(list);
			parameterPanel.setBackground(Color.WHITE);
			add(parameterPanel); 
		}
	}
}
