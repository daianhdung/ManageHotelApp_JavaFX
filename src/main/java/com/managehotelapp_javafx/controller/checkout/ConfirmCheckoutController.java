package com.managehotelapp_javafx.controller.checkout;

import com.managehotelapp_javafx.controller.ServiceController;
import com.managehotelapp_javafx.dto.BookingRoomDTO;
import com.managehotelapp_javafx.repository.BookingRoomRepository;
import com.managehotelapp_javafx.repository.imp.BookingRoomRepositoryImp;
import com.managehotelapp_javafx.services.BookingRoomService;
import com.managehotelapp_javafx.services.imp.BookingRoomServiceImp;
import com.managehotelapp_javafx.utils.constant.FXMLLoaderConstant;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ConfirmCheckoutController implements Initializable {
    BookingRoomService bookingRoomService = new BookingRoomServiceImp();

    private Stage primaryStage;
    private FXMLLoader fxmlLoader;
    @FXML
    private Button btnAll, btnCancel, btnCheckout;
    @FXML
    private TableColumn<BookingRoomDTO, String> customerNameCol, roomCol, serialCol;
    @FXML
    private TableView<BookingRoomDTO> tableView;
    @FXML
    void onCheckoutAll(ActionEvent event) {
        primaryStage = (Stage) btnAll.getScene().getWindow();
        fxmlLoader = FXMLLoaderConstant.getCheckoutScene();
        try {
            Parent root = fxmlLoader.load();
            primaryStage.setX(365); // Set X coordinate
            primaryStage.setY(80); // Set Y coordinate
            SubmitCheckoutController submitCheckoutController = fxmlLoader.getController();
            List<Integer> listBookingRoomId = bookingRoomDTOS.stream().map(BookingRoomDTO::getId).collect(Collectors.toList());
            System.out.println(listBookingRoomId.get(0));
            submitCheckoutController.displayCheckoutBookingRoom(listBookingRoomId);
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void onCheckoutOne(ActionEvent event) {
        primaryStage = (Stage) btnCheckout.getScene().getWindow();
        fxmlLoader = FXMLLoaderConstant.getCheckoutScene();
        try {
            Parent root = fxmlLoader.load();
            primaryStage.setX(365); // Set X coordinate
            primaryStage.setY(80); // Set Y coordinate
            SubmitCheckoutController submitCheckoutController = fxmlLoader.getController();
            int bookingRoomId = tableView.getSelectionModel().getSelectedItem().getId();
            List<Integer> listBookingRoomId = new ArrayList<>();
            listBookingRoomId.add(bookingRoomId);
            submitCheckoutController.displayCheckoutBookingRoom(listBookingRoomId);
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void onCancel(ActionEvent event) {
        primaryStage = (Stage) btnCancel.getScene().getWindow();
        primaryStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serialCol.setCellValueFactory(cell -> {
            String index = cell.getTableView().getItems().indexOf(cell.getValue()) + 1 + "";
            return new SimpleStringProperty(index);
        });
        roomCol.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
    }

    ObservableList<BookingRoomDTO> bookingRoomDTOS = FXCollections.observableArrayList();
    int bookingId;
    public void displayData(int id) {
        bookingRoomDTOS.addAll(bookingRoomService.getBookingRoomByIdBooking(id));
        tableView.setItems(bookingRoomDTOS);
        bookingId = id;
    }
}
