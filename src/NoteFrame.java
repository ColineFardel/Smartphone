import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
/*
 * Project POO Smartphone
 * Author: Coline Fardel
 * Date creation: 03.06.2019
 * Date last modification: 03.06.2019
 */
public class NoteFrame extends BaseFrame{
	private JPanel screen = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JPanel topPanel = new JPanel();
	
	private JLabel title = new JLabel("Notes");
	
	private JTextArea note = new JTextArea();
	
	private JButton save = new JButton("Sauvegarder");
	private JButton delete = new JButton("Supprimer");
	private JButton cancel = new JButton("Annuler");
	
	private String filePath = "";
	private String fileName = "Notes//Note";
	private String file="";
	private File f;
	
	
	public NoteFrame(String fileContent) {
		screen.setLayout(new GridBagLayout());
		
		for(int i=1;i<100;i++) {
			filePath=fileName + Integer.toString(i)+".txt";
			f=new File(filePath);
			String sortie ="";
			
			if(f.exists()) {
				sortie=readTxt(filePath);
				System.out.println("sortie "+sortie);
				System.out.println("filecontent"+fileContent);
				if(sortie.equals(fileContent)) {
				System.out.print("Dans le if");
				file=filePath;
				i=100;
				}
			}
		}
		
		title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
		title.setForeground(Color.WHITE);
		topPanel.setBackground(Color.GREEN);
		topPanel.setPreferredSize(new Dimension(LARGEUR,100));
		topPanel.add(title);
		
		par.gridx = 0;
		par.gridy = 0;
		
		screen.add(topPanel,par);
		
		f=new File(file);
		note.setText(fileContent);
		note.setPreferredSize(new Dimension(LARGEUR, 500));
		note.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		note.setLineWrap(true);
		
		par.gridx = 0;
		par.gridy = 1;
		
		screen.add(note,par);
		
		save.addActionListener(new ButtonListener());
		delete.addActionListener(new ButtonListener());
		cancel.addActionListener(new ButtonListener());
		
		buttonPanel.add(save);
		buttonPanel.add(delete);
		buttonPanel.add(cancel);
		
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
				JFrame frame = new NotesFrame();
				frame.setVisible(true);
				dispose();
			}
			if(e.getSource()==cancel) {
				JFrame frame = new NotesFrame();
				frame.setVisible(true);
				dispose();
			}
			if(e.getSource()==delete) {
				f.delete();
				JFrame frame = new NotesFrame();
				frame.setVisible(true);
				dispose();
			}
		}
		
	}
}
