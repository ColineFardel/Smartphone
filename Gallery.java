
public class Gallery extends JFrame{
private JLabel labelTitre = new JLabel("Mes photos");
private JLabel labelPhoto = new JLabel();

public Gallery() {
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	add(labelTitre, GridLayout.NORTH);
	add(labelPhoto, GridLayout.CENTER);
	
	pack();
	
}
	

}
