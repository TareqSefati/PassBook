package com.passbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.passbook.database.Database;
import com.passbook.database.IDatabase;
import com.passbook.model.PassEntity;
import com.passbook.model.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainPassBookDao extends Database implements IMainPassBookDao, IDatabase {

	private Connection connection;
	private QueryRunner queryRunner;
	
	private static final ObservableList<PassEntity> EMPTY = FXCollections.observableArrayList();
	
	public MainPassBookDao() {
		try {
			if(connection == null)
				connection = DriverManager.getConnection(IDatabase.DB_URL + ";create=true");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		queryRunner = new QueryRunner();
	}
	
	@Override
	public void setup() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public ObservableList<PassEntity> getAllEntities() {
		
		return EMPTY;
	}

}
