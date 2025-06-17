package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditProfileController{
    private Stage dialogStage;
    private HelloApplication mainApp;
    private User user;

    private boolean okClick = false;

    private static final String DB_URL = "jdbc:mysql://localhost/db_company";
    private static final String DB_USERNAME = "nessim";
    private static final String DB_PASSWORD = "nessim";

    public void setMainApp(HelloApplication mainApp) {
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private Label userHello;

    @FXML
    private TextField username;


    public boolean  isOkClick() {
        return okClick;
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
    public void Cancel() {
        dialogStage.close();
    }

    
    @FXML
    public void ok(ActionEvent event) {
        okClick = true;
        dialogStage.close();

    }

    @FXML
    public void Ok() {
        if(isValid()) {
            String sql = "Update admin set useranme = ?, password = ?, email = ? where admin_id = ?";
            try (Connection cnx = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)){
                PreparedStatement statement = cnx.prepareStatement(sql);
                SessionManager sm = SessionManager.getInstance();
                User u = sm.getCUrrnUser();
                {
                    statement.setString(1, username.getText());
                    statement.setString(2, password.getText());
                    statement.setString(3, email.getText());
                    statement.setInt(4, EditProfileController.getID(u.getUsername()));
                    int rowupdate = statement.executeUpdate();
                    if(rowupdate > 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.initOwner(dialogStage);
                        alert.setTitle("Success");
                        alert.setHeaderText("Info modified successfully");
                        alert.setContentText(":D3");
                        alert.show();
                    }
                    okClick = true;
                   // dialogStage.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
      
    }

    private boolean isValid() {
        String message = "";
        if(username.getText() == null) message+= "No valid username \n";
        if(password.getText() == null) message+= "No valid password\n";
        if(message.length() == 0) return true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(message);
            alert.show();
            return false;
        }
    }

    public static int getID(String u) {
        String str = "Select admin_id from admin where useranme = ?";
        int id = 0;
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stm = conn.prepareStatement(str)) {
             stm.setString(1, u);
             ResultSet rs = stm.executeQuery();
             while (rs.next()) {
                id = rs.getInt("admin_id");
             }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

}


