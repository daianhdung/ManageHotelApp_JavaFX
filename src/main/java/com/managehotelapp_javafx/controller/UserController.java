package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.dto.UserDTO;
import com.managehotelapp_javafx.repository.imp.UserStatusRepositoryImp;
import com.managehotelapp_javafx.services.UserRoleService;
import com.managehotelapp_javafx.services.UserService;
import com.managehotelapp_javafx.services.UserStatusService;
import com.managehotelapp_javafx.services.imp.UserRoleServiceImp;
import com.managehotelapp_javafx.services.imp.UserServiceImp;
import com.managehotelapp_javafx.services.imp.UserStatusServiceImp;
import com.managehotelapp_javafx.utils.constant.FXMLLoaderConstant;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private TableColumn<UserDTO, Boolean> actionCol;

    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<UserDTO, String> emailCol;

    @FXML
    private TableColumn<UserDTO, String> fullnameCol;

    @FXML
    private TableColumn<UserDTO, String> idCol;

    @FXML
    private TableColumn<UserDTO, String> indexCol;

    @FXML
    private TableColumn<UserDTO, String> roleCol;

    @FXML
    private TextField searchInput;
    @FXML
    private TextField fullnameTxt, identityTxt, emailTxt, idTxt, usernameTxt;
    @FXML
    private TextField phoneNumberTxt;
    @FXML
    private TextArea addressTxt;

    @FXML
    private ComboBox<String> roleBox;
    @FXML
    private ComboBox<String> statusBox;
    @FXML
    private Button backBtn, saveBtn, deleteBtn;
    @FXML
    private GridPane groupGenderBtn;


    @FXML
    private TableColumn<UserDTO, String> statusCol;

    @FXML
    private TableView<UserDTO> userTableView;

    @FXML
    private AnchorPane userTableScene, userDetailScene;
    @FXML
    private RadioButton gender1, gender2, gender3;
    @FXML
    private Label fullnameLabel, identityLabel, phoneLabel, emailLabel, addressLabel, usernameLabel;

    @FXML
    private TableColumn<UserDTO, String> usernameCol;

    ObservableList<UserDTO> userObservableList;
    UserService userService = new UserServiceImp();

    UserRoleService userRoleService = new UserRoleServiceImp();
    UserStatusService userStatusService = new UserStatusServiceImp();

    private Alert alert;

    private Stage primaryStage;
    private FXMLLoader fxmlLoader;

    @FXML
    void onAdd() {
        primaryStage = new Stage();
        fxmlLoader = FXMLLoaderConstant.getAddUserScene();
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onDelete(ActionEvent event) {
        String idUser = idTxt.getText();

        if (deleteBtn.isFocused()) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Are you sure you want to delete?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                userService.deleteUser(Integer.parseInt(idUser));
                onBack();
                showUserTableView();
            }
        }
    }

    public void onSave() {
        if (checkAllTextField()){

            UserDTO userDTO = new UserDTO();
            userDTO.setId(Integer.parseInt(idTxt.getText()));
            userDTO.setFullName(fullnameTxt.getText());
            userDTO.setUsername(usernameTxt.getText());
            userDTO.setEmail(emailTxt.getText());
            userDTO.setIdentity(identityTxt.getText());
            userDTO.setPhone(phoneNumberTxt.getText());
            userDTO.setRole(roleBox.getSelectionModel().getSelectedItem());
            userDTO.setStatus(statusBox.getSelectionModel().getSelectedItem());
            userDTO.setAddress(addressTxt.getText());
            userDTO.setGender(getGender());

            if (saveBtn.isFocused()){
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Do you want to save changes?");

                if (alert.showAndWait().get() == ButtonType.OK) {
                    userService.updateUser(userDTO);
                    onBack();
                    showUserTableView();
                }
            }
        }



    }

    public boolean checkAllTextField() {
        boolean result = false;

        BooleanBinding usernameBinding = Bindings.createBooleanBinding(() -> {
            return usernameTxt.getText().matches("[a-z0-9_-]{6,12}$");
        }, usernameTxt.textProperty());

        BooleanBinding emailBinding = Bindings.createBooleanBinding(() -> {
            return emailTxt.getText().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
        }, emailTxt.textProperty());


        BooleanBinding fullnameBinding = Bindings.createBooleanBinding(() -> {
            return fullnameTxt.getText().matches("^[A-Za-z ]{2,30}$");
        }, fullnameTxt.textProperty());


        BooleanBinding identityBinding = Bindings.createBooleanBinding(() -> {
            return identityTxt.getText().matches("^\\d{10}$");
        }, identityTxt.textProperty());

//        BooleanBinding phoneBinding = Bindings.createBooleanBinding(() -> {
//            return phoneNumberTxt.getText().matches("^\\d{10}$");
//        }, phoneNumberTxt.textProperty());

        BooleanBinding phoneBinding = Bindings.createBooleanBinding(() -> {
            String regex = "^(0)(3|5|7|8|9)+([0-9]{7})$";
            return phoneNumberTxt.getText().matches(regex);
        }, phoneNumberTxt.textProperty());


        BooleanBinding addressBinding = Bindings.createBooleanBinding(() -> {
            return addressTxt.getText().matches("^[0-9a-zA-Z\\s,\\-,\\/]{5,100}+$");
        }, addressTxt.textProperty());


        usernameTxt.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue) {
                usernameLabel.setText("- Must be between 6-12 characters\n" +
                        "- Only letters (a-z), numbers (0-9), underscore(_), hyphen (-)");
                usernameLabel.visibleProperty().bind(usernameBinding.not());
            }
        }));
        emailTxt.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue) {
                emailLabel.setText("- Pls enter a valid email address (ex: abc@gmail.com)");
                emailLabel.visibleProperty().bind(emailBinding.not());
            }
        }));

        fullnameTxt.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue) {
                fullnameLabel.setText("- Must be between 2-30 characters and only letters");
                fullnameLabel.visibleProperty().bind(fullnameBinding.not());
            }
        }));

        identityTxt.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue) {
                identityLabel.setText("- Only 10 digits");
                identityLabel.visibleProperty().bind(identityBinding.not());
            }
        }));

        phoneNumberTxt.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue) {
                phoneLabel.setText("- Invalid phone number");
                phoneLabel.visibleProperty().bind(phoneBinding.not());
            }
        }));

        addressTxt.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue) {
                addressLabel.setText("- Special characters are not allowed: @$!%*?&#");
                addressLabel.visibleProperty().bind(addressBinding.not());
            }
        }));

        System.out.println(addressBinding.getValue());


        if (!usernameBinding.getValue() || !emailBinding.getValue() || !identityBinding.getValue()
                || !fullnameBinding.getValue() || !phoneBinding.getValue() || !addressBinding.getValue()) {
            if (!usernameBinding.getValue()) {
                usernameTxt.requestFocus();
            } else if (!emailBinding.getValue()) {
                emailTxt.requestFocus();
            } else if (!identityBinding.getValue()) {
                identityTxt.requestFocus();
            } else if (!fullnameBinding.getValue()) {
                fullnameTxt.requestFocus();
            } else if (!phoneBinding.getValue()) {
                phoneNumberTxt.requestFocus();
            } else if (!addressBinding.getValue()) {
                addressTxt.requestFocus();
            }
        } else {
            result = true;
        }

        return result;
    }


    public void onBack() {
        userTableScene.setVisible(true);
        userDetailScene.setVisible(false);
    }

    @FXML
    void onSearch() {

        FilteredList<UserDTO> filteredList = new FilteredList<>(userObservableList, p -> true);

        searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(userSearch -> {
                String searchKeyword = newValue.toLowerCase();
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) return true;

                if (userSearch.getFullName().toLowerCase().indexOf(searchKeyword) > -1) return true;
                else if (userSearch.getEmail().toLowerCase().indexOf(searchKeyword) > -1) return true;
                else if (String.valueOf(userSearch.getId()).indexOf(searchKeyword) > -1) return true;
                else if (userSearch.getUsername().toLowerCase().indexOf(searchKeyword) > -1) return true;
                else if (userSearch.getStatus().toLowerCase().indexOf(searchKeyword) > -1) return true;
                else return false;
            });
        });
        SortedList<UserDTO> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(userTableView.comparatorProperty());
        userTableView.setItems(sortedList);
    }

    public void showStatusComboBox() {
        ObservableList<String> statusList = FXCollections.observableArrayList();
        userStatusService.getUserStatusList().forEach(userRoleDTO -> {
            statusList.add(userRoleDTO.getTitle());
        });
        statusBox.setItems(statusList);
    }

    public void showRoleComboBox() {
        ObservableList<String> roleList = FXCollections.observableArrayList();
        userRoleService.getUserRoleList().forEach(userRoleDTO -> {
            roleList.add(userRoleDTO.getTitle());
        });
        roleBox.setItems(roleList);
    }

    public void showUserTableView() {



        userObservableList = FXCollections.observableArrayList(userService.getUsers());

        indexCol.setCellValueFactory(cell -> {
            String index = cell.getTableView().getItems().indexOf(cell.getValue()) + 1 + "";
            return new SimpleStringProperty(index);
        });
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        fullnameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        actionCol.setCellFactory(cell -> tableCellDetailBtn());

        userTableView.setItems(userObservableList);
    }

    public TableCell tableCellDetailBtn() {
        Button detailBtn = new Button("Details");
        TableCell<UserDTO, Boolean> cell = new TableCell<>() {
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
            userTableScene.setVisible(false);
            userDetailScene.setVisible(true);
            getUserDetailScene(cell.getTableRow().getItem());
        });
        return cell;
    }

    public void getUserDetailScene(UserDTO user) {
        fullnameTxt.setText(user.getFullName());
        identityTxt.setText(user.getIdentity());
        phoneNumberTxt.setText(user.getPhone());
        emailTxt.setText(user.getEmail());
        idTxt.setText(String.valueOf(user.getId()));
        usernameTxt.setText(user.getUsername());
        roleBox.getSelectionModel().select(user.getRole());
        statusBox.getSelectionModel().select(user.getStatus());
        addressTxt.setText(user.getAddress());

        if (user.getGender().equalsIgnoreCase(gender1.getText())) {
            gender1.setSelected(true);
        } else if (user.getGender().equalsIgnoreCase(gender2.getText())) {
            gender2.setSelected(true);
        } else if (user.getGender().equalsIgnoreCase(gender3.getText())) {
            gender3.setSelected(true);
        }
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
        showUserTableView();
        onSearch();
        getGender();
        showRoleComboBox();
        showStatusComboBox();
        checkAllTextField();
    }
}
