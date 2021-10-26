package com.cs213.tuitionmanagerfx.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * A helper class for use in switching between scenes.
 *
 * @author Noor, Umar
 */
public class SceneManager {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    private static FXMLLoader mainLoader;
    private static Scene mainScene;

    /**
     * Main loader accessor method
     *
     * @return FXMLLoader mainLoader
     */
    public static FXMLLoader getMainLoader() {
        return mainLoader;
    }

    /**
     * Main loader mutator method
     *
     * @param mainLoader the new mainLoader
     */
    public static void setMainLoader(FXMLLoader mainLoader) {
        SceneManager.mainLoader = mainLoader;
    }

    /**
     * Main scene accessor method
     *
     * @return Scene mainScene
     */
    public static Scene getMainScene() {
        return mainScene;
    }

    /**
     * Main scene mutator method
     *
     * @param mainScene the new MainScene
     */
    public static void setMainScene(Scene mainScene) {
        SceneManager.mainScene = mainScene;
    }

    /**
     * Switch from the current scene to the new one passed in using new FXMLLoader
     *
     * @param path       the path to the new scene .fxml file
     * @param controller the current controller
     * @param stage      the current stage
     * @param error      the area to output error messages for the user.
     */
    public static void switchScene(String path, Class controller, Stage stage, TextArea error) {
        try {
            FXMLLoader loader = new FXMLLoader(controller.getResource(path));
            Scene scene = new Scene(loader.load(), WIDTH, HEIGHT);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            error.setText(e.getLocalizedMessage());
        }
    }
}
