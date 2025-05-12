package old.canvas.system;

import java.util.ArrayList;
 //instructor is a child of the User class
public class InstructorsOld extends UsersOld {

    //creating a list that stores courses
    private final ArrayList<CoursesOld> createdCourse = new ArrayList<>();

    //Instructor constructor that gives instructors names
    public InstructorsOld(String name) {
        //calls users constructor and their role is instructor
        super(name,"Instructor"); //call constructor in users
    }

    public CoursesOld createCourse(String nameOfCourse, String day, String startTime, String endTime){
            //set instructor to this
            InstructorsOld instructor = this;
            //Creating brand-new courses and give the course with the name to the instructor
            CoursesOld brandNewCourse = new CoursesOld(nameOfCourse,instructor,day,startTime,endTime);

            for (CoursesOld x : createdCourse) {
                if (x.conflicts(brandNewCourse)) {
                    System.out.println(nameOfCourse + " Cannot be created as it has time conflicts with previous class");
                    return null;
                }
         }

            //add brand-new course to arraylist
            createdCourse.add(brandNewCourse);
            return brandNewCourse;
    }

    //method takes in assignment name and assignment-grade
    public void gradeAssignments(AssignmentsOld assignment, double assignmentGrade){
        //set assignment grade
        assignment.setGrade(assignmentGrade); //setting assignment grade
        System.out.println("\n"+userName + " graded " + assignment.getAssignmentsName() + " with " + assignmentGrade);
    }

    @Override
    public void viewDashBoard() {
        System.out.println("\n******************************************************************");
        System.out.println("Instructor: "+userName+ " has " + createdCourse.size()+ " Courses.");
        //enhanced for loop x is the courses
        for(CoursesOld x:createdCourse){
            System.out.println("* "+ x.getCourse() + " during " + x.getCourseDay()+ " start time of "+x.getClassStartTime()+ " ends at " + x.getClassEndTime());
        }

        System.out.println("******************************************************************");


    }
    //return list of courses by instructor
     public ArrayList<CoursesOld> getCreatedCourses(){
        return createdCourse;
     }
}
