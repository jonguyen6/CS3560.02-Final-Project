package org.example.finalproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Modality;

import java.io.FileWriter;
import java.io.IOException;

public class Student {

    public void showMenu() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Student Dashboard");

        TabPane tabPane = new TabPane();

        Tab assignmentsTab = new Tab("Assignments");
        assignmentsTab.setContent(createAssignmentsPage());
        assignmentsTab.setClosable(false);

        Tab gradesTab = new Tab("Grades");
        gradesTab.setContent(createGradesPage());
        gradesTab.setClosable(false);

        tabPane.getTabs().addAll(assignmentsTab, gradesTab);

        BorderPane layout = new BorderPane(tabPane);
        layout.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    private VBox createAssignmentsPage() {
        VBox assignmentsPage = new VBox(10);
        assignmentsPage.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Label instructionLabel = new Label("Submit Assignment:");
        TextArea assignmentArea = new TextArea();
        assignmentArea.setPromptText("Enter assignment text...");

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> submitAssignment(assignmentArea.getText()));

        assignmentsPage.getChildren().addAll(instructionLabel, assignmentArea, submitButton);

        return assignmentsPage;
    }

    private VBox createGradesPage() {
        VBox gradesPage = new VBox(10);
        gradesPage.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader("grades.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Label gradeLabel = new Label(line);
                gradesPage.getChildren().add(gradeLabel);
            }
        } catch (java.io.IOException e) {
            gradesPage.getChildren().add(new Label("Error loading grades."));
        }

        return gradesPage;
    }

    private void submitAssignment(String assignmentText) {
        try (FileWriter writer = new FileWriter("assignments.txt", true)) {
            writer.write(assignmentText + "\n");
        } catch (IOException e) {
            System.err.println("Error saving assignment: " + e.getMessage());
        }
    }
}
