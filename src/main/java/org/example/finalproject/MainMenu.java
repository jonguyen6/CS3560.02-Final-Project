package org.example.finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu extends Application {

    private static final double IMAGE_WIDTH = 100;
    private static final double IMAGE_HEIGHT = 100;

    @Override
    public void start(Stage primaryStage) {
        // Create buttons
        Button launchButton = new Button("Launch Canvas");

        // Define actions for each button
        launchButton.setOnAction(e -> {
            Login canvasLogin = new Login();
            try {
                canvasLogin.showLogin("/Login.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Load Images
        ImageView studentImage = createImageView("/images/student.png");


        // Create VBox for each image-button pair
        VBox loginBox = new VBox(10, studentImage, launchButton);
        loginBox.setStyle("-fx-alignment: center;");



        // Layout
        HBox mainLayout = new HBox(20, loginBox);
        mainLayout.setStyle("-fx-alignment: center;");

        BorderPane layout = new BorderPane();
        layout.setCenter(mainLayout);
        layout.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("Student Software Mockup");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Function/Method for adding images for each profile (profiles on this menu got scrapped so this is only used for one image now)
    private ImageView createImageView(String path) {
        ImageView imageView = new ImageView();
        try {
            Image image = new Image(getClass().getResource(path).toExternalForm());
            imageView.setImage(image);
        } catch (Exception e) {
            System.err.println("Error loading image: " + path + " - " + e.getMessage());
        }
        imageView.setFitWidth(IMAGE_WIDTH);
        imageView.setFitHeight(IMAGE_HEIGHT);
        imageView.setPreserveRatio(true);
        return imageView;
    }



    public static void main(String[] args) {
        launch(args);
    }
}
