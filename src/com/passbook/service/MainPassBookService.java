package com.passbook.service;

import java.io.IOException;

import com.passbook.dao.MainPassBookDao;
import com.passbook.dao.PassEntityDao;
import com.passbook.model.PassEntity;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainPassBookService {

	// private MainPassBookDao mainPassBookDao;
	private PassEntityDao passEntityDao;

	public MainPassBookService() {
		// this.mainPassBookDao = new MainPassBookDao();
		// mainPassBookDao.connect();
		this.passEntityDao = new PassEntityDao();
		try {
			this.passEntityDao.setup();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Generic Dialog box for this class
	public void showDialog(String title, String header, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	private void showWindow(String path, String window) throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource(path).openStream());
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle(window);
		stage.setResizable(false);
		stage.sizeToScene();
		stage.show();
	}

	public void showAddEntityWindow() {
		try {
			showWindow("/com/passbook/view/UiAddPass.fxml", "Add New Entity");
		} catch (IOException e) {
			System.out.println("Unable to open UiAddPass fxml window. " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void showEditEntityWindow() {
		try {
			showWindow("/com/passbook/view/UiUpdatePass.fxml", "Update Entity");
		} catch (IOException e) {
			System.out.println("Unable to open UiUpdatePass fxml window. " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void onSearch() {
		// TODO Auto-generated method stub

	}

	public void quitMainWindow() {
		passEntityDao.close();
		Platform.exit();
		System.exit(0);
	}

	public ObservableList<PassEntity> getAllEntities(int userID) {
		return passEntityDao.findPassEntitiesByUserID(userID);
	}

	public void addEntity(int userID, String keyWord, String username, String password, String webAddress) {
		System.out.println("From main book service " + userID);
		if (keyWord.isEmpty() || username.isEmpty() || password.isEmpty() || webAddress.isEmpty()) {
			showDialog("Error", "Empty Fields Detected", "All fields must be filled.");
		}else if(keyWord.length()>60) {
			showDialog("Something went wrong", "KeyWord limit Exceeded", "KeyWord length must be with in 60 characters.");
		}else if(username.length()>60) {
			showDialog("Something went wrong", "Username limit Exceeded", "Username length must be with in 60 characters.");
		}else if(password.length()>80) {
			showDialog("Something went wrong", "Password limit Exceeded", "Password length must be with in 80 characters.");
		}else if(webAddress.length()> 1024) {
			showDialog("Something went wrong", "Web Address limit Exceeded", "Web Address length must be with in 1024 characters.");
		}else {
			PassEntity passEntity = new PassEntity();
			passEntity.setUserID(userID);
			passEntity.setKeyWord(keyWord);
			passEntity.setEntityUsername(username);
			passEntity.setEntityPassword(password);
			passEntity.setWebAddress(webAddress);
			System.out.println(passEntity);
			
			if(passEntityDao.addPassEntity(passEntity)) {
				
			}else {
				showDialog("Something went wrong", "Failed.", "Unable to Add Entity.");
			}
		}	
	}
	
	public void dataConnectionClose() {
		passEntityDao.close();
	}

}
