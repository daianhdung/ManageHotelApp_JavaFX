package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private Button btn_bookcount;

    @FXML
    private Button btn_booking;

    @FXML
    private Button btn_dailyrev;

    @FXML
    private Button btn_guestinfo;

    @FXML
    private Button btn_home;

    @FXML
    private Button btn_invoice;

    @FXML
    private Button btn_revenue;

    @FXML
    private Button btn_room;

    @FXML
    private PieChart ch_revbysv;

    @FXML
    private BarChart<?, ?> chart;

    @FXML
    private Label lbl_book;

    @FXML
    private Label lbl_rev;

    @FXML
    private Button btn_Logout;

    @FXML
    private void home() {
        Stage primaryStage = (Stage) btn_home.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void setBtn_booking() {
        Stage primaryStage = (Stage) btn_home.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void setBtn_guestinfo() {
        Stage primaryStage = (Stage) btn_home.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void setBtn_invoice() {
        Stage primaryStage = (Stage) btn_home.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void setBtn_revenue() {
        Stage primaryStage = (Stage) btn_home.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void setBtn_room() {
        Stage primaryStage = (Stage) btn_home.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void setBtn_Logout() {
        Stage primaryStage = (Stage) btn_home.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Logout");

        if(alert.showAndWait().get() == ButtonType.OK){
            try {
                primaryStage.setScene(new Scene(fxmlLoader.load()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

