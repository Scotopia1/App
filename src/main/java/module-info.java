module com.example.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;
	requires java.desktop;
	requires org.controlsfx.controls;
	requires com.fasterxml.jackson.databind;
	requires layout;
	requires kernel;


	opens com.example.app.controllers to javafx.fxml;
	opens com.example.app.ui to javafx.fxml;
	opens com.example.app.models to javafx.fxml;
    exports com.example.app.controllers;
    exports com.example.app.ui;
    exports com.example.app.models;
    exports com.example.app.services;
    exports com.example.app.utils;
}