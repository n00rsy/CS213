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

    public void init(StoreOrders storeOrders) {
        this.storeOrders = storeOrders;
        ordersChanged();
    }

    private void ordersChanged() {
        orderTableView.getItems().clear();
        orderTableView.setItems(FXCollections.observableArrayList(storeOrders.getOrders()));
    }

    @FXML
    public void handleRemoveOrderButtonClick() {
        if (selectedOrder == null) {
            SceneManager.showErrorAlert("Please select an order to remove.");
        } else {
            storeOrders.removeOrder(selectedOrder);
            ordersChanged();
        }
    }

    @FXML
    public void handleBackButtonClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(SceneManager.getMainScene());
        stage.show();
    }

    @FXML
    public void handleExportButtonClick() {
        try {
            storeOrders.export(Configuration.EXPORT_PATH);
            SceneManager.showInformationAlert("Exported orders to: " + Configuration.EXPORT_PATH);
        } catch (Exception e) {
            SceneManager.showErrorAlert(e.toString());
        }
    }
}
