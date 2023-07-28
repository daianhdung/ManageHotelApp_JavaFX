package com.managehotelapp_javafx.utils.constant;

import com.managehotelapp_javafx.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class FXMLLoaderConstant {

    public static FXMLLoader getCusScene(Tab tab) {
        FXMLLoader loader = new FXMLLoader();
        try {
            AnchorPane anchorPane1 = loader.load(HelloApplication.class.getResource("data/customer-table-view.fxml"));
            tab.setContent(anchorPane1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return loader;
    }

    public static FXMLLoader getUserScene(Tab tab) {
        FXMLLoader loader = new FXMLLoader();

        try {
            AnchorPane anchorPane2 = loader.load(HelloApplication.class.getResource("data/user-table-view.fxml"));
            tab.setContent(anchorPane2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return loader;
    }
    public static FXMLLoader getDataScene(){
        return new FXMLLoader(HelloApplication.class.getResource("data-view.fxml"));}
    public static FXMLLoader getHomeScene(){
        return new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));}

    public static FXMLLoader getBookingScene(){
        return new FXMLLoader(HelloApplication.class.getResource("booking/booking-view.fxml"));}

    public static FXMLLoader getLoginScene(){
        return new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
    }

    public static FXMLLoader getRoomScene() {
        return new FXMLLoader(HelloApplication.class.getResource("room-view.fxml"));
    }

    public static FXMLLoader getRoomDetailScene() {
        return new FXMLLoader(HelloApplication.class.getResource("roomdetail-view.fxml"));
    }

    public static FXMLLoader getUserScene(){
        return new FXMLLoader(HelloApplication.class.getResource("user-table-view.fxml"));
    }


    public static FXMLLoader getAddUserScene() {
        return new FXMLLoader(HelloApplication.class.getResource("create-account-view.fxml"));
    }

    public static FXMLLoader getBookingDetailScene(){
        return new FXMLLoader(HelloApplication.class.getResource("booking/booking-detail-view.fxml"));
    }

    
    public static FXMLLoader getInvoiceScene(){
        return new FXMLLoader(HelloApplication.class.getResource("invoice-view.fxml"));
    }

    public static FXMLLoader getBookingTabScene(Tab tab, String fxmlPath){
        FXMLLoader loader = new FXMLLoader();

        try {
            AnchorPane anchorPane = FXMLLoader.load(HelloApplication.class.getResource(fxmlPath));
            tab.setContent(anchorPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return loader;
    }

    public static FXMLLoader getServiceScene(){
        return new FXMLLoader(HelloApplication.class.getResource("service-view.fxml"));
    }

    public static FXMLLoader getCheckInScene(){
        return new FXMLLoader(HelloApplication.class.getResource("room-checkIn-view.fxml"));
    }

}
