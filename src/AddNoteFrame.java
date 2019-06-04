import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
/*
 * Project POO Smartphone
 * Author: Coline Fardel
 * Date creation: 28.05.2019
 * Date last modification: 03.06.2019
 */
public class AddNoteFrame extends BaseFrame{
	private String filename = "Note";
	
	private JLabel title = new JLabel("Ajouter une note");
	
	private JPanel screen = new JPanel();
	private JPanel topPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	
	private JTextArea noTextArea = new JTextArea();
	
	private JButton cancel = new JButton("Annuler");
	private JButton save = new JButton("Sauvegarder");
	
	private String fileName = "Note";
	private String filePath;
	
	private FileInputStream fis;
	private FileOutputStream fos;
	private File f;
	private BufferedWriter writer;
	private PrintWriter printer;
	
	public AddNoteFrame() {
		screen.setLayout(new GridBagLayout());
		
		title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
		title.setForeground(Color.WHITE);
		topPanel.setBackground(Color.GREEN);
		topPanel.setPreferredSize(new Dimension(LARGEUR,100));
		topPanel.add(title);
		
		par.gridx = 0;
		par.gridy = 0;
		
		screen.add(topPanel,par);
		
		noTextArea.setPreferredSize(new Dimension(LARGEUR,500));
		noTextArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		noTextArea.setLineWrap(true);
		
		par.gridx = 0;
		par.gridy = 1;
		
		screen.add(noTextArea,par);
		
		save.addActionListener(new ButtonListener());
		cancel.addActionListener(new ButtonListener());
		buttonPanel.add(save);
		buttonPanel.add(cancel);
		buttonPanel.setPreferredSize(new Dimension(LARGEUR,100));
		buttonPanel.setBackground(Color.WHITE);
		
		par.gridx = 0;
		par.gridy = 2;
		
		screen.add(buttonPanel,par);
		
		par.gridx = 0;
		par.gridy = 1;
		
		screen.setPreferredSize(new Dimension(LARGEUR,700));
		screen.setBackground(Color.WHITE);
		add(screen,par);
	}
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==save) {
				String fileContent = noTextArea.getText();
				
				for(int i=1;i<100;i++) {
				     
					filePath=fileName + Integer.toString(i)+".txt";
					f=new File("Notes//"+filePath);
					if(f.exists()==false) {
						try {
							printer = new PrintWriter(filePath, "UTF-8");
						} catch (Exception e2) {
							System.out.print("Une erreur s'est produite lors de la création du fichier texte");
						}
						printer.write(fileContent);
						printer.close();
						
						try {
							writer = new BufferedWriter(new FileWriter("Notes//"+filePath));
							writer.write(fileContent);
						    writer.close();
						} catch (Exception e2) {
							System.out.print("Une erreur s'est produite lors de l'écriture du fichier texte");
						}
						JFrame frame = new NotesFrame();
						frame.setVisible(true);
						dispose();
						return;
					}
				}
			}
			if(e.getSource()==cancel) {
				JFrame frame = new NotesFrame();
				frame.setVisible(true);
				dispose();
			}
		}
		
	}
}
