package com.managehotelapp_javafx.controller.booking;

import com.managehotelapp_javafx.controller.ServiceController;
import com.managehotelapp_javafx.controller.checkout.SubmitCheckoutController;
import com.managehotelapp_javafx.dto.BookingRoomDTO;
import com.managehotelapp_javafx.services.BookingRoomService;
import com.managehotelapp_javafx.services.imp.BookingRoomServiceImp;
import com.managehotelapp_javafx.utils.alert.AlertUtils;
import com.managehotelapp_javafx.utils.constant.FXMLLoaderConstant;
import com.managehotelapp_javafx.utils.enumpackage.BookingStatus;
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
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    @FXML
    private Button btnSearch;


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
        tableBookingView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ContextMenu contextMenu = new ContextMenu();
        tableBookingView.setRowFactory(tv -> {
            TableRow<BookingRoomDTO> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                List<BookingRoomDTO> booking = tableBookingView.getSelectionModel().getSelectedItems();
                if (event.getButton() == MouseButton.SECONDARY && !row.isEmpty()) {
                    if (!row.isSelected()) {
                        tableBookingView.getSelectionModel().clearSelection();
                        tableBookingView.getSelectionModel().select(row.getItem());
                    }
                    contextMenu.show(row, event.getScreenX(), event.getScreenY());
                    updateContextMenu(contextMenu, booking);
                    contextMenu.show(row, event.getScreenX(), event.getScreenY());
                }
            });
            return row;
        });
    }

    private void updateContextMenu(ContextMenu contextMenu, List<BookingRoomDTO> booking) {
        contextMenu.getItems().clear();
        MenuItem checkInItem = new MenuItem("Check In");
        MenuItem checkOutItem = new MenuItem("Check Out");
        MenuItem serviceItem = new MenuItem("Service");
        MenuItem noShowItem = new MenuItem("No Show");
        MenuItem cancelItem = new MenuItem("Cancel");
        // Handle the checkin event
        checkInItem.setOnAction(e -> {
            checkInBooking(tableBookingView.getSelectionModel().getSelectedItems());
        });
        // Handle the checkout event
        checkOutItem.setOnAction(e -> {
            checkOutBooking(tableBookingView.getSelectionModel().getSelectedItems());
        });
        serviceItem.setOnAction(e -> {
            serviceScene(tableBookingView.getSelectionModel().getSelectedItem());
        });
        booking.forEach(item -> {
            String status = item.getStatus().toUpperCase();
            if (status.equals(BookingStatus.CHECKED_IN.toString())) {
                checkInItem.setDisable(true);
                cancelItem.setDisable(true);
                noShowItem.setDisable(true);
            } else if (status.equals(BookingStatus.RESERVED.toString())) {
                checkOutItem.setDisable(true);
            }
        });
        if(booking.size() > 1){
            serviceItem.setDisable(true);
        }

        contextMenu.getItems().addAll(checkInItem, checkOutItem, serviceItem, noShowItem, cancelItem);
    }

    private void serviceScene(BookingRoomDTO bookingRoomDTO){
        primaryStage = (Stage) btnSearch.getScene().getWindow();
        fxmlLoader = FXMLLoaderConstant.getServiceScene();
        try {
            Parent root = fxmlLoader.load();
            ServiceController serviceController = fxmlLoader.getController();
            serviceController.getBookingData(bookingRoomDTO);
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkInBooking(List<BookingRoomDTO> bookingRoomDTOs){
        primaryStage = (Stage) btnSearch.getScene().getWindow();
        fxmlLoader = FXMLLoaderConstant.getBookingScene();
        try {
            bookingRoomService.changeStatusRoom(bookingRoomDTOs, BookingStatus.CHECKED_IN);
            AlertUtils alertUtils = new AlertUtils();
            alertUtils.alert(Alert.AlertType.INFORMATION, "Checkin", "Check In Successfully");
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void checkOutBooking(List<BookingRoomDTO> bookingRoomDTOs){
        primaryStage = new Stage();
        fxmlLoader = FXMLLoaderConstant.getCheckoutScene();
        try {
            primaryStage.setX(365); // Set X coordinate
            primaryStage.setY(80); // Set Y coordinate
            primaryStage.setScene(new Scene(fxmlLoader.load()));
            SubmitCheckoutController submitCheckoutController = fxmlLoader.getController();
            List<Integer> listBookingRoomId = bookingRoomDTOs.stream().map(BookingRoomDTO::getId).collect(Collectors.toList());
            submitCheckoutController.displayCheckoutBookingRoom(listBookingRoomId);

            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.initModality(Modality.APPLICATION_MODAL);

            primaryStage.setOnHiding(event2 -> {
                primaryStage = (Stage) btnSearch.getScene().getWindow();
                fxmlLoader = FXMLLoaderConstant.getBookingScene();
                try {
                    primaryStage.setScene(new Scene(fxmlLoader.load()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onSearch(ActionEvent event) {
        System.out.println(123);
    }

    public Button createButtonBack() {
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
