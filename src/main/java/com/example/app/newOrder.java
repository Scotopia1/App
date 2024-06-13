package com.example.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.Date;

public class newOrder {

	@FXML
	private Label Restaurant_Adress, Restaurant_PhoneNumber, Restaurant_WebLink, CustomerName, orderdetails,
			serverFullName, SubTotal, TaxCalc, Total, Points, RemainigAmount, clientphonenumber;

	@FXML
	private TreeView<String> orderItems;

	@FXML
	private TableView<String> clientpaymentsTv;

	@FXML
	private TableColumn<String, String> currenciesclmn, amountpaidclmn;

	@FXML
	ComboBox<String> CurrenciesCb;

	@FXML
	private Pane pane;

	private Order order;
	private ArrayList<String> orderItemslist = new ArrayList<>();
	private String clientid;
	private String Deliveryadress;
	private String orderid;
	private String ordertype;
	private int TableNumber;

	private static int buttonwidth = 150;
	private static int buttonheight = 90;

	@FXML
	protected void initialize() {
		System.out.println("New Order Screen Loaded " + Date.from(java.time.Instant.now()));
		Loadordertype();
	}

	private void LoadClientSearch() {
		// Load the order type selection first
		AnchorPane anchorPane = new AnchorPane();
		anchorPane.setPrefSize(950, 750);
		anchorPane.setStyle("-fx-background-color: #fcfcfc;");
		Label label = new Label("Choose Client");
		label.setLayoutX(378);
		label.setLayoutY(14);
		label.setStyle("-fx-font-family: 'Times New Roman' ; -fx-font-size: 36px; -fx-font-weight: Bold Italic;");
		TextField searchField = new TextField();
		searchField.setLayoutX(253);
		searchField.setLayoutY(65);
		searchField.setPrefSize(459, 26);
		searchField.setStyle("-fx-font-family: 'Times New Roman' ; -fx-font-size: 14px; -fx-font-weight: Bold Italic;");
		searchField.setPromptText("search by name or by phone number");
		Button searchButton = new Button("Search");
		searchButton.setLayoutX(371);
		searchButton.setLayoutY(108);
		searchButton.setPrefSize(106, 40);
		searchButton.setStyle("-fx-background-color: #252525; -fx-text-fill: #fcfcfc;" +
				" -fx-font-family: 'Times New Roman' ; -fx-font-size: 18px; -fx-font-weight: Bold Italic;");
		Button AddClient = new Button("Add Client");
		AddClient.setLayoutX(487);
		AddClient.setLayoutY(108);
		AddClient.setPrefSize(106, 40);
		AddClient.setStyle("-fx-background-color: #252525; -fx-text-fill: #fcfcfc;" +
				" -fx-font-family: 'Times New Roman' ; -fx-font-size: 18px; -fx-font-weight: Bold Italic;");
		TableView<String> resultsListView = new TableView<>();
		resultsListView.setLayoutX(163);
		resultsListView.setLayoutY(157);
		resultsListView.setPrefSize(640, 540);
		//add column that display a button to select the client
		TableColumn<String, Void> Choosebtn = new TableColumn<>("");
		TableColumn<String, String> column = new TableColumn<>("Client Name");
		TableColumn<String, String> column2 = new TableColumn<>("Phone Number");
		TableColumn<String, String> column3 = new TableColumn<>("Address");
		Choosebtn.setPrefWidth(40);
		column.setPrefWidth(200);
		column2.setPrefWidth(200);
		column3.setPrefWidth(200);
		Choosebtn.setResizable(false);
		column.setResizable(false);
		column2.setResizable(false);
		column3.setResizable(false);
		Choosebtn.setEditable(false);
		column.setEditable(false);
		column2.setEditable(false);
		column3.setEditable(false);
		column.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()));
		Button skipButton = new Button("Skip");
		skipButton.setLayoutX(163);
		skipButton.setLayoutY(702);
		skipButton.setPrefSize(85, 28);
		skipButton.setStyle("-fx-background-color: #252525; -fx-text-fill: #fcfcfc;" +
				" -fx-font-family: 'Times New Roman' ; -fx-font-size: 18px; -fx-font-weight: Bold Italic;");
		anchorPane.getChildren().addAll(label, searchField, searchButton, AddClient, resultsListView, skipButton);
		pane.getChildren().add(anchorPane);
		// initialize the columns
		Choosebtn.setCellFactory(param -> new TableCell<String, Void>() {
			private final Button btn = new Button("Select");

			{
				btn.setOnAction(event -> {
					String client = getTableView().getItems().get(getIndex());
					String clientFirstName = client.split(" ")[0];
					String clientLastName = client.split(" ")[1];
					System.out.println("Button Pressed: " + client + " " + Date.from(java.time.Instant.now()));
					CustomerName.setText("Customer: " + client);
					clientid = Customer.getCustomerId(clientFirstName, clientLastName);
					Deliveryadress = " ";
					clientphonenumber.setText("Phone Number: " + Customer.getPhone(Customer.getCustomerId(clientFirstName, clientLastName)));
					if (ordertype.equals("Delivery")) {
						orderdetails.setText(Deliveryadress);
					}
					if (ordertype.equals("TakeAway")) {
						order = new Order(clientid, LoginController.getEmployeeId());
						System.out.println("TakeAway created" + Date.from(java.time.Instant.now()));
						orderid = order.getOrderId();
						System.out.println("Order ID is set" + Date.from(java.time.Instant.now()));
					}
					else if (ordertype.equals("Dine In")) {
						order = new Order(clientid, LoginController.getEmployeeId() , TableNumber);
						System.out.println("Dine In created" + Date.from(java.time.Instant.now()));
						orderid = order.getOrderId();
						System.out.println("Order ID is set" + Date.from(java.time.Instant.now()));
					}
					else {
						Address address = new Address(Deliveryadress);
						order = new Order(clientid, LoginController.getEmployeeId(), address);
						System.out.println("Order created" + Date.from(java.time.Instant.now()));
						orderid = order.getOrderId();
						System.out.println("Order ID is set" + Date.from(java.time.Instant.now()));

					}
					pane.getChildren().clear();
					loadButtons(Category.getCategories());
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(btn);
				}
			}
		});
		//initialize the table
		resultsListView.getColumns().add(Choosebtn);
		resultsListView.getColumns().add(column);
		resultsListView.getColumns().add(column2);
		resultsListView.getColumns().add(column3);
		// Search button action
		skipButton.setOnAction(e -> {
			System.out.println("Button Pressed: Skip " + Date.from(java.time.Instant.now()));
			pane.getChildren().clear();
			loadButtons(Category.getCategories());
		});
		searchButton.setOnAction(e -> {
			System.out.println("Button Pressed: Search " + Date.from(java.time.Instant.now()));
			resultsListView.getItems().clear();
			ArrayList<String> clients = Customer.getCustomerId();
			SearchforClient(searchField.getText(), resultsListView, column, column2, column3, clients);
		});
	}

	private void Loadordertype() {
		AnchorPane anchorPane = new AnchorPane();
		anchorPane.setPrefSize(950, 750);
		anchorPane.setStyle("-fx-background-color: #252525;");
		Label label = new Label("Choose Order Type:");
		label.setLayoutX(333);
		label.setLayoutY(14);
		label.setStyle("-fx-font-family: 'Times New Roman' ; -fx-font-size: 36px; -fx-font-weight: Bold Italic; -fx-text-fill: #fcfcfc;");
		Button dinein = new Button("Dine In");
		dinein.setLayoutX(306);
		dinein.setLayoutY(108);
		dinein.setPrefSize(106, 40);
		dinein.setStyle("-fx-background-color: #fcfcfc; " +
				" -fx-font-family: 'Times New Roman' ; -fx-font-size: 18px; -fx-font-weight: Bold Italic; -fx-text-fill: #252525;");
		Button takeout = new Button("Take Out");
		takeout.setLayoutX(422);
		takeout.setLayoutY(108);
		takeout.setPrefSize(106, 40);
		takeout.setStyle("-fx-background-color: #fcfcfc; " +
				" -fx-font-family: 'Times New Roman' ; -fx-font-size: 18px; -fx-font-weight: Bold Italic; -fx-text-fill: #252525;");
		Button delivery = new Button("Delivery");
		delivery.setLayoutX(538);
		delivery.setLayoutY(108);
		delivery.setPrefSize(106, 40);
		delivery.setStyle("-fx-background-color: #fcfcfc; " +
				" -fx-font-family: 'Times New Roman' ; -fx-font-size: 18px; -fx-font-weight: Bold Italic; -fx-text-fill: #252525;");
		ImageView imageView = new ImageView("file:src/main/resources/com/example/app/Logo/whiteback.png");
		imageView.setLayoutX(820);
		imageView.setLayoutY(640);
		imageView.setFitHeight(124);
		imageView.setFitWidth(92);
		imageView.onMouseClickedProperty().set(e -> {
			System.out.println("Button Pressed: Back " + Date.from(java.time.Instant.now()));
			btnBackOnAction();
		});
		anchorPane.getChildren().addAll(label, dinein, takeout, delivery);
		pane.getChildren().add(anchorPane);
		dinein.setOnAction(e -> {
			System.out.println("Button Pressed: Dine In " + Date.from(java.time.Instant.now()));
			ordertype = "Dine In";
			pane.getChildren().clear();
			loadAvailableTables();
		});
		takeout.setOnAction(e -> {
			System.out.println("Button Pressed: Take Out " + Date.from(java.time.Instant.now()));
			ordertype = "Take Out";
			pane.getChildren().clear();
			LoadClientSearch();
		});

		delivery.setOnAction(e -> {
			System.out.println("Button Pressed: Delivery " + Date.from(java.time.Instant.now()));
			ordertype = "Delivery";
			pane.getChildren().clear();
			LoadClientSearch();
		});
	}

	private void loadAvailableTables() {
		AnchorPane anchorPane = new AnchorPane();
		anchorPane.setPrefSize(950, 750);
		anchorPane.setStyle("-fx-background-color: #fcfcfc;");
		Label label = new Label("Choose Table");
		label.setLayoutX(378);
		label.setLayoutY(14);
		label.setStyle("-fx-font-family: 'Times New Roman' ; -fx-font-size: 36px; -fx-font-weight: Bold Italic;");
		FlowPane flowPane = new FlowPane();
		flowPane.setLayoutX(0);
		flowPane.setLayoutY(108);
		flowPane.setPrefSize(950, 642);
		ArrayList<Integer> tables = Table.getTables();
		for (int table : tables) {
			if (Table.isAvailable(table)) {
				Button button = new Button("Table: " + table);
				button.setPrefSize(buttonwidth, buttonheight);
				button.setStyle("-fx-background-color: #fcfcfc;-fx-border-color: #252525; " +
						"-fx-border-width: 5px; -fx-font-family: 'Times New Roman' ; -fx-font-size: 20px; -fx-font-weight: bold;");
				button.setOnAction(e -> {
					int tableNumber = table;
					System.out.println("Button Pressed: Table " + tableNumber + " " + Date.from(java.time.Instant.now()));
					orderdetails.setText("Table: " + tableNumber);
					TableNumber = tableNumber;
					pane.getChildren().clear();
					loadButtons(Category.getCategories());
				});
				flowPane.getChildren().add(button);
				System.out.println("Table: " + table + "Loaded " + Date.from(java.time.Instant.now()));
			}
		}
		anchorPane.getChildren().addAll(label, flowPane);
		pane.getChildren().add(anchorPane);
	}

	private void SearchforClient(String text, TableView<String> resultsListView,
	                             TableColumn<String, String> column, TableColumn<String, String> column2,
	                             TableColumn<String, String> column3, ArrayList<String> clients) {
		ArrayList<String> clientNames = Customer.getCustomerNames();
		ArrayList<String> clientPhones = Customer.getCustomerPhoneNumbers();

		resultsListView.getItems().clear(); // Clear previous search results

		boolean foundResults = false; // Flag to track if any results are found

		for (String client : clients) {
			String clientName = Customer.getFullName(client); // Assuming you have a method to get the full name of a client by their ID
			String clientPhone = Customer.getPhone(client); // Assuming you have a method to get the phone number of a client by their ID

			if (clientName.toLowerCase().contains(text.toLowerCase()) || clientPhone.contains(text)) {
				column.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(Customer.getFullName(client)));
				column2.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(Customer.getPhone(client)));
				column3.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(Customer.getAddress(client).toString()));
				foundResults = true;
			}
		}

		if (!foundResults) {
			// If no results are found, display an error message
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("No Results");
			alert.setHeaderText(null);
			alert.setContentText("No clients found matching the search criteria.");
			alert.showAndWait();
		}
	}

	private void AddToReceipt(OrderItem orderItem){

	}

	@FXML
	protected void btnBackOnAction() {

		// When the "Cancel" button is pressed
		System.out.println("Back Button Pressed " + Date.from(java.time.Instant.now()));
		try {
			// Load the PowerOff.fxml file
			FXMLLoader fxmlLoader = new FXMLLoader(LoginScreen.class.getResource("HomeScreen.fxml"));
			Parent root = (Parent) fxmlLoader.load();

			// Create a new scene and stage for the "Power Off" window
			Scene newScene = new Scene(root);
			Stage newStage = new Stage();

			newStage.initStyle(StageStyle.UNDECORATED);
			newStage.setResizable(false);
			newStage.setScene(newScene);
			newStage.setTitle("Home Screen");

			// Show the "Power Off" window
			newStage.show();

			Stage homeStage = (Stage) pane.getScene().getWindow();
			homeStage.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadButtons(ArrayList<String> categories) {
		for (String category : categories) {
			Button button = new Button(category);
			button.setOnAction(event -> {
				pane.getChildren().clear();
				System.out.println("Button Pressed: " + category + " " + Date.from(java.time.Instant.now()));
				for (String item : MenuItem.getItems(category)) {
					Button itemButton = new Button(item);
					itemButton.setPrefSize(buttonwidth, buttonheight);
					itemButton.setStyle("-fx-background-color: #fcfcfc;-fx-border-color: #252525; " +
							"-fx-border-width: 5px; -fx-font-family: 'Times New Roman' ; -fx-font-size: 20px; -fx-font-weight: bold;");
					itemButton.setOnAction(e -> {
						System.out.println("Button Pressed: " + item + " " + Date.from(java.time.Instant.now()));
						pane.getChildren().clear();
						ArrayList<String> ingredientsRemoved = new ArrayList<>();
						// Load the items ingredients
						for (String ingredient : DatabaseConnection.LoadIngredients(item)) {
							Button ingredientButton = new Button(ingredient);
							ingredientButton.setPrefSize(buttonwidth, buttonheight);
							ingredientButton.setStyle("-fx-background-color: #fcfcfc;-fx-border-color: #252525; " +
									"-fx-border-width: 5px; -fx-font-family: 'Times New Roman' ; -fx-font-size: 20px; -fx-font-weight: bold;");
							ingredientButton.setOnAction(e2 -> {
								System.out.println("Button Pressed: " + ingredient + " " + Date.from(java.time.Instant.now()));
								if (ingredientsRemoved.contains(ingredient)) {
									System.out.println("Ingredient: " + ingredient + " Undo " + Date.from(java.time.Instant.now()));
									ingredientButton.setStyle("-fx-background-color: #fcfcfc;-fx-border-color: #252525; " +
											"-fx-border-width: 5px; -fx-font-family: 'Times New Roman' ; -fx-font-size: 20px; -fx-font-weight: bold;");
									ingredientsRemoved.remove(ingredient);
								} else {
									System.out.println("Ingredient: " + ingredient + "Added " + Date.from(java.time.Instant.now()));
									ingredientButton.setStyle("-fx-background-color: #fcfcfc;-fx-border-color: red; " +
											"-fx-border-width: 5px; -fx-font-family: 'Times New Roman' ; -fx-font-size: 20px; -fx-font-weight: bold;");
									ingredientsRemoved.add(ingredient);
								}
							});
							pane.getChildren().add(ingredientButton);
							System.out.println("Ingredient: " + ingredient + "Loaded " + Date.from(java.time.Instant.now()));
						}
						Button backButton = new Button("Back");
						backButton.setOnAction(e2 -> {
							pane.getChildren().clear();
							loadButtons(Category.getCategories());
						});
						backButton.setPrefSize(buttonwidth, buttonheight);
						backButton.setStyle("-fx-background-color: #fcfcfc;-fx-border-color: #252525; " +
								"-fx-border-width: 5px; -fx-font-family: 'Times New Roman' ; -fx-font-size: 20px; -fx-font-weight: bold;");
						pane.getChildren().add(backButton);
						Button Done = new Button("Done");
						Done.setOnAction(e2 -> {
							System.out.println("Button Pressed: Done " + Date.from(java.time.Instant.now()));
							String menuitemid = MenuItem.getMenuItemId(item);
							OrderItem orderItem = new OrderItem(item, menuitemid, ingredientsRemoved, orderid, MenuItem.getPrice(item), 1);
							orderItemslist.add(orderItem.getOrderItemId());
							pane.getChildren().clear();
							loadButtons(Category.getCategories());
						});
						Done.setPrefSize(buttonwidth, buttonheight);
						Done.setStyle("-fx-background-color: #fcfcfc;-fx-border-color: #252525; " +
								"-fx-border-width: 5px; -fx-font-family: 'Times New Roman' ; -fx-font-size: 20px; -fx-font-weight: bold;");
						pane.getChildren().add(Done);
					});
					pane.getChildren().add(itemButton);
					System.out.println("Item: " + item + "Loaded " + Date.from(java.time.Instant.now()));
				}
				Button backButton = new Button("Back");
				backButton.setOnAction(e -> {
					pane.getChildren().clear();
					loadButtons(Category.getCategories());
				});
				backButton.setPrefSize(buttonwidth, buttonheight);
				backButton.setStyle("-fx-background-color: #fcfcfc;-fx-border-color: #252525; " +
						"-fx-border-width: 5px; -fx-font-family: 'Times New Roman' ; -fx-font-size: 20px; -fx-font-weight: bold;");
				pane.getChildren().add(backButton);
			});
			button.setPrefSize(buttonwidth, buttonheight);
			button.setStyle("-fx-background-color: #fcfcfc;-fx-border-color: #252525; " +
					"-fx-border-width: 5px; -fx-font-family: 'Times New Roman' ; -fx-font-size: 20px; -fx-font-weight: bold;");
			pane.getChildren().add(button);
			System.out.println("Category: " + category + "Loaded " + Date.from(java.time.Instant.now()));
		}
		Button RefreshButton = new Button("Refresh");
		RefreshButton.setOnAction(e -> {
			pane.getChildren().clear();
			loadButtons(Category.getCategories());
		});
		RefreshButton.setPrefSize(buttonwidth, buttonheight);
		RefreshButton.setStyle("-fx-background-color: #fcfcfc;-fx-border-color: #252525; " +
				"-fx-border-width: 5px; -fx-font-family: 'Times New Roman' ; -fx-font-size: 20px; -fx-font-weight: bold;");
		pane.getChildren().add(RefreshButton);
		Button closeButton = new Button("Close Order");
		closeButton.setOnAction(e -> {
			System.out.println("Button Pressed: Close Order " + Date.from(java.time.Instant.now()));
			for (String orderItem : orderItemslist) {
				OrderItem.DeleteOrderItem(orderItem);
			}
			Order.DeleteOrder(orderid);
			pane.getChildren().clear();
			btnBackOnAction();
		});
		closeButton.setPrefSize(buttonwidth, buttonheight);
		closeButton.setStyle("-fx-background-color: #fcfcfc;-fx-border-color: #252525; " +
				"-fx-border-width: 5px; -fx-font-family: 'Times New Roman' ; -fx-font-size: 20px; -fx-font-weight: bold;");
		Button printButton = new Button("Print");
		printButton.setOnAction(e -> {
			System.out.println("Button Pressed: Print " + Date.from(java.time.Instant.now()));
			// Print the order receipt
			Order order = Order.getOrderFromdb(orderid);
			order.printReceipt();
		});
		printButton.setPrefSize(buttonwidth, buttonheight);
		printButton.setStyle("-fx-background-color: #fcfcfc;-fx-border-color: #252525; " +
				"-fx-border-width: 5px; -fx-font-family: 'Times New Roman' ; -fx-font-size: 20px; -fx-font-weight: bold;");
		pane.getChildren().add(printButton);
		Button CompleteOrder = new Button("Complete\n Order");
		CompleteOrder.setOnAction(e -> {
			System.out.println("Button Pressed: Complete Order " + Date.from(java.time.Instant.now()));
		});
		CompleteOrder.setPrefSize(buttonwidth, buttonheight);
		CompleteOrder.setStyle("-fx-background-color: #fcfcfc;-fx-border-color: #252525; " +
				"-fx-border-width: 5px; -fx-font-family: 'Times New Roman' ; -fx-font-size: 20px; -fx-font-weight: bold;");
		pane.getChildren().add(CompleteOrder);
		pane.getChildren().add(closeButton);
	}
}