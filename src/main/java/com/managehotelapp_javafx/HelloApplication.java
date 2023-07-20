package com.managehotelapp_javafx;

import com.managehotelapp_javafx.config.ConnectDB;

import com.managehotelapp_javafx.dto.CustomerDTO;
import com.managehotelapp_javafx.dto.UserDTO;
import com.managehotelapp_javafx.entity.UserEntity;
import com.managehotelapp_javafx.repository.CustomerTypeRepository;
import com.managehotelapp_javafx.repository.UserRepository;
import com.managehotelapp_javafx.repository.UserRoleRepository;
import com.managehotelapp_javafx.repository.UserStatusRepository;
import com.managehotelapp_javafx.repository.imp.CustomerTypeRepositoryImp;
import com.managehotelapp_javafx.repository.imp.UserRepositoryImp;
import com.managehotelapp_javafx.repository.imp.UserRoleRepositoryImp;
import com.managehotelapp_javafx.repository.imp.UserStatusRepositoryImp;
import com.managehotelapp_javafx.services.CustomerService;
import com.managehotelapp_javafx.services.CustomerTypeService;
import com.managehotelapp_javafx.services.UserRoleService;
import com.managehotelapp_javafx.services.UserService;
import com.managehotelapp_javafx.services.imp.CustomerServiceImp;
import com.managehotelapp_javafx.services.imp.CustomerTypeServiceImp;
import com.managehotelapp_javafx.services.imp.UserRoleServiceImp;
import com.managehotelapp_javafx.services.imp.UserServiceImp;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        ConnectDB.getSessionFactory().createEntityManager();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("customer-table-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.setOnCloseRequest(event -> {
            event.consume();
            stopProgram(stage);
        });
        stage.show();
    }

    public void stopProgram(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Xác nhận thoát chương trình");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}