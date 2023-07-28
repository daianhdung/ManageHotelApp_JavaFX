package com.managehotelapp_javafx.controller.checkout;

import com.managehotelapp_javafx.dto.BookingRoomDTO;
import com.managehotelapp_javafx.dto.BookingServiceDTO;
import com.managehotelapp_javafx.dto.RoomDTO;
import com.managehotelapp_javafx.entity.BookingRoomEntity;
import com.managehotelapp_javafx.services.BookingRoomService;
import com.managehotelapp_javafx.services.BookingServicesService;
import com.managehotelapp_javafx.services.imp.BookingRoomServiceImp;
import com.managehotelapp_javafx.services.imp.BookingServicesServiceImp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import static com.managehotelapp_javafx.utils.constant.DateFormatConstant.DATETIME_FORMAT_PATTERN;

public class SubmitCheckoutController implements Initializable {
    BookingRoomService bookingRoomService = new BookingRoomServiceImp();
    BookingServicesService bookingServicesService = new BookingServicesServiceImp();

    @FXML
    private Label checkInLabel, checkOutLabel, customerNameLabel;
    @FXML
    private TableView<BookingRoomDTO> bookingRoomTableView;
    @FXML
    private TableView<BookingServiceDTO> serviceTableView;
    @FXML
    private TableColumn<BookingRoomDTO, String> serialRoomCol,roomNoCol, roomTypeCol, roomPriceCol, dayOfStayCol, totalCostRoomCol;
    @FXML
    private TableColumn<BookingServiceDTO, String> serialServiceCol,serviceNameCol, servicePriceCol, quantityServiceCol, totalCostServiceCol;

    ObservableList<BookingRoomDTO> bookingRoomDTOObservableList = FXCollections.observableArrayList();
    ObservableList<BookingServiceDTO> bookingServiceDTOObservableList = FXCollections.observableArrayList();
    public void displayCheckoutAll(int bookingId){

    }

    public void displayCheckoutOneBookingRoom(int bookingRoomId){
        BookingRoomDTO bookingRoomDTO = bookingRoomService.getBookingRoomById(bookingRoomId);
        checkInLabel.setText(bookingRoomDTO.getCheckinDate());
        checkOutLabel.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATETIME_FORMAT_PATTERN)));
        customerNameLabel.setText(bookingRoomDTO.getCustomerName());

        bookingRoomDTOObservableList.add(bookingRoomDTO);
        bookingRoomTableView.setItems(bookingRoomDTOObservableList);

        bookingServiceDTOObservableList.addAll(bookingServicesService.findBooingServicesByBookingRoomId(bookingRoomDTO.getId()));
        serviceTableView.setItems(bookingServiceDTOObservableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serialRoomCol.setCellValueFactory(cell -> {
            String index = cell.getTableView().getItems().indexOf(cell.getValue()) + 1 + "";
            return new SimpleStringProperty(index);
        });
        roomNoCol.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        roomTypeCol.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        roomPriceCol.setCellValueFactory(new PropertyValueFactory<>("roomPrice"));
        dayOfStayCol.setCellValueFactory(new PropertyValueFactory<>("dayOfStay"));
        totalCostRoomCol.setCellValueFactory(new PropertyValueFactory<>("total"));

        serialServiceCol.setCellValueFactory(cell -> {
            String index = cell.getTableView().getItems().indexOf(cell.getValue()) + 1 + "";
            return new SimpleStringProperty(index);
        });
        serviceNameCol.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        servicePriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityServiceCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalCostServiceCol.setCellValueFactory(new PropertyValueFactory<>("total"));

    }


    @FXML
    void onCheckout(ActionEvent event) {

    }
    @FXML
    void onBack(ActionEvent event) {

    }
}
