package user;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

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
		mntmLogout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.ALT_DOWN_MASK));
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginUI logUI = new LoginUI();
				logUI.setVisible(true);
				setVisible(false);
			}
		});

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Change Password");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePasswordUI cpw = new ChangePasswordUI();
				cpw.setVisible(true);

			}
		});
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_DOWN_MASK));
		mnaccount.add(mntmNewMenuItem_1);
		mnaccount.add(mntmLogout);

		JMenu mnFile = new JMenu("Books");
		menuBar.add(mnFile);

		JMenuItem mntmMBooks = new JMenuItem("Manage Books");
		mntmMBooks.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
		mntmMBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageBooksUI mb = new ManageBooksUI();
				mb.setVisible(true);
			}
		});
		mnFile.add(mntmMBooks);

		JMenuItem mntmVBooks = new JMenuItem("View Books");
		mntmVBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBooksUI s = new ViewBooksUI();
				s.setVisible(true);
			}
		});
		mntmVBooks.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.ALT_DOWN_MASK));
		mnFile.add(mntmVBooks);

		JMenu mnCategory = new JMenu("Category");
		menuBar.add(mnCategory);

		JMenuItem mntmManageCategory = new JMenuItem("Manage Category");
		mntmManageCategory.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
		mntmManageCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageCategoryUI um = new ManageCategoryUI();
				um.setVisible(true);
			}
		});
		mnCategory.add(mntmManageCategory);

		JMenu mnManageAccounts = new JMenu("Accounts");
		menuBar.add(mnManageAccounts);

		JMenuItem mntmNewAccount = new JMenuItem("Manage Accounts");
		mntmNewAccount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_DOWN_MASK));
		mntmNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageAccountUI ca = new ManageAccountUI();
				ca.setVisible(true);
			}
		});
		mnManageAccounts.add(mntmNewAccount);

		JMenuItem mntmNewMenuItem = new JMenuItem("View Users");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewUserUI vu = new ViewUserUI();
				vu.setVisible(true);
			}
		});
		mnManageAccounts.add(mntmNewMenuItem);

		JMenu mnNewMenu = new JMenu("Billing");
		menuBar.add(mnNewMenu);

		JMenuItem mntmBill = new JMenuItem("New Bill");
		mntmBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.ALT_DOWN_MASK));
		mntmBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BillUI vu = new BillUI();
				vu.setVisible(true);
			}
		});
		mnNewMenu.add(mntmBill);

		JMenu mnNewMenu_1 = new JMenu("Stocks");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmAs = new JMenuItem("Add Stocks");
		mntmAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));
		mntmAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStockUI vu = new AddStockUI();
				vu.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmAs);

		JMenuItem mntmVs = new JMenuItem("View Stocks");
		mntmVs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_DOWN_MASK));
		mntmVs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewStocksUI vu = new ViewStocksUI();
				vu.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmVs);

		JMenu mnhelp = new JMenu("Help");
		menuBar.add(mnhelp);

		JMenuItem mniUsermaual = new JMenuItem("User Manual");
		mniUsermaual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManualUI um = new UserManualUI();
				um.setVisible(true);

			}
		});
		mniUsermaual.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.ALT_DOWN_MASK));
		mnhelp.add(mniUsermaual);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnVb = new JButton("View Books");
		btnVb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewBooksUI s = new ViewBooksUI();
				s.setVisible(true);
			}
		});
		btnVb.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnVb.setBounds(66, 249, 246, 50);
		contentPane.add(btnVb);
		Image viewB = new ImageIcon(this.getClass().getResource("/viewB.png")).getImage();
		btnVb.setIcon(new ImageIcon(viewB));

		JLabel lblNewLabel = new JLabel("Welcome!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 52));
		lblNewLabel.setBounds(299, 11, 635, 56);
		contentPane.add(lblNewLabel);

		JButton btnLogout = new JButton("");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginUI logUI = new LoginUI();
				logUI.setVisible(true);
				setVisible(false);
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/lout.png")).getImage();
		btnLogout.setIcon(new ImageIcon(img));
		btnLogout.setBounds(1214, 11, 40, 40);
		contentPane.add(btnLogout);

		JButton btnCP = new JButton("");
		btnCP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePasswordUI cpw = new ChangePasswordUI();
				cpw.setVisible(true);
			}
		});
		Image cp = new ImageIcon(this.getClass().getResource("/rpw.png")).getImage();
		btnCP.setIcon(new ImageIcon(cp));
		btnCP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCP.setBounds(1155, 11, 40, 40);
		contentPane.add(btnCP);

		JLabel lblNewLabel_1 = new JLabel("- City BookShop -");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(299, 49, 635, 71);
		contentPane.add(lblNewLabel_1);

		JLabel conStatus = new JLabel("");
		conStatus.setForeground(Color.BLACK);
		conStatus.setHorizontalAlignment(SwingConstants.LEFT);
		conStatus.setBounds(10, 717, 331, 22);
		contentPane.add(conStatus);
		conStatus.setText(ConnectionStatus.message);

		JLabel lblBooks = new JLabel("Books -");
		lblBooks.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblBooks.setBounds(66, 131, 246, 45);
		contentPane.add(lblBooks);

		JButton btnMb = new JButton("Manage Books");
		btnMb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageBooksUI mb = new ManageBooksUI();
				mb.setVisible(true);
			}
		});
		btnMb.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnMb.setBounds(66, 188, 246, 50);
		contentPane.add(btnMb);
		Image manageB = new ImageIcon(this.getClass().getResource("/manageB.png")).getImage();
		btnMb.setIcon(new ImageIcon(manageB));

		JButton btnVa = new JButton("View Accounts");
		btnVa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewUserUI vu = new ViewUserUI();
				vu.setVisible(true);
			}
		});
		btnVa.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnVa.setBounds(236, 615, 246, 50);
		contentPane.add(btnVa);
		Image viewA = new ImageIcon(this.getClass().getResource("/viewA.png")).getImage();
		btnVa.setIcon(new ImageIcon(viewA));

		JButton btnMa = new JButton("Manage Accounts");
		btnMa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageAccountUI ca = new ManageAccountUI();
				ca.setVisible(true);
			}
		});
		btnMa.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnMa.setBounds(236, 554, 246, 50);
		contentPane.add(btnMa);
		Image manageA = new ImageIcon(this.getClass().getResource("/manageA.png")).getImage();
		btnMa.setIcon(new ImageIcon(manageA));

		JLabel lblAccounts = new JLabel("Accounts - ");
		lblAccounts.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccounts.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblAccounts.setBounds(236, 498, 246, 45);
		contentPane.add(lblAccounts);

		JButton btnUserManual = new JButton("");
		btnUserManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManualUI um = new UserManualUI();
				um.setVisible(true);
			}
		});
		btnUserManual.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUserManual.setBounds(1100, 11, 40, 40);
		contentPane.add(btnUserManual);
		Image um = new ImageIcon(this.getClass().getResource("/um.png")).getImage();
		btnUserManual.setIcon(new ImageIcon(um));

		JButton btnMc = new JButton("Manage Category");
		btnMc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageCategoryUI um = new ManageCategoryUI();
				um.setVisible(true);
			}
		});
		btnMc.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnMc.setBounds(66, 399, 246, 50);
		contentPane.add(btnMc);
		Image Mc = new ImageIcon(this.getClass().getResource("/category.png")).getImage();
		btnMc.setIcon(new ImageIcon(Mc));

		JLabel lblAccounts_1 = new JLabel("Category - ");
		lblAccounts_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblAccounts_1.setBounds(66, 346, 246, 45);
		contentPane.add(lblAccounts_1);

		JButton btnBill = new JButton("New Bill");
		btnBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BillUI vu = new BillUI();
				vu.setVisible(true);
			}
		});
		btnBill.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnBill.setBounds(393, 399, 246, 50);
		contentPane.add(btnBill);
		Image bill = new ImageIcon(this.getClass().getResource("/bill.png")).getImage();
		btnBill.setIcon(new ImageIcon(bill));

		JLabel lblAccounts_1_1 = new JLabel("Billing - ");
		lblAccounts_1_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblAccounts_1_1.setBounds(393, 346, 246, 45);
		contentPane.add(lblAccounts_1_1);

		JButton btnVs = new JButton("View Stocks");
		btnVs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewStocksUI vu = new ViewStocksUI();
				vu.setVisible(true);
			}
		});
		btnVs.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnVs.setBounds(393, 249, 246, 50);
		contentPane.add(btnVs);
		Image bgVs = new ImageIcon(this.getClass().getResource("/viewStock.png")).getImage();
		btnVs.setIcon(new ImageIcon(bgVs));

		JButton btnAs = new JButton("Add Stocks");
		btnAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStockUI vu = new AddStockUI();
				vu.setVisible(true);
			}
		});
		btnAs.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnAs.setBounds(393, 188, 246, 50);
		contentPane.add(btnAs);
		Image bgStock = new ImageIcon(this.getClass().getResource("/addStock.png")).getImage();
		btnAs.setIcon(new ImageIcon(bgStock));

		JLabel lblAccounts_2 = new JLabel("Stocks - ");
		lblAccounts_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblAccounts_2.setBounds(393, 132, 246, 45);
		contentPane.add(lblAccounts_2);

		JLabel bgMain = new JLabel("");
		bgMain.setBounds(0, -21, 1286, 772);
		contentPane.add(bgMain);
		Image bgmain = new ImageIcon(this.getClass().getResource("/bgMain.jpg")).getImage();
		bgMain.setIcon(new ImageIcon(bgmain));

		Image redDot = new ImageIcon(this.getClass().getResource("/redDot.png")).getImage();
		Image greenDot = new ImageIcon(this.getClass().getResource("/greenDot.png")).getImage();
		if (ConnectionStatus.status) {
			conStatus.setIcon(new ImageIcon(greenDot));
		} else {
			conStatus.setIcon(new ImageIcon(redDot));
		}

		if (LoginStatus.type.equals("Cashier")) {
			mnManageAccounts.setVisible(false);
			btnVa.setVisible(false);
			btnMa.setVisible(false);
			lblAccounts.setVisible(false);
		}
	}
}
