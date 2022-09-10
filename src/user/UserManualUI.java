package user;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import data.UserDB;

public class UserManualUI extends JFrame {

	private JPanel contentPane;
	private UserDB uDB;
	private JTextField txtPrice;
	private JButton btnPrice;
	private JTextField txtCategory;
	private JButton btncategory;
	private JLabel lblNewLabel;
	private JLabel bgView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManualUI frame = new UserManualUI();
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
	public UserManualUI() {
		setResizable(false);
		setTitle("City Bookshop - User Manual");
		setBounds(100, 100, 1024, 638);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("User Manual");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(23, 11, 960, 43);
		contentPane.add(lblNewLabel);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancel.setBounds(824, 548, 159, 40);
		contentPane.add(btnCancel);
		Image cancel = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
		btnCancel.setIcon(new ImageIcon(cancel));

		TextArea textArea = new TextArea();
		textArea.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		textArea.setEditable(false);
		textArea.setFont(new Font("Tahoma", Font.BOLD, 13));
		textArea.setText("This is the application's user guide." + "\n" + "" + "\n"
				+ "To view the manual for each process, use the buttons on the left.");
		textArea.setBounds(243, 89, 731, 438);
		contentPane.add(textArea);

		JButton btnMb = new JButton("Managing Books");
		btnMb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("----- Add new book -----" + "\n" + "1. Enter the book details" + "\n"
						+ "2. Press 'Add' button" + "\n" + "" + "\n" + "----- Find & update book -----" + "\n"
						+ "1. Press the 'Find & Update' button " + "\n" + "2. Enter the book ID" + "\n"
						+ "3. Press 'Ok'" + "\n" + "4. Edit book details " + "\n" + "5. Press 'Update' button" + "\n"
						+ "" + "\n" + "----- Delete book -----" + "\n" + "1. Press the 'Delete' button " + "\n"
						+ "2. Enter the book ID" + "\n" + "3. Press 'Ok'" + "\n" + "4. Press 'Yes' to confirm delete "
						+ "\n");
			}
		});
		btnMb.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMb.setBounds(23, 99, 168, 23);
		contentPane.add(btnMb);

		JButton btnVb = new JButton("View Books");
		btnVb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("1. Type the keyword on the search bar (You can search by name, price, or category)"
						+ "\n" + "2. Press a 'Search' Button" + "\n"
						+ "  * Example – If you want to search by price, then type the price in the search bar and press the"
						+ "\n" + "    'Search by price' button ");
			}
		});
		btnVb.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVb.setBounds(23, 131, 168, 23);
		contentPane.add(btnVb);

		JButton btnMa = new JButton("Manage Accounts");
		btnMa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("----- Add new account -----" + "\n" + "1. Enter account details" + "\n"
						+ "2. Press 'Add' button" + "\n" + "" + "\n" + "----- Find & update account -----" + "\n"
						+ "1. Press the 'Find & Update' button " + "\n" + "2. Enter the user ID" + "\n"
						+ "3. Press 'Ok'" + "\n" + "4. Edit user details " + "\n" + "5. Press 'Update' button" + "\n"
						+ "" + "\n" + "----- Delete account -----" + "\n" + "1. Press the 'Delete' button " + "\n"
						+ "2. Enter the user ID" + "\n" + "3. Press 'Ok'" + "\n" + "4. Press 'Yes' to confirm delete "
						+ "\n");
			}
		});
		btnMa.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMa.setBounds(23, 470, 168, 23);
		contentPane.add(btnMa);

		JButton btnVa = new JButton("View Accounts");
		btnVa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(
						"1. Type the user name on the search bar" + "\n" + "2. Press a 'Search' Button" + "\n");
			}
		});
		btnVa.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVa.setBounds(23, 504, 168, 23);
		contentPane.add(btnVa);

		JButton btnCp = new JButton("Change Password");
		btnCp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("1. Enter the user ID, current password, new password and confirm the new password"
						+ "\n" + "2. Press 'Ok' to change the password" + "\n");
			}
		});
		btnCp.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCp.setBounds(23, 356, 168, 23);
		contentPane.add(btnCp);

		JButton btnMc = new JButton("Manage Categories");
		btnMc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("----- Add new category -----" + "\n" + "1. Enter the category name" + "\n"
						+ "2. Press 'Add' button" + "\n" + "" + "\n" + "----- Find & update category -----" + "\n"
						+ "1. Press the 'Find & Update' button " + "\n" + "2. Enter the category ID" + "\n"
						+ "3. Press 'Ok'" + "\n" + "4. Edit category name " + "\n" + "5. Press 'Update' button" + "\n"
						+ "" + "\n" + "----- Delete category -----" + "\n" + "1. Press the 'Delete' button " + "\n"
						+ "2. Enter the category ID" + "\n" + "3.Press 'Ok'" + "\n"
						+ "4. Press 'Yes' to confirm delete " + "\n");
			}
		});
		btnMc.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMc.setBounds(23, 322, 168, 23);
		contentPane.add(btnMc);

		JButton btnAs = new JButton("Add Stocks");
		btnAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(
						"1. Enter the book ID and the quantity of the books" + "\n" + "2. Press 'Add' button" + "\n");
			}
		});
		btnAs.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAs.setBounds(23, 206, 168, 23);
		contentPane.add(btnAs);

		JButton btnVs = new JButton("View Stocks");
		btnVs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("1. Enter the book ID " + "\n" + "2. Press 'Search' button" + "\n");
			}
		});
		btnVs.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVs.setBounds(23, 240, 168, 23);
		contentPane.add(btnVs);

		JButton btnB = new JButton("Billing");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("1. Select the book and the quantity from the drop-down " + "\n"
						+ "2. Press the 'Add' button to add the details to the bill" + "\n"
						+ "3. Press the 'Ok' to purchase");
			}
		});
		btnB.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnB.setBounds(23, 390, 168, 23);
		contentPane.add(btnB);

		bgView = new JLabel("");
		bgView.setBounds(0, 0, 1008, 599);
		contentPane.add(bgView);
		Image bgV = new ImageIcon(this.getClass().getResource("/bgViewBA.jpg")).getImage();
		bgView.setIcon(new ImageIcon(bgV));

	}
}
