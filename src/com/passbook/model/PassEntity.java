package com.passbook.model;

public class PassEntity {

	private int passEntityID;
	
	private int userID;
	
	private String keyWord;
	
	private String entityUsername;
	
	private String entityPassword;
	
	private String webAddress;

	public int getPassEntityID() {
		return passEntityID;
	}

	public void setPassEntityID(int passEntityID) {
		this.passEntityID = passEntityID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getEntityUsername() {
		return entityUsername;
	}

	public void setEntityUsername(String entityUsername) {
		this.entityUsername = entityUsername;
	}

	public String getEntityPassword() {
		return entityPassword;
	}

	public void setEntityPassword(String entityPassword) {
		this.entityPassword = entityPassword;
	}

	public String getWebAddress() {
		return webAddress;
	}

	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	@Override
	public String toString() {
		return "PassEntity [passEntityID=" + passEntityID + ", userID=" + userID + ", keyWord=" + keyWord
				+ ", entityUsername=" + entityUsername + ", entityPassword=" + entityPassword + ", webAddress="
				+ webAddress + "]";
	}
	
}
