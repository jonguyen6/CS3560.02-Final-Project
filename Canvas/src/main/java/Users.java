package Canvas.src.main.java;

public abstract class Users {
    //abstract will force all different forms of users to comply with same demands as in this users class

    protected String userName;
    //username of every user

    protected String userRoles;
    //what the user association is to canvas aka student ,professor

    //name = The instructors/Students name
    //currentStatus = Instructor or Student
    Users(String name,String currentStatus){
        this.userName=name;
        this.userRoles=currentStatus;
    }


    //get users name
    public String getUsername(){
        return userName;
    }

    //get users Roles
    public String getUserRoles(){
        return userRoles;
    }

    public abstract void viewDashBoard();


}
