package org.example.finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainMenu extends Application {

    private static final double IMAGE_WIDTH = 100;
    private static final double IMAGE_HEIGHT = 100;

    @Override
    public void start(Stage primaryStage) {
        // Create buttons
        Button studentButton = new Button("Student");
        Button instructorButton = new Button("Instructor");
        Button adminButton = new Button("Admin");

        studentButton.setOnAction(e -> System.out.println("Student Dashboard"));
        instructorButton.setOnAction(e -> System.out.println("Instructor Dashboard"));
        adminButton.setOnAction(e -> System.out.println("Admin Dashboard"));

        // Load Images
        ImageView studentImage = createImageView("/images/student.png");
        ImageView instructorImage = createImageView("/images/instructor.png");
        ImageView adminImage = createImageView("/images/admin.png");

        // Create VBox for each image-button pair
        VBox studentBox = new VBox(10, studentImage, studentButton);
        studentBox.setStyle("-fx-alignment: center;");

        VBox instructorBox = new VBox(10, instructorImage, instructorButton);
        instructorBox.setStyle("-fx-alignment: center;");

        VBox adminBox = new VBox(10, adminImage, adminButton);
        adminBox.setStyle("-fx-alignment: center;");

        // Layout
        HBox mainLayout = new HBox(20, studentBox, instructorBox, adminBox);
        mainLayout.setStyle("-fx-alignment: center;");

        BorderPane layout = new BorderPane();
        layout.setCenter(mainLayout);
        layout.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("Student Software Mockup");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

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
