package com.passbook.dao;

import com.passbook.model.PassEntity;

import javafx.collections.ObservableList;

public interface IMainPassBookDao {
	
	public ObservableList<PassEntity> getAllEntities();
}
