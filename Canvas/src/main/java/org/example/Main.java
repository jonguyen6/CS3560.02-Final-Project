package Canvas.src.main.java.org.example;

import Canvas.src.main.java.Assignments;
import Canvas.src.main.java.Courses;
import Canvas.src.main.java.Instructors;
import Canvas.src.main.java.Students;

import java.util.ArrayList;

public class Main{
    public static void main(String[]args){
        //creating instructors
        Instructors professor1 = new Instructors("Professor Donald");
        Instructors professor2 = new Instructors("Professor Mickey");

        //Professors creating courses
        //store return value of create courses
        Courses java = professor1.createCourse("Java","Monday","08:30","10:00");
        //cplus will fail to be created because the professor cant have two classes running at the same scheduling
        Courses cplus= professor1.createCourse("C++","Monday","08:45","10:00");
        Courses geology = professor1.createCourse("Geology","Tuesday","08:45","10:00");
        //CSharp will fail to be created because the professor cant have two classes running at the same scheduling
        Courses CSharp = professor1.createCourse("CSharp","Tuesday","09:45","11:00");
        Courses java2 =professor2.createCourse("Java","Wednesday","10:45","11:45");
        Courses CSharp2 =professor2.createCourse("CSharp","Tuesday","09:45","11:45");
        Courses OO =professor2.createCourse("Object Orientated","Friday","06:45","09:45");

        //Creating Students
        Students student1 = new Students("Pablo");
        Students student2 = new Students("Jared");

        //students adding courses
        //professor 1 = p1
        ArrayList<Courses> p1Courses = professor1.getCreatedCourses();
        ArrayList<Courses> p2Courses = professor2.getCreatedCourses();


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

        Assignments homework1 = new Assignments("Assignment 1");
        Assignments FinalProject = new Assignments("Final Project");

        student1.doneAssignments(homework1);
        student1.doneAssignments(FinalProject);

        professor1.gradeAssignments(homework1,100);
        professor1.gradeAssignments(FinalProject,20);

        Assignments homework2 = new Assignments("Assignment 2");
        Assignments FinalProject2 = new Assignments("Final Project 2");

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