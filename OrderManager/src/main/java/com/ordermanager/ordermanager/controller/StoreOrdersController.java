package com.ordermanager.ordermanager.controller;

import com.ordermanager.ordermanager.model.Order;
import com.ordermanager.ordermanager.model.StoreOrders;
import com.ordermanager.ordermanager.util.Configuration;
import com.ordermanager.ordermanager.util.SceneManager;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Controller class for store-orders-view.fxml
 *
 * @author Noor, Umar
 */
public class StoreOrdersController {

    @FXML
    TableView orderTableView;
    @FXML
    TableColumn phoneNumber;
    @FXML
    TableColumn details;
    @FXML
    TableColumn totalPrice;
    private StoreOrders storeOrders;
    private Order selectedOrder;

    /**
     * Initializes the scene.
     * Called automatically by JavaFX.
     */
    public void initialize() {
        totalPrice.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>) p ->
                new ReadOnlyStringWrapper("$" + String.format(Configuration.PRICE_FORMAT, p.getValue().price() + p.getValue().tax()))
        );

        phoneNumber.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>) p ->
                new ReadOnlyStringWrapper(p.getValue().getPhoneNumber())
        );

        details.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>) p ->
                new ReadOnlyStringWrapper(p.getValue().getPizzasAsString())
        );

        orderTableView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Order>) (observable, oldValue, newValue) -> {
            selectedOrder = newValue;
        });
    }

    /**
     * Initializes the current scene with custom parameters.
     *
     * @param storeOrders StoreOrders object to get a list of orders from
     */
    public void init(StoreOrders storeOrders) {
        this.storeOrders = storeOrders;
        ordersChanged();
    }

    /**
     * Updates the UI when the list of orders is changed
     */
    private void ordersChanged() {
        orderTableView.getItems().clear();
        orderTableView.setItems(FXCollections.observableArrayList(storeOrders.getOrders()));
    }

    /**
     * Handles the remove order button click by removing the selected order from the list
     */
    @FXML
    public void handleRemoveOrderButtonClick() {
        if (selectedOrder == null) {
            SceneManager.showErrorAlert("Please select an order to remove.");
        } else {
            storeOrders.removeOrder(selectedOrder);
            ordersChanged();
        }
    }

    /**
     * Handles the export button click by exporting the current list of orders to a txt file.
     */
    @FXML
    public void handleExportButtonClick() {
        try {
            storeOrders.export(Configuration.EXPORT_PATH);
            SceneManager.showInformationAlert("Exported orders to: " + Configuration.EXPORT_PATH);
        } catch (Exception e) {
            SceneManager.showErrorAlert(e.toString());
        }
    }

    /**
     * Handles the back button click event by returning to the main menu scene.
     *
     * @param event
     */
    @FXML
    public void handleBackButtonClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(SceneManager.getMainScene());
        stage.show();
    }
}
