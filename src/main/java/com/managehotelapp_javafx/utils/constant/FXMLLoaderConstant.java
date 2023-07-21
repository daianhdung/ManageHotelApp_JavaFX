package com.managehotelapp_javafx.utils.constant;

import com.managehotelapp_javafx.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

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

    public static FXMLLoader getUserScene(){
        return new FXMLLoader(HelloApplication.class.getResource("user-table-view.fxml"));
    }


    public static FXMLLoader getAddUserScene() {
        return new FXMLLoader(HelloApplication.class.getResource("create-account-view.fxml"));
    }

    public static FXMLLoader getBookingDetailScene(){
        return new FXMLLoader(HelloApplication.class.getResource("booking-detail-view.fxml"));
    }
}
