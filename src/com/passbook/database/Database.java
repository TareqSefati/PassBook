package com.passbook.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Database implements IDatabase {

	private Connection connection;
	
	public abstract void setup() throws Exception;

	@Override
	public void connect() {
		try {
			if(connection == null)
				connection = DriverManager.getConnection(IDatabase.DB_URL + ";create=true");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to connect with database. " + e.getMessage());
			System.out.println("Responsible class is: " + e.getClass());
		}
	}

	@Override
	public void close() {
		try {
			//connection = DriverManager.getConnection(IDatabase.DB_URL + ";create=true");
			if(connection != null)
				connection.close();
			//DriverManager.getConnection("jdbc:derby:;shutdown=true");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to shutdown the connection. " + e.getMessage());
			System.out.println("Responsible class is: " + e.getClass());
		}
	}

}
