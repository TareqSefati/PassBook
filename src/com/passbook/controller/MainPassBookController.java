package com.passbook.controller;

import com.passbook.model.PassEntity;
import com.passbook.service.MainPassBookService;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuBar;
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

	public void showEditEntityWindow(PassEntity passEntity, TableView<PassEntity> tableView) {
		mainPassBookService.showEditEntityWindow(passEntity, tableView);
	}

	public void onSearch(String searchKey, TableView<PassEntity> tableView) {
		mainPassBookService.onSearch(userID, searchKey, tableView);
	}

	public void quitMainWindow() {
		mainPassBookService.quitMainWindow();
	}

	public ObservableList<PassEntity> getAllEntities(int userID) {
		this.userID = userID;
		System.out.println("from another main " + this.userID);
		return mainPassBookService.getAllEntities(userID);
	}

	public void addEntity(String keyWord, String username, String password, String webAddress, ActionEvent event) {
		mainPassBookService.addEntity(userID , keyWord, username, password, webAddress, event);
	}
	
	public void updateEntity(int entityID, String keyWord, String username, String password, String webAddress, ActionEvent event) {
		mainPassBookService.updateEntity(entityID, userID , keyWord, username, password, webAddress, event);
		
	}

	public void deleteEntity(int entityID, ActionEvent event) {
		mainPassBookService.deleteEntity(entityID,userID, event);
		
	}

	public void dataConnectionClose() {
		mainPassBookService.dataConnectionClose();
	}

	public void logout(ActionEvent event, MenuBar menuBar) {
		mainPassBookService.logout(event, menuBar);
		
	}

	public void showCopyWindow(PassEntity passEntity) {
		mainPassBookService.showCopyWindow(passEntity);
	}
	
}
