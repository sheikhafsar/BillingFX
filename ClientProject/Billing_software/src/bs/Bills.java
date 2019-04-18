package bs;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;

public class Bills extends JFrame {

	private JPanel contentPane;
	private JTextField billid;
	private JTextField date;
	private JTextField customer;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bills frame = new Bills();
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
	public Bills() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BILLS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(377, 29, 110, 25);
		contentPane.add(lblNewLabel);
		
		billid = new JTextField();
		billid.setBounds(170, 85, 126, 40);
		contentPane.add(billid);
		billid.setColumns(10);
		
		date = new JTextField();
		date.setColumns(10);
		date.setBounds(468, 85, 126, 40);
		contentPane.add(date);
		
		customer = new JTextField();
		customer.setColumns(10);
		customer.setBounds(170, 153, 218, 40);
		contentPane.add(customer);
		
		table = new JTable();
		table.setBounds(106, 239, 802, 241);
		contentPane.add(table);
		
		JLabel lblBillId = new JLabel("Bill id");
		lblBillId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBillId.setBounds(62, 91, 56, 25);
		contentPane.add(lblBillId);
		
		JLabel lblCustomer = new JLabel("Customer");
		lblCustomer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCustomer.setBounds(62, 165, 96, 25);
		contentPane.add(lblCustomer);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDate.setBounds(347, 85, 96, 25);
		contentPane.add(lblDate);
	}

}
