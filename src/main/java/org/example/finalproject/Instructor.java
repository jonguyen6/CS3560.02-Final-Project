package org.example.finalproject;

import javafx.scene.Scene;
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

    // Set to keep track of already graded assignments
    private Set<String> gradedAssignments = new HashSet<>();
    private VBox assignmentsPage;  // The VBox that holds the assignments list

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

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    private VBox createAssignmentsPage() {
        assignmentsPage = new VBox();
        assignmentsPage.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        try (BufferedReader reader = new BufferedReader(new FileReader("assignments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Only display the assignment if it hasn't been graded yet
                if (!gradedAssignments.contains(line)) {
                    HBox assignmentBox = new HBox(10);
                    Label assignmentLabel = new Label(line);
                    TextField gradeField = new TextField();
                    gradeField.setPromptText("Enter Grade");
                    Button submitGradeButton = new Button("Submit Grade");
                    String finalLine = line;
                    submitGradeButton.setOnAction(e -> submitGrade(finalLine, gradeField.getText()));

                    assignmentBox.getChildren().addAll(assignmentLabel, gradeField, submitGradeButton);
                    assignmentsPage.getChildren().add(assignmentBox);
                }
            }
        } catch (IOException e) {
            assignmentsPage.getChildren().add(new Label("Error loading assignments."));
        }

        return assignmentsPage;
    }

    private void submitGrade(String assignmentText, String grade) {
        // Mark the assignment as graded
        gradedAssignments.add(assignmentText);

        // Save the grade to the grades.txt file
        try (FileWriter writer = new FileWriter("grades.txt", true)) {
            writer.write(assignmentText + " - Grade: " + grade + "\n");
        } catch (IOException e) {
            System.err.println("Error saving grade: " + e.getMessage());
        }

        // Delete the graded assignment from the assignments.txt file
        deleteAssignmentFromFile(assignmentText);

        // Update the UI: Remove the graded assignment from the list
        updateAssignmentsPage();
    }

    private void deleteAssignmentFromFile(String assignmentText) {
        // Read all the assignments and filter out the graded one
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

        // Rewrite the assignments file without the graded assignment
        try (FileWriter writer = new FileWriter("assignments.txt")) {
            for (String assignment : remainingAssignments) {
                writer.write(assignment + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing assignments: " + e.getMessage());
        }
    }


    private void updateAssignmentsPage() {
        // Remove all children from the VBox and refresh the list
        assignmentsPage.getChildren().clear();

        // Rebuild the assignment list with only ungraded assignments
        try (BufferedReader reader = new BufferedReader(new FileReader("assignments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Only display the assignment if it hasn't been graded yet
                if (!gradedAssignments.contains(line)) {
                    HBox assignmentBox = new HBox(10);
                    Label assignmentLabel = new Label(line);
                    TextField gradeField = new TextField();
                    gradeField.setPromptText("Enter Grade");
                    Button submitGradeButton = new Button("Submit Grade");
                    String finalLine = line;
                    submitGradeButton.setOnAction(e -> submitGrade(finalLine, gradeField.getText()));

                    assignmentBox.getChildren().addAll(assignmentLabel, gradeField, submitGradeButton);
                    assignmentsPage.getChildren().add(assignmentBox);
                }
            }
        } catch (IOException e) {
            assignmentsPage.getChildren().add(new Label("Error loading assignments."));
        }
    }
}
