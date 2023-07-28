package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.services.BookingRoomService;
import com.managehotelapp_javafx.services.BookingService;
import com.managehotelapp_javafx.services.imp.BookingRoomServiceImp;
import com.managehotelapp_javafx.services.imp.BookingServiceImp;
import com.managehotelapp_javafx.utils.enumpackage.BookingStatus;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Label reservationCountLabel;

    @FXML
    private Label checkInLabel;

    @FXML
    private Label dateLabel;

    BookingRoomService bookingRoomService = new BookingRoomServiceImp();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateLabel.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString());

        int checkInQty = (int) bookingRoomService.getCurrentBookingRoom()
                .stream().filter(bookingRoomDTO -> bookingRoomDTO.getStatus().toUpperCase().equals(BookingStatus.CHECKED_IN.toString()))
                .count();

        checkInLabel.setText(String.valueOf(checkInQty));

        int reservationQty = (int) bookingRoomService.getCurrentBookingRoom()
                .stream().filter(bookingRoomDTO -> bookingRoomDTO.getStatus().toUpperCase().equals(BookingStatus.RESERVED.toString()))
                .count();
        reservationCountLabel.setText(String.valueOf(reservationQty));


    }
}
