package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BankAccounts.Account;
import BankAccounts.SavingAccount;
import BankPackage.Bank;

public class Interfata extends JFrame {
	
	
	private JFrame frame=new JFrame("Bank Application");
	private JPanel mainPnl=new JPanel();
	private GridBagConstraints c=new GridBagConstraints();
	private JLabel pictureLabel;
	private JButton personOperationsButton=new JButton("Person operations");
	private JButton accountOperationsButton=new JButton("Account operations");
	private JLabel sp1=new JLabel(" ");
	public static Bank bank;
	
	public Interfata() {
		bank=new Bank();
		frame.setSize(new Dimension(400,500));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPnl.setBackground(new Color(176,224,230));
		mainPnl.setLayout(new GridBagLayout());
		
		File input = new File("bank1.jpg");
        BufferedImage image = null;
		try {
			image = ImageIO.read(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        BufferedImage resized = resize(image,75,75);
		
		
		pictureLabel=new JLabel(new ImageIcon(resized));
		
		
		c.gridx=1;
		c.gridy=0;
		mainPnl.add(pictureLabel,c);
		
		c.gridx=1;
		c.gridy=1;
		mainPnl.add(sp1,c);
		
		c.gridx=1;
		c.gridy=2;
		mainPnl.add(sp1,c);
		
		c.gridx=0;
		c.gridy=3;
		personOperationsButton.setBackground(new Color(65,105,225));
		personOperationsButton.setFocusPainted(false);
		personOperationsButton.setForeground(Color.WHITE);
		personOperationsButton.setPreferredSize(new Dimension(200,75));
		mainPnl.add(personOperationsButton,c);
		
		c.gridx=2;
		c.gridy=3;
		accountOperationsButton.setBackground(new Color(65,105,225));
		accountOperationsButton.setFocusPainted(false);
		accountOperationsButton.setForeground(Color.WHITE);
		accountOperationsButton.setPreferredSize(new Dimension(200,75));
		mainPnl.add(accountOperationsButton,c);
		
		
		personOperationsButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new PersonOperationsFrame();
			}
			
		});
		
		accountOperationsButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AccountOperationsFrame();
			}
			
		});
		
		frame.add(mainPnl);
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
	
	public static void main(String[] args) {
		Interfata i=new Interfata();

	}
}
