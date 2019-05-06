import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ContactFrame extends BaseFrame{
	
	private int nb=0;
	private JButton contacts [] = new JButton[nb];
	private JButton plusButton = new JButton(new ImageIcon("C:\\Users\\colin\\Dropbox\\Mes Dossiers\\Semestre 2\\ProjetPOO\\add.png"));
	//<div>Icons made by <a href="https://www.flaticon.com/authors/smashicons" title="Smashicons">Smashicons</a> from <a href="https://www.flaticon.com/" 			    title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/" 			    title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
	
	private JPanel screen = new JPanel();
	private JScrollPane scrollPane= new JScrollPane();
	
	public ContactFrame() {
		setLayout(new FlowLayout());
		
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		scrollPane.setLayout(new GridLayout(nb, 1));
		
		for(int i = 0;i<contacts.length;i++) {
			scrollPane.add(contacts[i]);
		}
		
		
		add(scrollPane);
	}
	class AddContactFrame extends BaseFrame{
		
		public AddContactFrame() {
			
		}
		
	}
	class PlusListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
}