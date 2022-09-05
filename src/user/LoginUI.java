package user;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

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
	private JLabel conStatus;

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
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(53, 35, 101, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
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
		btnOK.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				int userid=Integer.valueOf(txtUID.getText());
				String password=txtPWRD.getText();
				User u =uDB.get(userid); 
				
				//Hashing the entered password
				String hashPW =pwdHash.getMd5(password);
				
				if(u!=null&& hashPW.equals(u.getPassword())) {
					LoginStatus.type= String.valueOf(u.getType());
					MainUI mUI=new MainUI();
					mUI.setVisible(true); 
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Incorrect User ID or Password","Alert",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnOK.setBounds(53, 124, 120, 40);
		contentPane.add(btnOK);
		Image ok = new ImageIcon(this.getClass().getResource("/lin.png")).getImage();
		btnOK.setIcon(new ImageIcon(ok));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(210, 124, 120, 40);
		contentPane.add(btnCancel);
		Image cancel = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
		btnCancel.setIcon(new ImageIcon(cancel));
		
		conStatus = new JLabel("");
		conStatus.setForeground(Color.BLACK);
		conStatus.setHorizontalAlignment(SwingConstants.LEFT);
		conStatus.setBounds(10, 186, 331, 22);
		contentPane.add(conStatus);
		conStatus.setText(ConnectionStatus.message);
		
		JLabel bgLogin = new JLabel("");
		bgLogin.setBounds(0, 0, 400, 208);
		contentPane.add(bgLogin);
		Image bglogin = new ImageIcon(this.getClass().getResource("/bgLogin.jpg")).getImage();
		bgLogin.setIcon(new ImageIcon(bglogin));
		
		Image redDot= new ImageIcon(this.getClass().getResource("/redDot.png")).getImage();
		Image greenDot= new ImageIcon(this.getClass().getResource("/greenDot.png")).getImage();
		if(ConnectionStatus.status) {
			conStatus.setIcon(new ImageIcon(greenDot));
		}else {
		conStatus.setIcon(new ImageIcon(redDot));}
	}
}
