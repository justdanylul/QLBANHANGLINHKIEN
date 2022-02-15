package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Connect.LoginConnect;
import Model.login;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;

public class LoginFrame extends JFrame implements ActionListener {
	
	LoginConnect loginconnect = new LoginConnect();

	private JPanel contentPane;
	private JTextField tfPass;
	private JButton btnLogin;
	private JButton btnExit;
	
	private JTable table3 = new JTable();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setLocationRelativeTo(null);
					frame.loadData();
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
	public LoginFrame() {
		this.loginconnect.connect();
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Downloads\\lock-icon.png"));
		setTitle("Đăng nhập");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 320, 138);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pass :");
		lblNewLabel.setBounds(52, 32, 46, 14);
		contentPane.add(lblNewLabel);
		
		tfPass = new JTextField();
		tfPass.setBounds(90, 28, 142, 20);
		contentPane.add(tfPass);
		tfPass.setColumns(10);
		
		btnLogin = new JButton("Đăng nhập");
		btnLogin.setBounds(34, 63, 102, 23);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(this);
		
		
		btnExit = new JButton("Thoát");
		btnExit.setBounds(169, 63, 102, 23);
		contentPane.add(btnExit);
		btnExit.addActionListener(this);
	}
	
	public void loadData() {
		DefaultTableModel model = new DefaultTableModel();
		
		ResultSet rs = loginconnect.getData();
		try {
			ResultSetMetaData rsMD = rs.getMetaData();
			int colNum = rsMD.getColumnCount();
			String arr[] = new String[colNum];
			for (int i = 0; i < colNum; i++) {
				arr[i] = rsMD.getColumnName(i+1);
			}
			model.setColumnIdentifiers(arr);
			
			while (rs.next()) {
				for (int i = 0; i< colNum; i++) {
					arr[i] = rs.getString(i+1);
				}
				model.addRow(arr);
			}
			table3.setModel(model);
		} catch (SQLException e) {
		}
	}
		

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btnLogin) {
			int isTrue = 0;
			String pass = tfPass.getText();
			for (int i = 0; i < table3.getRowCount(); i++) {
				String value = (String) table3.getValueAt(i, 1);
				if (pass.equals(value)) {
					isTrue = 1;
				}
			}
			
			if (isTrue == 1) {
				MyFrame myframe = new MyFrame();
				myframe.setLocationRelativeTo(null);
				myframe.show();
				dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Bạn nhập sai mật khẩu", "Lỗi đăng nhập", JOptionPane.WARNING_MESSAGE);
			}
		}
		if (e.getSource()==btnExit) {
			dispose();
		}
	}

}
