package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.dto.CustomerDTO;
import com.managehotelapp_javafx.services.CustomerService;
import com.managehotelapp_javafx.services.CustomerTypeService;
import com.managehotelapp_javafx.services.imp.CustomerServiceImp;
import com.managehotelapp_javafx.services.imp.CustomerTypeServiceImp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {

    @FXML
    private TableColumn<CustomerDTO, Boolean> actionCol;

    @FXML
    private Label addressLabel;

    @FXML
    private TextField addressTxt;

    @FXML
    private Button backBtn;

    @FXML
    private TextField bookingCountTxt;

    @FXML
    private TextField cityTxt;

    @FXML
    private TableColumn<CustomerDTO, String> countryCol;

    @FXML
    private TextField countryTxt;

    @FXML
    private Label createdAtLabel;

    @FXML
    private AnchorPane customerDetailScene;

    @FXML
    private AnchorPane customerTableScene;

    @FXML
    private Button deleteBtn;

    @FXML
    private DatePicker dobDatePicker;

    @FXML
    private TableColumn<CustomerDTO, String> emailCol;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailTxt;

    @FXML
    private TableColumn<CustomerDTO, String> fullnameCol;

    @FXML
    private Label fullnameLabel;

    @FXML
    private TextField fullnameTxt;

    @FXML
    private RadioButton gender1, gender2, gender3;

    @FXML
    private GridPane groupGenderBtn1;

    @FXML
    private TableColumn<CustomerDTO, String> idCol;

    @FXML
    private Label idLabel;

    @FXML
    private TableColumn<CustomerDTO, String> identityCol;

    @FXML
    private Label identityLabel;

    @FXML
    private TextField identityTxt;

    @FXML
    private TableColumn<CustomerDTO, String> indexCol;
    @FXML
    private TableColumn<CustomerDTO, String> genderCol;

    @FXML
    private TableColumn<CustomerDTO, String> mobileCol;

    @FXML
    private TableColumn<CustomerDTO, String> passportCol;

    @FXML
    private TextField passportTxt;

    @FXML
    private Label phoneLabel;

    @FXML
    private TextField phoneTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchInput;

    @FXML
    private ComboBox<String> statusBox;

    @FXML
    private ComboBox<String> typeBox;

    @FXML
    private TableColumn<CustomerDTO, String> typeCol;

    @FXML
    private TableView<CustomerDTO> customerTableView;

    @FXML
    private Label usernameLabel;


    ObservableList<CustomerDTO> customerObservableList;

    CustomerService customerService = new CustomerServiceImp();
    CustomerTypeService customerTypeService = new CustomerTypeServiceImp();

    private Alert alert;

    private Stage primaryStage;
    private FXMLLoader fxmlLoader;


    @FXML
    void onBack() {
        customerTableScene.setVisible(true);
        customerDetailScene.setVisible(false);
    }

    @FXML
    void onDelete() {
        int idCus = Integer.parseInt(idLabel.getText());
        if (deleteBtn.isFocused()) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Are you sure you want to delete?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                customerService.deleteCustomer(idCus);
                onBack();
                showCustomerTableView();
            }
        }
    }

    @FXML
    void onSave() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(Integer.parseInt(idLabel.getText()));
        customerDTO.setFullName(fullnameTxt.getText());
        customerDTO.setCustomerType(typeBox.getSelectionModel().getSelectedItem());
        customerDTO.setDob(Date.valueOf(dobDatePicker.getValue()));
        customerDTO.setPhone(phoneTxt.getText());
        customerDTO.setEmail(emailTxt.getText());
        customerDTO.setGender(getGender());
        customerDTO.setIdentity(identityTxt.getText());
        customerDTO.setPassportNo(passportTxt.getText());
        customerDTO.setAddress(addressTxt.getText());
        customerDTO.setCity(cityTxt.getText());
        customerDTO.setCountry(countryCol.getText());

        if (saveBtn.isFocused()) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Do you want to save changes?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                customerService.updateCustomer(customerDTO);
                onBack();
                showCustomerTableView();
            }
        }
    }







    public void showCustomerTypeComboBox() {
        ObservableList<String> customerTypeList = FXCollections.observableArrayList();
        customerTypeService.customerTypeDTOList().forEach(customerTypeDTO -> {
            customerTypeList.add(customerTypeDTO.getTitle());
        });
        typeBox.setItems(customerTypeList);
    }

    @FXML
    void onSearch() {
        FilteredList<CustomerDTO> filteredList = new FilteredList<>(customerObservableList, p -> true);

        try {
            searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(customerDTO -> {
                    String keyword = newValue.toLowerCase();
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) return true;

                    if (customerDTO.getPassportNo()!= null|| customerDTO.getIdentity() != null){
                        if (customerDTO.getPassportNo()!= null && customerDTO.getPassportNo().toLowerCase().indexOf(keyword) > -1) {
                            return true;
                        }else if(customerDTO.getIdentity().indexOf(keyword) > -1) {
                            return true;
                        }
                    }
                    if (customerDTO.getFullName().toLowerCase().indexOf(keyword) > -1) return true;
                    else if (customerDTO.getEmail().toLowerCase().indexOf(keyword) > -1) return true;
                    else if (customerDTO.getCustomerType().toLowerCase().indexOf(keyword) > -1) return true;
                    else if (customerDTO.getCountry().toLowerCase().indexOf(keyword) > -1) return true;
                    return false;
                });
            });
            SortedList<CustomerDTO> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(customerTableView.comparatorProperty());
            customerTableView.setItems(sortedList);

        }catch (NullPointerException e){
            System.out.println(e);;
        }


    }

    public void showCustomerTableView() {
        customerObservableList = FXCollections.observableList(customerService.customerDTOList());
        indexCol.setCellValueFactory(cell -> {
            String index = cell.getTableView().getItems().indexOf(cell.getValue()) + 1 + "";
            return new SimpleStringProperty(index);
        });

//        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        fullnameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        identityCol.setCellValueFactory(new PropertyValueFactory<>("identity"));
        passportCol.setCellValueFactory(new PropertyValueFactory<>("passportNo"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
//        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("customerType"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        actionCol.setCellFactory(cell -> tableCellDetailBtn());

        customerTableView.setItems(customerObservableList);
    }

    public TableCell tableCellDetailBtn() {
        Button detailBtn = new Button("Details");
        TableCell<CustomerDTO, Boolean> cell = new TableCell<>() {
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
            customerTableScene.setVisible(false);
            customerDetailScene.setVisible(true);
            getCustomerDetailScene(cell.getTableRow().getItem());
        });
        return cell;
    }

    public void getCustomerDetailScene(CustomerDTO customerDTO) {
        idLabel.setText(String.valueOf(customerDTO.getId()));
        createdAtLabel.setText(String.valueOf(customerDTO.getCreatedAt()));
        fullnameTxt.setText(customerDTO.getFullName());
        typeBox.getSelectionModel().select(customerDTO.getCustomerType());
        bookingCountTxt.setText(String.valueOf(customerDTO.getBookingCount()));
        dobDatePicker.setValue(customerDTO.getDob().toLocalDate());
        phoneTxt.setText(customerDTO.getPhone());
        emailTxt.setText(customerDTO.getEmail());

        if (customerDTO.getGender().equalsIgnoreCase(gender1.getText())) {
            gender1.setSelected(true);
        } else if (customerDTO.getGender().equalsIgnoreCase(gender2.getText())) {
            gender2.setSelected(true);
        } else if (customerDTO.getGender().equalsIgnoreCase(gender3.getText())) {
            gender3.setSelected(true);
        }
        identityTxt.setText(customerDTO.getIdentity());
        passportTxt.setText(customerDTO.getPassportNo());
        addressTxt.setText(customerDTO.getAddress());
        cityTxt.setText(customerDTO.getCity());
        countryTxt.setText(customerDTO.getCountry());
    }

    public String getGender() {
        ToggleGroup groupBtn = new ToggleGroup();
        gender1.setToggleGroup(groupBtn);
        gender2.setToggleGroup(groupBtn);
        gender3.setToggleGroup(groupBtn);

        RadioButton selectedBtn = (RadioButton) groupBtn.selectedToggleProperty().getValue();
        return selectedBtn != null ? selectedBtn.getText() : null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showCustomerTableView();
        getGender();
        onSearch();
        showCustomerTypeComboBox();
    }
}
