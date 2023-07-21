package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.utils.constant.FXMLLoaderConstant;
import com.managehotelapp_javafx.utils.session.SessionUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserDataController implements Initializable {
    private Stage primaryStage;
    private FXMLLoader fxmlLoader;

    @FXML
    Tab cusTab;
    @FXML
    Tab userTab;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //  check role access users tab

        boolean isManager = SessionUser.getInstance().getRole().equals("Role Manager");
        if (isManager) {
            fxmlLoader = FXMLLoaderConstant.getUserScene(userTab);
            fxmlLoader = FXMLLoaderConstant.getCusScene(cusTab);
        } else {
            userTab.getTabPane().getTabs().remove(userTab);
            fxmlLoader = FXMLLoaderConstant.getCusScene(cusTab);
        }


    }
}
