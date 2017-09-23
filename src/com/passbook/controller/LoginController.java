package com.passbook.controller;

import java.io.IOException;

import com.passbook.service.UserService;

import javafx.event.ActionEvent;

public class LoginController {
	
	private UserService userService;
	
	//Initiating userService instance using this constructor
	public LoginController(UserService userService) {
		this.userService = userService;
	}
	
	public LoginController() {
		this.userService = new UserService();
	}
	
	//Manages login activity
	public void login(String username, String password, ActionEvent event) {
    	userService.login(username, password,event);	
	}

	public void dataConnectionClose() {	
		userService.dataConnectionClose();
		
	}

	public void addUser(String username, String password, String email, ActionEvent event) {
		userService.addUser(username, password, email, event);
	}

	public void registerUser(ActionEvent event) throws IOException {
		userService.registerUser(event);
	}
}
