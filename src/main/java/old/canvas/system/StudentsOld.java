package old.canvas.system;

import java.util.ArrayList;

public class StudentsOld extends UsersOld {
    //creating a list to keep track of assignments
    private final ArrayList<AssignmentsOld> doneAssignments = new ArrayList<>();
    //creating a list to keep track of courses
    private final ArrayList<CoursesOld> currentCourses = new ArrayList<>();


    public StudentsOld(String name) {
        super(name,"Student"); //call constructor in users
    }

    public void doneAssignments(AssignmentsOld assignment){
        //add any done assignments to our done Assignments list
        doneAssignments.add(assignment);
        //message to
        System.out.println("\n"+userName + " has submitted their assignment: "+ assignment.getAssignmentsName());

    }

    public void addCourse(CoursesOld courseName){
        //x being a variable for existing course
        for(CoursesOld x:  currentCourses){
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
        for(CoursesOld x: currentCourses){ //enhanced for loop to check every course
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
        for (CoursesOld x : currentCourses) {
            System.out.println("* " + x.getCourse());
        }
        // enhanced for loop to show the assignments submitted and if they were graded
        System.out.println("Submitted Assignments and Grades: ");
        for (AssignmentsOld x : doneAssignments) {
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
    public ArrayList<CoursesOld> getCurrentCourses(){
        return currentCourses;
    }
}
