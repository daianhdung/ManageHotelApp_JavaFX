package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.utils.constant.FXMLLoaderConstant;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SidebarController {
    @FXML
    private Button btnBooking;
    private Stage primaryStage;
    private FXMLLoader fxmlLoader;

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
}
