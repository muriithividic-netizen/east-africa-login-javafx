package com.medlife.eastafrica;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginApplication extends Application {
    private final TextField username = new TextField();
    private final PasswordField password = new PasswordField();
    private final TextField countryCode = new TextField();
    private final Label flag = new Label("Enter a country code");
    private final Label message = new Label();

    @Override
    public void start(Stage stage) {
        username.setPromptText("Username");
        password.setPromptText("Password");
        countryCode.setPromptText("Country code, e.g. +254");

        countryCode.textProperty().addListener((observable, oldValue, newValue) ->
                showCountry(newValue));

        Button loginButton = new Button("Log in");
        loginButton.setOnAction(event -> logIn());

        VBox layout = new VBox(10,
                new Label("East Africa Login"),
                new Label("Username"), username,
                new Label("Password"), password,
                new Label("Country code"), countryCode,
                flag,
                loginButton,
                message);
        layout.setPadding(new Insets(20));

        stage.setTitle("East Africa Login");
        stage.setScene(new Scene(layout, 340, 390));
        stage.show();
    }

    private void showCountry(String code) {
        CountryCodes.find(code).ifPresentOrElse(
                country -> flag.setText(country.flag() + " " + country.name()),
                () -> flag.setText(code.isBlank()
                        ? "Enter a country code"
                        : "Country code not found"));
    }

    private void logIn() {
        if (username.getText().isBlank()
                || password.getText().isEmpty()
                || CountryCodes.find(countryCode.getText()).isEmpty()) {
            message.setText("Enter a username, password, and valid country code.");
        } else {
            message.setText("Login demo only; no account is checked.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
