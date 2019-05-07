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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import BankPackage.Bank;

public class PersonOperationsFrame extends Observable{
	
	private JTabbedPane tabPnl=new JTabbedPane();
	private JFrame frame=new JFrame("Person Operations");
	private JPanel addPnl=new JPanel();
	private JPanel editPnl=new JPanel();
	private JPanel deletePnl=new JPanel();
	private GridBagConstraints c=new GridBagConstraints();
	private JLabel pictureAddLabel,pictureEditLabel,pictureDeleteLabel;
	private JTable personsTable,personsTable1,personsTable2;
	private GenericTable gt=new GenericTable();
	private JScrollPane scrollPersonsTable,scrollPersonsTable1,scrollPersonsTable2;
	
	private JLabel numeAddLabel=new JLabel("Last Name");
	private JLabel prenumeAddLabel=new JLabel("First Name");
	private JLabel dataAddLabel=new JLabel("Date of birth");
	private JLabel personalNumberAddLabel=new JLabel("Personal Number");
	
	private JTextField numeAddTxt=new JTextField(20);
	private JTextField prenumeAddTxt=new JTextField(20);
	private JTextField dataAddTxt=new JTextField(20);
	private JTextField personalNumberAddTxt=new JTextField(20);
	
	private JPanel namePnl=new JPanel();
	private JPanel prenumePnl=new JPanel();
	private JPanel dataPnl=new JPanel();
	private JPanel numberPnl=new JPanel();
	
	private JButton buttonAdd=new JButton("Add");
	
	private JLabel numeEditLabel=new JLabel("Last Name");
	private JLabel prenumeEditLabel=new JLabel("First Name");
	private JLabel idEditLabel=new JLabel("ID");
	
	private JTextField numeEditTxt=new JTextField(20);
	private JTextField prenumeEditTxt=new JTextField(20);
	private JTextField idEditTxt=new JTextField(20);
	
	private JPanel numeEditPnl=new JPanel();
	private JPanel prenumeEditPnl=new JPanel();
	private JPanel idEditPnl=new JPanel();
	
	private JButton buttonEdit=new JButton("Edit");
	
	private JLabel idDeleteLabel=new JLabel("ID");
	private JTextField idDeleteTxt=new JTextField(20);
	private JPanel idDeletePnl=new JPanel();
	private JButton buttonDelete=new JButton("Delete");
	
	
	
	
	

	
	//private JPanel addPnl=new JPanel();
	
	public PersonOperationsFrame() {
		
		//bank.writeData();
		frame.setSize(new Dimension(1000,1000));
		frame.setResizable(false);
		
		
		addPnl.setBackground(new Color(230,230,250));
		editPnl.setBackground(new Color(230,230,250));
		deletePnl.setBackground(new Color(230,230,250));
		
		tabPnl.addTab("Add", addPnl);
		tabPnl.addTab("Edit", editPnl);
		tabPnl.addTab("Delete", deletePnl);
		
		//tabPnl.setBackground(new Color(147,112,219));
		
		//add tab
		addPnl.setLayout(new GridBagLayout());
		File input = new File("addperson.jpg");
        BufferedImage image = null;
		try {
			image = ImageIO.read(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
        BufferedImage resized = resize(image,75,75);
		pictureAddLabel=new JLabel(new ImageIcon(resized));
		c.gridx=0;
		c.gridy=0;
		addPnl.add(pictureAddLabel,c);
		
		c.gridx=0;
		c.gridy=1;
		personsTable=new JTable(gt.createTable(Interfata.bank.getPersons()));
		personsTable.setSize(200, 200);
		scrollPersonsTable=new JScrollPane(personsTable);
		scrollPersonsTable.setSize(200,200);
		addPnl.add(scrollPersonsTable,c);
		
		namePnl.setLayout(new FlowLayout());
		namePnl.add(numeAddLabel);
		namePnl.add(numeAddTxt);
		namePnl.setBackground(new Color(230,230,250));
		c.gridx=0;
		c.gridy=2;
		addPnl.add(namePnl,c);
		
		prenumePnl.setLayout(new FlowLayout());
		prenumePnl.add(prenumeAddLabel);
		prenumePnl.add(prenumeAddTxt);
		prenumePnl.setBackground(new Color(230,230,250));
		c.gridx=0;
		c.gridy=3;
		addPnl.add(prenumePnl,c);
		
		dataPnl.setLayout(new FlowLayout());
		dataPnl.add(dataAddLabel);
		dataPnl.add(dataAddTxt);
		dataPnl.setBackground(new Color(230,230,250));
		c.gridx=0;
		c.gridy=4;
		addPnl.add(dataPnl,c);
		
		numberPnl.setLayout(new FlowLayout());
		numberPnl.add(personalNumberAddLabel);
		numberPnl.add(personalNumberAddTxt);
		numberPnl.setBackground(new Color(230,230,250));
		c.gridx=0;
		c.gridy=5;
		addPnl.add(numberPnl,c);
		
		c.gridx=0;
		c.gridy=6;
		addPnl.add(buttonAdd,c);
		
		buttonAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!numeAddTxt.getText().isEmpty() &&
						!prenumeAddTxt.getText().isEmpty() &&
						!dataAddTxt.getText().isEmpty() &&
						!personalNumberAddTxt.getText().isEmpty()) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
					Interfata.bank.addPerson(numeAddTxt.getText(), prenumeAddTxt.getText(), LocalDate.parse(dataAddTxt.getText(), formatter), Long.parseLong(personalNumberAddTxt.getText()));
					personsTable.setModel(gt.createTable(Interfata.bank.getPersons()));
					personsTable1.setModel(gt.createTable(Interfata.bank.getPersons()));
					personsTable2.setModel(gt.createTable(Interfata.bank.getPersons()));
					numeAddTxt.setText("");
					prenumeAddTxt.setText("");
					dataAddTxt.setText("");
					personalNumberAddTxt.setText("");
				}
				else {
					JOptionPane.showMessageDialog(null, "Fields must not be empty ", null, JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		//edit tab
		editPnl.setLayout(new GridBagLayout());
		File input1 = new File("editPerson.jpg");
        BufferedImage image1 = null;
		try {
			image1 = ImageIO.read(input1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
        BufferedImage resized1 = resize(image1,75,75);
		pictureEditLabel=new JLabel(new ImageIcon(resized1));
		c.gridx=1;
		c.gridy=0;
		editPnl.add(pictureEditLabel);
		
		c.gridx=0;
		c.gridy=1;
		personsTable1=new JTable(gt.createTable(Interfata.bank.getPersons()));
		personsTable1.setSize(200, 200);
		scrollPersonsTable1=new JScrollPane(personsTable1);
		scrollPersonsTable1.setSize(200,200);
		editPnl.add(scrollPersonsTable1,c);
		
		numeEditPnl.setLayout(new FlowLayout());
		numeEditPnl.add(numeEditLabel);
		numeEditPnl.add(numeEditTxt);
		numeEditPnl.setBackground(new Color(230,230,250));
		c.gridx=0;
		c.gridy=2;
		editPnl.add(numeEditPnl,c);
		
		prenumeEditPnl.setLayout(new FlowLayout());
		prenumeEditPnl.add(prenumeEditLabel);
		prenumeEditPnl.add(prenumeEditTxt);
		prenumeEditPnl.setBackground(new Color(230,230,250));
		c.gridx=0;
		c.gridy=3;
		editPnl.add(prenumeEditPnl,c);
		
		idEditPnl.setLayout(new FlowLayout());
		idEditPnl.add(idEditLabel);
		idEditPnl.add(idEditTxt);
		idEditPnl.setBackground(new Color(230,230,250));
		c.gridx=0;
		c.gridy=4;
		editPnl.add(idEditPnl,c);
		
		c.gridx=0;
		c.gridy=5;
		editPnl.add(buttonEdit,c);
		
		idEditTxt.setEditable(false);
		
		personsTable1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int linie=personsTable1.rowAtPoint(e.getPoint());
				int coloana=personsTable1.columnAtPoint(e.getPoint());
				if(coloana>=0&&linie>=0) {
					idEditTxt.setText(personsTable1.getValueAt(linie, coloana).toString());
				}
			}
		});
		
		buttonEdit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!numeEditTxt.getText().isEmpty() ||
						!prenumeEditTxt.getText().isEmpty() ) {
					Interfata.bank.editPerson(Long.parseLong(idEditTxt.getText()), numeEditTxt.getText(), prenumeEditTxt.getText());
					personsTable.setModel(gt.createTable(Interfata.bank.getPersons()));
					personsTable1.setModel(gt.createTable(Interfata.bank.getPersons()));
					personsTable2.setModel(gt.createTable(Interfata.bank.getPersons()));
					idEditTxt.setText("");
					numeEditTxt.setText("");
					prenumeEditTxt.setText("");
				}
				else {
					JOptionPane.showMessageDialog(null, "Fields must not be empty ", null, JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		//delete tab
		deletePnl.setLayout(new GridBagLayout());
		File input2 = new File("deletePerson.jpg");
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
		personsTable2=new JTable(gt.createTable(Interfata.bank.getPersons()));
		personsTable2.setSize(200, 200);
		scrollPersonsTable2=new JScrollPane(personsTable2);
		scrollPersonsTable2.setSize(200,200);
		deletePnl.add(scrollPersonsTable2,c);
		
		idDeletePnl.setLayout(new FlowLayout());
		idDeletePnl.add(idDeleteLabel);
		idDeletePnl.add(idDeleteTxt);
		idDeletePnl.setBackground(new Color(230,230,250));
		c.gridx=0;
		c.gridy=2;
		deletePnl.add(idDeletePnl,c);
		
		c.gridx=0;
		c.gridy=3;
		deletePnl.add(buttonDelete,c);
		
		idDeleteTxt.setEditable(false);
		
		personsTable2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int linie=personsTable2.rowAtPoint(e.getPoint());
				int coloana=personsTable2.columnAtPoint(e.getPoint());
				if(coloana>=0&&linie>=0) {
					idDeleteTxt.setText(personsTable2.getValueAt(linie, coloana).toString());
				}
			}
		});
		
		buttonDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Interfata.bank.removePerson(Long.parseLong(idDeleteTxt.getText()));
					personsTable.setModel(gt.createTable(Interfata.bank.getPersons()));
					personsTable1.setModel(gt.createTable(Interfata.bank.getPersons()));
					personsTable2.setModel(gt.createTable(Interfata.bank.getPersons()));
					
					idDeleteTxt.setText("");


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

}
