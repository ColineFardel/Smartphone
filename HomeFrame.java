import java.awt.*;
import javax.swing.*;

public class HomeFrame extends JFrame{
	private JPanel trucEnHaut = new JPanel();
	private JPanel trucAuMilieu = new JPanel();
	private JPanel trucEnBas = new JPanel();
	public HomeFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		add(trucEnHaut);
		add(trucAuMilieu);
		add(trucEnBas);
	}
}
