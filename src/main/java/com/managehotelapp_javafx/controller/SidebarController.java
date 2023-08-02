package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.utils.constant.FXMLLoaderConstant;
import com.managehotelapp_javafx.utils.session.SessionUser;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SidebarController implements Initializable {

    @FXML
    private Button btnBooking ,btnHome ,btnInvoice ,btnLogout ,btnRevenue, btnRoom;
    @FXML
    private Label nameLabel, roleLabel;

    private Stage primaryStage;
    private FXMLLoader fxmlLoader = FXMLLoaderConstant.getRoomScene();
    @FXML
    private boolean isManager;
    @FXML
    private Button btnData;

    @FXML
    private void onBooking(Event event) {
        primaryStage = (Stage) btnBooking.getScene().getWindow();
        fxmlLoader = FXMLLoaderConstant.getBookingScene();
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void setBtnLogout() {
        primaryStage = (Stage) btnLogout.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Logout");

        if(alert.showAndWait().get() == ButtonType.OK){
            try {
                fxmlLoader = FXMLLoaderConstant.getLoginScene();
                SessionUser.removeSession();
                primaryStage.setScene(new Scene(fxmlLoader.load()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void onHomeScene() {
        primaryStage = (Stage) btnHome.getScene().getWindow();
        fxmlLoader = FXMLLoaderConstant.getHomeScene();
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @FXML
    private void onInvoiceScene() {
        primaryStage = (Stage) btnInvoice.getScene().getWindow();
        fxmlLoader = FXMLLoaderConstant.getInvoiceScene();
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @FXML
    private void OnRoom() {
    	 primaryStage = (Stage) btnRoom.getScene().getWindow();
         fxmlLoader = FXMLLoaderConstant.getRoomScene();
         try {
             primaryStage.setScene(new Scene(fxmlLoader.load()));
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
    }

    @FXML
    private void onDataScene() {
        primaryStage = (Stage) btnData.getScene().getWindow();
        fxmlLoader = FXMLLoaderConstant.getDataScene();
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



        @FXML
    private void onRevenue() {
        primaryStage = (Stage) btnRevenue.getScene().getWindow();
        fxmlLoader = FXMLLoaderConstant.getRevenueScene();
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private ImageView revenueIcon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isManager = SessionUser.getInstance().getRole().equals("Role Manager");
        roleLabel.setText(isManager ? "Manager" : "Receptionist");
        nameLabel.setText(SessionUser.getInstance().getFullName());
        btnRevenue.setVisible(isManager);
        revenueIcon.setVisible(isManager);

//        String buttonHoverStyle = " ;-fx-background-color: #33B5E5; -fx-text-fill: white; -fx-border-color: white;";
//        for (Node node : paneMaintbns.getChildren()) {
//            if (node instanceof Button) {
//                Button button = (Button) node;
//                String currentStyle = button.getStyle();
//                button.setOnMouseEntered(e -> button.setStyle(currentStyle+buttonHoverStyle));
//                button.setOnMouseExited(e -> button.setStyle(currentStyle));
//            }
//        }
    }
}
