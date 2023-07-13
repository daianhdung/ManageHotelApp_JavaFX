package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.services.UserService;
import com.managehotelapp_javafx.services.imp.UserServiceImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class LoginController {

    UserService userService = new UserServiceImp();

    @FXML
    private TextField passwordTxt;

    @FXML
    private TextField usernameTxt;

    Alert alert;
    @FXML
    void onLogin(ActionEvent event) {
        String username = usernameTxt.getText();
        String password = passwordTxt.getText();
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
