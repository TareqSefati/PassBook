package com.passbook.database;

public interface IDatabase {
	
	public static String DB_URL = "jdbc:derby:passbook.db";
	
	public void connect() throws Exception;
	public void close() throws Exception;
}
