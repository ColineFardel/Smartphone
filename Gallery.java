import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Gallery extends JFrame{
private JLabel labelTitre = new JLabel("Mes photos");
private JLabel labelPhoto = new JLabel();

public Gallery() {
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	add(labelTitre, BorderLayout.NORTH);
	add(labelPhoto, BorderLayout.CENTER);
	
	pack();
	
}
	

}
