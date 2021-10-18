module com.cs213.tuitionmanagerfx.fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires junit;


    opens com.cs213.tuitionmanagerfx to javafx.fxml;
    exports com.cs213.tuitionmanagerfx;
    exports com.cs213.tuitionmanagerfx.controller;
    opens com.cs213.tuitionmanagerfx.controller to javafx.fxml;
}