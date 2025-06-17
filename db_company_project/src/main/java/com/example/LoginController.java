package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    
    private static final String DB_URL = "jdbc:mysql://localhost/db_company";
    private static final String DB_USERNAME = "nessim";
    private static final String DB_PASSWORD = "nessim";

    String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");




    @FXML
    private ComboBox<String> combobox;

    @FXML
    private PasswordField password;

    @FXML
    private Button signin;

    @FXML
    private TextField username;

    @FXML
    private Label labelmsg;





    @FXML
    private void login() throws IOException, ClassNotFoundException{
        if(username.getText().isBlank() || password.getText().isBlank()) {
            labelmsg.setText("The username and password are reqiured");
            labelmsg.setStyle(errorMessage);

            if (username.getText().isBlank()) 
                    username.setStyle(errorStyle);

            else if (password.getText().isBlank())
                    password.setStyle(errorStyle);
        }
        else if (connectUser(username.getText(), password.getText())) {

            User u = new User(username.getText());
            SessionManager sm = SessionManager.getInstance();
            sm.startSession(u);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("LOGIN SUCCESSEFUL");
            alert.setHeaderText("WELCOM :D");
            alert.show();

            HelloApplication hl = new HelloApplication();
            hl.changersc();
        }

        else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Admin doesn't exist");
            alert.setHeaderText("Verify your input");
            alert.show();
        }
    }

    public static boolean connectUser (String username, String password) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            Connection cnx = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String Query ="select * from admin where useranme=? and password =?";
            PreparedStatement st = cnx.prepareStatement(Query);
            st.setString(1,username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                rs.close();
                st.close();
                cnx.close();
                return true;
            }
            rs.close();
            st.close();
            cnx.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
