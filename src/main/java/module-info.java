module com.managehotelapp_javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.managehotelapp_javafx to javafx.fxml;
    exports com.managehotelapp_javafx;
}