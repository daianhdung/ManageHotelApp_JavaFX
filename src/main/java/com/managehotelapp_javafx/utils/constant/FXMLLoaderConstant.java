package com.managehotelapp_javafx.utils.constant;

import com.managehotelapp_javafx.HelloApplication;
import javafx.fxml.FXMLLoader;

public class FXMLLoaderConstant {

    private static final FXMLLoader homeScene = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));

    private static final FXMLLoader bookingScene = new FXMLLoader(HelloApplication.class.getResource("booking/booking-view.fxml"));

    public static FXMLLoader getHomeScene(){
        return homeScene;
    }

    public static FXMLLoader getBookingScene(){
        return bookingScene;
    }
}
