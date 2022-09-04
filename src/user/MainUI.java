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
		
		JMenu mnhelp = new JMenu("Help");
		menuBar.add(mnhelp);
		
		JMenuItem mniUsermaual = new JMenuItem("User Manual");
		mniUsermaual.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));
		mnhelp.add(mniUsermaual);
		
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(this);
		setContentPane(contentPane);
		
		
		
		if(LoginStatus.type.equals("Cashier")) {
			mnManageAccounts.setVisible(false);

		}
	}

}
