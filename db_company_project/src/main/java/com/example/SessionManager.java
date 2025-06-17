package com.example;

public class SessionManager {

    private static SessionManager instance;
    private User CurrentUser;

    public SessionManager(){

    }

    public SessionManager(User currentUser) {

    }

    public static SessionManager getInstance() {
        if (instance == null) 
            instance = new SessionManager();

        return instance;
    }

    public void startSession(User user) {
        CurrentUser  = user;
    }

    public void endSession() {
        CurrentUser = null;
    }

    public User getCUrrnUser() {
        return CurrentUser;
    }

    public boolean isLog() {
        return  CurrentUser != null;
    }
    
}
