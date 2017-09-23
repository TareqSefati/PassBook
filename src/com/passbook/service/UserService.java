package com.passbook.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;

import com.passbook.controller.LoginController;
import com.passbook.dao.IUserDao;
import com.passbook.dao.UserDao;
import com.passbook.model.User;
import com.passbook.view.UiMainPassBookController;
import com.passbook.view.UiRegisterController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UserService {

	private IUserDao iUserDao;

	private final int usernameLength = 16;
	private final int passwordLength = 16;
	private final int emailLength = 60;
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	// Initiating userDao instance using this constructor
	public UserService(IUserDao iUserDao) {
		this.iUserDao = iUserDao;
	}
	
	public UserService() {
		this.iUserDao = new UserDao();
	}

	//Email validator function
	private boolean emailValidator(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
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

	// Manage login Activity
	public void login(String username, String password, ActionEvent event) {
		if (username.isEmpty() || password.isEmpty()) {
			showDialog("Error", "Something went wrong", "Username or Passwowrd cann't be empty.");
		} else if (username.length() > usernameLength) {
			showDialog("Error", "Username is too long", "Username must be with in 60 character.");
		} else if (password.length() > passwordLength) {
			showDialog("Error", "Password is too long", "Passwowrd must be with in 80 character.");
		} else {
			User user = iUserDao.userLogin(username, password);

			if (user != null) {
				// show uiMainPassbook window
				try {
					((Node) event.getSource()).getScene().getWindow().hide();
					Stage stage = new Stage();
					FXMLLoader loader = new FXMLLoader();
					Pane root = loader
							.load(getClass().getResource("/com/passbook/view/UiMainPassBook.fxml").openStream());
					UiMainPassBookController controller = (UiMainPassBookController) loader.getController();
					controller.setUsername(username);
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					System.out.println("Unable to open required fxml file. " + e.getMessage());
					e.printStackTrace();
				}
			} else {
				showDialog("Error", "Login Failed", "Unable to login with this " + username + " and " + password);
			}
		}
	}

	public void dataConnectionClose() {
		try {
			iUserDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addUser(String username, String password, String email, ActionEvent event) {
		if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
			showDialog("Error", "Username too long.", "Any field can not be empty.");
		} else if (username.length() > usernameLength) {
			showDialog("Error", "Username too long.", "Username must be within 16 characters.");
		} else if (password.length() > passwordLength) {
			showDialog("Error", "Password too long.", "Password must be within 16 characters.");
		} else if(!emailValidator(email) || email.length() > emailLength) {
			showDialog("Error", "Invalid Email", "Email must be proper within 60 characters");
		} else {
			User user = new User(username, DigestUtils.sha1Hex(password), email, "user", true);
			if(iUserDao.addUser(user)) {
				showDialogSuccess("New User Registration", "New User is Registered Successfully.");
				((Node) event.getSource()).getScene().getWindow().hide();
			}else{
				showDialog("Error", "Registration Failed", "Unable to register new user.");
			}
		}
	}

	public void registerUser(ActionEvent event) throws IOException {
		Stage primaryStage = new Stage();
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow(); 
		FXMLLoader loader = new FXMLLoader();
		
		Parent root = loader.load(getClass().getResource("/com/passbook/view/UiRegister.fxml").openStream());
		//loader.setController(new UiRegisterController(primaryStage));
		loader.setController(new UiRegisterController(primaryStage));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setOnHidden(e-> st.show());
		primaryStage.setTitle("Registration Window");
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();
	}
}
