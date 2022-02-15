package Connect;

import java.awt.Taskbar.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.computer;
import Model.hethang;

public class MyConnect {
	
	public static String gpu = "gpu";
	public final String className ="com.mysql.jdbc.Driver";
	public final String url = "jdbc:mysql://localhost:3306/ql_banhanglinhkien";
	public final String user = "root";
	public final String pass = "6969";
	
	
	public Connection connection;
	
	public void connect() {
		try {
			Class.forName(className);
			try {
				connection = DriverManager.getConnection(url, user, pass);
				System.out.println("Connected");
			} catch (SQLException e) {
				System.out.println("Class not found");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Connect error");
		}
	}
	
	public void showData(ResultSet rs) {
		try {
			while (rs.next()) {
				System.out.printf("%-10s %-20s %-20s %-20s %-20s %-5.2f \n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6));
			}
		} catch (SQLException e) {
		}
	}
	
	public ResultSet getData(String table) {
		ResultSet rs = null;
		String sqlCommand = "Select * from "+table;
		Statement st;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			System.out.println("Error : "+e.toString());
		}
		return rs;
	}
	
	
	
	public ResultSet getDataWhere(String table, String where, String order, int isHang, int isXep) {
		ResultSet rs = null;
		String sqlCommand = null;
		if (isHang == 1) {
			if (isXep == 1) {
				sqlCommand = "Select * from "+table+" where Hang = '"+where+"' order by "+order;
			}
			else sqlCommand = "Select * from "+table+" where Hang = '"+where+"'";
		}
		else if (isXep == 1) {
			sqlCommand = "Select * from "+table+" order by "+order;
		}
		else sqlCommand = "Select * from "+table;
		
		Statement st;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			System.out.println("Error : "+e.toString());
		}
		return rs;
	}
	
	public ResultSet searchName(String table, String where, String name, String order, int isHang, int isXep) {
		ResultSet rs = null ;
		try {
		String sqlCommand = null;
		if (isHang == 1) {
			if (isXep == 1) {
				sqlCommand = "Select * from "+table+" where Hang = '"+where+"' and Ten like '%"+name+"%' order by "+order;
			}
			else sqlCommand = "Select * from "+table+" where Hang = '"+where+"' and Ten like '%"+name+"%'";
		}
		else if (isXep == 1) {
			sqlCommand = "Select * from "+table+" where Ten like '%"+name+"%' order by "+order;
		}
		else sqlCommand = "Select * from "+table+" where Ten like '%"+name+"%'";
		Statement st;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
		}
		} catch (Exception e) {
		}
		return rs;
	}
	
	public void showOut(ResultSet rs) {
		try {
			while (rs.next()) {
				System.out.printf("%-10s %-20s \n", rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
		}
	}
	
	public ResultSet getOut() {
		ResultSet rs = null;
		String sqlCommand = "select * from het_hang";
		Statement st;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
		}
		
		return rs;
	}
	
	public ResultSet getDataHang(String table) {
		ResultSet rs = null;
		String sqlCommand = "Select distinct Hang from "+table;
		Statement st;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(sqlCommand);
		} catch (SQLException e) {
			System.out.println("Error : "+e.toString());
		}
		return rs;
	}
	
	public void showDataHang(ResultSet rs) {
		try {
			while (rs.next()) {
				System.out.printf("%-10s \n", rs.getString(1));
			}
		} catch (SQLException e) {
		}
	}
	
	public void add(String table, computer c) {
		String sqlCommand = "Insert into "+table+" values (?, ?, ?, ?, ?, ?)";
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement(sqlCommand);
			pst.setString(1, c.getMa_hang());
			pst.setString(2, c.getTen());
			pst.setString(3, c.getHang());
			pst.setDouble(4, c.getGia_moi());
			pst.setDouble(5, c.getGia_cu());
			pst.setDouble(6, c.getSo_luong());
			if (pst.executeUpdate() < 0) System.out.println("Insert error");
			else System.out.println("Insert success");
		} catch (SQLException e) {
			System.out.println("Error : "+e.toString());
		}
	}
	
	public void delete(String table, String id) {
		String sqlCommand = "delete from "+table+" where Ma_hang = ?";
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement(sqlCommand);
			pst.setString(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error : "+e.toString());
		}
	}
	
	public void update(String table, String id, computer c) {
		String sqlCommand = "update "+table+" set Ten = ?, Hang = ?, Gia_moi = ?, Gia_cu = ?, So_luong = ? where Ma_hang = ?";
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement(sqlCommand);
			pst.setString(1, c.getTen());
			pst.setString(2, c.getHang());
			pst.setDouble(3, c.getGia_moi());
			pst.setDouble(4, c.getGia_cu());
			pst.setDouble(5, c.getSo_luong());
			pst.setString(6, c.getMa_hang());
			if (pst.executeUpdate() < 0) System.out.println("Update error");
			else System.out.println("Updated");
		} catch (SQLException e) {
			System.out.println("Error : "+e.toString());
		}
	}
	
	public void addOut(hethang c) {
		String sqlCommand = "Insert into het_hang values (?, ?)";
		PreparedStatement pst;
		try {
			pst = connection.prepareStatement(sqlCommand);
			pst.setString(1, c.getMahang());
			pst.setString(2, c.getTen());
			if (pst.executeUpdate() < 0) System.out.println("Insert error");
			else System.out.println("Insert success");
		} catch (SQLException e) {
			System.out.println("Error : "+e.toString());
		}
	}
	
	public void deleteOut() {
		String sqlCommand = "Delete from het_hang";
		Statement st;
		try {
			st = connection.createStatement();
			st.executeUpdate(sqlCommand);
		} catch (SQLException e) {
		}
	}
	
	public static void main(String[] args) {
		MyConnect myconnect = new MyConnect();
		myconnect.connect();
		myconnect.deleteOut();
		myconnect.showOut(myconnect.getOut());
//		myconnect.add("GPU", new computer("02", "GPU 5", "AMD", 300, 100, 9));
//		myconnect.showData(myconnect.getData("vga"));
//		myconnect.showData(myconnect.getDataWhere("GPU", "Nvidia", "Gia_moi DESC", 1, 1));
//		myconnect.delete("GPU", "02");
//		myconnect.showDataHang(myconnect.getDataHang("GPU"));
//		myconnect.update("GPU", "01", new computer("01","GPU RANDOM", "Zotech", 300, 800, 24));
//		myconnect.showData(myconnect.searchName("GPU", "Nvidia", "", "Gia_moi DESC", 0, 0));
	}

}
