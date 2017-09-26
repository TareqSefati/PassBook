package com.passbook.controller;

import com.passbook.model.PassEntity;
import com.passbook.service.MainPassBookService;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;

public class MainPassBookController {

	private MainPassBookService mainPassBookService;
	private static int userID;
	
	public MainPassBookController() {
		try {
			this.mainPassBookService = new MainPassBookService();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showAddEntityWindow(TableView<PassEntity> tv) {
		mainPassBookService.showAddEntityWindow(tv);
	}

	public void showEditEntityWindow() {
		mainPassBookService.showEditEntityWindow();
	}

	public void onSearch() {
		mainPassBookService.onSearch();
	}

	public void quitMainWindow() {
		mainPassBookService.quitMainWindow();
		
	}

	public ObservableList<PassEntity> getAllEntities(int userID) {
		this.userID = userID;
		System.out.println("from another main " + this.userID);
		return mainPassBookService.getAllEntities(userID);
	}

	public void addEntity(String keyWord, String username, String password, String webAddress, TableView<PassEntity> tableView, ActionEvent event) {
		mainPassBookService.addEntity(userID , keyWord, username, password, webAddress, tableView, event);
	}
	
	public void dataConnectionClose() {
		mainPassBookService.dataConnectionClose();
	}

}
