package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

public class StatisticFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnSold;
	private JButton btnReturned;
	private JButton btnOutofstock;
	private JButton btnExit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatisticFrame frame = new StatisticFrame();
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
	public StatisticFrame() {
		setResizable(false);
		setTitle("Thống kê");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 330, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Trong ng\u00E0y h\u00F4m nay", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 294, 80);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đã bán :");
		lblNewLabel.setBounds(10, 25, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblHngcTr = new JLabel("Hàng được trả :");
		lblHngcTr.setBounds(10, 50, 89, 14);
		panel.add(lblHngcTr);
		
		btnSold = new JButton("Xem thêm");
		btnSold.setBounds(178, 16, 106, 23);
		panel.add(btnSold);
		
		btnReturned = new JButton("Xem thêm");
		btnReturned.setBounds(178, 41, 106, 23);
		panel.add(btnReturned);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Kho h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 102, 294, 53);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Hết hàng :");
		lblNewLabel_1.setBounds(10, 25, 63, 14);
		panel_1.add(lblNewLabel_1);
		
		btnOutofstock = new JButton("Xem thêm");
		btnOutofstock.setBounds(182, 21, 102, 23);
		panel_1.add(btnOutofstock);
		
		btnExit = new JButton("Thoát");
		btnExit.setBounds(112, 160, 89, 23);
		contentPane.add(btnExit);
	}
}
