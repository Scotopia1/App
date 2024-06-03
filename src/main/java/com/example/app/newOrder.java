package com.example.app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Date;

public class newOrder {

	@FXML
	private Pane pane;

	private static int buttonwidth = 150;
	private static int buttonheight = 90;

	@FXML
	protected void initialize() {
		System.out.println("New Order Screen Loaded " + Date.from(java.time.Instant.now()));
		loadButtons(Category.getCategories());
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
						// Load the items ingredients
						for (String ingredient : DatabaseConnection.LoadIngredients(item)) {
							Button ingredientButton = new Button(ingredient);
							ingredientButton.setPrefSize(buttonwidth, buttonheight);
							ingredientButton.setStyle("-fx-background-color: #fcfcfc;-fx-border-color: #252525; " +
									"-fx-border-width: 5px; -fx-font-family: 'Times New Roman' ; -fx-font-size: 20px; -fx-font-weight: bold;");
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
	}


}