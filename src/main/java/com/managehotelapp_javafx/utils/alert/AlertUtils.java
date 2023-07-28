package com.managehotelapp_javafx.utils.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertUtils {

    public void alert(Alert.AlertType alertType, String title, String HeaderText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(HeaderText);
        alert.showAndWait();
    }
}
