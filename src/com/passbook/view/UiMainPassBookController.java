package com.passbook.view;

import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class UiMainPassBookController {

	@FXML
    private Label username;

    @FXML
    private JFXTextField searchKey;

    @FXML
    private TableView<?> tableView;

    public void setUsername(String username) {
    	this.username.setText("Hi " + username);
    }
    
    @FXML
    void onSearch(ActionEvent event) {

    }
}

