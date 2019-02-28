package data;

/**
 * Created by quick on 2017/6/23.
 */
public class User {
    private int userID;

    public User(){

    }
    public User(int userID){
        this.userID = userID;
    }
    public void setUserID(int userID){
        this.userID = userID;
    }
    public int getUserID(){
        return this.userID;
    }
}
