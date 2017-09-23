package com.passbook.view;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.passbook.controller.LoginController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class UiLoginController {
	
	private LoginController loginController;
//	public UiLoginController() {
//		this.loginController = new LoginController();
//	}
	
	public UiLoginController(LoginController loginController, Stage stage){
		this.loginController = loginController;
		stage.setOnCloseRequest(t -> {loginController.dataConnectionClose();
		System.out.println("data connection close from uiLogin Controller.");
	});
	}
	
	@FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private Hyperlink newUser;

    @FXML
    private ImageView appLogo;

    @FXML
    private JFXButton btnLogin;

    //Action Event for Login button pressed in login window
    @FXML
    void login(ActionEvent event) {
    	loginController.login(usernameField.getText(), passwordField.getText(), event);
    }

  //Action Event for Register New User Link clicked in login window
    @FXML
    void registerNewUser(ActionEvent event) throws IOException {
    	loginController.registerUser(event);
    }


}
