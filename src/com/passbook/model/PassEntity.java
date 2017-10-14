package com.passbook.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PassEntity {

	private final SimpleIntegerProperty passEntityID = new SimpleIntegerProperty();
	
	private final SimpleIntegerProperty userID = new SimpleIntegerProperty();
	
	private final SimpleStringProperty keyWord = new SimpleStringProperty();
	
	private final SimpleStringProperty entityUsername = new SimpleStringProperty();
	
	private final SimpleStringProperty entityPassword = new SimpleStringProperty();
	
	private final SimpleStringProperty webAddress = new SimpleStringProperty();
	//private Hyperlink webAddress;
	
//	public PassEntity(int userID, String keyWord, String entityUsername, String entityPassword, String webAddress) {
//		this.userID = new SimpleIntegerProperty(userID);
//		this.keyWord = new SimpleStringProperty(keyWord);
//		this.entityUsername = new SimpleStringProperty(entityUsername);
//		this.entityPassword = new SimpleStringProperty(entityPassword);
//		this.webAddress = new SimpleStringProperty(webAddress);
//	}

	public int getPassEntityID() {
		return passEntityID.get();
	}

	public void setPassEntityID(int passEntityID) {
		this.passEntityID.set(passEntityID);
	}

	public int getUserID() {
		return userID.get();
	}

	public void setUserID(int userID) {
		this.userID.set(userID);
	}

	public String getKeyWord() {
		return keyWord.get();
	}

	public void setKeyWord(String keyWord) {
		this.keyWord.set(keyWord);
	}

	public String getEntityUsername() {
		return entityUsername.get();
	}

	public void setEntityUsername(String entityUsername) {
		this.entityUsername.set(entityUsername);
	}

	public String getEntityPassword() {
		return entityPassword.get();
	}

	public void setEntityPassword(String entityPassword) {
		this.entityPassword.set(entityPassword);
	}

	public String getWebAddress() {
		return webAddress.get();
	}

	public void setWebAddress(String webAddress) {
		this.webAddress.set(webAddress);
	}

	@Override
	public String toString() {
		return "PassEntity [passEntityID=" + passEntityID + ", userID=" + userID + ", keyWord=" + keyWord
				+ ", entityUsername=" + entityUsername + ", entityPassword=" + entityPassword + ", webAddress="
				+ webAddress + "]";
	}
	
}
