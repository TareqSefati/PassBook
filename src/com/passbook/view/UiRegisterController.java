package com.passbook.view;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.passbook.controller.LoginController;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class UiRegisterController {

	private LoginController loginController;

	public UiRegisterController(Stage stage) {
		this.loginController = new LoginController();
		stage.setOnCloseRequest(t -> {
			loginController.dataConnectionClose();
			System.out.println("data connection close from uiRegister Controller.");
			Platform.exit();
			System.exit(0);
		});
	}

	public UiRegisterController() {
		this.loginController = new LoginController();
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
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	@FXML
	void registerUser(ActionEvent event) {
		loginController.addUser(username.getText(), password.getText(), email.getText(), event);
	}

	@FXML
	void cancel(ActionEvent event) {
		loginController.dataConnectionClose();
		Platform.exit();
		System.exit(0);
	}
}
