package bs;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddProduct extends JFrame {

	private JPanel contentPane;
	private JTextField barcode;
	private JTextField name;
	private JTextField quantity;
	private JTextField price;
	private JButton btnSubmit;
	private JButton btnUpdate;
	private JLabel lblNewLabel;
	private JLabel lblName;
	private JLabel lblQuantity;
	private JLabel lblPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProduct frame = new AddProduct();
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
	public AddProduct() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddProduct = new JLabel("ADD PRODUCT");
		lblAddProduct.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAddProduct.setBounds(365, 13, 268, 44);
		contentPane.add(lblAddProduct);
		
		barcode = new JTextField();
		barcode.setBounds(270, 81, 245, 44);
		contentPane.add(barcode);
		barcode.setColumns(10);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(270, 169, 245, 44);
		contentPane.add(name);
		
		quantity = new JTextField();
		quantity.setColumns(10);
		quantity.setBounds(270, 253, 245, 44);
		contentPane.add(quantity);
		
		price = new JTextField();
		price.setColumns(10);
		price.setBounds(270, 336, 245, 44);
		contentPane.add(price);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(250, 426, 106, 54);
		contentPane.add(btnSubmit);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(395, 426, 106, 54);
		contentPane.add(btnUpdate);
		
		lblNewLabel = new JLabel("Barcode ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(132, 85, 81, 33);
		contentPane.add(lblNewLabel);
		
		lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(132, 169, 81, 33);
		contentPane.add(lblName);
		
		lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuantity.setBounds(132, 253, 81, 33);
		contentPane.add(lblQuantity);
		
		lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrice.setBounds(132, 350, 81, 33);
		contentPane.add(lblPrice);
	}

}
