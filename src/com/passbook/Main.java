package com.passbook;

import com.passbook.controller.LoginController;
import com.passbook.view.UiLoginController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

//	private IUserDao makeUserkDao() {
//		return new UserDao();
//	}
//
//	private UserService makeUserService() {
//		return new UserService(makeUserkDao());
//	}
//
//	private LoginController makeLoginController() {
//		return new LoginController(makeUserService());
//	}
//
//	private UiLoginController makeUiLoginController(Stage stage) {
//		return new UiLoginController(makeLoginController(), stage);
//	}
	
	private UiLoginController initialization(Stage stage) {
		//IUserDao userDao = new UserDao();
		//UserService userService = new UserService();
		LoginController loginController = new LoginController();
		UiLoginController uiLoginController = new UiLoginController(loginController, stage);
		return uiLoginController;
	}

	public void test(String link) {
		getHostServices().showDocument(link);          
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/passbook/view/UiLogin.fxml"));
			loader.setControllerFactory(e -> initialization(primaryStage));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login Window");
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			primaryStage.show();

			// Stage stage = new Stage();
			// stage.setTitle("Login Window");
			// FXMLLoader loader = new FXMLLoader();
			// Parent root =
			// loader.load(getClass().getResource("/com/passbook/view/UiLogin.fxml").openStream());
			// Scene scene = new Scene(root);
			// stage.setScene(scene);
			// stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

		//BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
		//StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		//encryptor.setPassword("std");
		
		//String digest = DigestUtils.sha1Hex(data)
		
//		UserDao userDao = new UserDao();
//		userDao.setup();
//
//		User user1 = new User("user1", DigestUtils.sha1Hex("std123"), "user1@gmail.com", "user", true);
//		User user2 = new User("user2", DigestUtils.sha1Hex("std123"), "user2@gmail.com", "user", true);
//		User user3 = new User("user3", DigestUtils.sha1Hex("piash"), "user3@gmail.com", "user", true);
//
//		userDao.addUser(user1);
//		userDao.addUser(user2);
//		userDao.addUser(user3);
//
//		userDao.close();
//		System.out.println("Database, Table and User created successfully and connection is closed.");
//		System.exit(0);
	
	}
}
