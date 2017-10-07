package com.passbook.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

public class UiShowPassController {

	// public UiShowPassController() {
	// this.username.requestFocus();
	// }

	@FXML
	private JFXTextField keyWord;

	@FXML
	private JFXTextField username;

	@FXML
	private JFXButton btnCopyUsername;

	@FXML
	private JFXTextField password;

	@FXML
	private JFXButton btnCopyPassword;

	@FXML
	private JFXTextField webAddress;

	@FXML
	private JFXButton btnCopyAddress;

	public String getUsername() {
		return username.getText();
	}

	public void setUsername(String username) {
		this.username.setText(username);
		this.username.setEditable(false);
	}

	public String getPassword() {
		return password.getText();
	}

	public void setPassword(String password) {
		this.password.setText(password);
		this.password.setEditable(false);
	}

	public String getWebAddress() {
		return webAddress.getText();
	}

	public void setWebAddress(String webAddress) {
		this.webAddress.setText(webAddress);
		this.webAddress.setEditable(false);
	}

	public void setKeyWord(String keyWord) {
		this.keyWord.setText(this.keyWord.getText() + " " + keyWord);
		this.keyWord.setEditable(false);

		// make it uofocused.
	}

	@FXML
	void copyPassword(ActionEvent event) {
		this.password.requestFocus();
		this.btnCopyPassword.setText("Copied");
		this.btnCopyUsername.setText("Copy");
		this.btnCopyAddress.setText("Copy");
		copyToClipboard(password);
	}

	@FXML
	void copyUsername(ActionEvent event) {
		this.username.requestFocus();
		this.btnCopyUsername.setText("Copied");
		this.btnCopyPassword.setText("Copy");
		this.btnCopyAddress.setText("Copy");
		copyToClipboard(username);
	}

	@FXML
	void copyWebAddress(ActionEvent event) {
		this.webAddress.requestFocus();
		this.btnCopyAddress.setText("Copied");
		this.btnCopyPassword.setText("Copy");
		this.btnCopyUsername.setText("Copy");
		copyToClipboard(webAddress);
	}

	@FXML
	void dismissWindow(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

	private void copyToClipboard(TextField source) {
		if (source == null || source.getText().isEmpty()) {
			System.out.println("Nothing to copied.");
		} else {
			Clipboard clipboard = Clipboard.getSystemClipboard();
			ClipboardContent content = new ClipboardContent();
			content.putString(source.getText());
			clipboard.setContent(content);
		}
	}
}
