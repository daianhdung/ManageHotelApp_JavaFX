package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.entity.UserEntity;
import com.managehotelapp_javafx.entity.UserRoleEntity;
import com.managehotelapp_javafx.entity.UserStatusEntity;
import com.managehotelapp_javafx.repository.UserRoleRepository;
import com.managehotelapp_javafx.repository.UserStatusRepository;
import com.managehotelapp_javafx.repository.imp.UserRepositoryImp;
import com.managehotelapp_javafx.repository.imp.UserRoleRepositoryImp;
import com.managehotelapp_javafx.repository.imp.UserStatusRepositoryImp;
import com.managehotelapp_javafx.services.UserService;
import com.managehotelapp_javafx.services.imp.UserServiceImp;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.mindrot.jbcrypt.BCrypt;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField fullnameTxt;

    @FXML
    private RadioButton gender1;

    @FXML
    private RadioButton gender2;

    @FXML
    private RadioButton gender3;

    @FXML
    private TextField identityTxt;

    @FXML
    private PasswordField password1Txt;
    @FXML
    private PasswordField password2Txt;

    @FXML
    private TextField usernameTxt;
    @FXML
    private Label usernameLabel, emailLabel, password1Label, password2Label, fullnameLabel, genderLabel, identityLabel;
    @FXML
    private Button signUpBtn;

    Alert alert;


    public boolean checkAllTextField() {
        boolean result = false;

        BooleanBinding usernameBinding = Bindings.createBooleanBinding(() -> {
            return usernameTxt.getText().matches("[a-z0-9_-]{6,12}$");
        }, usernameTxt.textProperty());

        BooleanBinding emailBinding = Bindings.createBooleanBinding(() -> {
            return emailTxt.getText().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
        }, emailTxt.textProperty());

        BooleanBinding password1Binding = Bindings.createBooleanBinding(() -> {
            return password1Txt.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$");
        }, password1Txt.textProperty());

        BooleanBinding password2Binding = Bindings.createBooleanBinding(() -> {
            return password2Txt.getText().equals(password1Txt.getText());
        }, password2Txt.textProperty());

        BooleanBinding fullnameBinding = Bindings.createBooleanBinding(() -> {
            return fullnameTxt.getText().matches("^[a-z ]{2,29}$");
        }, fullnameTxt.textProperty());


        BooleanBinding identityBinding = Bindings.createBooleanBinding(() -> {
            return identityTxt.getText().matches("^\\d{10}$");
        }, identityTxt.textProperty());


        usernameTxt.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue) {
                usernameLabel.visibleProperty().bind(usernameBinding.not());
            }
        }));
        emailTxt.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue) {
                emailLabel.visibleProperty().bind(emailBinding.not());
            }
        }));

        password1Txt.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue) {
                password1Label.visibleProperty().bind(password1Binding.not());
            }
        }));
        password2Txt.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue) {
                password2Label.visibleProperty().bind(password2Binding.not());
            }
        }));
        fullnameTxt.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue) {
                fullnameLabel.visibleProperty().bind(fullnameBinding.not());
            }
        }));

        identityTxt.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue) {
                identityLabel.visibleProperty().bind(identityBinding.not());
            }
        }));


        if (!usernameBinding.getValue() || !emailBinding.getValue() || !password1Binding.getValue()
                || !password2Binding.getValue() || !identityBinding.getValue()
                || !fullnameBinding.getValue() ) {
            if (!usernameBinding.getValue()) {
                usernameTxt.requestFocus();
            } else if (!emailBinding.getValue()) {
                emailTxt.requestFocus();
            } else if (!password1Binding.getValue()) {
                password1Txt.requestFocus();
            } else if (!password2Binding.getValue()) {
                password2Txt.requestFocus();
            } else if (!identityBinding.getValue()) {
                identityTxt.requestFocus();
            } else if (!fullnameBinding.getValue()) {
                fullnameTxt.requestFocus();
            }
        } else {
            result = true;
        }

        return result;
    }

    @FXML
    void onSignUp(ActionEvent event) {
        if (checkAllTextField()) {
            UserEntity user = new UserEntity();
            user.setAddress("");
            user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
            user.setEmail(emailTxt.getText());
            user.setFullName(fullnameTxt.getText());
            user.setGender(getGender());
            user.setIdentity(identityTxt.getText());
            user.setPassword(getPassword());
            user.setUsername(usernameTxt.getText());
            user.setUserRole(getUserRole());
            user.setUserStatus(getUserStatus());

            UserService userService = new UserServiceImp();
            boolean isCreated = userService.insertUser(user);
            if (isCreated) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText("Successfully added ");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information");
                alert.setHeaderText("'" + user.getUsername() + "' already exists  ");
                alert.showAndWait();

                usernameTxt.requestFocus();
            }
        } else {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Please fill all fields");
            alert.showAndWait();


        }

    }

    public UserStatusEntity getUserStatus() {
        UserStatusRepository userStatusRepository = new UserStatusRepositoryImp();
        // default userStatus = active
        return userStatusRepository.findUserStatusById(2);
    }

    public UserRoleEntity getUserRole() {
        UserRoleRepository userRoleRepository = new UserRoleRepositoryImp();
        // set default userRole = receptionist
        return userRoleRepository.findUserRoleById(1);
    }

    public String getPassword() {
        String pass1 = BCrypt.hashpw(password1Txt.getText(), BCrypt.gensalt());
        if (BCrypt.checkpw(password2Txt.getText(), pass1)) return pass1;
        return null;
    }

    @FXML
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
        checkAllTextField();
    }
}