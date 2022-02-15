package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginConnect {
	
	public final String className ="com.mysql.jdbc.Driver";
	public final String url = "jdbc:mysql://localhost:3306/login";
	public final String user = "root";
	public final String pass = "6969";
	
	String table = "acc";
	
	public Connection connection;
	
	public void connect() {
		try {
			Class.forName(className);
			try {
				connection = DriverManager.getConnection(url, user, pass);
				System.out.println("Connect");
			} catch (SQLException e) {
				System.out.println("Class not found");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Error");
		}
	}
	
	public void showData(ResultSet rs) {
		try {
			while (rs.next()) {
				System.out.printf("%-10s %-20s \n", rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
		}
	}
	
	public ResultSet getData() {
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
	
	public static void main(String[] args) {
		LoginConnect yes = new LoginConnect();
		yes.connect();
		yes.showData(yes.getData());
	}

}
