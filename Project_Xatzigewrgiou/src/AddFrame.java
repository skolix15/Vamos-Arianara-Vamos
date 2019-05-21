import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


public class AddFrame extends JFrame{
	
	private JPanel panel;
	private JTextField code;
	private JTextField name;
	private JTextField price;
	private JButton confirm;
	private JLabel title;
	private JMenuBar mb;
	private JMenu orderMenu;
	private JMenu storageMenu;
	private JMenu statisticsMenu;
	private JMenu centralMenu_Menu;
	private JPanel menuPanel;
	private JMenuItem i1,i2,i3,i4,i5,iCentralMenu;
	private JSpinner spinner;
	
	
	public AddFrame () {
		
				//���������� panel
				panel = new JPanel();
				menuPanel = new JPanel();
				
				//���������� ������ ��� �� ��������
			    title = new JLabel("Add Medicine");
			    title.setAlignmentX(Component.LEFT_ALIGNMENT);
				
				//���������� ������ ��� �������� ����� ��� �������
				code = new JTextField("");
				code.setBorder(new TitledBorder("Code"));
				code.setPreferredSize(new Dimension(75,4));
				price = new JTextField("");
				price.setBorder(new TitledBorder("Price"));
				price.setPreferredSize(new Dimension(75,4));
				name = new JTextField("");
				name.setBorder(new TitledBorder("Name"));
				name.setPreferredSize(new Dimension(75,4));
				//quantity = new JTextField("��������");
				
				//���������� ��� ��������
				confirm = new JButton("Confirm");
				ButtonListenerConfirm confirmListener= new ButtonListenerConfirm();
				confirm.addActionListener(confirmListener);
				
				// Prosthiki eikonas kai listener gia aythn
				
				ImageIcon icon = new ImageIcon("hospital1.png");
				JLabel lb = new JLabel(icon);
			
				lb.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) 
				    {	 
				    	setVisible(false);
				        new AddFrame();           
				    }
					
				}	);
				
				
				//���������� ������ �����
				mb = new JMenuBar();
				
				//���������� �����
				centralMenu_Menu = new JMenu("Central Menu");
				orderMenu = new JMenu("Order");
				storageMenu = new JMenu("Storage");
				statisticsMenu = new JMenu("Statistics");
				
				
				//���������� �������� ���� �����
				
				
				
				i1 = new JMenuItem("Prescription");  
			    i2 = new JMenuItem("Supply");  
			    i3 = new JMenuItem("Add");
			    i4 = new JMenuItem("Delete"); 
			    i5 = new JMenuItem("Show Statistics");
			    iCentralMenu = new JMenuItem("Go to Central Menu");
			    
			    // Eisagwgi ActionListener gia ta pedia tou Menu
			    
			    JTablePopupMenuListener menuListener = new JTablePopupMenuListener();
			    i1.addActionListener(menuListener);
			    i2.addActionListener(menuListener);
			    i3.addActionListener(menuListener);
			    i4.addActionListener(menuListener);
			    i5.addActionListener(menuListener);
			    iCentralMenu.addActionListener(menuListener);
			    
			    //JSpinner
			    spinner = new JSpinner();
			    spinner.setPreferredSize(new Dimension(75, 50));
			    spinner.setBorder(new TitledBorder("Quantity"));
			    
				
			    //E������� ��� �����
			    centralMenu_Menu.add(iCentralMenu);
			    orderMenu.add(i1);
			    orderMenu.add(i2);
			    storageMenu.add(i3);
			    storageMenu.add(i4);
			    statisticsMenu.add(i5);
			    
			    //�������� ��� ����� ���� �����
			    mb.add(centralMenu_Menu);
			    mb.add(orderMenu);
			    mb.add(storageMenu);
			    mb.add(statisticsMenu);
			    mb.add(lb);
			    
			    menuPanel.add(mb);
			    panel.add(menuPanel);
			    
			    
			    panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
			    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
			    
				//�������� ��� panel
			    
				panel.add(title);	
				panel.add(code);
				panel.add(name);
				panel.add(price);
				panel.add(spinner);
				panel.add(confirm);
				
				
				
				
				//�������� ��� panel ��� contentpane
				
				this.setContentPane(panel);
				
				
				//���������� ��� ������� ��������������� ��� panel
				
				this.setSize(600,400);
				this.setTitle("/Supply Chain/Pharmacist/Storage/Add");
				this.setVisible(true);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				
				
			}
			
			class JTablePopupMenuListener implements ActionListener {


				public void actionPerformed(ActionEvent e) {
					
					if(e.getActionCommand().equals("Go to Central Menu")){
						
						dispose();
						new SupplyChainMainFrame();
					}
					
					else if(e.getActionCommand().equals("Prescription") ) {
							
						dispose();
						new PrescriptionAndSupplyFrame(true);
						
					}
					
					else if(e.getActionCommand().equals("Supply")){
						
						dispose();
						new PrescriptionAndSupplyFrame(false);
						
					}
					
					else if(e.getActionCommand().equals("Add")) {
						
						dispose();
						new AddFrame();
					}
					
					else if(e.getActionCommand().equals("Delete")) {
						
						dispose();
						new DeleteFrame();
					}
					
					else if(e.getActionCommand().equals("Show Statistics")) {
						
						dispose();
						new StatisticsFrame();
					}
					
				}
					
			 }
			
			class ButtonListenerConfirm implements ActionListener {
				
				public void actionPerformed(ActionEvent e) {
						
					String	codeText = code.getText();
					String	nameText = name.getText();
					String 	priceText = price.getText();
					String 	quantityText = spinner.getValue().toString();
					
					dispose();
					new AddFrame();
					
					double priceDouble = Double.parseDouble(priceText);
					int quantityInt = Integer.parseInt(quantityText);
					
					
					if (Storage.searchMedicine(nameText, codeText) == null)
						Storage.addMedicine(nameText, codeText, priceDouble, quantityInt);	
					else
						JOptionPane.showMessageDialog(null, nameText + " already exists");
						

					
				}
			}

		}
