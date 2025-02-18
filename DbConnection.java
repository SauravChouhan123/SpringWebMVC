package in.sp.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	private static Connection con;

	public static Connection getConnection() {
		try {
			if (con == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mvc_db1", "root", "root");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;

	}
}
