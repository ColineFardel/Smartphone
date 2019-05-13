import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.*;

public class BaseFrame extends JFrame{

	private JPanel topPanel= new JPanel();
	private JPanel botPannel= new JPanel();
	
	private JButton homeButton = new JButton(new ImageIcon("C:\\Users\\colin\\Dropbox\\Mes Dossiers\\Semestre 2\\ProjetPOO\\Smartphone\\src\\home-icon-silhouette.png"));
	//<div>Icons made by <a href="https://www.freepik.com/" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" 			    title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" 			    title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
	private JButton closeButton = new JButton(new ImageIcon("C:\\Users\\colin\\Dropbox\\Mes Dossiers\\Semestre 2\\ProjetPOO\\Smartphone\\src\\power2.png"));
	//<div>Icons made by <a href="https://www.flaticon.com/<?=_('authors').'/'?>freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" 		    title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" 		    title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
	
	protected final int LARGEUR = 480;
	
	protected GridBagConstraints par = new GridBagConstraints();
	
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
		botPannel.add(homeButton);
		
		closeButton.addActionListener(new CloseListener());
		closeButton.setPreferredSize(new Dimension(40,40));
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
				System.out.println("Prout");
			}
		return contacts;
	}
}
