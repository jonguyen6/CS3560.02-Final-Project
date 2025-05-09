package org.example.finalproject;

import java.util.ArrayList;

public class Students extends Users{
    //creating a list to keep track of assignments
    private final ArrayList<Assignments> doneAssignments = new ArrayList<>();
    //creating a list to keep track of courses
    private final ArrayList<Courses> currentCourses = new ArrayList<>();


    public Students(String name) {
        super(name,"Student"); //call constructor in users
    }

    public void doneAssignments(Assignments assignment){
        //add any done assignments to our done Assignments list
        doneAssignments.add(assignment);
        //message to
        System.out.println("\n"+userName + " has submitted their assignment: "+ assignment.getAssignmentsName());

    }

    public void addCourse(Courses courseName){
        //x being a variable for existing course
        for(Courses x:  currentCourses){
            //if current course conflicts with the course name then
            if(x.conflicts(courseName)){
                System.out.println("Unable to add course due to schedule conflicts!");
                return;
            }
        }
        //otherwise were good to add
        currentCourses.add(courseName); //add course to our list
        System.out.println(userName + " is in the course: " + courseName.getCourse());
    }

    public void dropCourse(String courseName){
        for(Courses x: currentCourses){ //enhanced for loop to check every course
            if(x.getCourse().equalsIgnoreCase(courseName)){ //checks if a course is course we want to remove
                currentCourses.remove(x); //removes course
                System.out.println("\n"+userName + " has dropped the course: " + courseName);
                return;
            }
        }
        //if both fail student isnt in the course
        System.out.println("\n"+userName + " you are not registered in the course: " + courseName);

    }

    @Override
    public void viewDashBoard() {
        System.out.println("\n******************************************************************");
        System.out.println("Student: " + userName);
        System.out.println("Enrolled Courses: " + currentCourses.size());
        //enhanced for loop to display all classes for a student
        for (Courses x : currentCourses) {
            System.out.println("* " + x.getCourse());
        }
        // enhanced for loop to show the assignments submitted and if they were graded
        System.out.println("Submitted Assignments and Grades: ");
        for (Assignments x : doneAssignments) {
            String displayGrade;
            if(x.gradedStatus()) {
                displayGrade = "Grade: " + x.getGrade();
            }else{
                displayGrade = "Assignment has yet to be graded.";
            }
            System.out.println("* " + x.getAssignmentsName() + " " + displayGrade);
        }
        System.out.println("******************************************************************");
    }
    //will return list of courses student is taking
    public ArrayList<Courses> getCurrentCourses(){
        return currentCourses;
    }
}
