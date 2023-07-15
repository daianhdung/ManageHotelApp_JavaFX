package com.managehotelapp_javafx.utils.constant;

import com.managehotelapp_javafx.HelloApplication;
import javafx.fxml.FXMLLoader;

public class FXMLLoaderConstant {

    public static FXMLLoader getHomeScene(){
        return new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));}

    public static FXMLLoader getBookingScene(){
        return new FXMLLoader(HelloApplication.class.getResource("booking/booking-view.fxml"));}

    public static FXMLLoader getLoginScene(){
        return new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));

    }
}
