package com.daoconnec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {

	private static Connection connection;
	
	public static Connection getConnection1() throws SQLException
	{
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl="jdbc:oracle:thin:@drdw-scan.bansel.it:1521/OTH_PPORABOH.bsella.it";
			String userName="STAGEO99";
			String password="Mahesh@534,";
			connection=DriverManager.getConnection(dburl,userName,password);
			
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
}
