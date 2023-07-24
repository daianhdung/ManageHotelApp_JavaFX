package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.HelloApplication;
import com.managehotelapp_javafx.dto.BookingRoomDTO;
import com.managehotelapp_javafx.services.BookingRoomService;
import com.managehotelapp_javafx.services.BookingService;
import com.managehotelapp_javafx.services.imp.BookingRoomServiceImp;
import com.managehotelapp_javafx.services.imp.BookingServiceImp;
import com.managehotelapp_javafx.utils.constant.FXMLLoaderConstant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BookingController implements Initializable {

    private Stage primaryStage;
    private FXMLLoader fxmlLoader;

    @FXML
    private Tab currentTab;
    @FXML
    private Tab historyTab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fxmlLoader = FXMLLoaderConstant.getBookingTabScene(historyTab, "booking/booking-history-view.fxml");
        fxmlLoader = FXMLLoaderConstant.getBookingTabScene(currentTab, "booking/current-booking-view.fxml");
    }


}
