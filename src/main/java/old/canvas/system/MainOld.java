package old.canvas.system;

import java.util.ArrayList;

public class MainOld {
    public static void main(String[]args){
        //creating instructors
        InstructorsOld professor1 = new InstructorsOld("Professor Donald");
        InstructorsOld professor2 = new InstructorsOld("Professor Mickey");

        //Professors creating courses
        //store return value of create courses
        CoursesOld java = professor1.createCourse("Java","Monday","08:30","10:00");

        //cplus will fail to be created because the professor cant have two classes running at the same scheduling
        CoursesOld cplus= professor1.createCourse("C++","Monday","08:45","10:00");
        CoursesOld geology = professor1.createCourse("Geology","Tuesday","08:45","10:00");

        //CSharp will fail to be created because the professor cant have two classes running at the same scheduling
        CoursesOld CSharp = professor1.createCourse("CSharp","Tuesday","09:45","11:00");
        CoursesOld java2 =professor2.createCourse("Java","Wednesday","10:45","11:45");
        CoursesOld CSharp2 =professor2.createCourse("CSharp","Tuesday","09:45","11:45");
        CoursesOld OO =professor2.createCourse("Object Orientated","Friday","06:45","09:45");

        //Creating Students
        StudentsOld student1 = new StudentsOld("Pablo");
        StudentsOld student2 = new StudentsOld("Jared");

        //students adding courses
        //professor 1 = p1
        ArrayList<CoursesOld> p1Courses = professor1.getCreatedCourses();
        ArrayList<CoursesOld> p2Courses = professor2.getCreatedCourses();


        //if java == null then it means the class failed to be added cause of scheduling
        //if java is not null then it means java was added successfully
        //testing student1
        if (java != null){
            student1.addCourse(java);
        }
        if (cplus != null) {
            student1.addCourse(cplus);   //should get skipped as professor cant host it
        }
        if (geology != null){
            student1.addCourse(geology);
        }

        //testing student2
        if(CSharp2!= null){
            student2.addCourse(CSharp2);
        }
        if(OO!=null){
            student2.addCourse(OO);
        }

        AssignmentsOld homework1 = new AssignmentsOld("Assignment 1");
        AssignmentsOld FinalProject = new AssignmentsOld("Final Project");

        student1.doneAssignments(homework1);
        student1.doneAssignments(FinalProject);

        professor1.gradeAssignments(homework1,100);
        professor1.gradeAssignments(FinalProject,20);

        AssignmentsOld homework2 = new AssignmentsOld("Assignment 2");
        AssignmentsOld FinalProject2 = new AssignmentsOld("Final Project 2");

        student2.doneAssignments(homework2);
        student2.doneAssignments(FinalProject2);

        professor1.gradeAssignments(homework2,97);
        professor1.gradeAssignments(FinalProject2,120);

        professor1.viewDashBoard();
        professor2.viewDashBoard();

        student1.viewDashBoard();
        student2.viewDashBoard();
    }
}