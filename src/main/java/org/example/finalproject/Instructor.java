package org.example.finalproject;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Modality;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Instructor {

    private Set<String> gradedAssignments = new HashSet<>();
    private VBox assignmentsPage;

    public void showMenu() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Instructor Dashboard");

        TabPane tabPane = new TabPane();
        Tab assignmentsTab = new Tab("Assignments");
        assignmentsTab.setContent(createAssignmentsPage());
        assignmentsTab.setClosable(false);

        tabPane.getTabs().add(assignmentsTab);

        BorderPane layout = new BorderPane(tabPane);
        layout.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(layout, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

    private VBox createAssignmentsPage() {
        assignmentsPage = new VBox(10);
        assignmentsPage.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        try (BufferedReader reader = new BufferedReader(new FileReader("assignments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!gradedAssignments.contains(line)) {
                    HBox assignmentBox = new HBox(10);
                    Label assignmentLabel = new Label(line);
                    TextField gradeField = new TextField();
                    gradeField.setPromptText("Enter Grade from 1-100");
                    Button submitGradeButton = new Button("Submit Grade");
                    String finalLine = line;
                    submitGradeButton.setOnAction(e -> handleGradeSubmission(finalLine, gradeField));

                    assignmentBox.getChildren().addAll(assignmentLabel, gradeField, submitGradeButton);
                    assignmentsPage.getChildren().add(assignmentBox);
                }
            }
        } catch (IOException e) {
            assignmentsPage.getChildren().add(new Label("Error loading assignments."));
        }

        return assignmentsPage;
    }

    private void handleGradeSubmission(String assignmentText, TextField gradeField) {
        String grade = gradeField.getText().trim();

        if (grade.isEmpty()) {
            showAlert("Error", "Grade cannot be empty.");
        } else {
            submitGrade(assignmentText, grade);
        }
    }

    private void submitGrade(String assignmentText, String grade) {
        gradedAssignments.add(assignmentText);

        try (FileWriter writer = new FileWriter("grades.txt", true)) {
            writer.write(assignmentText + " - Grade: " + grade + "\n");
        } catch (IOException e) {
            System.err.println("Error saving grade: " + e.getMessage());
        }

        deleteAssignmentFromFile(assignmentText);

        updateAssignmentsPage();
    }

    private void deleteAssignmentFromFile(String assignmentText) {
        List<String> remainingAssignments = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("assignments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.equals(assignmentText)) {
                    remainingAssignments.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading assignments: " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter("assignments.txt")) {
            for (String assignment : remainingAssignments) {
                writer.write(assignment + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing assignments: " + e.getMessage());
        }
    }

    private void updateAssignmentsPage() {
        assignmentsPage.getChildren().clear();

        try (BufferedReader reader = new BufferedReader(new FileReader("assignments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!gradedAssignments.contains(line)) {
                    HBox assignmentBox = new HBox(10);
                    Label assignmentLabel = new Label(line);
                    TextField gradeField = new TextField();
                    gradeField.setPromptText("Enter Grade from 1-100");
                    Button submitGradeButton = new Button("Submit Grade");
                    String finalLine = line;
                    submitGradeButton.setOnAction(e -> handleGradeSubmission(finalLine, gradeField));

                    assignmentBox.getChildren().addAll(assignmentLabel, gradeField, submitGradeButton);
                    assignmentsPage.getChildren().add(assignmentBox);
                }
            }
        } catch (IOException e) {
            assignmentsPage.getChildren().add(new Label("Error loading assignments."));
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
