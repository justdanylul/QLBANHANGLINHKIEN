package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Connect.MyConnect;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateFrame extends JFrame {
	
	MyConnect myconnect = new MyConnect(); 

	public JPanel contentPane;
	public JTextField tfTen;
	public JTextField tfHang;
	public JTextField tfGiamoi;
	public JTextField tfGiacu;
	public JTextField tfSoluong;
	public JButton btnEdit;
	public JButton btnHuy;

	String Ten;

	public String Hang;

	public double Giamoi;

	public double Giacu;

	public double Soluong;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateFrame frame = new UpdateFrame();
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
	public UpdateFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Downloads\\edit-file-icon.png"));
		setTitle("Chỉnh sửa dữ liệu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 355, 203);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTn = new JLabel("Tên :");
		lblTn.setBounds(10, 14, 68, 14);
		contentPane.add(lblTn);
		
		JLabel lblHng = new JLabel("Hãng :");
		lblHng.setBounds(10, 39, 68, 14);
		contentPane.add(lblHng);
		
		JLabel lblGiMi = new JLabel("Giá mới :");
		lblGiMi.setBounds(10, 64, 68, 14);
		contentPane.add(lblGiMi);
		
		JLabel lblGiC = new JLabel("Giá cũ :");
		lblGiC.setBounds(10, 89, 68, 14);
		contentPane.add(lblGiC);
		
		JLabel lblSLng = new JLabel("Số lượng :");
		lblSLng.setBounds(10, 114, 68, 14);
		contentPane.add(lblSLng);
		
		tfTen = new JTextField();
		tfTen.setColumns(10);
		tfTen.setBounds(75, 11, 250, 20);
		contentPane.add(tfTen);
		
		tfHang = new JTextField();
		tfHang.setColumns(10);
		tfHang.setBounds(75, 36, 250, 20);
		contentPane.add(tfHang);
		
		tfGiamoi = new JTextField();
		tfGiamoi.setColumns(10);
		tfGiamoi.setBounds(75, 61, 250, 20);
		contentPane.add(tfGiamoi);
		
		tfGiacu = new JTextField();
		tfGiacu.setColumns(10);
		tfGiacu.setBounds(75, 86, 250, 20);
		contentPane.add(tfGiacu);
		
		tfSoluong = new JTextField();
		tfSoluong.setColumns(10);
		tfSoluong.setBounds(75, 111, 250, 20);
		contentPane.add(tfSoluong);
		
		btnEdit = new JButton("Chỉnh sửa");
		btnEdit.setBounds(75, 139, 101, 23);
		contentPane.add(btnEdit);
		
		btnHuy = new JButton("Hủy");
		btnHuy.setBounds(193, 139, 101, 23);
		contentPane.add(btnHuy);
	}

}
