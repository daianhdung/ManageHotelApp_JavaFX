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

    private Stage primaryStage;
    private FXMLLoader fxmlLoader;

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
                                primaryStage = (Stage) btn.getScene().getWindow();
                                BookingRoomDTO booking = getTableView().getItems().get(getIndex());
                                fxmlLoader = FXMLLoaderConstant.getBookingDetailScene();
                                try {
                                    Parent root = fxmlLoader.load();
                                    BookingDetailController bookingDetailController = fxmlLoader.getController();
                                    bookingDetailController.displayData(bookingRoomService.getBookingRoomById(booking.getId()));
                                    primaryStage.setScene(new Scene(root));
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

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
