package old.canvas.system;

import java.time.LocalTime;

public class CoursesOld {
    //Name of the course
    private final String courseName;
    //instructor who created the course
    private final InstructorsOld instructor;

    //for the course scheduling
    private String day;
    private LocalTime classStartTime;
    private LocalTime classEndTime;


    //creates a course object with a course name and instructor , the courses scheduling as well
    public CoursesOld(String nameOfCourse, InstructorsOld instructor, String day, String startTime, String endTime) {
        this.courseName = nameOfCourse;
        this.instructor = instructor;
        this.day = day;
        this.classStartTime = LocalTime.parse(startTime);
        this.classEndTime = LocalTime.parse(endTime);
    }

    //will return course name
    public String getCourse() {
        return courseName;
    }

    //will return instructor name
    public InstructorsOld getInstructor() {
        return instructor;
    }

    //will return course day
    public String getCourseDay() {
        return day;
    }

    //will return class starting time
    public LocalTime getClassStartTime() {
        return classStartTime;
    }

    //will return class starting time
    public LocalTime getClassEndTime() {
        return classEndTime;
    }

    public boolean conflicts(CoursesOld x) {
        //not on the same day means no conflicts
        if (!this.day.equalsIgnoreCase(x.day)) {
            return false;
        }
        //if conflicts then check times
        return this.classStartTime.isBefore(x.classEndTime) &&
                x.classStartTime.isBefore(this.classEndTime);
    }
}