package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.dto.BookingServiceDTO;
import com.managehotelapp_javafx.dto.InvoiceDTO;
import com.managehotelapp_javafx.dto.RoomDTO;
import com.managehotelapp_javafx.dto.ServiceDTO;
import com.managehotelapp_javafx.entity.BookingRoomEntity;
import com.managehotelapp_javafx.services.*;
import com.managehotelapp_javafx.services.imp.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {

    @FXML
    private TableColumn<InvoiceDTO, Boolean> actionCol;
    @FXML
    private TextField searchInput;


    @FXML
    private Button backBtn;

    @FXML
    private Label checkInLabel;

    @FXML
    private Label checkOutLabel;

    @FXML
    private TableColumn<InvoiceDTO, String> createdAtCol;

    @FXML
    private Label createdAtLabel;

    @FXML
    private Label cusLabel;

    @FXML
    private TableColumn<InvoiceDTO, String> customerCol;

    @FXML
    private Button deleteBtn;

    @FXML
    private Label discountLabel;

    @FXML
    private TableColumn<InvoiceDTO, String> idBookingCol;

    @FXML
    private Label idBookingLabel;

    @FXML
    private TableColumn<InvoiceDTO, String> idInvCol;
    @FXML
    private TableColumn<InvoiceDTO, String> amountCol;
    @FXML
    private TableColumn<InvoiceDTO, String> indexInvCol;

    @FXML
    private Label idInvLabel, paymentStatus;

    @FXML
    private TableColumn<InvoiceDTO, String> indexCol;


    @FXML
    private AnchorPane invoiceDetailScene;

    @FXML
    private TableView<InvoiceDTO> invoiceTableView;


    @FXML
    private Button printBtn;

    @FXML
    private TableColumn<RoomDTO, String> roomAmountCol;

    @FXML
    private TableColumn<RoomDTO, String> roomNumberCol;
    @FXML
    private TableColumn<RoomDTO, String> roomPriceCol;

    @FXML
    private TableColumn<RoomDTO, String> roomQtyCol;

    @FXML
    private TableView<RoomDTO> roomTableView;

    @FXML
    private TableColumn<RoomDTO, String> roomTypeCol;

    @FXML
    private Button searchBtn;

    @FXML
    private TableColumn<BookingServiceDTO, String> serviceAmountCol;

    @FXML
    private TableColumn<BookingServiceDTO, String> serviceNameCol;
    @FXML
    private TableColumn<BookingServiceDTO, String> servicePriceCol;

    @FXML
    private TableColumn<BookingServiceDTO, String> serviceQtyCol;

    @FXML
    private TableView<BookingServiceDTO> serviceTableView;
    @FXML
    private BorderPane invoiceTableScene;

    @FXML
    private Label totalLabel;

    ObservableList<InvoiceDTO> invoiceObservableList;
    InvoiceService invoiceService = new InvoiceServiceImp();
    InvoiceStatusService invoiceStatusService = new InvoiceStatusServiceImp();
    BookingRoomService bookingRoomService = new BookingRoomServiceImp();
    BookingService bookingService = new BookingServiceImp();
    BookingServicesService bookingServicesService = new BookingServicesServiceImp();
    RoomService roomService = new RoomServiceImp();
    RoomTypeService roomTypeService = new RoomTypeServiceImp();

    ServicesService service = new ServicesServiceImp();




    @FXML
    void onSearch() {
        FilteredList<InvoiceDTO> filteredList = new FilteredList<>(invoiceObservableList, p -> true);
        searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(invoiceDTO -> {
                String searchKeyword = newValue.toLowerCase();
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) return true;

                if (invoiceDTO.getCustomer().toLowerCase().indexOf(searchKeyword) > -1) return true;
                else if (String.valueOf(invoiceDTO.getId()).toLowerCase().indexOf(searchKeyword) > -1) return true;
                else return false;
            });
        });
        SortedList<InvoiceDTO> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(invoiceTableView.comparatorProperty());
        invoiceTableView.setItems(sortedList);

    }


    public void showInvoiceTableView() {
        invoiceObservableList = FXCollections.observableList(invoiceService.getInvoiceDTOList());
//        indexInvCol.setCellValueFactory(cell -> {
//            String index = cell.getTableView().getItems().indexOf(cell.getValue()) + 1 + "";
//            return new SimpleStringProperty(index);
//        });

        idInvCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("customer"));
        createdAtCol.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        actionCol.setCellFactory(cell -> tableCellDetailBtn());
        amountCol.setCellValueFactory(new PropertyValueFactory<>("paymentAmount"));

        invoiceTableView.setItems(invoiceObservableList);

    }

    public int paymentAmount(List<RoomDTO> roomDTOList, List<BookingServiceDTO> bookingServiceDTOList) {

        int roomCharge = 0;

        for (RoomDTO roomDTO : roomDTOList) {
            roomCharge += roomDTO.getPrice();
        }

        int serviceCharge = 0;

        for (BookingServiceDTO bookingServiceDTO : bookingServiceDTOList) {
            serviceCharge += (bookingServiceDTO.getPrice()
                    * bookingServiceDTO.getQuantity());
        }

        return roomCharge + serviceCharge;
    }

    public TableCell<InvoiceDTO, Boolean> tableCellDetailBtn() {
        Button detailBtn = new Button("Details");
        TableCell<InvoiceDTO, Boolean> cell = new TableCell<>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(detailBtn);
                }
            }
        };
        detailBtn.setOnAction(event -> {
            invoiceDetailScene.setVisible(true);
            getInvoiceDetailScene(cell.getTableRow().getItem());
        });
        return cell;
    }

    public void getInvoiceDetailScene(InvoiceDTO invoiceDTO) {
        int idInvoice = invoiceDTO.getId();

        int idBookingRoom = bookingRoomService.getBookingRoomByIdInvoice(idInvoice).getId();
        var booking = bookingService.getBookingById(idBookingRoom);
        String checkInDay = booking.getActualDateIn().toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String checkOutDay = booking.getActualDateOut().toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));


        idBookingLabel.setText(String.valueOf(idBookingRoom));
        createdAtLabel.setText(invoiceDTO.getCreatedAt().toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        checkInLabel.setText(checkInDay);
        checkOutLabel.setText(checkOutDay);
        idInvLabel.setText(String.valueOf(invoiceDTO.getId()));
        cusLabel.setText(invoiceDTO.getCustomer());
        totalLabel.setText(String.valueOf(invoiceDTO.getPaymentAmount()));

        //get booking_id to show room list

        List<RoomDTO> roomInvoiceList = new ArrayList<>();

        bookingRoomService.getRoomByIdBooking(idBookingRoom).forEach(roomDTO -> {
            RoomDTO room = new RoomDTO();
            var roomTypeDetail = roomService.getRoomById(roomDTO.getId()).getType();
            room.setRoomNo(roomDTO.getRoomNo());
            room.setType(roomTypeDetail);
            room.setPrice(roomTypeService.getRoomById(Integer.parseInt(roomTypeDetail)).getPrice());

            roomInvoiceList.add(room);
        });
        getRoomDetailTableView(roomInvoiceList);

        // get booking_service

        List<BookingServiceDTO> bookingServiceInvList = new ArrayList<>();
        bookingServicesService.findBooingServicesByBookingRoomId(idBookingRoom).forEach(bookingServiceDTO -> {
            var serviceDetails = service.findServicesById(bookingServiceDTO.getServiceId());

            BookingServiceDTO bookingService = new BookingServiceDTO();
            bookingService.setServiceName(serviceDetails.getDescription());
            bookingService.setPrice(serviceDetails.getPrice());
            bookingService.setQuantity(bookingServiceDTO.getQuantity());

            bookingServiceInvList.add(bookingService);
        });
        getRoomServiceTableView(bookingServiceInvList);


        // total payment
        int paymentAmount = paymentAmount(roomInvoiceList, bookingServiceInvList);
        totalLabel.setText(String.valueOf(paymentAmount));


        // payment status
        paymentStatus.setText(invoiceService.findInvoiceById(idInvoice).getInvoiceStatus());

    }



    public void getRoomDetailTableView(List<RoomDTO> list) {
        ObservableList<RoomDTO> roomObservableList = FXCollections.observableList(list);

        roomNumberCol.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        roomTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        roomPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        roomTableView.setItems(roomObservableList);
    }

    public void getRoomServiceTableView(List<BookingServiceDTO> list) {
        ObservableList<BookingServiceDTO> serviceList = FXCollections.observableList(list);

        serviceNameCol.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        servicePriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        serviceQtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        serviceAmountCol.setCellValueFactory(cell -> {
            return new SimpleStringProperty(String.valueOf(cell.getValue().getPrice()
                    * cell.getValue().getQuantity()));
        });

        serviceTableView.setItems(serviceList);
    }

    public void onBack() {
        invoiceDetailScene.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showInvoiceTableView();
        onSearch();

    }
}
