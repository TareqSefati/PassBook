package com.passbook.service;

import java.io.IOException;

import com.passbook.dao.PassEntityDao;
import com.passbook.model.PassEntity;
import com.passbook.view.UiAddPassController;
import com.passbook.view.UiUpdatePassController;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainPassBookService {

	// private MainPassBookDao mainPassBookDao;
	private PassEntityDao passEntityDao;
	
	//we have to make this variable static otherwise it throws NPException.
	// TableView is passed by initializing static variable. Not by making constructor
	private static TableView<PassEntity> tableView;
	
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

	public void showDialogSuccess(String title, String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
	}

	private void showWindow(String path, String window) throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		// TableView is passed by initializing static variable. Not by making constructor
		// to keep this method generic for addEntity and editEntity Method
		//loader.setControllerFactory(e-> new UiAddPassController(tv));
		Pane root = loader.load(getClass().getResource(path).openStream());
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle(window);
		stage.setResizable(false);
		stage.sizeToScene();
		stage.show();
	}

	public void showAddEntityWindow(TableView<PassEntity> tv) {
		//we set static tableview here as soon as we get the value of tableview from this func
		// TableView is passed by initializing static variable. Not by making constructor
		if(this.tableView == null)
			this.tableView = tv;
		try {
			showWindow("/com/passbook/view/UiAddPass.fxml", "Add New Entity");
		} catch (IOException e) {
			System.out.println("Unable to open UiAddPass fxml window. " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void showEditEntityWindow(PassEntity passEntity, TableView<PassEntity> tv) {
		if(this.tableView == null)
			this.tableView = tv;
		try {
			//showWindow("/com/passbook/view/UiUpdatePass.fxml", "Update Entity");
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			// TableView is passed by initializing static variable. Not by making constructor
			// to keep this method generic for addEntity and editEntity Method
			//loader.setControllerFactory(e-> new UiAddPassController(tv));
			
			Pane root = loader.load(getClass().getResource("/com/passbook/view/UiUpdatePass.fxml").openStream());
			UiUpdatePassController uiUpdatePassController = (UiUpdatePassController) loader.getController();
			uiUpdatePassController.setEntityID(passEntity.getPassEntityID());
			uiUpdatePassController.setKeyWord(passEntity.getKeyWord());
			uiUpdatePassController.setUsername(passEntity.getEntityUsername());
			uiUpdatePassController.setPassword(passEntity.getEntityPassword());
			uiUpdatePassController.setWebAddress(passEntity.getWebAddress());
			
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Update Entity");
			stage.setResizable(false);
			stage.sizeToScene();
			stage.show();
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

	public void addEntity(int userID, String keyWord, String username, String password, String webAddress, ActionEvent event) {
		//here we can get the value of tableview from function.
		//we can use this function passed value or 
		// we can use static variable value in this class to update the Main Table View
		// TableView is passed by initializing static variable. Not by making constructor
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
				//tableView.getItems().clear();
				//Using static value to update Main Table View.
				this.tableView.setItems(passEntityDao.findPassEntitiesByUserID(userID));
				
				//using function passed value to update Main Table view
				//viewtable.setItems(passEntityDao.findPassEntitiesByUserID(userID));
				
				showDialogSuccess("Adding New Entity", "New Entity is added successfully");
				Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
				stage.close();
			}else {
				showDialog("Something went wrong", "Failed.", "Unable to Add Entity.");
			}
		}	
	}

	public void updateEntity(int entityID, int userID, String keyWord, String username, String password, String webAddress, ActionEvent event) {
		
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
			passEntity.setPassEntityID(entityID);
			passEntity.setUserID(userID);
			passEntity.setKeyWord(keyWord);
			passEntity.setEntityUsername(username);
			passEntity.setEntityPassword(password);
			passEntity.setWebAddress(webAddress);
			
			if(passEntityDao.updatePassEntity(passEntity)) {
				//this.tableView.setItems(passEntityDao.findPassEntitiesByUserID(passEntity.getUserID()));
				showDialogSuccess("Updating New Entity", "Selected Entity is updated successfully");
				Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
				stage.close();
				this.tableView.refresh();
			}else {
				showDialog("Something went wrong", "Failed.", "Unable to Update Entity.");
			}
		}
	}
	
	

	public void deleteEntity(int entityID, int userID, ActionEvent event) {
		PassEntity passEntity = new PassEntity();
		passEntity.setPassEntityID(entityID);
		passEntity.setUserID(userID);
		
		if(passEntityDao.deletePassEntity(passEntity)) {
			//this.tableView.getItems().clear();
			this.tableView.setItems(passEntityDao.findPassEntitiesByUserID(passEntity.getUserID()));
			showDialogSuccess("Deleting Entity", "Selected Entity is deleted successfully");
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			stage.close();
		}else {
			showDialog("Something went wrong", "Failed.", "Unable to Delete Entity.");
		}
	}
	
	public void dataConnectionClose() {
		passEntityDao.close();
	}

}
