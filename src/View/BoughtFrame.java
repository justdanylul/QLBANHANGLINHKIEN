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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Connect.MyConnect;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class BoughtFrame extends JFrame implements ActionListener {
	
	MyConnect myconnect = new MyConnect();

	private JPanel contentPane;
	private JTable table;

	public JButton btnExit;

	public JButton btnReset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoughtFrame frame = new BoughtFrame();
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
	public BoughtFrame() {
		setResizable(false);
		this.myconnect.connect();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 564, 287);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane(table = createTable());
		panel.add(scrollPane, BorderLayout.CENTER);
		
		btnExit = new JButton("Thoát");
		btnExit.setBounds(203, 305, 89, 23);
		contentPane.add(btnExit);
		
		btnReset = new JButton("Làm mới");
		btnReset.setBounds(302, 305, 89, 23);
		contentPane.add(btnReset);
		btnExit.addActionListener(this);
	}
	
	public JTable createTable() {
		JTable table= new JTable();
		
		return table;
	}
	
	public void loadData() {
		DefaultTableModel model = new DefaultTableModel();
		
		ResultSet rs = myconnect.getOut();
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
			table.setModel(model);
		} catch (SQLException e) {
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (e.getSource()==btnExit) {
			dispose();
		}
	}
}
