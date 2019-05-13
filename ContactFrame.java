import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ContactFrame extends BaseFrame{
	
	private JList list;
	private DefaultListModel listModel= new DefaultListModel();
	
	private JButton plusButton = new JButton(new ImageIcon("C:\\Users\\colin\\Dropbox\\Mes Dossiers\\Semestre 2\\ProjetPOO\\Smartphone\\src\\add.png"));
	//<div>Icons made by <a href="https://www.flaticon.com/authors/smashicons" title="Smashicons">Smashicons</a> from <a href="https://www.flaticon.com/" 			    title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" 			    title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
	
	private JPanel screen = new JPanel();
	private JPanel topJPanel = new JPanel();
	private JLabel title = new JLabel("Contacts");
	private JScrollPane scrollPane;
	private JPanel testJPanel = new JPanel();
	
	public ContactFrame() {
		screen.setLayout(new GridBagLayout());
		
		ArrayList<Contact> contacts = readContacts();
		
		for(int i=0; i<contacts.size();i++) {
			listModel.addElement(contacts.get(i).getFirstname()+" "+contacts.get(i).getLastname());
		}
		
		list=  new JList(listModel);
		
		plusButton.setPreferredSize(new Dimension(40,40));
		plusButton.addActionListener(new PlusListener());
		
		topJPanel.setLayout(new FlowLayout());
		title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
		topJPanel.add(title);
		topJPanel.add(plusButton);
		topJPanel.setPreferredSize(new Dimension(LARGEUR,80));
		
		par.gridx = 0;
		par.gridy = 0;
		
		screen.add(topJPanel,par);
		
		list.setPreferredSize(new Dimension(LARGEUR,500));
		list.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		
		list.addListSelectionListener(new ListSelectionListener()
	    {

	      public void valueChanged(ListSelectionEvent evt) 
	      {
	        // To avoid double value selected
	        if (evt.getValueIsAdjusting())
	                  return;
	        System.out.println("Selected: " + list.getSelectedValue());
	        JFrame frame = new InfosFrame((String)list.getSelectedValue());
			frame.setVisible(true);
			dispose();
	      }

	    });

		scrollPane= new JScrollPane(list);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		scrollPane.setPreferredSize(new Dimension(LARGEUR,1000));
		
		testJPanel.setLayout(new FlowLayout());
		testJPanel.setPreferredSize(new Dimension(LARGEUR,600));
		testJPanel.add(scrollPane);
		
		par.gridx = 0;
		par.gridy = 1;
		
		screen.add(testJPanel,par);
		
		par.gridx = 0;
		par.gridy = 1;
		
		screen.setPreferredSize(new Dimension(LARGEUR,700));
		screen.setBackground(Color.WHITE);
		add(screen,par);
	}
	
	class PlusListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new AddContactFrame();
			frame.setVisible(true);
			dispose();
		}
	}
}
