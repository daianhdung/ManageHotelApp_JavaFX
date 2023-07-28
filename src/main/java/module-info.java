module com.managehotelapp_javafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.sql;
    requires java.naming;
    requires jbcrypt;
    requires de.jensd.fx.glyphs.fontawesome;

    opens com.managehotelapp_javafx to javafx.fxml;
    exports com.managehotelapp_javafx;

    exports com.managehotelapp_javafx.controller;
    opens com.managehotelapp_javafx.controller to javafx.fxml;

    exports com.managehotelapp_javafx.entity;
    opens com.managehotelapp_javafx.entity to org.hibernate.orm.core;

    exports com.managehotelapp_javafx.entity.id;
    opens com.managehotelapp_javafx.entity.id to org.hibernate.orm.core;

    exports com.managehotelapp_javafx.dto;
    opens com.managehotelapp_javafx.dto to javafx.fxml;

    exports com.managehotelapp_javafx.controller.booking;
    opens com.managehotelapp_javafx.controller.booking to javafx.fxml;
    exports com.managehotelapp_javafx.controller.checkout;
    opens com.managehotelapp_javafx.controller.checkout to javafx.fxml;

}