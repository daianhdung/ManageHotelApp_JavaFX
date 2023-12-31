package com.managehotelapp_javafx;

import com.managehotelapp_javafx.config.ConnectDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        ConnectDB.getSessionFactory().createEntityManager();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image image = new Image(Objects.requireNonNull(getClass().getResource("/asset/image/logo_hotel_stage.png")).toExternalForm());

        stage.setTitle("Hotel Management System");
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.setOnCloseRequest(event -> {
            event.consume();
            stopProgram(stage);
        });

        stage.show();
    }

    public void stopProgram(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận");
        alert.setHeaderText("Xác nhận thoát chương trình");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }

    public static void main(String[] args) throws IOException {
        launch();
    }
}