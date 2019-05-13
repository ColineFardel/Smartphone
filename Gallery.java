import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class Gallery extends BaseFrame{


protected JLabel galleryLabel = new JLabel("Mes photos");

protected JButton addPictureButton = new JButton(new ImageIcon("C:\\Users\\ismor\\workspace\\Smartphone\\src\\images\\icons"));

protected JScrollPane scrollBar;

protected JPanel screen = new JPanel();

protected JPanel galleryPanel = new JPanel();

/**
 * Construction de la galerie
 */

public Gallery(){
	
	par.gridx = 0;
    par.gridy = 1;

    screen.setPreferredSize(new Dimension(LARGEUR,700));
    screen.setBackground(Color.RED);
    
 
 
    

    screen.add(addPictureButton,BorderLayout.NORTH);
 

    add(screen,par);
      

	}
}
