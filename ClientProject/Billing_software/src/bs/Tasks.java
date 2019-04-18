package bs;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Tasks extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tasks frame = new Tasks();
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
	public Tasks() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddProduct = new JButton("ADD PRODUCT");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new addProductList().setVisible(true);
			}
		});
		btnAddProduct.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddProduct.setBounds(352, 31, 269, 60);
		contentPane.add(btnAddProduct);
		
		JButton btnEmployee = new JButton("EMPLOYEE");
		btnEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new EmployeeRegistration().setVisible(true);
			}
		});
		btnEmployee.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEmployee.setBounds(361, 140, 260, 60);
		contentPane.add(btnEmployee);
		
		JButton button = new JButton("BILL");
		button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Bills().setVisible(true);
			}
		});
		button.setBounds(361, 240, 260, 60);
		contentPane.add(button);
	}
}
