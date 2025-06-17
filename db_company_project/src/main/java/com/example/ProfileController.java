package com.example;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ProfileController {

    private Stage dialogStage;
    private HelloApplication mainApp;
    private User user;


    private boolean okClick = false;

    private static final String DB_URL = "jdbc:mysql://localhost/db_company";
    private static final String DB_USERNAME = "nessim";
    private static final String DB_PASSWORD = "nessim";



    @FXML
    private Label email;

    @FXML
    private Label password;

    @FXML
    private Label userHello;

    @FXML
    private Label username;

    @FXML
    void change() throws SQLException, IOException {
        HelloApplication start = new HelloApplication();
        setMainApp(mainApp);
        SessionManager sm = SessionManager.getInstance();
         User c = sm.getCUrrnUser();
        mainApp.showProfileEdit(c);


    }

    public void setUser (User u) throws SQLException {
        SessionManager sm =SessionManager.getInstance();
        this.user = sm.getCUrrnUser();
        String selectquery = "Select * from admin where useranme = ?";
        Connection cnx = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            cnx = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            stm = cnx.prepareStatement(selectquery);
            stm.setString(1, user.getUsername());
            rs = stm.executeQuery();
            while (rs.next()) {
                userHello.setText("Hello "+ user.getUsername());
                username.setText(user.getUsername());
                password.setText(rs.getString("password"));
                email.setText(rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            cnx.close();
            rs.close();
            stm.close();
        }
    }


    @FXML
    public void ok(ActionEvent event) {
        okClick = true;
        dialogStage.close();

    }

    public boolean  isOkClick() {
        return okClick;
    }

    
    public void setMainApp(HelloApplication mainApp) {
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setOkClick(boolean okClick) {
        this.okClick = okClick;
    }

    public void setEmail(Label email) {
        this.email = email;
    }

    public void setPassword(Label password) {
        this.password = password;
    }

    public void setUserHello(Label userHello) {
        this.userHello = userHello;
    }

    public void setUsername(Label username) {
        this.username = username;
    }

}

