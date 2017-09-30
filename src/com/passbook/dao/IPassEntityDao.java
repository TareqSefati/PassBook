package com.passbook.dao;

import java.util.List;

import com.passbook.model.PassEntity;

public interface IPassEntityDao {

	public boolean addPassEntity(PassEntity passEntity);
	public boolean deletePassEntity(PassEntity passEntity);
	public boolean updatePassEntity(PassEntity passEntity);
	
	public List<PassEntity> getAllPassEntity();
	public PassEntity findPassEntityByID(int passEntityID);
	public List<PassEntity> findPassEntitiesByUserID(int userID);
	public List<PassEntity> findPassEntitiesByKeyWord(String key, int userID);
	
}
