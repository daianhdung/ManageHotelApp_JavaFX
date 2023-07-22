package com.managehotelapp_javafx.controller;

import com.managehotelapp_javafx.utils.constant.FXMLLoaderConstant;
import com.managehotelapp_javafx.utils.session.SessionUser;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SidebarController implements Initializable {

    @FXML
    private Button btnBooking;

    @FXML
    private Button btnGuestinfo;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnInvoice;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnRevenue;

    @FXML
    private Button btnRoom;

    @FXML
    private Pane paneMaintbns;

    private Stage primaryStage;
    private FXMLLoader fxmlLoader = FXMLLoaderConstant.getRoomScene();
    @FXML
    private boolean isManager;

    @FXML
    private Button btnAddUser;
    @FXML
    private Button btn_room;
    @FXML
    private Button btn_invoice;

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
        primaryStage = (Stage) btnHome.getScene().getWindow();
        fxmlLoader = FXMLLoaderConstant.getInvoiceScene();
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void onGuestInfoScene() {
        primaryStage = (Stage) btnHome.getScene().getWindow();
        fxmlLoader = FXMLLoaderConstant.getGuestInfoScene();
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @FXML
    private void OnRoom() {
    	 primaryStage = (Stage) btnHome.getScene().getWindow();
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
    private void onRoomScene() {
        primaryStage = (Stage) btnRoom.getScene().getWindow();
        fxmlLoader = FXMLLoaderConstant.getRoomScene();
        try {
            primaryStage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //    @FXML
//    private void setBtnRevenue() {
//        primaryStage = (Stage) btn_home.getScene().getWindow();
//        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
//        try {
//            primaryStage.setScene(new Scene(fxmlLoader.load()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        isManager = SessionUser.getInstance().getRole().equals("Role Manager");
//
//
//        btnRevenue.setVisible(true);

        String buttonHoverStyle = " ;-fx-background-color: #33B5E5; -fx-text-fill: white; -fx-border-color: white;";
        for (Node node : paneMaintbns.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                String currentStyle = button.getStyle();
                button.setOnMouseEntered(e -> button.setStyle(currentStyle+buttonHoverStyle));
                button.setOnMouseExited(e -> button.setStyle(currentStyle));
            }
        }


//      btnRevenue.setVisible(isManager);
//        btnUser.setVisible(isManager);


    }
}
