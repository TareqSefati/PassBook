package com.passbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.passbook.database.Database;
import com.passbook.database.IDatabase;
import com.passbook.model.User;

public class UserDao extends Database implements IUserDao, IDatabase {

	private Connection connection;
	private QueryRunner queryRunner;

	private static final List<User> EMPTY = new ArrayList<>();

	public UserDao() {
		try {
			if (connection == null)
				this.connection = DriverManager.getConnection(IDatabase.DB_URL + ";create=true");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		queryRunner = new QueryRunner();
	}

	// Create Database and User Table
	@Override
	public void setup() {

		try {
			if (connection == null)
				connection = DriverManager.getConnection(IDatabase.DB_URL + ";create=true");
			queryRunner.update(connection,
					"CREATE TABLE USERS ("
							+ "userID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
							+ "username VARCHAR(60), password VARCHAR(80), email VARCHAR(60),"
							+ "role VARCHAR(15), enabled BOOLEAN, PRIMARY KEY (userID)" + ")");
		} catch (SQLException e) {
			if (e.getSQLState().equals("X0Y32")) {
				return;
			} else {
				System.out.println("Creating Table failed. " + e.getMessage());
				System.out.println("Responsible Class: " + e.getClass());
				e.printStackTrace();
			}
		}
	}

	// Getting all users
	@Override
	public List<User> getAllUser() {
		try {
			return queryRunner.query(connection, "SELECT * FROM USERS", new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to get All Users. " + e.getMessage());
		}
		return EMPTY;
	}

	// Adding a new user
	@Override
	public boolean addUser(User user) {

		try {
			queryRunner.insert(connection,
					"INSERT INTO USERS (username, password, email, role, enabled) VALUES (?,?,?,?,?)",
					new ScalarHandler<Integer>(), user.getUsername(), user.getPassword(), user.getEmail(),
					user.getRole(), user.isEnabled());
			// queryRunner.update(connection, "INSERT INTO USERS (username,
			// password, email, role, enabled) VALUES (?,?,?,?,?)",
			// user.getUsername(), user.getPassword(), user.getEmail(),
			// user.getRole(), user.isEnabled());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to Add User. " + e.getMessage());
		}
		return false;
	}

	// Deleting a user
	@Override
	public boolean deleteUser(User user) {
		try {
			queryRunner.update(connection, "DELETE FROM USERS WHERE userID=?", user.getUserID());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to Delete User. " + e.getMessage());
		}
		return false;
	}

	// Updating a user
	@Override
	public boolean updateUser(User user) {
		try {
			queryRunner.update(connection,
					"UPDATE USERS SET username=?, password=?, email=?, role=?, enabled=? WHERE userID=?",
					user.getUsername(), user.getPassword(), user.getEmail(), user.getRole(), user.isEnabled(),
					user.getUserID());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to Update User. " + e.getMessage());
		}
		return false;
	}

	// Getting a user by ID
	@Override
	public User findUserByID(int id) {
		try {
			return queryRunner.query(connection, "SELECT * FROM USERS WHERE userID=?",
					new BeanHandler<User>(User.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to find the user with id " + id + ". " + e.getMessage());
		}
		return null;
	}

	// Getting a user by username and password
	@Override
	public User userLogin(String username, String password) {

		try {
			return queryRunner.query(connection, "SELECT * FROM USERS WHERE username=? AND password=?",
					new BeanHandler<User>(User.class), username, DigestUtils.sha1Hex(password));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to find the user with [username:password] = [" + username + ":" + password
					+ "]. " + e.getMessage());
		}
		return null;
	}

}
