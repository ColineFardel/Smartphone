/*
 * Project POO Smartphone
 * Author: Coline Fardel
 * Date creation: 27.05.2019
 * Date last modification: 03.06.2019
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;

public class NotesFrame extends BaseFrame{
	private JLabel title = new JLabel("Notes");
	
	private String fileName = "Note";
	private String filePath;
	
	private FileInputStream fis;
	private File f;
	
	private JPanel topPanel = new JPanel();
	private JPanel screen = new JPanel();
	private JPanel mainJPanel = new JPanel();
	
	private JScrollPane scrollPane;
	
	private JList list;
	private DefaultListModel listModel= new DefaultListModel();
	
	private JButton addButton = new JButton(new ImageIcon("Images//add.png"));
	
	
	public NotesFrame() {
		screen.setLayout(new GridBagLayout());
		
		title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
		
		addButton.addActionListener(new ButtonListener());
		addButton.setOpaque(false);
		addButton.setContentAreaFilled(false);
		addButton.setBorderPainted(false);
		addButton.setPreferredSize(new Dimension(40,40));
		
		topPanel.setLayout(new FlowLayout());
		topPanel.setPreferredSize(new Dimension(LARGEUR,80));
		topPanel.setBackground(Color.WHITE);
		topPanel.add(title);
		topPanel.add(addButton);
		
		par.gridx = 0;
		par.gridy = 0;
		
		screen.add(topPanel,par);
		
		// On crée un tableau de byte pour indiquer le nombre de bytes lus à
        // chaque tour de boucle
        byte[] buf = new byte[8];
		
		for(int i=1;i<100;i++) {
			filePath=fileName + Integer.toString(i)+".txt";
			f=new File("Notes//"+filePath);
			String sortie ="";
			
			if(f.exists()) {
				sortie=readTxt("Notes//"+filePath);
				listModel.addElement(sortie);
			}
		}
		
		list=  new JList(listModel);
		
		list.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		
		//When you click it open the info frame without having to click on another button
		list.addListSelectionListener(new ListSelectionListener()
	    {
	      public void valueChanged(ListSelectionEvent evt) 
	      {
	        // To avoid double value selected
	        if (evt.getValueIsAdjusting())
	                  return;
	        JFrame frame = new NoteFrame((String)list.getSelectedValue());
			frame.setVisible(true);
			dispose();
	      }
	    });

		scrollPane= new JScrollPane(list);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		scrollPane.setPreferredSize(new Dimension(LARGEUR,600));
		
		mainJPanel.setLayout(new FlowLayout());
		mainJPanel.setPreferredSize(new Dimension(LARGEUR,600));
		mainJPanel.setBackground(Color.WHITE);
		mainJPanel.add(scrollPane);
		
		par.gridx = 0;
		par.gridy = 1;
		
		screen.add(mainJPanel,par);
		
		par.gridx = 0;
		par.gridy = 1;
		
		screen.setPreferredSize(new Dimension(LARGEUR,700));
		screen.setBackground(Color.WHITE);
		add(screen,par);
	}
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new AddNoteFrame();
			frame.setVisible(true);
			dispose();
			}
		}
}
