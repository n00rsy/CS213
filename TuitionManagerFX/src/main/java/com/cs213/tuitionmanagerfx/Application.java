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
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SceneManager.WIDTH, SceneManager.HEIGHT);
        stage.setTitle("Tuition Manager");
        stage.setScene(scene);
        stage.show();
    }
}