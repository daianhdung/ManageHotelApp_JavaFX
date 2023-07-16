package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.dto.BookingRoomDTO;
import com.managehotelapp_javafx.services.BookingRoomService;
import com.managehotelapp_javafx.services.BookingService;
import com.managehotelapp_javafx.services.imp.BookingRoomServiceImp;
import com.managehotelapp_javafx.services.imp.BookingServiceImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BookingController implements Initializable {

    BookingService bookingService = new BookingServiceImp();
    BookingRoomService bookingRoomService = new BookingRoomServiceImp();

    @FXML
    private TextField searchInput;

    @FXML
    private TableView<BookingRoomDTO> tableBookingView;
    @FXML
    private TableColumn<BookingRoomDTO, String> roomNo;

    @FXML
    private TableColumn<BookingRoomDTO, String> customerName;

    @FXML
    private TableColumn<BookingRoomDTO, String> phoneNumber;

    @FXML
    private TableColumn<BookingRoomDTO, LocalDate> bookingDate;
    @FXML
    private TableColumn<BookingRoomDTO, LocalDate> checkinDate;
    @FXML
    private TableColumn<BookingRoomDTO, String> statusBooking;
    @FXML
    private TableColumn<BookingRoomDTO, Void> action;

    ObservableList<BookingRoomDTO> listBookingRoom = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roomNo.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        bookingDate.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));
        statusBooking.setCellValueFactory(new PropertyValueFactory<>("status"));

        action.setCellFactory(new Callback<>() {
            @Override
            public TableCell call(final TableColumn<BookingRoomDTO, Void> param) {
                return new TableCell<BookingRoomDTO, Void>() {
                    final Button btn = new Button("Details");
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            btn.setOnAction(event -> {
                                BookingRoomDTO booking = getTableView().getItems().get(getIndex());
                                System.out.println(booking.getRoomNo()
                                        + "   " + booking.getPhoneNumber());
                            });
                            setGraphic(btn);
                        }
                    }
                };
            }
        });

        listBookingRoom.addAll(bookingRoomService.getAllBookingsRoom());
        tableBookingView.setItems(listBookingRoom);
    }

    public void onSearch(ActionEvent event) {

    }
}
