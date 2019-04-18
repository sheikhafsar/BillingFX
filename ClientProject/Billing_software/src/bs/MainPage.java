package bs;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JPasswordField;

public class MainPage {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	Connection connection=null;
	
	
	
	
	private void initialize() {
		
		/*
		try {
		Class.forName("org.h2.Driver");
		connection=DriverManager.getConnection("jdbc:h2:~/test","sa","");
		
		JOptionPane.showMessageDialog(null,"connection Sucessfull");
		
		} 
		catch(ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
		*/
		
		
		
		frame = new JFrame();
		frame.setBounds(200, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Billing System");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setBounds(233, 13, 424, 87);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(292, 146, 319, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(292, 207, 319, 32);
		frame.getContentPane().add(textField_1);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUserName.setBounds(129, 133, 130, 32);
		frame.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(129, 201, 154, 39);
		frame.getContentPane().add(lblPassword);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Tasks().setVisible(true);
				
				//System.out.println("ho");
			}
		});
		
		btnLogIn.setBounds(359, 284, 167, 32);
		frame.getContentPane().add(btnLogIn);
	}
}
