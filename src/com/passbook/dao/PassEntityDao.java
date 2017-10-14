package com.passbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.passbook.database.Database;
import com.passbook.database.IDatabase;
import com.passbook.model.PassEntity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PassEntityDao extends Database implements IPassEntityDao, IDatabase {

	private Connection connection;
	private QueryRunner queryRunner;
	private static final List<PassEntity> EMPTY = new ArrayList<>();

	public PassEntityDao() {
		try {
			if (connection == null)
				connection = DriverManager.getConnection(IDatabase.DB_URL + ";create=true");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		queryRunner = new QueryRunner();
	}

	@Override
	public void setup() throws Exception {

		try {
			if (connection == null)
				connection = DriverManager.getConnection(IDatabase.DB_URL + ";create=true");
			queryRunner.update(connection,
					"CREATE TABLE PASSENTITIES ("
							+ "passEntityID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
							+ "userID INTEGER NOT NULL, " + "keyWord VARCHAR(60), entityUsername VARCHAR(60), "
							+ "entityPassword VARCHAR(80), webAddress VARCHAR(1024), " + "PRIMARY KEY (passEntityID), "
							+ "FOREIGN KEY (userID) REFERENCES USERS (userID) " + ")");
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

	@Override
	public boolean addPassEntity(PassEntity passEntity) {

		try {
			queryRunner.insert(connection,
					"INSERT INTO PASSENTITIES (userID, keyWord, entityUsername, entityPassword, webAddress) VALUES (?,?,?,?,?)",
					new ScalarHandler<Integer>(), passEntity.getUserID(), passEntity.getKeyWord(),
					passEntity.getEntityUsername(), passEntity.getEntityPassword(), passEntity.getWebAddress());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to Add New Pass Entity. " + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean deletePassEntity(PassEntity passEntity) {

		try {
			queryRunner.update(connection, "DELETE FROM PASSENTITIES WHERE passEntityID=?",
					passEntity.getPassEntityID());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to Delete Pass Entity. " + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean updatePassEntity(PassEntity passEntity) {
		try {
			queryRunner.update(connection,
					"UPDATE PASSENTITIES SET keyWord=?, entityUsername=?, entityPassword=?, webAddress=? WHERE passEntityID=?",
					passEntity.getKeyWord(), passEntity.getEntityUsername(), passEntity.getEntityPassword(),
					passEntity.getWebAddress(), passEntity.getPassEntityID());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to Update Pass Entity. " + e.getMessage());
		}
		return false;
	}

	@Override
	public List<PassEntity> getAllPassEntity() {

		try {
			return queryRunner.query(connection, "SELECT * FROM PASSENTITIES",
					new BeanListHandler<PassEntity>(PassEntity.class));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to get All Pass entities. " + e.getMessage());
		}
		return EMPTY;
	}

	@Override
	public PassEntity findPassEntityByID(int passEntityID) {
		try {
			return queryRunner.query(connection, "SELECT * FROM PASSENTITIES WHERE passEntityID=?",
					new BeanHandler<PassEntity>(PassEntity.class), passEntityID);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to get Pass entities with passEntityID " + passEntityID + ". " + e.getMessage());
		}
		return null;
	}

	@Override
	public ObservableList<PassEntity> findPassEntitiesByUserID(int userID) {

		try {
			ObservableList<PassEntity> list = FXCollections
					.observableList(queryRunner.query(connection, "SELECT * FROM PASSENTITIES WHERE userID=?",
							new BeanListHandler<PassEntity>(PassEntity.class), userID));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to get All Pass entities of userID " + userID + ". " + e.getMessage());
		}
		return null;
	}

	@Override
	public ObservableList<PassEntity> findPassEntitiesByKeyWord(String key, int userID) {
		try {
			ObservableList<PassEntity> list = FXCollections
					.observableList(queryRunner.query(connection, "SELECT * FROM PASSENTITIES WHERE keyWord LIKE ? AND userID=?",
							new BeanListHandler<PassEntity>(PassEntity.class), "%" + key + "%", userID));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to get Pass entities of pattren key " + key + ". " + e.getMessage());
		}
		return null;
	}

	@Override
	public boolean resetPassBookDatabase(int userID) {
		try {
			queryRunner.update(connection, "DELETE FROM PASSENTITIES WHERE userID=?",userID);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to Reset Pass Entity Table. " + e.getMessage());
		}
		return false;	
	}
}
