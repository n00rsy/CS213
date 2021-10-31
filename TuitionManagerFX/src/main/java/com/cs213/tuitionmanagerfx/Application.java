package com.cs213.tuitionmanagerfx;

import com.cs213.tuitionmanagerfx.util.Constants;
import com.cs213.tuitionmanagerfx.util.SceneManager;
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
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Sets up initial scene in the JavaFX app.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.setMainLoader(new FXMLLoader(Application.class.getResource(Constants.MAIN_VIEW_PATH)));
        SceneManager.setMainScene(new Scene(SceneManager.getMainLoader().load(), SceneManager.WIDTH, SceneManager.HEIGHT));
        stage.setTitle(Constants.STAGE_TITLE);
        stage.setScene(SceneManager.getMainScene());
        stage.show();
    }
}