package user;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.Color;
import data.CashierDB;
import data.pwdHash;
import business.Cashier;

public class LoginCUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUID;
	private JPasswordField txtPWRD;
	private JButton btnOK; 
	private CashierDB cDB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUItemp frame = new LoginUItemp();
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
	public LoginCUI() {
		setTitle("Cashier Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 232);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cDB = new CashierDB();
		
		JLabel lblNewLabel = new JLabel("User ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(50, 34, 101, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(50, 68, 101, 23);
		contentPane.add(lblPassword);
		
		txtUID = new JTextField();
		txtUID.setBounds(163, 37, 129, 20);
		contentPane.add(txtUID);
		txtUID.setColumns(10);
		
		txtPWRD = new JPasswordField();
		txtPWRD.setBounds(163, 71, 129, 20);
		contentPane.add(txtPWRD);
		
		btnOK = new JButton("Login");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				int userid=Integer.valueOf(txtUID.getText());
				String password=txtPWRD.getText();
				Cashier c =cDB.get(userid); 
				
				String hashPW =pwdHash.getMd5(password);
				System.out.println(hashPW);
				System.out.println(c.getPassword());
				
				if(c!=null&& hashPW.equals(c.getPassword())) {
					LoginStatus.userID = String.valueOf(cDB.get(userid));
					MainUI mUI=new MainUI();
					mUI.setVisible(true); 
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Incorrect User ID or Password");
				}
			}
		});
		btnOK.setBounds(61, 138, 89, 23);
		contentPane.add(btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(181, 138, 89, 23);
		contentPane.add(btnCancel);
	}
}
