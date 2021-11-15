package com.ordermanager.ordermanager.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

/**
 * A helper class for use in scene management.
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
     * Shows an error alert with the message passed in
     *
     * @param msg The message to display
     */
    public static void showErrorAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * Shows an information alert with the message passed in
     *
     * @param msg The message to display
     */
    public static void showInformationAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}