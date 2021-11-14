package com.ordermanager.ordermanager;

import com.ordermanager.ordermanager.util.Configuration;
import com.ordermanager.ordermanager.util.SceneManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A driver class for the application.
 *
 * @author Noor, Umar
 */
public class Application extends javafx.application.Application {
    /**
     * The entry point for the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Sets up initial scene in the JavaFX app.
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.setMainLoader(new FXMLLoader(Application.class.getResource("main-menu-view.fxml")));
        SceneManager.setMainScene(new Scene(SceneManager.getMainLoader().load(), Configuration.WINDOW_WIDTH, Configuration.WINDOW_HEIGHT));
        stage.setTitle(Configuration.WINDOW_TITLE);
        stage.setScene(SceneManager.getMainScene());
        stage.show();
    }
}