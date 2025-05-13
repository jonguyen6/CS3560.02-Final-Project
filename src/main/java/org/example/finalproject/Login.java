package org.example.finalproject;

import com.sun.tools.javac.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {
    public void showLogin(String fxml) throws IOException {
        // Locate the FXML file
        java.net.URL fxmlResource = getClass().getResource(fxml);

        // DEBUG: Print the resolved URL or null
        System.out.println("Loading FXML from: " + fxmlResource);

        // Check if the resource was found
        if (fxmlResource == null) {
            throw new IOException("FXML file not found: " + fxml);
        }

        // Login page dimension set-up
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Login");
        stage.setScene(new Scene(root,  600, 400));

        // Show the completed stage
        stage.show();
    }
}
