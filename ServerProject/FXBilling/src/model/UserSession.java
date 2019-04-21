package model;

public class UserSession {

    private static UserSession instance;

    public static UserSession loggedInUser;

    private String userName;

    private int userId;

    private UserSession(String userName, int userId) {
        this.userName = userName;
        this.userId = userId;
    }

    public static UserSession getInstance(String userName,int userId) {
        if(instance == null) {
            instance = new UserSession(userName,userId);
        }
        return instance;
    }

    public static void setInstance(UserSession instance) {
        UserSession.instance = instance;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
