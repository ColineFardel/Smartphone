import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BaseFrame extends JFrame{

	private JPanel topPanel= new JPanel();
	private JPanel botPannel= new JPanel();
	
	private JButton homeButton = new JButton(new ImageIcon("C:\\Users\\colin\\Dropbox\\Mes Dossiers\\Semestre 2\\ProjetPOO\\index.png"));
	
	private final int LARGEUR = 480;
	
	private GridBagConstraints par = new GridBagConstraints();
	
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
	
}
