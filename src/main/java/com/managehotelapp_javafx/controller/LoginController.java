package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.services.UserService;
import com.managehotelapp_javafx.services.imp.UserServiceImp;
import com.managehotelapp_javafx.utils.constant.FXMLLoaderConstant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    private Button btnLogin;

    private Alert alert = new Alert(Alert.AlertType.WARNING);

    @FXML
    void onBtnManager(ActionEvent event) {
        usernameTxt.setText("nguyenvana");
        passwordTxt.setText("admin123");
    }

    @FXML
    void onBtnReceptionist(ActionEvent event) {
        usernameTxt.setText("nhanvien");
        passwordTxt.setText("admin123");
    }

    @FXML
    void onLogin(ActionEvent event) {
        String username = usernameTxt.getText();
        String password = passwordTxt.getText();

        if(checkAllTextField()){
            try{
                boolean checkLogin = userService.checkLogin(username,password);
                if (checkLogin){
                    Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
                    try {
                        primaryStage.setScene(new Scene(FXMLLoaderConstant.getHomeScene().load()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }catch (Exception e){
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    public boolean checkAllTextField() {

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
