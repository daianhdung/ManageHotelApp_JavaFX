package com.managehotelapp_javafx.controller;


import com.managehotelapp_javafx.dto.BookingRoomDTO;
import com.managehotelapp_javafx.dto.BookingServiceDTO;
import com.managehotelapp_javafx.dto.ServiceDTO;
import com.managehotelapp_javafx.services.BookingServicesService;
import com.managehotelapp_javafx.services.ServicesService;
import com.managehotelapp_javafx.services.imp.BookingServicesServiceImp;
import com.managehotelapp_javafx.services.imp.ServicesServiceImp;
import com.managehotelapp_javafx.utils.alert.AlertUtils;
import com.managehotelapp_javafx.utils.constant.FXMLLoaderConstant;
import com.managehotelapp_javafx.utils.enumpackage.BookingStatus;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.managehotelapp_javafx.utils.constant.DateFormatConstant.DATETIME_FORMAT_PATTERN;

public class ServiceController implements Initializable {
    private Stage primaryStage;
    private FXMLLoader fxmlLoader;
    ServicesService servicesService = new ServicesServiceImp();
    BookingServicesService bookingServicesService = new BookingServicesServiceImp();
    @FXML
    private TableView<BookingServiceDTO> tableView;

    @FXML
    private TableColumn<BookingServiceDTO, String> serviceCol, serialCol, orderDate;
    @FXML
    private TableColumn<BookingServiceDTO, Integer> quantityCol, priceCol, totalCol;
    @FXML
    private Label customerName, roomNo, orderFee;
    @FXML
    private Button btnDelete, btnBack, btnOrder,btnSaveService;

    @FXML
    private ComboBox<String> serviceCombo;
    @FXML
    private TextField stockText;

    ObservableList<String> nameService = FXCollections.observableArrayList();
    List<ServiceDTO> serviceDTOList =  servicesService.getServicesList();

    ObservableList<BookingServiceDTO> listService = FXCollections.observableArrayList();
    List<BookingServiceDTO> listServiceDelete = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serviceDTOList.forEach(item -> {
            nameService.add(item.getDescription());
        });
        serviceCombo.setItems(nameService);
        serviceCombo.setValue(nameService.get(0));
        stockText.setText(String.valueOf(1));
        orderFee.setText(String.valueOf(0));

        serialCol.setCellValueFactory(cell -> {
            String index = cell.getTableView().getItems().indexOf(cell.getValue()) + 1 + "";
            return new SimpleStringProperty(index);
        });
        serviceCol.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        orderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

        listService.addListener((ListChangeListener) change -> orderFee.setText(String.valueOf((updateNewOrderFee()))));

    }

    public int calculateTotal(int price, int quantity){
        return price * quantity;
    }

    private BookingRoomDTO bookingRoomDTO;
    public void getBookingData(BookingRoomDTO bookingRoomDTO){
        customerName.setText(bookingRoomDTO.getCustomerName());
        roomNo.setText(bookingRoomDTO.getRoomNo());

        this.bookingRoomDTO = bookingRoomDTO;
        boolean isNotHistory = bookingRoomDTO.getStatus().toUpperCase().equals(BookingStatus.CHECKED_IN.toString())
                || bookingRoomDTO.getStatus().toUpperCase().equals(BookingStatus.RESERVED.toString());
        listService.addAll(bookingServicesService.findBooingServicesByBookingRoomId(bookingRoomDTO.getId()));

        orderFee.setText(String.valueOf(listService.stream().mapToInt(BookingServiceDTO::getTotal).sum()));

        tableView.setItems(listService);

        btnDelete.setVisible(isNotHistory);
        btnOrder.setVisible(isNotHistory);
        btnSaveService.setVisible(isNotHistory);
    }

    public ServiceDTO findServiceByName(String itemService){
        for(var item: serviceDTOList){
            if(item.getDescription().equals(itemService)){
                return item;
            }
        }
        return null;
    }

    private int updateNewOrderFee() {
        return tableView.getItems().stream().mapToInt(BookingServiceDTO::getTotal).sum();
    }

    public void onDelete(ActionEvent e) {
        BookingServiceDTO bookingServiceDTO = tableView.getSelectionModel().getSelectedItem();
        listServiceDelete.add(bookingServiceDTO);
        listService.remove(bookingServiceDTO);
        tableView.setItems(listService);
        tableView.getSelectionModel().clearSelection();
    }

    public void onBack(ActionEvent e) {
        primaryStage = (Stage) btnBack.getScene().getWindow();
        fxmlLoader = FXMLLoaderConstant.getBookingScene();
        try {
            Parent root = fxmlLoader.load();
            primaryStage.setScene(new Scene(root));
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }
    }

    public void onSave(ActionEvent e) {
        ServiceDTO serviceDTO = findServiceByName(serviceCombo.getValue());
        BookingServiceDTO bookingServiceDTO = new BookingServiceDTO();
        bookingServiceDTO.setServiceId(serviceDTO.getId());
        bookingServiceDTO.setBookingRoomId(bookingRoomDTO.getId());

        bookingServiceDTO.setServiceName(serviceDTO.getDescription());
        bookingServiceDTO.setPrice(serviceDTO.getPrice());
        bookingServiceDTO.setQuantity(Integer.parseInt(stockText.getText()));

        String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATETIME_FORMAT_PATTERN));
        bookingServiceDTO.setOrderDate(formattedDateTime);
        bookingServiceDTO.setTotal(calculateTotal((int) bookingServiceDTO.getPrice(), bookingServiceDTO.getQuantity()));

        listService.stream().filter(item -> item.getServiceName().equals(serviceDTO.getDescription()))
                .findFirst()
                .ifPresentOrElse(
                        item -> {
                            int index = listService.indexOf(item);
                            item.setQuantity(item.getQuantity() + bookingServiceDTO.getQuantity());
                            item.setTotal(item.getTotal() + bookingServiceDTO.getTotal());
                            listService.set(index, item);
                        },
                        () -> listService.add(bookingServiceDTO)
                );
        tableView.setItems(listService);
    }

    public void onSaveService(ActionEvent e) {
        try{
            bookingServicesService.insertBookingService(new ArrayList<>(tableView.getItems()), listServiceDelete, Integer.parseInt(orderFee.getText()));
            AlertUtils alertUtils = new AlertUtils();
            alertUtils.alert(Alert.AlertType.INFORMATION, "Success", "Save order successfully.");
        }catch (Exception err){
            err.printStackTrace();
        }
    }


}
