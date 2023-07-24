package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.dto.*;
import com.managehotelapp_javafx.entity.BookingRoomEntity;
import com.managehotelapp_javafx.entity.BookingServiceEntity;
import com.managehotelapp_javafx.entity.ServiceEntity;
import com.managehotelapp_javafx.repository.BookingRoomRepository;
import com.managehotelapp_javafx.repository.imp.BookingRoomRepositoryImp;
import com.managehotelapp_javafx.services.*;
import com.managehotelapp_javafx.services.imp.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.time.format.DateTimeFormatter;
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

                if (invoiceDTO.getCustomer().toLowerCase().indexOf(searchKeyword) >-1)return true;
                else if (String.valueOf(invoiceDTO.getId()).toLowerCase().indexOf(searchKeyword) >-1) return true;
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

    public int paymentAmount(List<RoomDTO> roomDTOList , List<BookingServiceDTO> bookingServiceDTOList){

        int roomCharge = 0;

        for (RoomDTO roomDTO : roomDTOList){
            int roomType = Integer.parseInt(roomDTO.getType());
            roomTypeService.getRoomById(Integer.valueOf(roomType)).getPrice();
            int roomPrice = roomTypeService.getRoomById(Integer.valueOf(roomType)).getPrice();
            roomCharge += roomPrice;
        }


        int serviceCharge = 0;
        for (BookingServiceDTO bookingServiceDTO: bookingServiceDTOList){
            serviceCharge += (service.findServicesById(bookingServiceDTO.getServiceId()).getPrice()
                                * service.findServicesById(bookingServiceDTO.getServiceId()).getQuantity());
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
        String checkInDay = bookingService.getBookingById(idBookingRoom).getActualDateIn().toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String checkOunDay = bookingService.getBookingById(idBookingRoom).getActualDateOut().toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        idBookingLabel.setText(String.valueOf(idBookingRoom));
        createdAtLabel.setText(invoiceDTO.getCreatedAt().toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        checkInLabel.setText(checkInDay);
        checkOutLabel.setText(checkOunDay);
        idInvLabel.setText(String.valueOf(invoiceDTO.getId()));
        cusLabel.setText(invoiceDTO.getCustomer());
        totalLabel.setText(String.valueOf(invoiceDTO.getPaymentAmount()));

        //get booking_id to show room list
        List<RoomDTO> roomDTOList = bookingRoomService.getRoomByIdBooking(idBookingRoom);
        getRoomDetailTableView(roomDTOList);

        // get booking_service
        BookingRoomEntity bookingRoom = bookingRoomService.getBookingRoomByIdInvoice(idInvoice);
        List<BookingServiceDTO> bookingServiceDTO = bookingService.findBooingServicesByBookingRoomId(bookingRoom.getId());
        getRoomServiceTableView(bookingServiceDTO);

        // total payment
        int paymentAmount = paymentAmount(roomDTOList,bookingServiceDTO);
        totalLabel.setText(String.valueOf(paymentAmount));


        // payment status
        paymentStatus.setText(invoiceService.findInvoiceById(idInvoice).getInvoiceStatus());

    }

    public void getRoomDetailTableView(List<RoomDTO> list){
        ObservableList<RoomDTO> roomObservableList = FXCollections.observableList(list);

        roomNumberCol.setCellValueFactory(cell -> {

           return new SimpleStringProperty(cell.getValue().getRoomNo());
        });
        roomTypeCol.setCellValueFactory(cell ->{
            int roomType = Integer.valueOf(roomService.getRoomById(cell.getValue().getId()).getType());

            return new SimpleStringProperty(roomTypeService.getRoomById(roomType).getDescription());
        });
        roomPriceCol.setCellValueFactory(cell -> {
            int roomType = Integer.valueOf(roomService.getRoomById(cell.getValue().getId()).getType());
            return new SimpleStringProperty(String.valueOf(roomTypeService.getRoomById(roomType).getPrice()));
        });
        roomTableView.setItems(roomObservableList);

    }

    public void getRoomServiceTableView(List<BookingServiceDTO> list){
        ObservableList<BookingServiceDTO> serviceList = FXCollections.observableArrayList(list);
        serviceNameCol.setCellValueFactory(cell -> new SimpleStringProperty(service.findServicesById(cell.getValue().getServiceId()).getDescription()));

        servicePriceCol.setCellValueFactory(cell -> {
            return new SimpleStringProperty(service.findServicesById(cell.getValue().getServiceId()).getPrice() + "");
        });
//
        serviceQtyCol.setCellValueFactory(cell -> {
            return new SimpleStringProperty(service.findServicesById(cell.getValue().getServiceId()).getQuantity() + "");
        });
//
        serviceAmountCol.setCellValueFactory(cell -> {
            return new SimpleStringProperty(service.findServicesById(cell.getValue().getServiceId()).getPrice() *
                    service.findServicesById(cell.getValue().getServiceId()).getQuantity() +"");
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
