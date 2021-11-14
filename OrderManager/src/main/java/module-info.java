module com.ordermanager.ordermanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires junit;


    opens com.ordermanager.ordermanager to javafx.fxml;
    exports com.ordermanager.ordermanager;
    exports com.ordermanager.ordermanager.controller;
    opens com.ordermanager.ordermanager.controller to javafx.fxml;
}