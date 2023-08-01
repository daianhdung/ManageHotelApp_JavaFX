package com.managehotelapp_javafx.controller.booking;

import com.managehotelapp_javafx.dto.BookingRoomDTO;
import com.managehotelapp_javafx.services.BookingRoomService;
import com.managehotelapp_javafx.services.imp.BookingRoomServiceImp;
import com.managehotelapp_javafx.utils.constant.FXMLLoaderConstant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CurrentBookingController implements Initializable {
    BookingRoomService bookingRoomService = new BookingRoomServiceImp();
    @FXML
    private TextField searchInput;
    @FXML
    public Pane bookingDetail;

    @FXML
    private TableView<BookingRoomDTO> tableBookingView;
    @FXML
    private TableColumn<BookingRoomDTO, String> roomNo, customerName, phoneNumber, statusBooking, idBookingRoom;
    @FXML
    private TableColumn<BookingRoomDTO, LocalDate> bookingDate, checkinDate, checkoutDate;
    @FXML
    private TableColumn<BookingRoomDTO, Void> action;
    ObservableList<BookingRoomDTO> listBookingRoom = FXCollections.observableArrayList();

    private Stage primaryStage;
    private FXMLLoader fxmlLoader;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookingDetail.setVisible(false);

        idBookingRoom.setCellValueFactory(new PropertyValueFactory<>("id"));
        roomNo.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        bookingDate.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));
        checkinDate.setCellValueFactory(new PropertyValueFactory<>("checkinDate"));
        checkoutDate.setCellValueFactory(new PropertyValueFactory<>("checkoutDate"));
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
                                fxmlLoader = FXMLLoaderConstant.getBookingDetailScene();
                                try {
                                    Pane bookingDetailView = fxmlLoader.load();
                                    bookingDetail.getChildren().add(bookingDetailView);
                                    bookingDetail.getChildren().add(createButtonBack());
                                    BookingDetailController bookingDetailController = fxmlLoader.getController();
                                    bookingDetailController.displayData(bookingRoomService.getBookingRoomById(booking.getId()));
                                    bookingDetail.setVisible(true);
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

        listBookingRoom.addAll(bookingRoomService.getCurrentBookingRoom());
        tableBookingView.setItems(listBookingRoom);
    }

    public void onSearch(ActionEvent event) {
        System.out.println(123);
    }

    public void changeBookingScene(){
        primaryStage = (Stage) searchInput.getScene().getWindow();
        fxmlLoader = FXMLLoaderConstant.getBookingScene();
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Button createButtonBack(){
        Button btnBack = new Button("Back");
        btnBack.setLayoutX(437);
        btnBack.setLayoutY(585);
        btnBack.setPrefWidth(126);
        btnBack.setPrefHeight(44);
        btnBack.getStyleClass().add("button-normal");
        btnBack.setTextFill(javafx.scene.paint.Color.WHITE);

        Font font = Font.font("Bold", 17);
        btnBack.setFont(font);

        btnBack.setOnAction(item -> {
            bookingDetail.setVisible(false);
        });
        return btnBack;
    }

}
