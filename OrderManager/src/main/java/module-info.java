module com.ordermanager.ordermanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.ordermanager.ordermanager to javafx.fxml;
    exports com.ordermanager.ordermanager;
}