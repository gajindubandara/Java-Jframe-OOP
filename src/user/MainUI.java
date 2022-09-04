package user;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class MainUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI();
					frame.setVisible(true);
//					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("City Bookshop");
		setBounds(100, 100, 1280, 800);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnaccount = new JMenu("My account");
		menuBar.add(mnaccount);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginUI logUI=new LoginUI();
				logUI.setVisible(true);
				setVisible(false);
			}
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Change Password");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePasswordUI cpw=new ChangePasswordUI();
				cpw.setVisible(true);
				
			}
		});
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
		mnaccount.add(mntmNewMenuItem_1);
		mnaccount.add(mntmLogout);
		
		JMenu mnFile = new JMenu("Books");
		menuBar.add(mnFile);
		
		JMenuItem mntmMBooks = new JMenuItem("Manage Books");
		mntmMBooks.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		mntmMBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageBooksUI mb =new ManageBooksUI();
				mb.setVisible(true);
			}
		});
		mnFile.add(mntmMBooks);
		
		JMenuItem mntmVBooks = new JMenuItem("View Books");
		mntmVBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBooksUI s =new ViewBooksUI();
				s.setVisible(true);			
			}
		});
		mntmVBooks.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
		mnFile.add(mntmVBooks);
		
		JMenuItem mntmNewCat = new JMenuItem("Add a New Category");
		mntmNewCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnFile.add(mntmNewCat);
		
		JMenu mnManageAccounts = new JMenu("Accounts");
		menuBar.add(mnManageAccounts);
		
		JMenuItem mntmNewAccount = new JMenuItem("Manage Accounts");
		mntmNewAccount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		mntmNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageAccountUI ca=new ManageAccountUI();
				ca.setVisible(true);
			}
		});
		mnManageAccounts.add(mntmNewAccount);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("View Users");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewUserUI vu=new ViewUserUI ();
				vu.setVisible(true);
			}
		});
		mnManageAccounts.add(mntmNewMenuItem);
		
		JMenu mnhelp = new JMenu("Help");
		menuBar.add(mnhelp);
		
		JMenuItem mniUsermaual = new JMenuItem("User Manual");
		mniUsermaual.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));
		mnhelp.add(mniUsermaual);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVb = new JButton("View Books");
		btnVb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVb.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnVb.setBounds(298, 267, 246, 74);
		contentPane.add(btnVb);
		Image viewB = new ImageIcon(this.getClass().getResource("/viewB.png")).getImage();
		btnVb.setIcon(new ImageIcon(viewB));
		
		JLabel lblNewLabel = new JLabel("Welcome!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 52));
		lblNewLabel.setBounds(298, 11, 635, 137);
		contentPane.add(lblNewLabel);

		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/lout.png")).getImage();
		btnLogout.setIcon(new ImageIcon(img));
		btnLogout.setBounds(1134, 11, 120, 45);
		contentPane.add(btnLogout);
		
		JButton btnCP = new JButton("Change Pasword");
		btnCP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Image cp = new ImageIcon(this.getClass().getResource("/pw.png")).getImage();
		btnCP.setIcon(new ImageIcon(cp));
		btnCP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCP.setBounds(1069, 67, 185, 45);
		contentPane.add(btnCP);

		
		JLabel lblNewLabel_1 = new JLabel("- City BookShop -");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(298, 115, 635, 71);
		contentPane.add(lblNewLabel_1);
		
		
		JLabel conStatus = new JLabel("");
		conStatus.setForeground(Color.BLACK);
		conStatus.setHorizontalAlignment(SwingConstants.LEFT);
		conStatus.setBounds(10, 706, 331, 22);
		contentPane.add(conStatus);
		conStatus.setText(ConnectionStatus.message);
		
		JLabel lblNewLabel_2 = new JLabel("Books -");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_2.setBounds(10, 197, 246, 45);
		contentPane.add(lblNewLabel_2);
		
		JButton btnMb = new JButton("Manage Books");
		btnMb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMb.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnMb.setBounds(293, 490, 246, 74);
		contentPane.add(btnMb);
		Image manageB = new ImageIcon(this.getClass().getResource("/manageB.png")).getImage();
		btnMb.setIcon(new ImageIcon(manageB));
		
		JButton btnVa = new JButton("View Accounts");
		btnVa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVa.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnVa.setBounds(10, 490, 246, 74);
		contentPane.add(btnVa);
		Image viewA = new ImageIcon(this.getClass().getResource("/viewA.png")).getImage();
		btnVa.setIcon(new ImageIcon(viewA));
		
		
		JButton btnMa = new JButton("Manage Accounts");
		btnMa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMa.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnMa.setBounds(10, 267, 246, 74);
		contentPane.add(btnMa);
		Image manageA = new ImageIcon(this.getClass().getResource("/manageA.png")).getImage();
		btnMa.setIcon(new ImageIcon(manageA));
		
		JLabel lblNewLabel_2_1 = new JLabel("Accounts -");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_2_1.setBounds(10, 383, 246, 45);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel bgMain = new JLabel("");
		bgMain.setBounds(0, -21, 1286, 772);
		contentPane.add(bgMain);
		Image bgmain = new ImageIcon(this.getClass().getResource("/bgMain.jpg")).getImage();
		bgMain.setIcon(new ImageIcon(bgmain));
		
		Image redDot= new ImageIcon(this.getClass().getResource("/redDot.png")).getImage();
		Image greenDot= new ImageIcon(this.getClass().getResource("/greenDot.png")).getImage();
		if(ConnectionStatus.status) {
			conStatus.setIcon(new ImageIcon(greenDot));
		}else {
		conStatus.setIcon(new ImageIcon(redDot));}
		
		
		
//		if(LoginStatus.type.equals("Cashier")) {
//			mnManageAccounts.setVisible(false);
//
//		}
	}
}
