package model;

import java.util.UUID;

public class UserSession {

    private static UserSession instance;

    public static UserSession loggedInUser;

    private String userName;

    private UUID userId;

    private UserSession(String userName, UUID userId) {
        this.userName = userName;
        this.userId = userId;
    }

    public static UserSession getInstance(String userName,UUID userId) {
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
