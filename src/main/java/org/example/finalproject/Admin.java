package org.example.finalproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Admin {

    public void showMenu() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Admin Dashboard");

        TabPane tabPane = new TabPane();
        Tab gradesTab = new Tab("Grades");
        gradesTab.setContent(createGradesPage());
        gradesTab.setClosable(false);

        tabPane.getTabs().add(gradesTab);

        BorderPane layout = new BorderPane(tabPane);
        layout.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    private VBox createGradesPage() {
        VBox gradesPage = new VBox(10);
        gradesPage.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        List<String> gradeEntries = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("grades.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                gradeEntries.add(line);
            }
        } catch (IOException e) {
            gradesPage.getChildren().add(new Label("Error loading grades."));
        }

        if (gradeEntries.isEmpty()) {
            gradesPage.getChildren().add(new Label("No grades available."));
        } else {
            for (String entry : gradeEntries) {
                HBox gradeBox = new HBox(10);
                Label gradeLabel = new Label(entry);
                Button deleteButton = new Button("Delete");

                // Action to delete the grade entry
                deleteButton.setOnAction(e -> {
                    gradesPage.getChildren().remove(gradeBox);  // Remove the entry from the UI
                    deleteGrade(entry);  // Remove the entry from the file
                });

                gradeBox.getChildren().addAll(gradeLabel, deleteButton);
                gradesPage.getChildren().add(gradeBox);
            }
        }

        return gradesPage;
    }

    private void deleteGrade(String gradeEntry) {
        List<String> remainingEntries = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("grades.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.equals(gradeEntry)) {
                    remainingEntries.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading grades: " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter("grades.txt")) {
            for (String entry : remainingEntries) {
                writer.write(entry + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing grades: " + e.getMessage());
        }
    }
}
