package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.entity.UserEntity;
import com.managehotelapp_javafx.entity.UserRoleEntity;
import com.managehotelapp_javafx.entity.UserStatusEntity;
import com.managehotelapp_javafx.repository.UserRoleRepository;
import com.managehotelapp_javafx.repository.UserStatusRepository;
import com.managehotelapp_javafx.repository.imp.UserRepositoryImp;
import com.managehotelapp_javafx.repository.imp.UserRoleRepositoryImp;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
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
    private Label usernameLabel, emailLabel, password1Label, password2Label, fullnameLabel, identityLabel;

    @FXML
    private Button signUpBtn;

    Alert alert;
    UserStatusService userStatusService = new UserStatusServiceImp();
    UserRoleService userRoleService = new UserRoleServiceImp();

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
            return fullnameTxt.getText().matches("^[A-Za-z ]{2,30}$");
        }, fullnameTxt.textProperty());


        BooleanBinding identityBinding = Bindings.createBooleanBinding(() -> {
            return identityTxt.getText().matches("^\\d{10}$");
        }, identityTxt.textProperty());


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

        password1Txt.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue) {
                password1Label.setText("- Must be at least 8 characters\n" +
                        "- Must contain at least 1 lowercase/uppercase letter, 1 digit, 1 special character from the set @$!%*?&# ");
                password1Label.setWrapText(true);
                password1Label.visibleProperty().bind(password1Binding.not());
            }
        }));
        password2Txt.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue) {
                password2Label.setText("- Pls re-enter your password");
                password2Label.visibleProperty().bind(password2Binding.not());
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
    void onSignUp() {
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
            user.setUserRole(userRoleService.findUserRoleById(2));
            user.setUserStatus(userStatusService.findUserStatusById(2));

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
        signUpBtn.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) onSignUp();
        });
    }

    @FXML
    private Button btnBack;
    private Stage primaryStage;
    private FXMLLoader fxmlLoader;
    @FXML
    private void onBackHome() {
        primaryStage = (Stage) btnBack.getScene().getWindow();
        fxmlLoader = FXMLLoaderConstant.getHomeScene();
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}