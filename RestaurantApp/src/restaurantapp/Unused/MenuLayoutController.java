package restaurantapp;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MenuLayoutController implements Initializable {

    private String fullOrder = "";
    FileWriter outputStream = null;

    @FXML TableView<MenuItems> menuTable = new TableView<MenuItems>();
    @FXML TableColumn<MenuItems, String> itemNameCol;
    @FXML TableColumn<MenuItems, String> descCol;
    @FXML TableColumn<MenuItems, String> priceCol;
    @FXML TextArea testOrder;
    
    private final ObservableList<MenuItems> starterItems = FXCollections.observableArrayList(
            new MenuItems("Starter 1", "This is Starter 1", "1.99"),
            new MenuItems("Starter 2", "This is Starter 2", "2.99"),
            new MenuItems("Starter 3", "This is Starter 3", "3.99"));
    private final ObservableList<MenuItems> mainCourseItems = FXCollections.observableArrayList(
            new MenuItems("Main 1", "This is Main Course 1", "1.99"),
            new MenuItems("Main 2", "This is Main Course 2", "2.99"),
            new MenuItems("Main 3", "This is Main Course 3", "3.99"));
    private final ObservableList<MenuItems> drinkItems = FXCollections.observableArrayList(
            new MenuItems("Drink 1", "This is Drink 1", "1.99"),
            new MenuItems("Drink 2", "This is Drink 2", "2.99"),
            new MenuItems("Drink 3", "This is Drink 3", "3.99"));
    private final ObservableList<MenuItems> dessertItems = FXCollections.observableArrayList(
            new MenuItems("Dessert 1", "This is Dessert 1", "1.99"),
            new MenuItems("Dessert 2", "This is Dessert 2", "2.99"),
            new MenuItems("Dessert 3", "This is Dessert 3", "3.99"));
    
    @FXML private void showStarters(ActionEvent e) throws IOException {
        itemNameCol.setCellValueFactory(new PropertyValueFactory("itemName"));
        descCol.setCellValueFactory(new PropertyValueFactory("itemDesc"));
        priceCol.setCellValueFactory(new PropertyValueFactory("itemPrice"));
        menuTable.setItems(starterItems);
        menuTable.getColumns().setAll(itemNameCol, descCol, priceCol);
    }
    
    @FXML private void showMains(ActionEvent e) throws IOException {
        itemNameCol.setCellValueFactory(new PropertyValueFactory("itemName"));
        descCol.setCellValueFactory(new PropertyValueFactory("itemDesc"));
        priceCol.setCellValueFactory(new PropertyValueFactory("itemPrice"));
        menuTable.setItems(mainCourseItems);
        menuTable.getColumns().setAll(itemNameCol, descCol, priceCol);
    }
    
    @FXML private void showDrinks(ActionEvent e) throws IOException {
        itemNameCol.setCellValueFactory(new PropertyValueFactory("itemName"));
        descCol.setCellValueFactory(new PropertyValueFactory("itemDesc"));
        priceCol.setCellValueFactory(new PropertyValueFactory("itemPrice"));
        menuTable.setItems(drinkItems);
        menuTable.getColumns().setAll(itemNameCol, descCol, priceCol);
    }
    
    @FXML private void showDesserts(ActionEvent e) throws IOException {
        itemNameCol.setCellValueFactory(new PropertyValueFactory("itemName"));
        descCol.setCellValueFactory(new PropertyValueFactory("itemDesc"));
        priceCol.setCellValueFactory(new PropertyValueFactory("itemPrice"));
        menuTable.setItems(dessertItems);
        menuTable.getColumns().setAll(itemNameCol, descCol, priceCol);
    }

    @FXML private void selectItem(ActionEvent e) throws IOException {
        MenuItems test = menuTable.getSelectionModel().getSelectedItem();
        //testOrder.setText(test.getItemName() + " - " + test.getItemPrice());
        fullOrder = fullOrder + " " + test.getItemName() + " - " + test.getItemPrice();
    }

    @FXML private void returnItem(ActionEvent e) throws IOException {
        Parent tableLayoutView = FXMLLoader.load(getClass().getResource("TableLayout.fxml"));
        Scene tableLayoutScene = new Scene(tableLayoutView);

        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(tableLayoutScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public static class MenuItems {
        private final SimpleStringProperty itemName;
        private final SimpleStringProperty itemDesc;
        private final SimpleStringProperty itemPrice;
    
        private MenuItems(String iName, String iDesc, String iPrice) {
            this.itemName = new SimpleStringProperty(iName);
            this.itemDesc = new SimpleStringProperty(iDesc);
            this.itemPrice = new SimpleStringProperty(iPrice);
        }
    
        public void setItemName(String name) {
            itemName.set(name);
        }
        public String getItemName() {
            return itemName.get();
        }
        public void setItemDesc(String desc) {
            itemDesc.set(desc);
        }
        public String getItemDesc() {
            return itemDesc.get();
        }
        public void setItemPrice(String price) {
            itemPrice.set(price);
        }
        public String getItemPrice() {
            return itemPrice.get();
        }
    }
}
