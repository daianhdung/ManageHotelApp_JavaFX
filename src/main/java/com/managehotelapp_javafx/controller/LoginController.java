package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.repository.imp.UserRepositoryImp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button login_btn;

    @FXML
    private TextField password_txt;

    @FXML
    private TextField username_txt;

    Alert alert;
    @FXML
    public void login(){
        String username = username_txt.getText();
        String password = password_txt.getText();

        boolean checkLogin = UserRepositoryImp.getInstance().checkUser(username,password);
        if (checkLogin){
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Login");
            alert.showAndWait();

            // wait homescene to switch
        }


    }

}
