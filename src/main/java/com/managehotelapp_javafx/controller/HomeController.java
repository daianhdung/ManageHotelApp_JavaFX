package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.dto.InvoiceDTO;
import com.managehotelapp_javafx.dto.ServiceDTO;
import com.managehotelapp_javafx.services.BookingRoomService;
import com.managehotelapp_javafx.services.BookingService;
import com.managehotelapp_javafx.services.InvoiceService;
import com.managehotelapp_javafx.services.ServicesService;
import com.managehotelapp_javafx.services.imp.BookingRoomServiceImp;
import com.managehotelapp_javafx.services.imp.BookingServiceImp;
import com.managehotelapp_javafx.services.imp.InvoiceServiceImp;
import com.managehotelapp_javafx.services.imp.ServicesServiceImp;
import com.managehotelapp_javafx.utils.enumpackage.BookingStatus;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HomeController implements Initializable {

    @FXML
    private Label reservationCountLabel, checkInLabel, incomesLabel;
    @FXML
    private GridPane gridPane;

    @FXML
    private AnchorPane storageScene;

    BookingRoomService bookingRoomService = new BookingRoomServiceImp();

    ServicesService services = new ServicesServiceImp();
    BookingService bookingService = new BookingServiceImp();
    InvoiceService invoiceService = new InvoiceServiceImp();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        // -------set check-in label
        int checkInQty = (int) bookingRoomService.getCurrentBookingRoom()
                .stream().filter(bookingRoomDTO -> bookingRoomDTO.getStatus().toUpperCase().equals(BookingStatus.CHECKED_IN.toString()))
                .count();

        checkInLabel.setText(String.valueOf(checkInQty));

        // -------set reservation label
        int reservationQty = (int) bookingRoomService.getCurrentBookingRoom()
                .stream().filter(bookingRoomDTO -> bookingRoomDTO.getStatus().toUpperCase().equals(BookingStatus.RESERVED.toString()))
                .count();
        reservationCountLabel.setText(String.valueOf(reservationQty));


        // -------set storage
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setLayoutY(90);
        gridPane.setStyle("-fx-background-color: white");

        List<ServiceDTO> list = services.getServicesList();
        int maxRow = list.size();

        Node[][] contentArray = new Node[2][maxRow];

        int col = 0;
        int i = 0;
        for (int row = 0; row < maxRow; row++) {
            var service = list.get(i);

            Label label = new Label(service.getDescription());
            label.setPadding(new Insets(40));
            label.setFont(new Font("Arial", 25));
            contentArray[col][row] = label;
            gridPane.add(label, col, row);


            int qtyConsumed = services.findServicesById(service.getId()).getQtyConsumed();
            int inStock = service.getQuantity();
            Label label2 = new Label(String.valueOf(inStock - qtyConsumed + "/" + inStock));
            label2.setPadding(new Insets(40));
            label2.setFont(new Font("Arial", 25));
            contentArray[col + 1][row] = label2;

            gridPane.add(label2, col + 1, row);
            i++;
        }
        storageScene.getChildren().add(gridPane);


        // -------set revenue

        List<InvoiceDTO> Invlist = invoiceService.getInvoiceDTOList().stream().filter(invoiceDTO -> {
            Timestamp timestamp = invoiceDTO.getCreatedAt();
            LocalDate localDate = timestamp.toLocalDateTime().toLocalDate();
            return localDate.equals(LocalDate.now());
        }).collect(Collectors.toList());

        int sum = 0;
        for (var invAmount : Invlist) {
            sum += invAmount.getPaymentAmount();
        }
        incomesLabel.setText(String.valueOf(sum));

    }



}
