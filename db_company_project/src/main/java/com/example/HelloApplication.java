package com.example;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class HelloApplication extends Application {

    static Stage stage;
    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {

        HelloApplication.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Manager App");
        stage.setScene(scene);
        stage.show();
    }

        public void changersc() throws IOException {
        FXMLLoader fxmlLoaderViewAdmin = new FXMLLoader(getClass().getResource("ViewAdmin.fxml"));
        Scene sc = new Scene(fxmlLoaderViewAdmin.load());
        stage.setScene(sc);
        stage.show();


    }

    public boolean showProfile(User u) throws IOException, SQLException{
        FXMLLoader fxmlLoaderProfile = new FXMLLoader(HelloApplication.class.getResource("Profile.fxml"));
        AnchorPane profile = fxmlLoaderProfile.load();
        Stage s = new Stage();
        s.setTitle("View Profile");
        s.initModality(Modality.WINDOW_MODAL);
        s.initOwner(stage);
        Scene sc = new Scene(profile);
        s.setScene(sc);
        //Give the Controller access to the main App (HelloApplication)
        ProfileController pf = fxmlLoaderProfile.getController();
        pf.setDialogStage(s);
        pf.setUser(u);
        pf.setMainApp(this);
        s.show();
        return  pf.isOkClick();

        
    }

    public boolean showProfileEdit(User u) throws IOException, SQLException{
        FXMLLoader fxmlLoaderEdit = new FXMLLoader(HelloApplication.class.getResource("EditProfile.fxml"));
        AnchorPane edprofile = fxmlLoaderEdit.load();
        Stage s = new Stage();
        s.setTitle("Edit Profile");
        s.initModality(Modality.WINDOW_MODAL);
        s.initOwner(stage);
        Scene sc = new Scene(edprofile);
        s.setScene(sc);
        //Give the Controller access to the main App (HelloApplication)
        EditProfileController pf = fxmlLoaderEdit.getController();
        pf.setDialogStage(s);
        pf.setUser(u);
        pf.setMainApp(this);
        s.show();
        return  pf.isOkClick();

        
    }
    public static void main(String[] args) {
        launch(args);
    }
}
