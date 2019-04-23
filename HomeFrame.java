import java.awt.*;
import javax.swing.*;

public class HomeFrame extends JFrame{
	private JPanel trucEnHaut = new JPanel();
	private JPanel trucAuMilieu = new JPanel();
	private JPanel trucEnBas = new JPanel();
	private JLabel test1 = new JLabel("test1");
	private JLabel test2 = new JLabel("test2");
	private JLabel test3 = new JLabel("test3");
	private final int LARGEUR = 450;
	
	private GridBagConstraints prout = new GridBagConstraints();
	
	public HomeFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		
		trucEnHaut.add(test1);
		trucAuMilieu.add(test2);
		trucEnBas.add(test3);
		
		prout.gridx = 0;
		prout.gridy = 0;
		trucEnHaut.setPreferredSize(new Dimension(LARGEUR,50));
		trucEnHaut.setOpaque(true);
		trucEnHaut.setBackground(Color.BLUE);
		add(trucEnHaut,prout);
		
		prout.gridx = 0;
		prout.gridy = 2;
		trucAuMilieu.setPreferredSize(new Dimension(LARGEUR,600));
		trucAuMilieu.setOpaque(true);
		trucAuMilieu.setBackground(Color.ORANGE);
		add(trucAuMilieu,prout);
		
		prout.gridx = 0;
		prout.gridy = 3;
		trucEnBas.setPreferredSize(new Dimension(LARGEUR,50));
		trucEnBas.setOpaque(true);
		trucEnBas.setBackground(Color.BLACK);
		add(trucEnBas,prout);
		
		pack();
	}
}
