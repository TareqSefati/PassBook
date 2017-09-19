package com.passbook.view;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.passbook.controller.LoginController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class UiRegisterController {

	private LoginController loginController;

	public UiRegisterController(LoginController loginController, Stage stage) {
		this.loginController = loginController;
		stage.setOnCloseRequest(t -> loginController.dataConnectionClose());
	}

	public UiRegisterController() {

	}

	@FXML
	private JFXTextField username;

	@FXML
	private JFXPasswordField password;

	@FXML
	private JFXTextField email;

	@FXML
	private ImageView appLogo;

	@FXML
	void backToLogin(ActionEvent event) {

	}

	@FXML
	void registerUser(ActionEvent event) {
		 loginController.addUser(username.getText(), password.getText(),
		 email.getText());
	}
}
