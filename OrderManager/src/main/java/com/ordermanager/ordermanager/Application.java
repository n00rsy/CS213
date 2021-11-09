package com.ordermanager.ordermanager;

import com.ordermanager.ordermanager.util.Configuration;
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
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Configuration.WINDOW_WIDTH, Configuration.WINDOW_HEIGHT);
        stage.setTitle(Configuration.WINDOW_TITLE);
        stage.setScene(scene);
        stage.show();
    }
}