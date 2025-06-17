package com.example;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ViewAdminController {
    
    private HelloApplication MainApp;
   
    public ViewAdminController() {
    }
    @FXML
    void Show(ActionEvent event) throws IOException, SQLException {
        HelloApplication startprofile = new HelloApplication();
        setMainApp(startprofile);
        SessionManager sm =SessionManager.getInstance();
        User Current = sm.getCUrrnUser();
        boolean okclick = MainApp.showProfile(Current);
        if(okclick) 
            System.out.println("test fini");

    }
    public void setMainApp(HelloApplication mainApp) {
        MainApp = mainApp;
    }

}
