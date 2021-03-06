package com.passbook.dao;

import java.util.List;

import com.passbook.model.User;

public interface IUserDao {
	
	public boolean addUser(User user);
	public boolean deleteUser(User user);
	public boolean updateUser(User user);
	
	public List<User> getAllUser();
	public User findUserByID(int id);
	public User userLogin(String username, String password);
}
