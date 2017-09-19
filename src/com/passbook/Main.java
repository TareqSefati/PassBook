package com.passbook;

import org.apache.commons.codec.digest.DigestUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.password.BasicPasswordEncryptor;

import com.passbook.controller.LoginController;
import com.passbook.dao.IUserDao;
import com.passbook.dao.UserDao;
import com.passbook.model.User;
import com.passbook.service.UserService;
import com.passbook.view.UiLoginController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private IUserDao makeUserkDao() {
		return new UserDao();
	}

	private UserService makeUserService() {
		return new UserService(makeUserkDao());
	}

	private LoginController makeLoginController() {
		return new LoginController(makeUserService());
	}

	private UiLoginController makeUiLoginController(Stage stage) {
		return new UiLoginController(makeLoginController(), stage);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/passbook/view/UiLogin.fxml"));
			loader.setControllerFactory(e -> makeUiLoginController(primaryStage));
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
