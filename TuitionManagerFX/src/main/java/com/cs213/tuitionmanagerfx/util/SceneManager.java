package com.cs213.tuitionmanagerfx.util;

import com.cs213.tuitionmanagerfx.controller.AddStudentController;
import com.cs213.tuitionmanagerfx.controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class SceneManager {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    public static Object switchScene(String path, Class controller, Stage stage, TextArea error) {
        try {
            FXMLLoader loader = new FXMLLoader(controller.getResource(path));
            Parent root = loader.load();
            Scene scene = new Scene(root, WIDTH, HEIGHT);
            stage.setScene(scene);
            stage.show();
            return loader.getController();
        }
        catch (Exception e) {
            error.setText(e.toString());
            return null;
        }
    }

}
