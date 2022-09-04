package user;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import java.awt.Toolkit;

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
		setBounds(100, 100, 778, 448);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
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
		
		JMenu mnManageAccounts = new JMenu("Manage Accounts");
		menuBar.add(mnManageAccounts);
		
		JMenuItem mntmNewAccount = new JMenuItem("Create New Account");
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
		
		JLabel lblCon = new JLabel("New label");
		lblCon.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCon.setForeground(Color.GRAY);
		lblCon.setBounds(542, 362, 210, 14);
		contentPane.add(lblCon);
		
		lblCon.setText(ConnectionStatus.message);
		
		JButton btnNewButton = new JButton("Manage Books");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnNewButton.setBounds(132, 69, 481, 45);
		contentPane.add(btnNewButton);
		
		JButton btnManageAccounts = new JButton("Manage Accounts");
		btnManageAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnManageAccounts.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnManageAccounts.setBounds(132, 178, 481, 45);
		contentPane.add(btnManageAccounts);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHelp.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnHelp.setBounds(132, 234, 481, 45);
		contentPane.add(btnHelp);
		
		JLabel lblNewLabel = new JLabel("Welcome!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(132, 11, 481, 47);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("View Books");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnNewButton_2.setBounds(132, 122, 481, 45);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.setBounds(21, 314, 154, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Change Password");
		btnNewButton_1_1.setBounds(21, 348, 154, 23);
		contentPane.add(btnNewButton_1_1);
//		System.out.println(ConnectionStatus.message);
		
		if(LoginStatus.type.equals("Cashier")) {
			mnManageAccounts.setVisible(false);

		}
	}
}
