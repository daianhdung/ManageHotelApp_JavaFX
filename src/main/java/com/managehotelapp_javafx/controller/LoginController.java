package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.HelloApplication;
import com.managehotelapp_javafx.services.UserService;
import com.managehotelapp_javafx.services.imp.UserServiceImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    UserService userService = new UserServiceImp();

    @FXML
    private TextField passwordTxt;

    @FXML
    private TextField usernameTxt;

    @FXML
    private Button btn_login;

    Alert alert;
    @FXML
    void onLogin(ActionEvent event) {
        String username = "nguyenvana";
        String password = "admin123";
        if(checkAllTextField()){
            try{
                boolean checkLogin = userService.checkLogin(username,password);
                if (checkLogin){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login");
                    alert.showAndWait();
                    // wait homescene to switch
                    Stage primaryStage = (Stage) btn_login.getScene().getWindow();
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
                    try {
                        primaryStage.setScene(new Scene(fxmlLoader.load()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    public boolean checkAllTextField() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Thông báo");
        boolean usernameIsEmpty = usernameTxt.getText().isEmpty();
        boolean passwordIsEmpty = passwordTxt.getText().isEmpty();

        if( usernameIsEmpty || passwordIsEmpty){
            if(usernameIsEmpty){
                alert.setHeaderText("Username cannot be empty");
                usernameTxt.requestFocus();
            }else{
                alert.setHeaderText("Password Username cannot be empty");
                passwordTxt.requestFocus();
            }
            alert.showAndWait();
            return false;
        }else{
            return true;
        }
    }
}
