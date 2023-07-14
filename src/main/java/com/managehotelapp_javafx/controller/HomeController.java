package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.HelloApplication;
import com.managehotelapp_javafx.utils.constant.FXMLLoaderConstant;
import javafx.event.Event;
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
    private Button btn_dailyrev;

    @FXML
    private Button btn_guestinfo;

    @FXML
    private Button btn_invoice;

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



    private Stage primaryStage;
    private FXMLLoader fxmlLoader;

//
//    @FXML
//    private void setBtn_guestinfo() {
//        primaryStage = (Stage) btn_home.getScene().getWindow();
//        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
//        try {
//            primaryStage.setScene(new Scene(fxmlLoader.load()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @FXML
//    private void setBtn_invoice() {
//        primaryStage = (Stage) btn_home.getScene().getWindow();
//        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
//        try {
//            primaryStage.setScene(new Scene(fxmlLoader.load()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @FXML
//    private void setBtn_room() {
//        primaryStage = (Stage) btn_home.getScene().getWindow();
//        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
//        try {
//            primaryStage.setScene(new Scene(fxmlLoader.load()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

}

