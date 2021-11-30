package br.com.escola.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static final String USERNAME = "root";
	
	private static final String PASSWORD = "dale";
	
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/dados?autoReconnect=true&useSSL=false";
	
	
	public static Connection createConnectionToMySQL() throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}
	
	public static void main(String[] args) throws Exception {
		
		Connection con = createConnectionToMySQL();
		
		if(con!=null) {
			System.out.println("Conectado!");
			con.close();
		}
	}
}
