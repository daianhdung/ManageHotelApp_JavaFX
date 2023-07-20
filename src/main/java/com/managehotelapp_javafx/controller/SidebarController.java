package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.utils.constant.FXMLLoaderConstant;
import com.managehotelapp_javafx.utils.session.SessionUser;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SidebarController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnBooking;
    @FXML
    private Button btnLogout;

    private Stage primaryStage;
    private FXMLLoader fxmlLoader;
    @FXML
    private boolean isManager;

    @FXML
    private Button btnRevenue;

    @FXML
    private Button btnUser;

    @FXML
    private void onBooking(Event event) {
        primaryStage = (Stage) btnBooking.getScene().getWindow();
        fxmlLoader = FXMLLoaderConstant.getBookingScene();
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void setBtnLogout() {
        primaryStage = (Stage) btnLogout.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Logout");

        if(alert.showAndWait().get() == ButtonType.OK){
            try {
                fxmlLoader = FXMLLoaderConstant.getLoginScene();
                SessionUser.removeSession();
                primaryStage.setScene(new Scene(fxmlLoader.load()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void onHomeScene() {
        primaryStage = (Stage) btnHome.getScene().getWindow();
        fxmlLoader = FXMLLoaderConstant.getHomeScene();
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onUserScene() {
        primaryStage = (Stage) btnUser.getScene().getWindow();
        fxmlLoader = FXMLLoaderConstant.getUserScene();
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //    @FXML
//    private void setBtnRevenue() {
//        primaryStage = (Stage) btn_home.getScene().getWindow();
//        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
//        try {
//            primaryStage.setScene(new Scene(fxmlLoader.load()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isManager = SessionUser.getInstance().getRole().equals("Role Manager");
        btnRevenue.setVisible(isManager);
        btnUser.setVisible(isManager);
    }
}
