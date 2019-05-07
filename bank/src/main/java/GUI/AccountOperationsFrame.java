package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import BankClients.Person;
import BankPackage.Bank;

public class AccountOperationsFrame implements Observer {
	
	private JFrame frame=new JFrame("Account Operations");
	private JTabbedPane tabPnl=new JTabbedPane();
	private JPanel addPnl=new JPanel();
	private JPanel deletePnl=new JPanel();
	private JPanel editPnl=new JPanel();
	private JPanel depositPnl=new JPanel();
	private JPanel withdrawPnl=new JPanel();
	private GridBagConstraints c=new GridBagConstraints();
	private JLabel pictureAddLabel,pictureDeleteLabel,pictureDepositLabel,pictureWithdrawLabel,pictureEditLabel;
	private JTable accountsTable,accountsTable1,accountsTable2,personsTable,accountsTable3,accountsTable4,personsTable1;
	private GenericTable gt=new GenericTable();
	private JScrollPane scrollAccountsTable,scrollAccountsTable1,scrollAccountsTable2,scrollPersonsTable,scrollAccountsTable3,scrollAccountsTable4,scrollPersonsTable1;
	
	private JLabel idAddLabel=new JLabel("ID");
	private JLabel sumaAddLabel=new JLabel("Sum");
	private JLabel periodAddLabel=new JLabel("Period");
	
	private JComboBox<String> typeAddCombo=new JComboBox<String>();
	
	private JTextField idAddTxt=new JTextField(20);
	private JTextField sumaAddTxt=new JTextField(20);
	private JTextField periodAddTxt=new JTextField(20);
	
	private JPanel idPnl=new JPanel();
	private JPanel sumaPnl=new JPanel();
	private JPanel typePnl=new JPanel();
	private JPanel periodPnl=new JPanel();
	
	
	private JLabel idEditLabel=new JLabel("Account ID");
	private JLabel idNewHolderLabel=new JLabel("New holder ID");
	private JTextField idEditTxt=new JTextField(20);
	private JTextField idNewHolderTxt=new JTextField(20);
	private JPanel idEditPnl=new JPanel();
	private JPanel idNewHolderEditPnl=new JPanel();
	private JButton buttonEdit=new JButton("Edit");
	
	private JLabel idDeleteLabel=new JLabel("ID");
	private JTextField idDeleteTxt=new JTextField(20);
	private JPanel idDeletePnl=new JPanel();
	
	private JButton buttonAdd=new JButton("Add");
	
	private JButton buttonDelete=new JButton("Delete");
	
	private JLabel idDepositLabel=new JLabel("ID");
	private JLabel sumaDepositLabel=new JLabel("Sum");
	private JTextField idDepositTxt=new JTextField(20);
	private JTextField sumaDepositTxt=new JTextField(20);
	private JPanel idDepositPnl=new JPanel();
	private JPanel sumaDepositPnl=new JPanel();
	private JButton buttonDeposit=new JButton("Deposit");
	
	private JLabel idWithdrawLabel=new JLabel("ID");
	private JLabel sumaWithdrawLabel=new JLabel("Sum");
	private JTextField idWithdrawTxt=new JTextField(20);
	private JTextField sumaWithdrawTxt=new JTextField(20);
	private JPanel idWithdrawPnl=new JPanel();
	private JPanel sumaWithdrawPnl=new JPanel();
	private JButton buttonWithdraw=new JButton("Wtihdraw");
	
	public AccountOperationsFrame() {
		frame.setSize(new Dimension(1400,1000));
		frame.setResizable(false);
		
		
		addPnl.setBackground(new Color(255,204,153));
		deletePnl.setBackground(new Color(255,204,153));
		editPnl.setBackground(new Color(255,204,153));
		depositPnl.setBackground(new Color(255,204,153));
		withdrawPnl.setBackground(new Color(255,204,153));
		
		tabPnl.addTab("Add", addPnl);
		tabPnl.addTab("Edit", editPnl);
		tabPnl.addTab("Delete", deletePnl);		
		tabPnl.addTab("Deposit", depositPnl);
		tabPnl.addTab("Withdraw", withdrawPnl);
		
		
		//add tab
		addPnl.setLayout(new GridBagLayout());
		File input = new File("addaccount.jpg");
        BufferedImage image = null;
		try {
			image = ImageIO.read(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
        BufferedImage resized = resize(image,75,75);
		pictureAddLabel=new JLabel(new ImageIcon(resized));
		c.gridx=1;
		c.gridy=0;
		addPnl.add(pictureAddLabel,c);
		
		c.gridx=2;
		c.gridy=1;
		accountsTable=new JTable(gt.createTable(Interfata.bank.getAccounts()));
		accountsTable.setSize(200, 200);
		scrollAccountsTable=new JScrollPane(accountsTable);
		scrollAccountsTable.setSize(200,200);
		addPnl.add(scrollAccountsTable,c);
		
		c.gridx=0;
		c.gridy=1;
		personsTable=new JTable(gt.createTable(Interfata.bank.getPersons()));
		personsTable.setSize(200, 200);
		scrollPersonsTable=new JScrollPane(personsTable);
		scrollPersonsTable.setSize(200,200);
		addPnl.add(scrollPersonsTable,c);
		
		
		
		idPnl.setLayout(new FlowLayout());
		idPnl.add(idAddLabel);
		idPnl.add(idAddTxt);
		idPnl.setBackground(new Color(255,204,153));
		c.gridx=1;
		c.gridy=2;
		addPnl.add(idPnl,c);
		
		sumaPnl.setLayout(new FlowLayout());
		sumaPnl.add(sumaAddLabel);
		sumaPnl.add(sumaAddTxt);
		sumaPnl.setBackground(new Color(255,204,153));
		c.gridx=1;
		c.gridy=3;
		addPnl.add(sumaPnl,c);
		
		periodPnl.setLayout(new FlowLayout());
		periodPnl.add(periodAddLabel);
		periodPnl.add(periodAddTxt);
		periodPnl.setBackground(new Color(255,204,153));
		c.gridx=1;
		c.gridy=4;
		addPnl.add(periodPnl,c);
		
		typePnl.setLayout(new FlowLayout());
		typeAddCombo.addItem("Savings Account");
		typeAddCombo.addItem("Spending Account");
		typePnl.add(typeAddCombo);
		typePnl.setBackground(new Color(255,204,153));
		c.gridx=1;
		c.gridy=5;
		addPnl.add(typePnl,c);
		
		c.gridx=1;
		c.gridy=6;
		addPnl.add(buttonAdd,c);
		
		idAddTxt.setEditable(false);
		
		personsTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int linie=personsTable.rowAtPoint(e.getPoint());
				int coloana=personsTable.columnAtPoint(e.getPoint());
				if(coloana>=0&&linie>=0) {
					idAddTxt.setText(personsTable.getValueAt(linie, coloana).toString());
				}
			}
		});
		sumaPnl.setVisible(false);
		periodPnl.setVisible(true);
		typeAddCombo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(typeAddCombo.getSelectedItem().toString().equals("Savings Account")) {
					sumaPnl.setVisible(false);
					periodPnl.setVisible(true);
				}
				else {
					sumaPnl.setVisible(true);
					periodPnl.setVisible(false);
				}
			}
			
		});
		buttonAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(!idAddTxt.getText().isEmpty() &&
						(!sumaAddTxt.getText().isEmpty() ||
						!periodAddTxt.getText().isEmpty())) {
					
					Interfata.bank.addAccount(Long.parseLong(idAddTxt.getText()), sumaAddTxt.getText().isEmpty()?0:Double.parseDouble(sumaAddTxt.getText()),typeAddCombo.getSelectedItem().toString(), periodAddTxt.getText().isEmpty()?0:Integer.parseInt(periodAddTxt.getText()));
					accountsTable.setModel(gt.createTable(Interfata.bank.getAccounts()));
					accountsTable1.setModel(gt.createTable(Interfata.bank.getAccounts()));
					accountsTable2.setModel(gt.createTable(Interfata.bank.getAccounts()));
					accountsTable3.setModel(gt.createTable(Interfata.bank.getAccounts()));
					accountsTable4.setModel(gt.createTable(Interfata.bank.getAccounts()));
					idAddTxt.setText("");
					sumaAddTxt.setText("");
					periodAddTxt.setText("");
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Fields must not be empty ", null, JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		//edit tab
		editPnl.setLayout(new GridBagLayout());
		File input4 = new File("editaccount.jpg");
        BufferedImage image4 = null;
		try {
			image4 = ImageIO.read(input4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
        BufferedImage resized4 = resize(image4,75,120);
		pictureEditLabel=new JLabel(new ImageIcon(resized4));
		c.gridx=1;
		c.gridy=0;
		editPnl.add(pictureEditLabel,c);
		
		c.gridx=2;
		c.gridy=1;
		accountsTable4=new JTable(gt.createTable(Interfata.bank.getAccounts()));
		accountsTable4.setSize(200, 200);
		scrollAccountsTable4=new JScrollPane(accountsTable4);
		scrollAccountsTable4.setSize(200,200);
		editPnl.add(scrollAccountsTable4,c);
		
		c.gridx=0;
		c.gridy=1;
		personsTable1=new JTable(gt.createTable(Interfata.bank.getPersons()));
		personsTable1.setSize(200, 200);
		scrollPersonsTable1=new JScrollPane(personsTable1);
		scrollPersonsTable1.setSize(200,200);
		editPnl.add(scrollPersonsTable1,c);
		
		
		
		idEditPnl.setLayout(new FlowLayout());
		idEditPnl.add(idEditLabel);
		idEditPnl.add(idEditTxt);
		idEditPnl.setBackground(new Color(255,204,153));
		c.gridx=1;
		c.gridy=2;
		editPnl.add(idEditPnl,c);
		
		idNewHolderEditPnl.setLayout(new FlowLayout());
		idNewHolderEditPnl.add(idNewHolderLabel);
		idNewHolderEditPnl.add(idNewHolderTxt);
		idNewHolderEditPnl.setBackground(new Color(255,204,153));
		c.gridx=1;
		c.gridy=3;
		editPnl.add(idNewHolderEditPnl,c);
		
		c.gridx=1;
		c.gridy=4;
		editPnl.add(buttonEdit,c);
		
		idEditTxt.setEditable(false);
		idNewHolderTxt.setEditable(false);
		
		personsTable1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int linie=personsTable1.rowAtPoint(e.getPoint());
				int coloana=personsTable1.columnAtPoint(e.getPoint());
				if(coloana>=0&&linie>=0) {
					idNewHolderTxt.setText(personsTable1.getValueAt(linie, coloana).toString());
				}
			}
		});
		
		accountsTable4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int linie=accountsTable4.rowAtPoint(e.getPoint());
				int coloana=accountsTable4.columnAtPoint(e.getPoint());
				if(coloana>=0&&linie>=0) {
					idEditTxt.setText(accountsTable4.getValueAt(linie, coloana).toString());
				}
			}
		});
		
		buttonEdit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(!idEditTxt.getText().isEmpty() &&
						(!idNewHolderTxt.getText().isEmpty())) {
					
					Interfata.bank.editAccount(Integer.parseInt(idEditTxt.getText()),Long.parseLong(idNewHolderTxt.getText()));
					accountsTable.setModel(gt.createTable(Interfata.bank.getAccounts()));
					accountsTable1.setModel(gt.createTable(Interfata.bank.getAccounts()));
					accountsTable2.setModel(gt.createTable(Interfata.bank.getAccounts()));
					accountsTable3.setModel(gt.createTable(Interfata.bank.getAccounts()));
					accountsTable4.setModel(gt.createTable(Interfata.bank.getAccounts()));
					idEditTxt.setText("");
					idNewHolderTxt.setText("");					
				}
				else {
					JOptionPane.showMessageDialog(null, "Fields must not be empty ", null, JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		//delete tab
		deletePnl.setLayout(new GridBagLayout());
		File input2 = new File("deleteaccount.jpg");
        BufferedImage image2 = null;
		try {
			image2 = ImageIO.read(input2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
        BufferedImage resized2 = resize(image2,75,75);
		pictureDeleteLabel=new JLabel(new ImageIcon(resized2));
		c.gridx=1;
		c.gridy=0;
		deletePnl.add(pictureDeleteLabel);
		
		c.gridx=0;
		c.gridy=1;
		accountsTable2=new JTable(gt.createTable(Interfata.bank.getAccounts()));
		accountsTable2.setSize(200, 200);
		scrollAccountsTable2=new JScrollPane(accountsTable2);
		scrollAccountsTable2.setSize(200,200);
		deletePnl.add(scrollAccountsTable2,c);
		
		idDeletePnl.setLayout(new FlowLayout());
		idDeletePnl.add(idDeleteLabel);
		idDeletePnl.add(idDeleteTxt);
		idDeletePnl.setBackground(new Color(255,204,153));
		c.gridx=0;
		c.gridy=2;
		deletePnl.add(idDeletePnl,c);
		
		c.gridx=0;
		c.gridy=3;
		deletePnl.add(buttonDelete,c);
		
		idDeleteTxt.setEditable(false);
		
		accountsTable2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int linie=accountsTable2.rowAtPoint(e.getPoint());
				int coloana=accountsTable2.columnAtPoint(e.getPoint());
				if(coloana>=0&&linie>=0) {
					idDeleteTxt.setText(accountsTable2.getValueAt(linie, coloana).toString());
				}
			}
		});
		
		buttonDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Interfata.bank.removeAccount(Integer.parseInt(idDeleteTxt.getText()));
				accountsTable.setModel(gt.createTable(Interfata.bank.getAccounts()));
				accountsTable1.setModel(gt.createTable(Interfata.bank.getAccounts()));
				accountsTable2.setModel(gt.createTable(Interfata.bank.getAccounts()));
				accountsTable3.setModel(gt.createTable(Interfata.bank.getAccounts()));
				accountsTable4.setModel(gt.createTable(Interfata.bank.getAccounts()));
				idDeleteTxt.setText("");

			}
			
		});
		
		
		//deposit tab
		depositPnl.setLayout(new GridBagLayout());
		File input1 = new File("deposit.jpg");
        BufferedImage image1 = null;
		try {
			image1 = ImageIO.read(input1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot read");
			e.printStackTrace();
		}		
        BufferedImage resized1 = resize(image1,75,75);
		pictureDepositLabel=new JLabel(new ImageIcon(resized1));
		c.gridx=0;
		c.gridy=0;
		depositPnl.add(pictureDepositLabel,c);
		
		c.gridx=0;
		c.gridy=1;
		accountsTable1=new JTable(gt.createTable(Interfata.bank.getAccounts()));
		accountsTable1.setSize(200, 200);
		scrollAccountsTable1=new JScrollPane(accountsTable1);
		scrollAccountsTable1.setSize(200,200);
		depositPnl.add(scrollAccountsTable1,c);
		
		idDepositPnl.setLayout(new FlowLayout());
		idDepositPnl.add(idDepositLabel);
		idDepositPnl.add(idDepositTxt);
		idDepositPnl.setBackground(new Color(255,204,153));
		c.gridx=0;
		c.gridy=2;
		depositPnl.add(idDepositPnl,c);
		
		sumaDepositPnl.setLayout(new FlowLayout());
		sumaDepositPnl.add(sumaDepositLabel);
		sumaDepositPnl.add(sumaDepositTxt);
		sumaDepositPnl.setBackground(new Color(255,204,153));
		c.gridx=0;
		c.gridy=3;
		depositPnl.add(sumaDepositPnl,c);
		
		
		c.gridx=0;
		c.gridy=4;
		depositPnl.add(buttonDeposit,c);
		
		idDepositTxt.setEditable(false);
		
		accountsTable1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int linie=accountsTable1.rowAtPoint(e.getPoint());
				int coloana=accountsTable1.columnAtPoint(e.getPoint());
				if(coloana>=0&&linie>=0) {
					idDepositTxt.setText(accountsTable1.getValueAt(linie, coloana).toString());
				}
			}
		});
		
		buttonDeposit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!idDepositTxt.getText().isEmpty() &&
						!sumaDepositTxt.getText().isEmpty() ) {
					
					Interfata.bank.addMoneyToAccount(Integer.parseInt(idDepositTxt.getText()), Double.parseDouble(sumaDepositTxt.getText()));
					accountsTable.setModel(gt.createTable(Interfata.bank.getAccounts()));
					accountsTable1.setModel(gt.createTable(Interfata.bank.getAccounts()));
					accountsTable2.setModel(gt.createTable(Interfata.bank.getAccounts()));
					accountsTable3.setModel(gt.createTable(Interfata.bank.getAccounts()));
					accountsTable4.setModel(gt.createTable(Interfata.bank.getAccounts()));
					idDepositTxt.setText("");
					sumaDepositTxt.setText("");
					JOptionPane.showMessageDialog(null,Interfata.bank.getS(),null,JOptionPane.INFORMATION_MESSAGE);
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Fields must not be empty ", null, JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		//withdraw tab
		withdrawPnl.setLayout(new GridBagLayout());
		File input3 = new File("withdraw.jpg");
        BufferedImage image3 = null;
		try {
			image3 = ImageIO.read(input3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot read");
			e.printStackTrace();
		}		
        BufferedImage resized3 = resize(image3,75,75);
		pictureWithdrawLabel=new JLabel(new ImageIcon(resized3));
		c.gridx=0;
		c.gridy=0;
		withdrawPnl.add(pictureWithdrawLabel,c);
		
		c.gridx=0;
		c.gridy=1;
		accountsTable3=new JTable(gt.createTable(Interfata.bank.getAccounts()));
		accountsTable3.setSize(200, 200);
		scrollAccountsTable3=new JScrollPane(accountsTable3);
		scrollAccountsTable3.setSize(200,200);
		withdrawPnl.add(scrollAccountsTable3,c);
		
		idWithdrawPnl.setLayout(new FlowLayout());
		idWithdrawPnl.add(idWithdrawLabel);
		idWithdrawPnl.add(idWithdrawTxt);
		idWithdrawPnl.setBackground(new Color(255,204,153));
		c.gridx=0;
		c.gridy=2;
		withdrawPnl.add(idWithdrawPnl,c);
		
		sumaWithdrawPnl.setLayout(new FlowLayout());
		sumaWithdrawPnl.add(sumaWithdrawLabel);
		sumaWithdrawPnl.add(sumaWithdrawTxt);
		sumaWithdrawPnl.setBackground(new Color(255,204,153));
		c.gridx=0;
		c.gridy=3;
		withdrawPnl.add(sumaWithdrawPnl,c);
		
		
		c.gridx=0;
		c.gridy=4;
		withdrawPnl.add(buttonWithdraw,c);
		
		idWithdrawTxt.setEditable(false);
		
		accountsTable3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int linie=accountsTable3.rowAtPoint(e.getPoint());
				int coloana=accountsTable3.columnAtPoint(e.getPoint());
				if(coloana>=0&&linie>=0) {
					idWithdrawTxt.setText(accountsTable3.getValueAt(linie, coloana).toString());
				}
			}
		});
		
		buttonWithdraw.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!idWithdrawTxt.getText().isEmpty() &&
						!sumaWithdrawTxt.getText().isEmpty() ) {
					
					Interfata.bank.withdrawMoneyFromAccount(Integer.parseInt(idWithdrawTxt.getText()), Double.parseDouble(sumaWithdrawTxt.getText()));
					accountsTable.setModel(gt.createTable(Interfata.bank.getAccounts()));
					accountsTable1.setModel(gt.createTable(Interfata.bank.getAccounts()));
					accountsTable2.setModel(gt.createTable(Interfata.bank.getAccounts()));
					accountsTable3.setModel(gt.createTable(Interfata.bank.getAccounts()));
					accountsTable4.setModel(gt.createTable(Interfata.bank.getAccounts()));
					idWithdrawTxt.setText("");
					sumaWithdrawTxt.setText("");
					JOptionPane.showMessageDialog(null,Interfata.bank.getS(),null,JOptionPane.INFORMATION_MESSAGE);
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Fields must not be empty ", null, JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		frame.add(tabPnl);
		frame.setVisible(true);
	}
	public BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		frame.revalidate();
	}

}
