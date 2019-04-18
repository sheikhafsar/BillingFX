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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeRegistration extends JFrame {

	private JPanel contentPane;
	private JTextField empName;
	private JTextField lastname;
	private JLabel lblLastName;
	private JTextField phoneNumber;
	private JLabel lblContactNo;
	private JTextField email;
	private JLabel lblEmail;
	private JTextField address;
	private JLabel lblAddress;
	private JTextField password;
	private JLabel lblPassword;
	private JTextField confPassword;
	private JLabel lblConfirmPassword;
	private JButton btnRegister;
	private JButton btnReset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeRegistration frame = new EmployeeRegistration();
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
	public EmployeeRegistration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Registration");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setBounds(261, 13, 466, 49);
		contentPane.add(lblNewLabel);
		
		empName = new JTextField();
		empName.setBounds(189, 94, 202, 34);
		contentPane.add(empName);
		empName.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(70, 101, 56, 16);
		contentPane.add(lblName);
		
		lastname = new JTextField();
		lastname.setColumns(10);
		lastname.setBounds(189, 163, 202, 34);
		contentPane.add(lastname);
		
		lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLastName.setBounds(70, 172, 92, 16);
		contentPane.add(lblLastName);
		
		phoneNumber = new JTextField();
		phoneNumber.setColumns(10);
		phoneNumber.setBounds(189, 242, 202, 34);
		contentPane.add(phoneNumber);
		
		lblContactNo = new JLabel("Contact No.");
		lblContactNo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblContactNo.setBounds(70, 249, 114, 16);
		contentPane.add(lblContactNo);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(189, 330, 202, 34);
		contentPane.add(email);
		
		lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(70, 337, 82, 16);
		contentPane.add(lblEmail);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(189, 432, 202, 34);
		contentPane.add(address);
		
		lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAddress.setBounds(70, 441, 82, 16);
		contentPane.add(lblAddress);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(636, 94, 209, 34);
		contentPane.add(password);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(463, 98, 92, 23);
		contentPane.add(lblPassword);
		
		confPassword = new JTextField();
		confPassword.setColumns(10);
		confPassword.setBounds(636, 163, 209, 34);
		contentPane.add(confPassword);
		
		lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblConfirmPassword.setBounds(463, 167, 183, 23);
		contentPane.add(lblConfirmPassword);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//registration code
				//query
				
				
			}
		});
		btnRegister.setBounds(503, 268, 107, 51);
		contentPane.add(btnRegister);
		
		btnReset = new JButton("Reset");
		btnReset.setBounds(698, 268, 107, 51);
		contentPane.add(btnReset);
	}
}
