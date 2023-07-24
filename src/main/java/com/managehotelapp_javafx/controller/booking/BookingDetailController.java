package com.managehotelapp_javafx.controller.booking;

import com.managehotelapp_javafx.dto.BookingRoomDTO;
import com.managehotelapp_javafx.services.StatusBookingService;
import com.managehotelapp_javafx.services.imp.StatusBookingServiceImp;
import com.managehotelapp_javafx.utils.constant.FXMLLoaderConstant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class BookingDetailController  implements Initializable{

    StatusBookingService statusBookingService = new StatusBookingServiceImp();
    @FXML
    private Label roomNo;
    @FXML
    private TextField customerName,phoneNumber,bookingDate
            ,checkinDate,checkoutDate,email,numAdult,numChildren;
    @FXML
    private TextArea specialRequest;

    @FXML
    private ComboBox<String> status;


    private Stage primaryStage;
    private FXMLLoader fxmlLoader;

    @FXML
    private Button btnService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        status.setItems(statusBookingService.getAllStatusBooking());

    }

    public void displayData(BookingRoomDTO bookingRoomDTO){
        roomNo.setText("Room " + bookingRoomDTO.getRoomNo());customerName.setText(bookingRoomDTO.getCustomerName());
        phoneNumber.setText(bookingRoomDTO.getPhoneNumber());bookingDate.setText(bookingRoomDTO.getBookingDate());
        checkinDate.setText(bookingRoomDTO.getCheckinDate());checkoutDate.setText(bookingRoomDTO.getCheckoutDate());
        email.setText(bookingRoomDTO.getEmail());numAdult.setText(String.valueOf(bookingRoomDTO.getNumAdult()));
        numChildren.setText(String.valueOf(bookingRoomDTO.getNumChildren()));
        status.setValue(bookingRoomDTO.getStatus());
        specialRequest.setText(bookingRoomDTO.getSpecialRequest());
    }
}
