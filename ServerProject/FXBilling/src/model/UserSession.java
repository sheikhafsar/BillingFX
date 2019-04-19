package model;

public class UserSession {
    private static UserSession instance;

    public static UserSession loggedInUser;

    private String userName;

    private UserSession(String userName) {
        this.userName = userName;
    }

    public static UserSession getInstance(String userName) {
        if(instance == null) {
            instance = new UserSession(userName);
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }
}
