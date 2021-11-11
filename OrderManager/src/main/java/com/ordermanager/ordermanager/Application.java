package com.ordermanager.ordermanager;

import com.ordermanager.ordermanager.util.Configuration;
import com.ordermanager.ordermanager.util.SceneManager;
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
        SceneManager.setMainLoader(new FXMLLoader(Application.class.getResource("main-menu-view.fxml")));
        SceneManager.setMainScene(new Scene(SceneManager.getMainLoader().load(), Configuration.WINDOW_WIDTH, Configuration.WINDOW_HEIGHT));
        stage.setTitle(Configuration.WINDOW_TITLE);
        stage.setScene(SceneManager.getMainScene());
        stage.show();
    }
}