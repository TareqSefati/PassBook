package com.passbook.view;

import com.jfoenix.controls.JFXTextField;
import com.passbook.controller.MainPassBookController;
import com.passbook.model.PassEntity;
import com.sun.javafx.scene.control.skin.TableViewSkinBase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class UiAddPassController {
	
	private MainPassBookController mainPassBookController;
	
	private TableView<PassEntity> tableView;
	
	//In constructor we collected table view to auto update the table view.
	public UiAddPassController(TableView<PassEntity> tableView) {
		this.tableView = tableView;
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
		
		//here we send tableview with addEntity button action to use it later.
		mainPassBookController.addEntity(keyWord.getText(), username.getText(), password.getText(), webAddress.getText(), tableView, event);
		
		//tableView.getItems().clear();
		//tableView.getProperties().put(TableViewSkinBase.RECREATE, Boolean.TRUE);
//		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//		stage.close();
	}
	
    @FXML
    void cancel(ActionEvent event) {
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
    }


}
