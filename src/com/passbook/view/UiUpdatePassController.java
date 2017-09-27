package com.passbook.view;

import com.jfoenix.controls.JFXTextField;
import com.passbook.controller.MainPassBookController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class UiUpdatePassController {

	private MainPassBookController mainPassBookController;
	
	private int entityID;

	public UiUpdatePassController() {
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

	public int getEntityID() {
		return entityID;
	}

	public void setEntityID(int entityID) {
		this.entityID = entityID;
	}
	
	public String getKeyWord() {
		return keyWord.getText();
	}

	public void setKeyWord(String keyWord) {
		this.keyWord.setText(keyWord);
	}

	public String getUsername() {
		return username.getText();
	}

	public void setUsername(String username) {
		this.username.setText(username);
	}

	public String getPassword() {
		return password.getText();
	}

	public void setPassword(String password) {
		this.password.setText(password);
	}

	public String getWebAddress() {
		return webAddress.getText();
	}

	public void setWebAddress(String webAddress) {
		this.webAddress.setText(webAddress);
	}

	@FXML
	void deleteEntity(ActionEvent event) {
		mainPassBookController.deleteEntity(getEntityID(), event);
	}

	@FXML
	void updateEntity(ActionEvent event) {
		mainPassBookController.updateEntity(getEntityID(), getKeyWord(), getUsername(), getPassword(), getWebAddress(), event);
	}
	

    @FXML
    void cancel(ActionEvent event) {
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
    }
}
