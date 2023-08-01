package com.managehotelapp_javafx.controller.checkout;

import com.managehotelapp_javafx.controller.booking.CurrentBookingController;
import com.managehotelapp_javafx.dto.BookingRoomDTO;
import com.managehotelapp_javafx.dto.BookingServiceDTO;
import com.managehotelapp_javafx.dto.InvoiceDTO;
import com.managehotelapp_javafx.dto.RoomDTO;
import com.managehotelapp_javafx.entity.BookingRoomEntity;
import com.managehotelapp_javafx.services.BookingRoomService;
import com.managehotelapp_javafx.services.BookingServicesService;
import com.managehotelapp_javafx.services.imp.BookingRoomServiceImp;
import com.managehotelapp_javafx.services.imp.BookingServicesServiceImp;
import com.managehotelapp_javafx.utils.alert.AlertUtils;
import com.managehotelapp_javafx.utils.constant.FXMLLoaderConstant;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.managehotelapp_javafx.utils.constant.DateFormatConstant.DATETIME_FORMAT_PATTERN;

public class SubmitCheckoutController implements Initializable {
    BookingRoomService bookingRoomService = new BookingRoomServiceImp();
    BookingServicesService bookingServicesService = new BookingServicesServiceImp();

    @FXML
    private Label checkInLabel, checkOutLabel, customerNameLabel, totalLabel;
    @FXML
    private Button btnBack, btnCheckout;
    @FXML
    private TableView<BookingRoomDTO> bookingRoomTableView;
    @FXML
    private TableView<BookingServiceDTO> serviceTableView;
    @FXML
    private TableColumn<BookingRoomDTO, String> serialRoomCol, roomNoCol, roomTypeCol, roomPriceCol, dayOfStayCol, totalCostRoomCol;
    @FXML
    private TableColumn<BookingServiceDTO, String> serialServiceCol, serviceNameCol, servicePriceCol, quantityServiceCol, totalCostServiceCol, roomNoServiceCol;

    private Stage primaryStage;
    private FXMLLoader fxmlLoader;
    ObservableList<BookingRoomDTO> bookingRoomDTOObservableList = FXCollections.observableArrayList();
    ObservableList<BookingServiceDTO> bookingServiceDTOObservableList = FXCollections.observableArrayList();

    private List<BookingRoomDTO> bookingRoomDTOList = new ArrayList<>();

    public void displayCheckoutBookingRoom(List<Integer> bookingRoomIdList) {
        checkOutLabel.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATETIME_FORMAT_PATTERN)));

        int totalCheckout = 0;
        for (var item : bookingRoomIdList) {
            BookingRoomDTO bookingRoomDTO = bookingRoomService.getBookingRoomById(item);
            bookingRoomDTO.setCheckoutDate(checkOutLabel.getText());

            bookingRoomDTOList.add(bookingRoomDTO);
            bookingRoomDTOObservableList.add(bookingRoomDTO);
            bookingServiceDTOObservableList.addAll(bookingServicesService.findBooingServicesByBookingRoomId(bookingRoomDTO.getId()));

            totalCheckout += bookingRoomDTO.getTotalCheckOut();
        }
        totalLabel.setText(String.valueOf(totalCheckout));
        checkInLabel.setText(bookingRoomDTOObservableList.get(0).getCheckinDate());

        customerNameLabel.setText(bookingRoomDTOObservableList.get(0).getCustomerName());

        bookingRoomTableView.setItems(bookingRoomDTOObservableList);
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
        dayOfStayCol.setCellValueFactory(new PropertyValueFactory<>("lengthOfStay"));
        totalCostRoomCol.setCellValueFactory(new PropertyValueFactory<>("totalRoomFee"));

        serialServiceCol.setCellValueFactory(cell -> {
            String index = cell.getTableView().getItems().indexOf(cell.getValue()) + 1 + "";
            return new SimpleStringProperty(index);
        });
        roomNoServiceCol.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        serviceNameCol.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        servicePriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityServiceCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalCostServiceCol.setCellValueFactory(new PropertyValueFactory<>("total"));

    }


    @FXML
    void onCheckout(ActionEvent event) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setPaymentAmount(Integer.parseInt(totalLabel.getText()));
        boolean isSuccess = bookingRoomService.checkOutRoom(bookingRoomDTOList, invoiceDTO);
        if (isSuccess) {
            AlertUtils alertUtils = new AlertUtils();
            alertUtils.alert(Alert.AlertType.INFORMATION, "Checkout", "Checkout successfully");
            primaryStage = (Stage) btnCheckout.getScene().getWindow();
            primaryStage.close();

        }
    }

    @FXML
    void onBack(ActionEvent event) {
        primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryStage.close();
    }
}
