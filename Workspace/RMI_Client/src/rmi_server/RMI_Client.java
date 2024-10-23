package rmi_server;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class RMI_Client extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public JTextField textField_1;
    public JButton btnNewButton_1 = new JButton("Login");
    public JPasswordField jpassWord;
    public static void main(String[] args) {
        // Tạo một thể hiện của RMI_Client và hiển thị nó
        RMI_Client frame = new RMI_Client();
        frame.setVisible(true);
    }

    public RMI_Client() {

        setForeground(new Color(128, 0, 0));
        this.setTitle("Login with admin!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 450);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(244, 255, 244));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Login!");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
        lblNewLabel_1.setBounds(128, 114, 243, 34);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_3_1 = new JLabel("User Name:");
        lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_3_1.setBounds(47, 174, 119, 34);
        contentPane.add(lblNewLabel_3_1);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(184, 175, 243, 41);
        contentPane.add(textField_1);

        JLabel lblNewLabel_3_2 = new JLabel("Password:");
        lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_3_2.setBounds(47, 241, 119, 34);
        contentPane.add(lblNewLabel_3_2);

        jpassWord = new JPasswordField();
        jpassWord.setColumns(10);
        jpassWord.setBounds(184, 242, 243, 41);
        contentPane.add(jpassWord);

        JCheckBox chckbxNewCheckBox = new JCheckBox("Show Pass");
        chckbxNewCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (chckbxNewCheckBox.isSelected()) {
                    jpassWord.setEchoChar((char) 0);
                } else {
                    jpassWord.setEchoChar('*');
                }
            }
        });
        chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
        chckbxNewCheckBox.setBackground(new Color(244, 255, 244));
        chckbxNewCheckBox.setBounds(184, 290, 99, 23);
        contentPane.add(chckbxNewCheckBox);

        btnNewButton_1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    doLogin();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
        });
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton_1.setForeground(new Color(0, 0, 0));
        btnNewButton_1.setBackground(new Color(157, 157, 255));
        btnNewButton_1.setBounds(304, 337, 107, 41);
        contentPane.add(btnNewButton_1);

        JButton btnSignup = new JButton("Sign Up");
        btnSignup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnSignup.setForeground(Color.BLACK);
        btnSignup.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnSignup.setBackground(new Color(157, 157, 255));
        btnSignup.setBounds(10, 351, 119, 23);
        contentPane.add(btnSignup);

    }

    public void doLogin() throws Exception {
        // Lấy thông tin người dùng từ GUI
        String username = textField_1.getText();
        String password = getPass(jpassWord.getPassword());

        // Kết nối tới RMI Server
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        LoginSyncInterface stub = (LoginSyncInterface) registry.lookup("ServerA");
        
        Registry registryB = LocateRegistry.getRegistry("localhost", 1100); // Địa chỉ IP của Server B
        LoginSyncInterface stubB = (LoginSyncInterface) registryB.lookup("ServerB");

        // Kiểm tra trạng thái đăng nhập
        boolean isLoggedIn = stub.isUserLoggedIn(username);
        boolean isLoggedIn2 = stubB.isUserLoggedIn(username);
        if (isLoggedIn || isLoggedIn2 ) {
        	JOptionPane.showMessageDialog(this, "Người dùng đã đăng nhập ở nơi khác!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else {
            // Kiểm tra mật khẩu (nếu cần) và cập nhật trạng thái đăng nhập
            stub.updateUserStatus(username, true);
            stubB.updateUserStatus(username, true);
            System.out.println("Đăng nhập thành công!");
            RMI_banking bank = new RMI_banking(textField_1.getText(),stub,stubB);
            bank.setVisible(true);
        //    stub.updateBanking(password, username, password);
       //     stubB.updateBanking(password, username, password);
//            MainFrame mainFrame = new MainFrame();
//            mainFrame.setVisible(true);
            dispose(); // Ẩn giao diện đăng nhập
        }

        // Sau khi đăng nhập, có thể reset lại các trường
        textField_1.setText("");
        jpassWord.setText("");
    }

    public String getPass(char[] pass) {
        String p = "";
        for (char x : pass) {
            p += x;
        }
        return p;
    }
}
