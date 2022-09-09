package user;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.Stock;
import data.StockDB;

public class AddStockUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtAmount;
	protected JLabel GetAllText;
	private JTextField txtID;

	private StockDB sDB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStockUI frame = new AddStockUI();
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
	public AddStockUI() {
		setTitle("City Bookshop - Add Stock");
		setResizable(false);
		setBounds(100, 100, 406, 247);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		StockDB sDB = new StockDB();

		JLabel lblNewLabel_1 = new JLabel("Quantity");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(49, 77, 110, 14);
		contentPane.add(lblNewLabel_1);

		txtAmount = new JTextField();
		txtAmount.setBounds(177, 72, 198, 20);
		contentPane.add(txtAmount);
		txtAmount.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Validating text fields
				if (checkValid()) {
					int id = Integer.valueOf(txtID.getText());
					int newNoOfBooks = Integer.valueOf(txtAmount.getText());
					Stock stockTest = sDB.getStock(id);

					// Checking the availability of the stock
					if (stockTest != null) {
						int currentStock = Integer.valueOf(stockTest.getsAmount());
						String totalStock = String.valueOf(currentStock + newNoOfBooks);
						Stock s = new Stock(id, totalStock);

						// Updating the stock
						int result = sDB.updateStock(s);
						if (result == 1) {
							JOptionPane.showMessageDialog(null, "The Stock is updated", "Alert",
									JOptionPane.INFORMATION_MESSAGE);
							txtID.setText("");
							txtID.setEnabled(true);
							txtAmount.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "The Stock is not updated", "Alert",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Book ID is Incorrect.", "Alert",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdd.setBounds(25, 125, 168, 40);
		contentPane.add(btnAdd);
		Image add = new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		btnAdd.setIcon(new ImageIcon(add));

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancel.setBounds(207, 125, 168, 40);
		contentPane.add(btnCancel);
		Image cancel = new ImageIcon(this.getClass().getResource("/cancel.png")).getImage();
		btnCancel.setIcon(new ImageIcon(cancel));

		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(177, 41, 198, 20);
		contentPane.add(txtID);

		JLabel lblNewLabel_1_2 = new JLabel("Book ID");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(49, 46, 118, 14);
		contentPane.add(lblNewLabel_1_2);

		JLabel bgMb = new JLabel("");
		bgMb.setBounds(0, 0, 390, 208);
		contentPane.add(bgMb);
		Image bg = new ImageIcon(this.getClass().getResource("/bgSmall.jpg")).getImage();
		bgMb.setIcon(new ImageIcon(bg));

	}

	private boolean checkValid() {
		if (txtID.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Book name cannot be blank");
			return false;
		}
		try {
			int id = Integer.valueOf(txtID.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Book ID must be numeric", "Alert", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (txtAmount.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Number of books cannot be blank");
			return false;
		}
		try {
			int id = Integer.valueOf(txtAmount.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Number of books must be numeric", "Alert",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		return true;
	}
}
