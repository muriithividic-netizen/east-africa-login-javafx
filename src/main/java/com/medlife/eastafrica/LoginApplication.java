package com.medlife.eastafrica;

import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginApplication extends Application {
    private final TextField usernameField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final TextField countryCodeField = new TextField();
    private final Label countryResult = new Label("Your country flag will appear here");
    private final Label message = new Label();

    @Override
    public void start(Stage stage) {
        Label brand = new Label("MEDLIFE");
        brand.getStyleClass().add("brand");

        Label title = new Label("Welcome back");
        title.getStyleClass().add("title");

        Label subtitle = new Label("Sign in to continue to your account");
        subtitle.getStyleClass().add("subtitle");

        configureField(usernameField, "Enter your username");
        configureField(passwordField, "Enter your password");
        configureField(countryCodeField, "+254");

        countryCodeField.textProperty().addListener((observable, oldValue, newValue) ->
                updateCountry(newValue));
        countryResult.getStyleClass().add("country-result");

        Button loginButton = new Button("Sign in");
        loginButton.getStyleClass().add("login-button");
        loginButton.setMaxWidth(Double.MAX_VALUE);
        loginButton.setDefaultButton(true);
        loginButton.setOnAction(event -> login());

        message.getStyleClass().add("message");
        message.setWrapText(true);

        VBox form = new VBox(9,
                fieldLabel("Username"), usernameField,
                fieldLabel("Password"), passwordField,
                fieldLabel("Country calling code"), countryCodeField,
                countryResult,
                loginButton,
                message);
        form.getStyleClass().add("form");

        VBox card = new VBox(10, brand, title, subtitle, form);
        card.getStyleClass().add("card");
        card.setMaxWidth(410);
        card.setPadding(new Insets(36));

        HBox frame = new HBox(card);
        frame.setAlignment(Pos.CENTER);
        frame.setPadding(new Insets(28));
        frame.getStyleClass().add("page");

        Scene scene = new Scene(frame, 720, 680);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        stage.setTitle("Medlife | Sign in");
        stage.setMinWidth(420);
        stage.setMinHeight(560);
        stage.setScene(scene);
        stage.show();
        usernameField.requestFocus();
    }

    private void configureField(TextField field, String prompt) {
        field.setPromptText(prompt);
        field.getStyleClass().add("input");
        field.setMaxWidth(Double.MAX_VALUE);
    }

    private Label fieldLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("field-label");
        return label;
    }

    private void updateCountry(String input) {
        Optional<Country> country = CountryCodes.find(input);
        if (country.isPresent()) {
            Country selected = country.get();
            countryResult.setText(selected.flag() + "   " + selected.name());
            countryResult.getStyleClass().removeAll("country-placeholder", "country-invalid");
            countryResult.getStyleClass().add("country-valid");
        } else if (input.isBlank()) {
            countryResult.setText("Your country flag will appear here");
            countryResult.getStyleClass().removeAll("country-valid", "country-invalid");
            countryResult.getStyleClass().add("country-placeholder");
        } else {
            countryResult.setText("No East African country found for that code");
            countryResult.getStyleClass().removeAll("country-valid", "country-placeholder");
            countryResult.getStyleClass().add("country-invalid");
        }
    }

    private void login() {
        String username = usernameField.getText().trim();
        if (username.isEmpty()) {
            showMessage("Enter your username.");
        } else if (passwordField.getText().isEmpty()) {
            showMessage("Enter your password.");
        } else if (CountryCodes.find(countryCodeField.getText()).isEmpty()) {
            showMessage("Enter a valid East African country calling code.");
            countryCodeField.requestFocus();
        } else {
            showMessage("Welcome, " + username + "! This demo does not connect to an account service.");
        }
    }

    private void showMessage(String text) {
        message.setText(text);
        message.getStyleClass().removeAll("message-error", "message-success");
        message.getStyleClass().add(text.startsWith("Welcome,") ? "message-success" : "message-error");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
