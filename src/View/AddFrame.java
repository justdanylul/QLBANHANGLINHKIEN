package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Connect.MyConnect;
import Model.computer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class AddFrame extends JFrame implements ActionListener {
	MyConnect myconnect = new MyConnect();
	MyFrame myframe = new MyFrame();

	public JPanel contentPane;
	public JTextField tfMahang;
	public JTextField tfTen;
	public JTextField tfHang;
	public JTextField tfGiamoi;
	public JTextField tfGiacu;
	public JTextField tfSoluong;

	public JComboBox comboDanhmuc;
	public JButton btnThem;
	public JButton btnHuy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFrame frame = new AddFrame();
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
	public AddFrame() {
		this.myconnect.connect();
		setTitle("Thêm dữ liệu");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Downloads\\Add-Folder-icon.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 376, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã hàng :");
		lblNewLabel.setBounds(10, 21, 57, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên :");
		lblNewLabel_1.setBounds(10, 46, 57, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Hãng :");
		lblNewLabel_2.setBounds(10, 73, 57, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Giá mới :");
		lblNewLabel_3.setBounds(10, 99, 57, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Giá cũ :");
		lblNewLabel_4.setBounds(10, 124, 57, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Số lượng :");
		lblNewLabel_5.setBounds(10, 149, 72, 14);
		contentPane.add(lblNewLabel_5);
		
		tfMahang = new JTextField();
		tfMahang.setBounds(79, 18, 268, 20);
		contentPane.add(tfMahang);
		tfMahang.setColumns(10);
		
		tfTen = new JTextField();
		tfTen.setColumns(10);
		tfTen.setBounds(79, 43, 268, 20);
		contentPane.add(tfTen);
		
		tfHang = new JTextField();
		tfHang.setColumns(10);
		tfHang.setBounds(79, 70, 268, 20);
		contentPane.add(tfHang);
		
		tfGiamoi = new JTextField();
		tfGiamoi.setColumns(10);
		tfGiamoi.setBounds(79, 96, 268, 20);
		contentPane.add(tfGiamoi);
		
		tfGiacu = new JTextField();
		tfGiacu.setColumns(10);
		tfGiacu.setBounds(79, 121, 268, 20);
		contentPane.add(tfGiacu);
		
		tfSoluong = new JTextField();
		tfSoluong.setColumns(10);
		tfSoluong.setBounds(79, 146, 268, 20);
		contentPane.add(tfSoluong);
		
		btnThem = new JButton("Thêm");
		btnThem.setBounds(64, 207, 89, 23);
		contentPane.add(btnThem);
		
		btnHuy = new JButton("Hủy");
		btnHuy.setBounds(224, 207, 89, 23);
		contentPane.add(btnHuy);
		
		String danhmuc[] = {"","VGA", "CPU", "Fans", "PSU","Ocung", "Mainboard", "CasePC"};
		
		comboDanhmuc = new JComboBox(danhmuc);
		comboDanhmuc.setBounds(150, 177, 89, 22);
		contentPane.add(comboDanhmuc);
		
		JLabel lblNewLabel_5_1 = new JLabel("Danh mục :");
		lblNewLabel_5_1.setBounds(79, 182, 72, 14);
		contentPane.add(lblNewLabel_5_1);
		
		btnThem.addActionListener(this);
		btnHuy.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals("Hủy")) {
			dispose();
		}
	}
}
