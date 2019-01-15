package ie.todolist.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	public static void main(String[] args) throws SQLException {
		Connection connection = DriverManager.getConnection("");
		System.out.println("Connection open");
		connection.close();
	}
}
