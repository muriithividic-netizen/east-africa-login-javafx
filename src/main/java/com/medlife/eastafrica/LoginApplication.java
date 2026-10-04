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

// Application is the JavaFX base class used to create and show a desktop window.
public class LoginApplication extends Application {
    // These controls are fields so their values can be read when the user clicks Log in.
    private final TextField username = new TextField();
    private final PasswordField password = new PasswordField();
    private final TextField countryCode = new TextField();
    private final Label flag = new Label("Enter a country code");
    private final Label message = new Label();

    @Override
    public void start(Stage stage) {
        // Prompt text gives the user an example without entering a value for them.
        username.setPromptText("Username");
        password.setPromptText("Password");
        countryCode.setPromptText("Country code, e.g. +254");

        // Listen for typing so the matching flag appears without a separate button.
        countryCode.textProperty().addListener((observable, oldValue, newValue) ->
                showCountry(newValue));

        // Run the login form's basic input checks when the button is clicked.
        Button loginButton = new Button("Log in");
        loginButton.setOnAction(event -> logIn());

        // A VBox places each label and input below the previous one.
        VBox layout = new VBox(10,
                new Label("East Africa Login"),
                new Label("Username"), username,
                new Label("Password"), password,
                new Label("Country code"), countryCode,
                flag,
                loginButton,
                message);
        layout.setPadding(new Insets(20));

        // Configure the window, put the form in its scene, and display it.
        stage.setTitle("East Africa Login");
        stage.setScene(new Scene(layout, 340, 390));
        stage.show();
    }

    // Look up the typed calling code and show its flag/name, or a helpful prompt.
    private void showCountry(String code) {
        CountryCodes.find(code).ifPresentOrElse(
                country -> flag.setText(country.flag() + " " + country.name()),
                () -> flag.setText(code.isBlank()
                        ? "Enter a country code"
                        : "Country code not found"));
    }

    // Check that all fields are valid. This demo does not authenticate a real account.
    private void logIn() {
        if (username.getText().isBlank()
                || password.getText().isEmpty()
                || CountryCodes.find(countryCode.getText()).isEmpty()) {
            message.setText("Enter a username, password, and valid country code.");
        } else {
            message.setText("Login demo only; no account is checked.");
        }
    }

    // Java starts the JavaFX application from this main method.
    public static void main(String[] args) {
        launch(args);
    }
}
