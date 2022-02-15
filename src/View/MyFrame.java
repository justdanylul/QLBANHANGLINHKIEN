package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Result;

import Connect.MyConnect;
import Model.computer;
import Model.hethang;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class MyFrame extends JFrame implements ActionListener {
	
	MyConnect myconnect = new MyConnect();

	public JPanel contentPane;
	public JTextField textField;
	public JTable table;
	public JTable tableHang;
	public JButton btnUpdate;
	public JButton btnAdd;
	public JButton btnDelete;
	public JButton btnStatic;
	public JComboBox comboDanhmuc;
	public JComboBox comboHang;
	public JComboBox comboXep;

	public String tablee;

	public int isXep = 0;

	public int isHang = 0;

	public String where;

	public String order;

	public String text = "";
	
	public String[] hang = {"Tất cả"};
	
	public String[] hang2;

	public String[] arrHang = new String[100];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame frame = new MyFrame();
					frame.setLocationRelativeTo(null);
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
	public MyFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\Downloads\\Settings-icon.png"));
		this.myconnect.connect();
		setTitle("Quản lí bán hàng linh kiện máy tính");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 435);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel searchbar = new JPanel();
		searchbar.setBorder(new EmptyBorder(0, 0, 0, 0));
		searchbar.setBackground(new Color(255, 255, 255));
		searchbar.setBounds(221, 11, 669, 54);
		contentPane.add(searchbar);
		searchbar.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tên hàng :");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Nunito", Font.BOLD, 12));
		lblNewLabel_1.setBounds(16, 20, 58, 14);
		searchbar.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBackground(new Color(173, 216, 230));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
				if (text.equals("")) {
					loadDataWhere(tablee, where, order);
				}
				search();
			} catch (Exception e1) {
			}
			}
		});
		textField.setBounds(79, 17, 580, 20);
		searchbar.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\Admin\\Pictures\\Brown.png"));
		lblNewLabel_4.setBounds(0, 0, 669, 54);
		searchbar.add(lblNewLabel_4);
		
		JPanel itemBtn = new JPanel();
		itemBtn.setBackground(new Color(255, 255, 255));
		itemBtn.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		itemBtn.setBounds(10, 11, 201, 374);
		contentPane.add(itemBtn);
		itemBtn.setLayout(null);
		
		//Combobox content
		String danhmuc[] = {"VGA", "CPU", "Fans", "PSU","Ocung", "Mainboard", "CasePC"};
		String xep[] = {"Không","Giá cao đến thấp", "Giá thấp đến cao", "Hàng nhiều đến ít", "Hàng ít đến nhiều"};
		
		JLabel lblNewLabel_6_1 = new JLabel("Thực hiện bởi :");
		lblNewLabel_6_1.setForeground(Color.WHITE);
		lblNewLabel_6_1.setFont(new Font("Nunito", Font.PLAIN, 12));
		lblNewLabel_6_1.setBounds(9, 319, 191, 14);
		itemBtn.add(lblNewLabel_6_1);
		
		comboDanhmuc = new JComboBox(danhmuc);
		comboDanhmuc.setForeground(Color.BLACK);
		comboDanhmuc.setBackground(Color.WHITE);
		comboDanhmuc.setBounds(82, 121, 109, 22);
		itemBtn.add(comboDanhmuc);
		comboDanhmuc.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("Danh mục :");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Nunito", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 125, 72, 14);
		itemBtn.add(lblNewLabel);
		
		comboHang = new JComboBox(hang);
		comboHang.setBounds(82, 166, 109, 22);
		itemBtn.add(comboHang);
		comboHang.setEnabled(false);
		comboHang.addActionListener(this);
		
		JLabel lblHng = new JLabel("Hãng          :");
		lblHng.setForeground(Color.WHITE);
		lblHng.setFont(new Font("Nunito", Font.BOLD, 12));
		lblHng.setBounds(10, 170, 72, 14);
		itemBtn.add(lblHng);
		
		JLabel lblXpTheo = new JLabel("Xếp theo   :");
		lblXpTheo.setForeground(Color.WHITE);
		lblXpTheo.setFont(new Font("Nunito", Font.BOLD, 12));
		lblXpTheo.setBounds(10, 217, 72, 14);
		itemBtn.add(lblXpTheo);
		
		comboXep = new JComboBox(xep);
		comboXep.setBounds(82, 213, 109, 22);
		itemBtn.add(comboXep);
		comboXep.addActionListener(this);
		comboXep.setEnabled(false);
		
		JLabel lblNewLabel_6 = new JLabel("Trần Thành Đạt");
		lblNewLabel_6.setFont(new Font("Nunito", Font.PLAIN, 12));
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(54, 334, 109, 14);
		itemBtn.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Trần Phạm Quốc Bảo");
		lblNewLabel_7.setFont(new Font("Nunito", Font.PLAIN, 12));
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setBounds(54, 349, 130, 14);
		itemBtn.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\Admin\\Downloads\\273484372_634014161045268_3858369274943591692_n.png"));
		lblNewLabel_8.setBounds(37, 10, 132, 96);
		itemBtn.add(lblNewLabel_8);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Admin\\Pictures\\Brown.png"));
		lblNewLabel_3.setBounds(0, 0, 201, 374);
		itemBtn.add(lblNewLabel_3);
		
		JPanel data = new JPanel();
		data.setBorder(new EmptyBorder(0, 0, 0, 0));
		data.setBounds(221, 71, 669, 266);
		contentPane.add(data);
		data.setLayout(new BorderLayout(0, 0));
		
		//Table
		
		JScrollPane scrollPane = new JScrollPane(table = createTable());
		data.add(scrollPane, BorderLayout.CENTER);
		
		JPanel tableInterfere = new JPanel();
		tableInterfere.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tableInterfere.setBounds(221, 348, 669, 37);
		contentPane.add(tableInterfere);
		tableInterfere.setLayout(null);
		
		btnUpdate = new JButton("Chỉnh sửa");
		btnUpdate.setBounds(171, 7, 98, 23);
		tableInterfere.add(btnUpdate);
		btnUpdate.addActionListener(this);
		
		btnAdd = new JButton("Thêm");
		btnAdd.setBounds(279, 7, 77, 23);
		tableInterfere.add(btnAdd);
		btnAdd.addActionListener(this);
		
		btnDelete = new JButton("Xóa");
		btnDelete.setBounds(366, 7, 66, 23);
		tableInterfere.add(btnDelete);
		btnDelete.addActionListener(this);
		
		btnStatic = new JButton("Hết hàng");
		btnStatic.setBounds(442, 7, 98, 23);
		tableInterfere.add(btnStatic);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\Admin\\Pictures\\Brown.png"));
		lblNewLabel_5.setBounds(0, 0, 669, 37);
		tableInterfere.add(lblNewLabel_5);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Admin\\Pictures\\BG.png"));
		lblNewLabel_2.setBounds(0, 0, 900, 396);
		contentPane.add(lblNewLabel_2);
		btnStatic.addActionListener(this);
	}
	
	public JTable createTable() {
		JTable table = new JTable();
		return table;
	}
	
	public void loadDataSearch(String tablee, String where, String name, String order) {
		DefaultTableModel model = new DefaultTableModel();
		try {
		ResultSet rs = myconnect.searchName(tablee,where,name,order,isHang,isXep);
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
		} catch (Exception e) {
		}
	}
	
	public void loadDataWhere(String tablee, String where, String order) {
		DefaultTableModel model = new DefaultTableModel();
		
		ResultSet rs = myconnect.getDataWhere(tablee,where,order,isHang,isXep);
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
	
	public void loadDataHang(String tablee) {
		DefaultTableModel model = new DefaultTableModel();
		
		ResultSet rs = myconnect.getDataHang(tablee);
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
			tableHang.setModel(model);
		} catch (SQLException e) {
		}
	}
	
	//Searchbar
	
	public void search() {
		try {
		text = textField.getText().trim();
		loadDataSearch(tablee, where, text, order);
	} catch (Exception whatthefuck) {
	}
	}
	
	//Delete data
	
	public void deleteId() {
		int row = table.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(null, "Bạn cần chọn dòng dữ liệu để xóa", "Lỗi Xóa", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int select = JOptionPane.showOptionDialog(null, "Bạn có muốn xóa không ?", "Xóa", 0, JOptionPane.YES_NO_OPTION, null, null, 1);
		if (select == 0) {
			myconnect.delete(tablee, (String) table.getValueAt(row, 0));
			loadDataSearch(tablee, where, text, order);
			removeHang();
			addHang();
		}
	}
	
	//Find the Hang
	
	public void addHang() {
		int row = table.getRowCount();
		arrHang[0] = "no";
		int i = 1;
		int a = 0;
		try {
		while (i <= row) {
			int isDuplicate = 0;
			String value = (String) table.getValueAt(a,2);
			a++;
			for (int j = 0; j < i; j++) {
				if (value.equals(arrHang[j])) {
					isDuplicate = 1;
				}
			}
			if (isDuplicate == 0) {
//				System.out.println(value);
				arrHang [i] = value;
				comboHang.addItem(arrHang[i]);
//				System.out.println(arrHang[i]);
				i++;
			}
		}
		} catch (Exception ayo) {
		}
	}
	
	//Delete the Hang
	
	public void removeHang() {
		int item = comboHang.getItemCount();
		for (int i = 1; i < item; i++) {
			comboHang.removeItem(arrHang[i]);
		}
	}
	
	//Out of stock
	
	public void outOfStock() {
		int row = table.getRowCount();
		try {
		for (int i = 1; i <= row; i++) {
			String luong = (String) table.getValueAt(i, 5);
			if (luong.equals("0")) {
				String mahang = (String) table.getValueAt(i, 0);
				String ten = (String) table.getValueAt(i, 1);
				myconnect.addOut(new hethang(mahang, ten));
			}
		}
		} catch (Exception adu) {
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String sqlCommand = "Select * from "+table+" where Hang = '"+where+"' order by "+order;
		if (e.getSource()==btnUpdate) {
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "Bạn cần chọn dòng dữ liệu để chỉnh sửa", "Lỗi chỉnh sửa", JOptionPane.ERROR_MESSAGE);
				return;
			}
			UpdateFrame upframe = new UpdateFrame();
			upframe.setLocationRelativeTo(null);
			upframe.tfTen.setText((String) table.getValueAt(row, 1));
			upframe.tfHang.setText((String) table.getValueAt(row, 2));
			upframe.tfGiamoi.setText((String) table.getValueAt(row, 3));
			upframe.tfGiacu.setText((String) table.getValueAt(row, 4));
			upframe.tfSoluong.setText((String) table.getValueAt(row, 5));
			
			upframe.btnHuy.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e2) {
					upframe.dispose();
				}
			});
			
			upframe.btnEdit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e1) {
					String id = (String) table.getValueAt(row, 0);
					int select = JOptionPane.showOptionDialog(null, "Bạn có muốn chỉnh sửa không? ?", "Chỉnh sửa", 0, JOptionPane.YES_NO_OPTION, null, null, 1);
					if (select == 0) {
					myconnect.update(tablee, id, new computer(id, upframe.tfTen.getText().trim(), upframe.tfHang.getText().trim(),
							Double.parseDouble(upframe.tfGiamoi.getText().trim()), Double.parseDouble(upframe.tfGiacu.getText().trim()),
							Double.parseDouble(upframe.tfSoluong.getText().trim())));
					loadDataSearch(tablee, where, text, order);
					removeHang();
					addHang();
					}
				}
			});
			upframe.show();
		}
		if (e.getSource()==btnAdd) {
			System.out.println("ok");
			AddFrame addframe = new AddFrame();
			addframe.setLocationRelativeTo(null);
			addframe.btnThem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e69) {
					String mahang = addframe.tfMahang.getText().trim();
					String ten = addframe.tfTen.getText().trim();
					String hang = addframe.tfHang.getText().trim();
					double giamoi = Double.parseDouble(addframe.tfGiamoi.getText().trim());
					double giacu = Double.parseDouble(addframe.tfGiacu.getText().trim());
					double soluong = Double.parseDouble(addframe.tfSoluong.getText().trim());
					String table3 = (String) addframe.comboDanhmuc.getSelectedItem();
					
					if (table3.equals("")) {
						JOptionPane.showMessageDialog(null, "Bạn cần chọn danh mục", "Lỗi thêm dữ liệu", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						try {
						myconnect.add(table3, new computer(mahang, ten, hang, giamoi, giacu, soluong));
						loadDataSearch(tablee, where, text, order);
						removeHang();
						addHang();
						} catch (Exception ayowhat) {
							JOptionPane.showMessageDialog(null, "Bạn nhập đầy đủ thông tin", "Lỗi thêm dữ liệu", JOptionPane.INFORMATION_MESSAGE);
						}
						JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công", "Thêm thành công", JOptionPane.PLAIN_MESSAGE);
					}
				}
			});
			addframe.show();
		}
		if (e.getSource()==btnDelete) {
			deleteId();
		}
		if (e.getSource()==btnStatic) {
			BoughtFrame stacframe = new BoughtFrame();
			stacframe.btnReset.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					myconnect.deleteOut();
					stacframe.loadData();
				}
			});
			outOfStock();
			stacframe.loadData();
			stacframe.show();
		}
		if (e.getSource()==comboDanhmuc) {
			tablee = (String) comboDanhmuc.getSelectedItem();
			loadDataSearch(tablee, where, text, order);
			removeHang();
			addHang();
			comboHang.setEnabled(true);
			comboXep.setEnabled(true);
		}
		if (e.getSource()==comboHang) {
//			textField.setText("");
			where = (String) comboHang.getSelectedItem();
			if (where.equals("Tất cả")) {
				isHang = 0;
				loadDataSearch(tablee, "", text, order);
			}
			else {
			isHang = 1;
			loadDataSearch(tablee, where, text, order);
			}
//			System.out.println(sqlCommand);
		}
		if (e.getSource()==comboXep) {
//			textField.setText("");
			order = (String) comboXep.getSelectedItem();
			if (order.equals("Không")) {
				isXep = 0;
				loadDataSearch(tablee, where, text, order);
			}
			if (order.equals("Giá cao đến thấp")) {
				isXep = 1;
				loadDataSearch(tablee, where, text, "Gia_moi DESC");
				isXep = 0;
			}
			if (order.equals("Giá thấp đến cao")) {
				isXep = 1;
				loadDataSearch(tablee, where, text, "Gia_moi ASC");
				isXep = 0;
			}
			if (order.equals("Hàng nhiều đến ít")) {
				isXep = 1;
				loadDataSearch(tablee, where, text, "So_luong DESC");
				isXep = 0;
			}
			if (order.equals("Hàng ít đến nhiều")) {
				isXep = 1;
				loadDataSearch(tablee, where, text, "So_luong ASC");
				isXep = 0;
			}
//			System.out.println(sqlCommand);
		}
//		String xep[] = {"Không","Giá cao đến thấp", "Giá thấp đến cao", "Hàng nhiều đến ít", "Hàng ít đến nhiều"};
	}
}
