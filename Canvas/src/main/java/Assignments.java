package Canvas.src.main.java;

public class Assignments{
    private final String assignmentName; //stores assignmentName
    private boolean graded = false; //assignment has not been graded yet
    private double grade;

    //constructor to get assignment name
    public Assignments(String assignment){
        this.assignmentName=assignment;
    }

    //return the assignment name
    public String getAssignmentsName(){
        return assignmentName;
    }

    //professor can set a assignment grade and its marked true
    public void setGrade(double g){
        this.grade=g; //set assignment grade
        this.graded=true; //assignment was graded
    }

    //will return the assignments grade
    public double getGrade(){
        return grade;
    }

    //will return if a assignment was graded true or false
    public boolean gradedStatus(){
        return graded;
    }



}
