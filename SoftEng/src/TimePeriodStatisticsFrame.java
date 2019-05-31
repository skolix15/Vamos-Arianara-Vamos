import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class TimePeriodStatisticsFrame extends JFrame{
	
	private JPanel panel;
	private JMenuBar mb;
	private JMenu orderMenu;
	private JMenu storageMenu;
	private JMenu statisticsMenu;
	private JMenu centralMenu_Menu;
	private JPanel menuPanel;
	private JMenuItem i1,i2,i3,i4,i5,iCentralMenu;

	private JButton supplySatisticsButton;
	private JButton prescriptionStatisticsButton;
	
	private JPanel buttonsPanel;
	private JPanel textFieldsPanel;
	
	private JTextField firstDateTextField;
	private JTextField secondDateTextField;

	JFrame frame = this;
	private db conn;

	public TimePeriodStatisticsFrame(db connection) {
		
		conn= connection;
		
		/*// Testing 
		
		ArrayList<Integer> id = new ArrayList<Integer>();
		ArrayList<Integer> quantities = new ArrayList<Integer>();
		
		conn.getInfoFromOrderDataBaseForSpecificDates(true, "31/05/2019", "31/05/2019", id, quantities);
		
		for(int i=0;i<id.size();i++) {
			
			System.out.println(id.get(i) + " | " + quantities.get(i));
		}
		*/
		
			
		// Dimiourgia text field
		
		firstDateTextField = new JTextField();
		firstDateTextField.setBorder(new TitledBorder("First Date"));
		firstDateTextField.setPreferredSize(new Dimension(200,54));
		
		secondDateTextField = new JTextField();
		secondDateTextField.setBorder(new TitledBorder("Second Date"));
		secondDateTextField.setPreferredSize(new Dimension(200,54));		
		
		// Dimiourgia buttons
		
		supplySatisticsButton = new JButton("Supply Time Period Statistics");
		prescriptionStatisticsButton = new JButton("Prescription Time Period Statistics");
		
		
		// Dimiourgia baras menu
		
		mb = new JMenuBar();
		
		// Dimiourgia twn triwn menu
		
		orderMenu = new JMenu("Order");
		storageMenu = new JMenu("Storage");
		statisticsMenu = new JMenu("Statistics");
		centralMenu_Menu = new JMenu("Central Menu");
		
		// Dimiourgia epilogwn-pediwn gia kathe lista ( Paraggelia, Apothiki, Statistika )
		
		i1 = new JMenuItem("Prescription");  
	    i2 = new JMenuItem("Supply");  
	    i3 = new JMenuItem("Add");  
	    i4 = new JMenuItem("Delete"); 
	    i5 = new JMenuItem("Show Statistics");
	    iCentralMenu = new JMenuItem("Go to Central Menu");
	    
	    // Eisagwgi ActionListener gia ta pedia tou Menu
	    
	    JTablePopupMenuListener listener = new JTablePopupMenuListener();
	    i1.addActionListener(listener);
	    i2.addActionListener(listener);
	    i3.addActionListener(listener);
	    i4.addActionListener(listener);
	    i5.addActionListener(listener);
	    iCentralMenu.addActionListener(listener);
	    
	    // Prosthiki eikonas kai listener gia aythn
		
	 		ImageIcon icon = new ImageIcon("hospital1.png");
	 		JLabel lb = new JLabel(icon);
	 	
	 		lb.addMouseListener(new MouseAdapter() {
	 			public void mouseClicked(MouseEvent e) 
	 		    {	 
	 				dispose();
	 				new GlobalHomeFrame(conn);              
			     
	 		    }
	 			
	 		}	);
	    
	    // Eisagwgi twn pediwn sto antistoixo menu
	    
	    centralMenu_Menu.add(iCentralMenu);
	    
	    orderMenu.add(i1);
	    orderMenu.add(i2);
	    
	    storageMenu.add(i3);
	    storageMenu.add(i4);
	    
	    statisticsMenu.add(i5);
	    
	    
	    // Eisagwgi twn menu sth bara menu
	    
	    mb.add(centralMenu_Menu);
	    mb.add(orderMenu);
	    mb.add(storageMenu);
	    mb.add(statisticsMenu);
	    mb.add(lb);
	    
	    // Dimiourgia Panel
	    
	    menuPanel = new JPanel();
	    panel = new JPanel();
	    buttonsPanel = new JPanel();
	    textFieldsPanel = new JPanel();
	    
	    // Prosthiki olwn twn menu sto antistoixo panel 
	    
	    menuPanel.add(mb);
	    menuPanel.setAlignmentX(Component.TOP_ALIGNMENT);
	    
	    // Prosthiki olwn twn buttons sto antistoixo panel
	    
	    buttonsPanel.add(supplySatisticsButton);
	    buttonsPanel.add(prescriptionStatisticsButton);
	    
	    buttonsPanel.setAlignmentX(Component.BOTTOM_ALIGNMENT);
	    
	    // Prosthiki owln twn textFields sto antistoixo panel
	    
	    textFieldsPanel.add(firstDateTextField);
	    textFieldsPanel.add(secondDateTextField);
	    
	    textFieldsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    // Prosthiki twn parapanw panel sto teliko panel 
	    
	    panel.add(menuPanel);
	    
	    panel.add(textFieldsPanel);
	    
	    panel.add(buttonsPanel);
		
		
		this.setContentPane(panel);
		
		this.setVisible(true);;
		this.setSize(600,400);
		this.setTitle("/Supply Chain/Pharmacist/Statistics/Show Statistics/Time Period Statistics");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class JTablePopupMenuListener implements ActionListener {


		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("Go to Central Menu")){
				
				dispose();
				new SupplyChainMainFrame(conn);
			}
			
			else if(e.getActionCommand().equals("Prescription") ) {
					
				dispose();
				new PrescriptionAndSupplyFrame(true,conn);
				
			}
			
			else if(e.getActionCommand().equals("Supply")){
				
				dispose();
				new PrescriptionAndSupplyFrame(false,conn);
				
			}
			
			else if(e.getActionCommand().equals("Add")) {
				
				dispose();
				new AddFrame(conn);
			}
			
			else if(e.getActionCommand().equals("Delete")) {
				
				dispose();
				new DeleteFrame(conn);
			}
			
			else if(e.getActionCommand().equals("Show Statistics")) {
				
				dispose();
				new StatisticsFrame(conn);
			}
			
		}
			
	 }

}