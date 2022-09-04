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
import data.UserDB;
import data.pwdHash;
import business.User;
import javax.swing.SwingConstants;

public class LoginUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtUID;
	private JPasswordField txtPWRD;
	private JButton btnOK; 
	private UserDB uDB;
	private JLabel lblCon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
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
	public LoginUI() {
		setTitle("City Bookshop - Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 247);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		uDB = new UserDB();
		
		JLabel lblNewLabel = new JLabel("User ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(53, 35, 101, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(53, 69, 101, 23);
		contentPane.add(lblPassword);
		
		txtUID = new JTextField();
		txtUID.setBounds(166, 38, 162, 20);
		contentPane.add(txtUID);
		txtUID.setColumns(10);
		
		txtPWRD = new JPasswordField();
		txtPWRD.setBounds(166, 72, 162, 20);
		contentPane.add(txtPWRD);
		
		btnOK = new JButton("Login");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				int userid=Integer.valueOf(txtUID.getText());
				String password=txtPWRD.getText();
				User u =uDB.get(userid); 
				String hashPW =pwdHash.getMd5(password);
				
				if(u!=null&& hashPW.equals(u.getPassword())) {
					LoginStatus.type= String.valueOf(u.getType());
					MainUI mUI=new MainUI();
					mUI.setVisible(true); 
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Incorrect User ID or Password","Alert",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnOK.setBounds(64, 127, 110, 24);
		contentPane.add(btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(210, 127, 110, 24);
		contentPane.add(btnCancel);
		
		lblCon = new JLabel("");
		lblCon.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCon.setForeground(Color.GRAY);
		lblCon.setBounds(170, 183, 210, 14);
		contentPane.add(lblCon);
		
		lblCon.setText(ConnectionStatus.message);
	}
}
