package com.passbook.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;
import com.passbook.controller.MainPassBookController;
import com.passbook.model.PassEntity;
import com.sun.javafx.scene.control.skin.TableViewSkinBase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UiMainPassBookController {
    
	private MainPassBookController mainPassBookController;
	
	public UiMainPassBookController() {
		try {
			this.mainPassBookController = new MainPassBookController();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int userID;
	
    public void setUserID(int userID) {
		this.userID = userID;
		System.out.println("from ui main controller" + this.userID);
		init();
	}

	@FXML
    private Label username;

    @FXML
    private JFXTextField searchKey;

    @FXML
    private TableView<PassEntity> tableView;
    
    @FXML
    private TableColumn<PassEntity, String> entityUsername;

    @FXML
    private TableColumn<PassEntity, String> keyWord;

    @FXML
    private TableColumn<PassEntity, String> entityPassword;

    @FXML
    private TableColumn<PassEntity, String> webAddress;

    @FXML
    void addEntity(ActionEvent event) {
    	mainPassBookController.showAddEntityWindow(tableView);
    	//mainPassBookController.dataConnectionClose();
    	//this.mainPassBookController = new MainPassBookController();
    	//tableView.refresh();
    	//tableView.getProperties().put(TableViewSkinBase.RECREATE, Boolean.TRUE);
    	//tableView.setItems(mainPassBookController.getAllEntities(userID));
    	//tableView.getItems().clear();
    	System.out.println("new entity added. length is: " + mainPassBookController.getAllEntities(userID).toArray().length);
    }

    @FXML
    void editEntity(ActionEvent event) {
    	mainPassBookController.showEditEntityWindow();
    	tableView.refresh();
    }

    @FXML
    void onSearch(ActionEvent event) {
    	mainPassBookController.onSearch();
    }

    @FXML
    void quit(ActionEvent event) {
    	mainPassBookController.quitMainWindow();
    }
    
    public void setUsername(String username) {
    	this.username.setText("Hi " + username);
    }
    
    public void init() {
    	keyWord.setCellValueFactory(new PropertyValueFactory<PassEntity, String>("keyWord"));
		entityUsername.setCellValueFactory(new PropertyValueFactory<PassEntity, String>("entityUsername"));
		entityPassword.setCellValueFactory(new PropertyValueFactory<PassEntity, String>("entityPassword"));
		webAddress.setCellValueFactory(new PropertyValueFactory<PassEntity, String>("webAddress"));
		tableView.setItems(mainPassBookController.getAllEntities(userID));
    }

//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
////		keyWord.setCellValueFactory(new PropertyValueFactory<PassEntity, String>("keyWord"));
////		entityUsername.setCellValueFactory(new PropertyValueFactory<PassEntity, String>("entityUsername"));
////		entityPassword.setCellValueFactory(new PropertyValueFactory<PassEntity, String>("entityPassword"));
////		webAddress.setCellValueFactory(new PropertyValueFactory<PassEntity, String>("webAddress"));
//		//tableView.setItems(mainPassBookController.getAllEntities(userID));
//		//System.out.println("from main controller in initializable method" + this.userID);
//	}
    
}



