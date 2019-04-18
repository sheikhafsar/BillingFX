package bs;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class addProductList extends JFrame {

	private JPanel contentPane;
	private JTable ProductTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addProductList frame = new addProductList();
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
	public addProductList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ProductTable = new JTable();
		ProductTable.setBounds(37, 104, 740, 308);
		contentPane.add(ProductTable);
		
		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Add product
			}
		});
		btnAddProduct.setBounds(122, 446, 150, 52);
		contentPane.add(btnAddProduct);
		
		JButton btnDeleteProduct = new JButton("Delete Product");
		btnDeleteProduct.setBounds(372, 446, 150, 52);
		contentPane.add(btnDeleteProduct);
		
		JLabel lblProductList = new JLabel("PRODUCT LIST");
		lblProductList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProductList.setBounds(209, 45, 294, 34);
		contentPane.add(lblProductList);
	}
}
