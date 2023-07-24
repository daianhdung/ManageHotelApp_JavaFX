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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
    private TableColumn<BookingRoomDTO, String> roomNo, customerName, phoneNumber, statusBooking;
    @FXML
    private TableColumn<BookingRoomDTO, LocalDate> bookingDate, checkinDate;
    @FXML
    private TableColumn<BookingRoomDTO, Void> action;
    ObservableList<BookingRoomDTO> listBookingRoom = FXCollections.observableArrayList();

    private Stage primaryStage;
    private FXMLLoader fxmlLoader;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookingDetail.setVisible(false);


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

    }

    public Button createButtonBack(){
        Button btnBack = new Button();
        btnBack.setLayoutX(12.0);
        btnBack.setLayoutY(34.0);
        btnBack.setMnemonicParsing(false);
        btnBack.setPrefHeight(35.0);
        btnBack.setPrefWidth(38.0);
        btnBack.setStyle("-fx-background-color: orange;");
        btnBack.setTextFill(javafx.scene.paint.Color.WHITE);

        ImageView imageView = new ImageView();
        imageView.setFitHeight(30.0);
        imageView.setFitWidth(24.0);
        imageView.setLayoutX(130.0);
        imageView.setLayoutY(125.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-background-color: green; -fx-border-color: white;");

        Image image = new Image(getClass().getResourceAsStream("/asset/image/angle_left.png"));
        imageView.setImage(image);

        btnBack.setGraphic(imageView);

        btnBack.setOnAction(item -> {
            bookingDetail.setVisible(false);
        });
        return btnBack;
    }

}
