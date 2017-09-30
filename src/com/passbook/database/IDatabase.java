package com.passbook.database;

import java.sql.Connection;

public interface IDatabase {
	
	public static String DB_URL = "jdbc:derby:src/passbook.db";
	
	public Connection connect() throws Exception;
	public void close() throws Exception;
}
