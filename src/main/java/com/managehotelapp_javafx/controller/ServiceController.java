package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.dto.BookingRoomDTO;
import com.managehotelapp_javafx.dto.BookingServiceDTO;
import com.managehotelapp_javafx.dto.ServiceDTO;
import com.managehotelapp_javafx.services.ServicesService;
import com.managehotelapp_javafx.services.imp.ServicesServiceImp;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static com.managehotelapp_javafx.utils.constant.DateFormatConstant.DATETIME_FORMAT_PATTERN;

public class ServiceController implements Initializable {
    ServicesService servicesService = new ServicesServiceImp();
    @FXML
    private TableView<BookingServiceDTO> tableView;

    @FXML
    private TableColumn<BookingServiceDTO, String> serviceCol, serialCol, orderDate;
    @FXML
    private TableColumn<BookingServiceDTO, Integer> quantityCol, priceCol, totalCol;
    @FXML
    private Label customerName, roomNo, totalExpenses, orderFee;
    @FXML
    private Button btnDelete, btnBack, btnOrder, btnDefault,btnSaveService;
    @FXML
    private ComboBox<String> serviceCombo;
    @FXML
    private TextField stockText;

    ObservableList<String> nameService = FXCollections.observableArrayList();
    List<ServiceDTO> serviceDTOList =  servicesService.getServicesList();

    ObservableList<BookingServiceDTO> oldServiceList = FXCollections.observableArrayList();
    ObservableList<BookingServiceDTO> listService = FXCollections.observableArrayList();
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

    public void getBookingData(BookingRoomDTO bookingRoomDTO){
        customerName.setText(bookingRoomDTO.getCustomerName());
        roomNo.setText(bookingRoomDTO.getRoomNo());
        totalExpenses.setText(String.valueOf(bookingRoomDTO.getTotalExpenses()));
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
        listService.remove(bookingServiceDTO);
        tableView.setItems(listService);
    }

    public void onBack(ActionEvent e) {

    }

    public void onSave(ActionEvent e) {
        ServiceDTO serviceDTO = findServiceByName(serviceCombo.getValue());
        BookingServiceDTO bookingServiceDTO = new BookingServiceDTO();
        bookingServiceDTO.setServiceName(serviceDTO.getDescription());
        bookingServiceDTO.setPrice(serviceDTO.getPrice());
        bookingServiceDTO.setQuantity(Integer.parseInt(stockText.getText()));

        String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATETIME_FORMAT_PATTERN));
        bookingServiceDTO.setOrderDate(formattedDateTime);
        bookingServiceDTO.setTotal(calculateTotal((int) bookingServiceDTO.getPrice(), bookingServiceDTO.getQuantity()));

        listService.add(bookingServiceDTO);
        tableView.setItems(listService);
    }

    public void onDefault(ActionEvent e) {
        listService.clear();
        tableView.setItems(oldServiceList);
    }

    public void onSaveService(ActionEvent e) {

    }


}
