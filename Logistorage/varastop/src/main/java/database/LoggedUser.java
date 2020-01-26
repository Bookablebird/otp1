package database;

import model.User;

public class LoggedUser {
    private static User user;
    /**
     * Logged in user
     * */
    public LoggedUser(User user) {
        this.user = user;
    }
    /**
     * Logged in users type
     * */
    public String getType() {
        return user.getType();
    }
    /**
     * Logged in users firstname
     * */
    public String getFirstName(){
        return user.getFirstName();
    }
    /**
     * Logged in users username
     * */
    public String getUserName(){
        return user.getUsername();
    }
}
