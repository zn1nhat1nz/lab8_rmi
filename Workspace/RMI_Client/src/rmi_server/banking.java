package rmi_server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class banking extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtReceiver;
	private JTextField txtMoney;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					banking frame = new banking();
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
	public banking() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 371, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tài khoản của bạn");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 36, 161, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblSD = new JLabel("Số dư: ");
		lblSD.setHorizontalAlignment(SwingConstants.CENTER);
		lblSD.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblSD.setBounds(24, 66, 71, 13);
		contentPane.add(lblSD);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setText("5.000.000");
		textField.setBounds(96, 64, 127, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblTiKhonch = new JLabel("Tài khoản đích");
		lblTiKhonch.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTiKhonch.setBounds(10, 159, 134, 20);
		contentPane.add(lblTiKhonch);
		
		txtReceiver = new JTextField();
		txtReceiver.setBounds(154, 156, 140, 31);
		contentPane.add(txtReceiver);
		txtReceiver.setColumns(10);
		
		JLabel lblSTin = new JLabel("Số tiền");
		lblSTin.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSTin.setBounds(10, 208, 134, 20);
		contentPane.add(lblSTin);
		
		txtMoney = new JTextField();
		txtMoney.setColumns(10);
		txtMoney.setBounds(154, 197, 140, 31);
		contentPane.add(txtMoney);
		
		JButton btnBanking = new JButton("Chuyển khoản");
		btnBanking.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnBanking.setBounds(197, 272, 115, 21);
		contentPane.add(btnBanking);
		
		JButton btnngXut = new JButton("Đăng xuất");
		btnngXut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnngXut.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnngXut.setBounds(232, 10, 115, 21);
		contentPane.add(btnngXut);
	}
}
