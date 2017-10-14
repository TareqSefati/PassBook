package com.passbook.view;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.passbook.controller.MainPassBookController;
import com.passbook.model.PassEntity;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class UiMainPassBookController {

	private MainPassBookController mainPassBookController;

	public UiMainPassBookController() {
		try {
			this.mainPassBookController = new MainPassBookController();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showDialog(String title, String header, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	private int userID;

	private Button clear;

	boolean clearAdded = false;

	@FXML
	private MenuBar menuBar;

	@FXML
	private HBox hBox;

	@FXML
	private Label username;

	@FXML
	private JFXTextField searchKey;

	@FXML
	private JFXButton searchBtn;

	@FXML
	private TableView<PassEntity> tableView;

	@FXML
	private TableColumn<PassEntity, String> entityUsername;

	@FXML
	private TableColumn<PassEntity, String> keyWord;

	@FXML
	private TableColumn<PassEntity, String> entityPassword;

	@FXML
	private TableColumn<PassEntity, String> webAddress;

	public void setUserID(int userID) {
		this.userID = userID;
		System.out.println("from ui main controller" + this.userID);
		init();
		autoSearch();
	}

	@FXML
	void addEntity(ActionEvent event) {
		mainPassBookController.showAddEntityWindow(tableView);

		// tableView.refresh();
		// tableView.getProperties().put(TableViewSkinBase.RECREATE,
		// Boolean.TRUE);
		// tableView.setItems(mainPassBookController.getAllEntities(userID));
		// tableView.getItems().clear();
		// System.out.println("new entity added. length is: " +
		// mainPassBookController.getAllEntities(userID).toArray().length);
	}

	@FXML
	void editEntity(ActionEvent event) {
		PassEntity passEntity = tableView.getSelectionModel().getSelectedItem();
		System.out.println(passEntity);

		mainPassBookController.showEditEntityWindow(passEntity, tableView);
		// tableView.refresh();
	}

	@FXML
	void onSearch(ActionEvent event) {
		if (!searchKey.getText().isEmpty()) {
			mainPassBookController.onSearch(searchKey.getText(), tableView);

			if (clearAdded == false) {
				clear = new Button();
				clear.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				clear.setPrefSize(30, 30);
				ImageView imageView = new ImageView(new Image("/com/passbook/view/images/erase.png"));
				imageView.setFitHeight(20);
				imageView.setFitWidth(20);
				clear.setGraphic(imageView);
				clear.setCancelButton(true);

				hBox.getChildren().add(hBox.getChildren().size() - 1, clear);
				clearAdded = true;
				clear.setTranslateX(clear.getLayoutX() + 5);

				searchKey.textProperty().addListener((observable, oldValue, newValue) -> {
					// System.out.println("textfield changed from " + oldValue +
					// " to " + newValue);
					if (searchKey.getText().isEmpty()) {
						searchKey.setText("");
						tableView.setItems(mainPassBookController.getAllEntities(userID));
						hBox.getChildren().remove(clear);
						clearAdded = false;
					}
				});
				clear.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						searchKey.setText("");
						tableView.setItems(mainPassBookController.getAllEntities(userID));
						hBox.getChildren().remove(clear);
						clearAdded = false;
					}
				});
			}
		}
	}

	private void makingClearButton() {
		clear = new Button();
		clear.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		clear.setPrefSize(30, 30);
		ImageView imageView = new ImageView(new Image("/com/passbook/view/images/erase.png"));
		imageView.setFitHeight(20);
		imageView.setFitWidth(20);
		clear.setGraphic(imageView);
		clear.setCancelButton(true);
		hBox.getChildren().add(hBox.getChildren().size() - 1, clear);
		clear.setTranslateX(clear.getLayoutX() + 5);
	}

	private void autoSearch() {
		searchKey.textProperty().addListener((observable, oldValue, newValue) -> {
			// System.out.println("textfield changed from " + oldValue + " to "
			// + newValue);
			if (!searchKey.getText().isEmpty()) {
				mainPassBookController.onSearch(searchKey.getText(), tableView);

				if (clearAdded == false) {
					clear = new Button();
					clear.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
					clear.setPrefSize(30, 30);
					ImageView imageView = new ImageView(new Image("/com/passbook/view/images/erase.png"));
					imageView.setFitHeight(20);
					imageView.setFitWidth(20);
					clear.setGraphic(imageView);
					clear.setCancelButton(true);

					hBox.getChildren().add(hBox.getChildren().size() - 1, clear);
					clearAdded = true;
					clear.setTranslateX(clear.getLayoutX() + 5);

					clear.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							searchKey.setText("");
							tableView.setItems(mainPassBookController.getAllEntities(userID));
							hBox.getChildren().remove(clear);
							clearAdded = false;
						}
					});
				}
			} else if (searchKey.getText().isEmpty()) {
				searchKey.setText("");
				tableView.setItems(mainPassBookController.getAllEntities(userID));
				hBox.getChildren().remove(clear);
				clearAdded = false;
			}
		});
	}

	@FXML
	void logout(ActionEvent event) {
		mainPassBookController.logout(event, menuBar);
	}

	@FXML
	void quit(ActionEvent event) {
		mainPassBookController.quitMainWindow();
	}

	public void setUsername(String username) {
		this.username.setText("Hi " + username);
	}

	public void init() {
		keyWord.setCellValueFactory(new PropertyValueFactory<PassEntity, String>("keyWord"));
		entityUsername.setCellValueFactory(new PropertyValueFactory<PassEntity, String>("entityUsername"));
		entityPassword.setCellValueFactory(new PropertyValueFactory<PassEntity, String>("entityPassword"));
		webAddress.setCellValueFactory(new PropertyValueFactory<>("webAddress"));
		webAddress.setCellFactory(new Callback<TableColumn<PassEntity,String>, TableCell<PassEntity,String>>() {
			
			@Override
			public TableCell<PassEntity, String> call(TableColumn<PassEntity, String> param) {
				TableCell<PassEntity, String> cell = new TableCell<PassEntity, String>() {
					@Override
		            protected void updateItem(String item, boolean empty) {
		                Hyperlink link = new Hyperlink(item);
						setGraphic(link);
						link.setOnAction(e->{
							try {
								Desktop.getDesktop().browse(new URI(link.getText()));
							} catch (IOException | URISyntaxException e1) {
								Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle("Error");
								alert.setHeaderText("Unable to open this URL Address.");
								alert.setContentText("Please verify or modify the address. Provide 'http://www' or 'https://www' properly.");
								alert.showAndWait();
								e1.printStackTrace();
							}
						});
		            }
				};
				return cell;
			}
		});
		
		tableView.setItems(mainPassBookController.getAllEntities(userID));

		// not necessary now. for future use. to active double clicking
		// activity.
		tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				// tableView.getSelectionModel().clearSelection();
				//System.out.println("this is 2 click demo: " + newSelection);
			}
		});
		
		//to show copy window for double click activity.
		tableView.setRowFactory(e -> {
			TableRow<PassEntity> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
					mainPassBookController.showCopyWindow(row.getItem());
				}
			});

			return row;
		});
	}
	
    @FXML
    void resetPassBookDatabase(ActionEvent event) {
    	mainPassBookController.resetPassBookDatabase();
    }

    @FXML
    void resetDatabase(ActionEvent event) {
    	
    }
    
    @FXML
    void manageUsers(ActionEvent event) {

    }
    
    
    
    public static void test() {
//    	final Hyperlink link = new Hyperlink("hk");
//        link.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent t) {
//            	getHostServices().showDocument(link.getText());
//                //openBrowser(link.getText());
//            }
//
//        });
//        //listView.getItems().add(link);
    }
}
