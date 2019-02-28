package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.*;

public class DBUtil {
	// table
	public static final String TABLE_PASSWORD = "table_user_password";
	public static final String TABLE_REQUIREMENT_INFOS = "table_requirement_infos";
	public static final String TABLE_INFORMATIONS = "table_informations";
	public static final String TABLE_TASK_INFO = "table_task_info";
	public static final String TABLE_PROJECT_INFO = "table_project_info";
	public static final String TABLE_FROM_MESSAGE_TO = "table_from_message_to";
	
	// connect to MySql database
	public static Connection getConnect() {
		String url = "jdbc:mysql://localhost:3306/teamwork"; // 数据库的Url
		Connection connecter = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); // java反射，固定写法
			connecter = (Connection) DriverManager.getConnection(url, "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connecter;
	}
}
