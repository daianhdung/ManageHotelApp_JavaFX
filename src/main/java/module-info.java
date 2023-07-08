module com.managehotelapp_javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.hibernate.orm.core;

    opens com.managehotelapp_javafx to javafx.fxml;
    exports com.managehotelapp_javafx;
    exports com.managehotelapp_javafx.controller;
    opens com.managehotelapp_javafx.controller to javafx.fxml;
}