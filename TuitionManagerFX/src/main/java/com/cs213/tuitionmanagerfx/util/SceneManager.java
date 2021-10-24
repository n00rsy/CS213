package com.cs213.tuitionmanagerfx.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class SceneManager {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    private static FXMLLoader mainLoader;
    private static Scene mainScene;


    public static FXMLLoader getMainLoader() {
        return mainLoader;
    }

    public static void setMainLoader(FXMLLoader mainLoader) {
        SceneManager.mainLoader = mainLoader;
    }

    public static Scene getMainScene() {
        return mainScene;
    }

    public static void setMainScene(Scene mainScene) {
        SceneManager.mainScene = mainScene;
    }

    public static void switchScene(String path, Class controller, Stage stage, TextArea error) {
        try {
            FXMLLoader loader = new FXMLLoader(controller.getResource(path));
            Scene scene = new Scene(loader.load(), WIDTH, HEIGHT);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            error.setText(e.toString());
        }
    }
}
