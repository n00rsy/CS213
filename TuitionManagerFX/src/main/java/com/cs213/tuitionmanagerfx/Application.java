package com.cs213.tuitionmanagerfx;

import com.cs213.tuitionmanagerfx.util.SceneManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.setMainLoader(new FXMLLoader(Application.class.getResource("main-view.fxml")));
        SceneManager.setMainScene(new Scene(SceneManager.getMainLoader().load(), SceneManager.WIDTH, SceneManager.HEIGHT));
        stage.setTitle("Tuition Manager");
        stage.setScene(SceneManager.getMainScene());
        stage.show();
    }
}