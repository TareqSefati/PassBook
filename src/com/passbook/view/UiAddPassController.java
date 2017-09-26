package com.passbook.view;

import com.jfoenix.controls.JFXTextField;
import com.passbook.controller.MainPassBookController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class UiAddPassController {
	
	private MainPassBookController mainPassBookController;
	
	public UiAddPassController() {
		this.mainPassBookController = new MainPassBookController();
	}

	@FXML
	private JFXTextField keyWord;

	@FXML
	private JFXTextField username;

	@FXML
	private JFXTextField password;

	@FXML
	private JFXTextField webAddress;

	@FXML
	void addEntity(ActionEvent event) {
		mainPassBookController.addEntity(keyWord.getText(), username.getText(), password.getText(), webAddress.getText());
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}

}
