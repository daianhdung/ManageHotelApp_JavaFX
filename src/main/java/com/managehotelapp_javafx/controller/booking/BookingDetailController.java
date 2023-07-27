package com.managehotelapp_javafx.controller.booking;

import com.managehotelapp_javafx.controller.ServiceController;
import com.managehotelapp_javafx.dto.BookingRoomDTO;
import com.managehotelapp_javafx.services.BookingRoomService;
import com.managehotelapp_javafx.services.StatusBookingService;
import com.managehotelapp_javafx.services.imp.BookingRoomServiceImp;
import com.managehotelapp_javafx.services.imp.StatusBookingServiceImp;
import com.managehotelapp_javafx.utils.constant.FXMLLoaderConstant;
import com.managehotelapp_javafx.utils.enumpackage.BookingStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookingDetailController  implements Initializable{

    StatusBookingService statusBookingService = new StatusBookingServiceImp();
    BookingRoomService bookingRoomService = new BookingRoomServiceImp();
    @FXML
    private Label roomNo, lengthOfStay, totalExpense;
    @FXML
    private TextField customerName,phoneNumber,bookingDate
            ,checkinDate,checkoutDate,email,numAdult,numChildren, identityText, bookingAgentText, depositText;
    @FXML
    private TextArea specialRequest;
    @FXML
    private ComboBox<String> status;
    private Stage primaryStage;
    private FXMLLoader fxmlLoader;
    @FXML
    private Button btnService,btnCheckout,btnSave;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        status.setItems(statusBookingService.getAllStatusBooking());
        bookingDate.setDisable(true);
        checkinDate.setDisable(true);
        checkoutDate.setDisable(true);
        bookingAgentText.setDisable(true);
    }
    private BookingRoomDTO bookingDetail;
    public void displayData(BookingRoomDTO bookingRoomDTO){
        roomNo.setText("Room " + bookingRoomDTO.getRoomNo());customerName.setText(bookingRoomDTO.getCustomerName());
        phoneNumber.setText(bookingRoomDTO.getPhoneNumber());bookingDate.setText(bookingRoomDTO.getBookingDate());
        checkinDate.setText(bookingRoomDTO.getCheckinDate());checkoutDate.setText(bookingRoomDTO.getCheckoutDate());
        email.setText(bookingRoomDTO.getEmail());numAdult.setText(String.valueOf(bookingRoomDTO.getNumAdult()));
        numChildren.setText(String.valueOf(bookingRoomDTO.getNumChildren()));
        status.setValue(bookingRoomDTO.getStatus());
        specialRequest.setText(bookingRoomDTO.getSpecialRequest());
        identityText.setText(bookingRoomDTO.getIdentity());
        bookingAgentText.setText(bookingRoomDTO.getBookingAgent());
        depositText.setText(String.valueOf(bookingRoomDTO.getDeposit()));

        String statusBooking = bookingRoomDTO.getStatus().toUpperCase();
        if(statusBooking.equals(BookingStatus.CHECKED_IN.toString())){
            status.setDisable(true);
            customerName.setDisable(true);
            phoneNumber.setDisable(true);
            email.setDisable(true);
            numAdult.setDisable(true);
            numChildren.setDisable(true);
            identityText.setDisable(true);
        } else if (statusBooking.equals(BookingStatus.CHECKED_OUT.toString()) || statusBooking.equals(BookingStatus.CANCELLED.toString())
        || statusBooking.equals(BookingStatus.NO_SHOW.toString())) {
            customerName.setDisable(true);
            phoneNumber.setDisable(true);
            email.setDisable(true);
            numAdult.setDisable(true);
            numChildren.setDisable(true);
            specialRequest.setDisable(true);
            identityText.setDisable(true);
            status.setDisable(true);
            depositText.setDisable(true);
        } else if (statusBooking.equals(BookingStatus.RESERVED.toString())) {
            btnCheckout.setDisable(true);
        }
        bookingDetail = bookingRoomDTO;
    }

    @FXML
    void onCheckout(ActionEvent event) {

    }

    @FXML
    void onSave(ActionEvent event) {
        bookingDetail.setCustomerName(customerName.getText());
        bookingDetail.setIdentity(identityText.getText());
        bookingDetail.setPhoneNumber(phoneNumber.getText());
        bookingDetail.setNumChildren(Integer.parseInt(numChildren.getText()));
        bookingDetail.setNumAdult(Integer.parseInt(numAdult.getText()));
        bookingDetail.setStatus(status.getValue());
        bookingDetail.setEmail(email.getText());
        bookingDetail.setDeposit(Integer.parseInt(depositText.getText()));
        bookingDetail.setSpecialRequest(specialRequest.getText());
        boolean isSuccess = bookingRoomService.updateBookingRoom(bookingDetail);
        if(isSuccess){
            primaryStage = (Stage) btnSave.getScene().getWindow();
            fxmlLoader = FXMLLoaderConstant.getBookingScene();
            try {
                primaryStage.setScene(new Scene(fxmlLoader.load()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @FXML
    void onService(ActionEvent event) {
        primaryStage = (Stage) btnService.getScene().getWindow();
        fxmlLoader = FXMLLoaderConstant.getServiceScene();
        try {
            Parent root = fxmlLoader.load();
            ServiceController serviceController = fxmlLoader.getController();
            serviceController.getBookingData(bookingDetail);
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
