package restaurantapp;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.*;
import javafx.stage.Stage;

import javax.swing.*;

public class TableLayoutController implements Initializable {

    @FXML Label userdisplay;
    @FXML Menu statReports;
    @FXML MenuItem viewReports;
    @FXML AnchorPane menuScreenPane;
    @FXML TabPane tableOnePane, tableTwoPane, tableThreePane, tableFourPane;
    @FXML Circle tableOneStatus, tableTwoStatus, tableThreeStatus, tableFourStatus;
    @FXML Button starterMenu, mainCourseMenu, drinkMenu, dessertMenu;
    @FXML Button tableOneSeatOne, tableOneSeatTwo, tableOneSeatThree, tableOneSeatFour;
    @FXML Button tableTwoSeatOne, tableTwoSeatTwo, tableTwoSeatThree, tableTwoSeatFour;
    @FXML Button tableThreeSeatOne, tableThreeSeatTwo, tableThreeSeatThree, tableThreeSeatFour;
    @FXML Button tableFourSeatOne, tableFourSeatTwo, tableFourSeatThree, tableFourSeatFour;
    @FXML TextArea tableOneSeatOneOrder, tableOneSeatTwoOrder, tableOneSeatThreeOrder, tableOneSeatFourOrder;
    @FXML TextArea tableTwoSeatOneOrder, tableTwoSeatTwoOrder, tableTwoSeatThreeOrder, tableTwoSeatFourOrder;
    @FXML TextArea tableThreeSeatOneOrder, tableThreeSeatTwoOrder, tableThreeSeatThreeOrder, tableThreeSeatFourOrder;
    @FXML TextArea tableFourSeatOneOrder, tableFourSeatTwoOrder, tableFourSeatThreeOrder, tableFourSeatFourOrder;
    @FXML TableView<MenuItems> menuTable = new TableView<MenuItems>();
    @FXML TableColumn<MenuItems, String> itemNameCol, descCol, priceCol;

    @FXML TextArea currentOrderText;
    @FXML Button tableOnePreviewButton, tableOneSubmitButton, tableTwoPreviewButton, tableTwoSubmitButton, tableThreePreviewButton, tableThreeSubmitButton, tableFourPreviewButton, tableFourSubmitButton;
    @FXML TextArea tableOnePreviewOrder, tableTwoPreviewOrder, tableThreePreviewOrder, tableFourPreviewOrder;
    @FXML Label tableOneSubmitLabel, tableTwoSubmitLabel, tableThreeSubmitLabel, tableFourSubmitLabel;
    @FXML Tab tableOnePaymentTab, tableTwoPaymentTab, tableThreePaymentTab, tableFourPaymentTab;

    @FXML AnchorPane tableOneCashPane, tableOneCardPane, tableTwoCashPane, tableTwoCardPane, tableThreeCashPane, tableThreeCardPane, tableFourCashPane, tableFourCardPane;
    @FXML Button tableOneCashButton, tableOneCardButton, tableOneCalcButton, tableOneFinaliseButton;
    @FXML Button tableTwoCashButton, tableTwoCardButton, tableTwoCalcButton, tableTwoFinaliseButton;
    @FXML Button tableThreeCashButton, tableThreeCardButton, tableThreeCalcButton, tableThreeFinaliseButton;
    @FXML Button tableFourCashButton, tableFourCardButton, tableFourCalcButton, tableFourFinaliseButton;
    @FXML TextArea tableOnePeoplePaying, tableOneCostPer, tableOnePIN;
    @FXML TextArea tableTwoPeoplePaying, tableTwoCostPer, tableTwoPIN;
    @FXML TextArea tableThreePeoplePaying, tableThreeCostPer, tableThreePIN;
    @FXML TextArea tableFourPeoplePaying, tableFourCostPer, tableFourPIN;
    @FXML TextField tableOneTotalPrice, tableTwoTotalPrice, tableThreeTotalPrice, tableFourTotalPrice;
    @FXML CheckBox tableOneStudDisc, tableTwoStudDisc, tableThreeStudDisc, tableFourStudDisc;

    @FXML TextArea reportViewBox;
    @FXML AnchorPane statPane;


    private final ObservableList<MenuItems> starterItems = FXCollections.observableArrayList(
            new MenuItems("Chicken Wings", "4 Chicken Wings glazed in peri-peri sauce.", 2.99),
            new MenuItems("Spicy Samosas", "3 Spicy and crunchy Samosas.", 1.99),
            new MenuItems("Lentil Soup", "Delicious bowl of Lentil Soup.", 3.99),
            new MenuItems("Chicken Salad", "Light Salad with various vegetables and Chicken.", 2.49),
            new MenuItems("Plain Salad", "Small Plain Salad with crispy lettuce.", 1.49));
    private final ObservableList<MenuItems> mainCourseItems = FXCollections.observableArrayList(
            new MenuItems("Chicken Biryani", "Basmati Rice with Chicken Breasts and seasonings.", 5.49),
            new MenuItems("Steamed Salmon", "Fresh Salmon steamed to perfection", 4.99),
            new MenuItems("Beef Lasagne", "Beef-filled lasagne.", 6.49),
            new MenuItems("Roast Chicken", "A whole chicken roasted just for you.", 7.99),
            new MenuItems("Inegol Kofte", "Best turkish kofte around; just for Mustafa :)", 8.99));
    private final ObservableList<MenuItems> drinkItems = FXCollections.observableArrayList(
            new MenuItems("Orange Juice", "Squeezed Orange with bits removed.", 0.99),
            new MenuItems("Apple Juice", "Pressed Apple with bits removed.", 0.99),
            new MenuItems("Fanta", "It's Fanta.", 1.49),
            new MenuItems("Red Wine", "No idea what this tastes like.", 5.99));
    private final ObservableList<MenuItems> dessertItems = FXCollections.observableArrayList(
            new MenuItems("Profiteroles", "5 dough balls filled with cream, topped with chocolate.", 2.99),
            new MenuItems("Eclair", "1 long dough sausage filled with cream, topped with chocolate.", 1.99),
            new MenuItems("Ice Cream", "Good old Ice Cream.", 1.49));

    private String fullOrder = "";
    private double fullPrice = 0.00;
    private double tableOneFullPrice = 0.00;
    private double tableTwoFullPrice = 0.00;
    private double tableThreeFullPrice = 0.00;
    private double tableFourFullPrice = 0.00;

    private double tableOneSeatOnePrice = 0.00;
    private double tableOneSeatTwoPrice = 0.00;
    private double tableOneSeatThreePrice = 0.00;
    private double tableOneSeatFourPrice = 0.00;
    private double tableTwoSeatOnePrice = 0.00;
    private double tableTwoSeatTwoPrice = 0.00;
    private double tableTwoSeatThreePrice = 0.00;
    private double tableTwoSeatFourPrice = 0.00;
    private double tableThreeSeatOnePrice = 0.00;
    private double tableThreeSeatTwoPrice = 0.00;
    private double tableThreeSeatThreePrice = 0.00;
    private double tableThreeSeatFourPrice = 0.00;
    private double tableFourSeatOnePrice = 0.00;
    private double tableFourSeatTwoPrice = 0.00;
    private double tableFourSeatThreePrice = 0.00;
    private double tableFourSeatFourPrice = 0.00;

    private ActionEvent currentSeat;
    private ArrayList<Invoice> staffReports = new ArrayList<>();

    @FXML private void takeSeatOrder(ActionEvent e) throws IOException {
        currentSeat = e;
        menuScreenPane.setVisible(true);
        if(currentSeat.getSource() == tableOneSeatOne)
            currentOrderText.setText(tableOneSeatOneOrder.getText());
        else if(currentSeat.getSource() == tableOneSeatTwo)
            currentOrderText.setText(tableOneSeatTwoOrder.getText());
        else if(currentSeat.getSource() == tableOneSeatThree)
            currentOrderText.setText(tableOneSeatThreeOrder.getText());
        else if(currentSeat.getSource() == tableOneSeatFour)
            currentOrderText.setText(tableOneSeatFourOrder.getText());
        else if(currentSeat.getSource() == tableTwoSeatOne)
            currentOrderText.setText(tableTwoSeatOneOrder.getText());
        else if(currentSeat.getSource() == tableTwoSeatTwo)
            currentOrderText.setText(tableTwoSeatTwoOrder.getText());
        else if(currentSeat.getSource() == tableTwoSeatThree)
            currentOrderText.setText(tableTwoSeatThreeOrder.getText());
        else if(currentSeat.getSource() == tableTwoSeatFour)
            currentOrderText.setText(tableTwoSeatFourOrder.getText());
        else if(currentSeat.getSource() == tableThreeSeatOne)
            currentOrderText.setText(tableThreeSeatOneOrder.getText());
        else if(currentSeat.getSource() == tableThreeSeatTwo)
            currentOrderText.setText(tableThreeSeatTwoOrder.getText());
        else if(currentSeat.getSource() == tableThreeSeatThree)
            currentOrderText.setText(tableThreeSeatThreeOrder.getText());
        else if(currentSeat.getSource() == tableThreeSeatFour)
            currentOrderText.setText(tableThreeSeatFourOrder.getText());
        else if(currentSeat.getSource() == tableFourSeatOne)
            currentOrderText.setText(tableFourSeatOneOrder.getText());
        else if(currentSeat.getSource() == tableFourSeatTwo)
            currentOrderText.setText(tableFourSeatOneOrder.getText());
        else if(currentSeat.getSource() == tableFourSeatThree)
            currentOrderText.setText(tableFourSeatOneOrder.getText());
        else if(currentSeat.getSource() == tableFourSeatFour)
            currentOrderText.setText(tableFourSeatOneOrder.getText());
    }

    @FXML private void showMenuItems(ActionEvent e) throws IOException {
        itemNameCol.setCellValueFactory(new PropertyValueFactory("itemName"));
        descCol.setCellValueFactory(new PropertyValueFactory("itemDesc"));
        priceCol.setCellValueFactory(new PropertyValueFactory("itemPrice"));
        if(e.getSource() == starterMenu)
            menuTable.setItems(starterItems);
        else if(e.getSource() == mainCourseMenu)
            menuTable.setItems(mainCourseItems);
        else if(e.getSource() == drinkMenu)
            menuTable.setItems(drinkItems);
        else if(e.getSource() == dessertMenu)
            menuTable.setItems(dessertItems);
        else {}
        menuTable.getColumns().setAll(itemNameCol, descCol, priceCol);
    }

    @FXML private void addItemToOrder(ActionEvent e) throws IOException {
        try {
            MenuItems selectedItem = menuTable.getSelectionModel().getSelectedItem();
            fullOrder = fullOrder + " " + selectedItem.getItemName() + " - " + selectedItem.getItemPrice() + "\n";
            fullPrice = fullPrice + selectedItem.getItemPrice();
            if(currentSeat.getSource() == tableOneSeatOne)
                tableOneSeatOnePrice = tableOneSeatOnePrice + selectedItem.getItemPrice();
            else if(currentSeat.getSource() == tableOneSeatTwo)
                tableOneSeatTwoPrice = tableOneSeatTwoPrice + selectedItem.getItemPrice();
            else if(currentSeat.getSource() == tableOneSeatThree)
                tableOneSeatThreePrice = tableOneSeatThreePrice + selectedItem.getItemPrice();
            else if(currentSeat.getSource() == tableOneSeatFour)
                tableOneSeatFourPrice = tableOneSeatFourPrice + selectedItem.getItemPrice();
            else if(currentSeat.getSource() == tableTwoSeatOne)
                tableTwoSeatOnePrice = tableTwoSeatOnePrice + selectedItem.getItemPrice();
            else if(currentSeat.getSource() == tableTwoSeatTwo)
                tableTwoSeatTwoPrice = tableTwoSeatTwoPrice + selectedItem.getItemPrice();
            else if(currentSeat.getSource() == tableTwoSeatThree)
                tableTwoSeatThreePrice = tableTwoSeatThreePrice + selectedItem.getItemPrice();
            else if(currentSeat.getSource() == tableTwoSeatFour)
                tableTwoSeatFourPrice = tableTwoSeatFourPrice + selectedItem.getItemPrice();
            else if(currentSeat.getSource() == tableThreeSeatOne)
                tableThreeSeatOnePrice = tableThreeSeatOnePrice + selectedItem.getItemPrice();
            else if(currentSeat.getSource() == tableThreeSeatTwo)
                tableThreeSeatTwoPrice = tableThreeSeatTwoPrice + selectedItem.getItemPrice();
            else if(currentSeat.getSource() == tableThreeSeatThree)
                tableThreeSeatThreePrice = tableThreeSeatThreePrice + selectedItem.getItemPrice();
            else if(currentSeat.getSource() == tableThreeSeatFour)
                tableThreeSeatFourPrice = tableThreeSeatFourPrice + selectedItem.getItemPrice();
            else if(currentSeat.getSource() == tableFourSeatOne)
                tableFourSeatOnePrice = tableFourSeatOnePrice + selectedItem.getItemPrice();
            else if(currentSeat.getSource() == tableFourSeatTwo)
                tableFourSeatTwoPrice = tableFourSeatTwoPrice + selectedItem.getItemPrice();
            else if(currentSeat.getSource() == tableFourSeatThree)
                tableFourSeatThreePrice = tableFourSeatThreePrice + selectedItem.getItemPrice();
            else if(currentSeat.getSource() == tableFourSeatFour)
                tableFourSeatFourPrice = tableFourSeatFourPrice + selectedItem.getItemPrice();
            currentOrderText.setText(fullOrder);
        } catch (NullPointerException ex) {
            System.out.println("No menu item chosen.");
        }
    }

    @FXML private void finishOrder(ActionEvent e) throws IOException {
        if(fullOrder != "") {
            if (currentSeat.getSource() == tableOneSeatOne) { // table one
                tableOneSeatOneOrder.setText(fullOrder);
                tableOneFullPrice = tableOneFullPrice + fullPrice;
                tableOneStatus.setFill(Color.YELLOW);
            } else if (currentSeat.getSource() == tableOneSeatTwo) {
                tableOneSeatTwoOrder.setText(fullOrder);
                tableOneFullPrice = tableOneFullPrice + fullPrice;
                tableOneStatus.setFill(Color.YELLOW);
            } else if (currentSeat.getSource() == tableOneSeatThree) {
                tableOneSeatThreeOrder.setText(fullOrder);
                tableOneFullPrice = tableOneFullPrice + fullPrice;
                tableOneStatus.setFill(Color.YELLOW);
            } else if (currentSeat.getSource() == tableOneSeatFour) {
                tableOneSeatFourOrder.setText(fullOrder);
                tableOneFullPrice = tableOneFullPrice + fullPrice;
                tableOneStatus.setFill(Color.YELLOW);
            } else if (currentSeat.getSource() == tableTwoSeatOne) { // table two
                tableTwoSeatOneOrder.setText(fullOrder);
                tableTwoFullPrice = tableTwoFullPrice + fullPrice;
                tableTwoStatus.setFill(Color.YELLOW);
            } else if (currentSeat.getSource() == tableTwoSeatTwo) {
                tableTwoSeatTwoOrder.setText(fullOrder);
                tableTwoFullPrice = tableTwoFullPrice + fullPrice;
                tableTwoStatus.setFill(Color.YELLOW);
            } else if (currentSeat.getSource() == tableTwoSeatThree) {
                tableTwoSeatThreeOrder.setText(fullOrder);
                tableTwoFullPrice = tableTwoFullPrice + fullPrice;
                tableTwoStatus.setFill(Color.YELLOW);
            } else if (currentSeat.getSource() == tableTwoSeatFour) {
                tableTwoSeatFourOrder.setText(fullOrder);
                tableTwoFullPrice = tableTwoFullPrice + fullPrice;
                tableTwoStatus.setFill(Color.YELLOW);
            } else if (currentSeat.getSource() == tableThreeSeatOne) { // table three
                tableThreeSeatOneOrder.setText(fullOrder);
                tableThreeFullPrice = tableThreeFullPrice + fullPrice;
                tableThreeStatus.setFill(Color.YELLOW);
            } else if (currentSeat.getSource() == tableThreeSeatTwo) {
                tableThreeSeatTwoOrder.setText(fullOrder);
                tableThreeFullPrice = tableThreeFullPrice + fullPrice;
                tableThreeStatus.setFill(Color.YELLOW);
            } else if (currentSeat.getSource() == tableThreeSeatThree) {
                tableThreeSeatThreeOrder.setText(fullOrder);
                tableThreeFullPrice = tableThreeFullPrice + fullPrice;
                tableThreeStatus.setFill(Color.YELLOW);
            } else if (currentSeat.getSource() == tableThreeSeatFour) {
                tableThreeSeatFourOrder.setText(fullOrder);
                tableThreeFullPrice = tableThreeFullPrice + fullPrice;
                tableThreeStatus.setFill(Color.YELLOW);
            } else if (currentSeat.getSource() == tableFourSeatOne) { // table four
                tableFourSeatOneOrder.setText(fullOrder);
                tableFourFullPrice = tableFourFullPrice + fullPrice;
                tableFourStatus.setFill(Color.YELLOW);
            } else if (currentSeat.getSource() == tableFourSeatTwo) {
                tableFourSeatTwoOrder.setText(fullOrder);
                tableFourFullPrice = tableFourFullPrice + fullPrice;
                tableFourStatus.setFill(Color.YELLOW);
            } else if (currentSeat.getSource() == tableFourSeatThree) {
                tableFourSeatThreeOrder.setText(fullOrder);
                tableFourFullPrice = tableFourFullPrice + fullPrice;
                tableFourStatus.setFill(Color.YELLOW);
            } else if (currentSeat.getSource() == tableFourSeatFour) {
                tableFourSeatFourOrder.setText(fullOrder);
                tableFourFullPrice = tableFourFullPrice + fullPrice;
                tableFourStatus.setFill(Color.YELLOW);
            }

            fullOrder = "";
            fullPrice = 0.00;
        } else { }
        currentOrderText.setText("");
        menuScreenPane.setVisible(false);
    }

    @FXML private void resetOrder(ActionEvent e) throws IOException {
        fullOrder = "";
        fullPrice = 0.00;
        if(currentSeat.getSource() == tableOneSeatOne) {
            tableOneFullPrice = tableOneFullPrice - tableOneSeatOnePrice;
            tableOneSeatOnePrice = 0.00;
            tableOneSeatOneOrder.clear();
        } else if(currentSeat.getSource() == tableOneSeatTwo) {
            tableOneFullPrice = tableOneFullPrice - tableOneSeatTwoPrice;
            tableOneSeatTwoPrice = 0.00;
            tableOneSeatTwoOrder.clear();
        } else if(currentSeat.getSource() == tableOneSeatThree) {
            tableOneFullPrice = tableOneFullPrice - tableOneSeatThreePrice;
            tableOneSeatThreePrice = 0.00;
            tableOneSeatThreeOrder.clear();
        } else if(currentSeat.getSource() == tableOneSeatFour) {
            tableOneFullPrice = tableOneFullPrice - tableOneSeatFourPrice;
            tableOneSeatFourPrice = 0.00;
            tableOneSeatFourOrder.clear();
        } else if(currentSeat.getSource() == tableTwoSeatOne) {
            tableTwoFullPrice = tableTwoFullPrice - tableTwoSeatOnePrice;
            tableTwoSeatOnePrice = 0.00;
            tableTwoSeatOneOrder.clear();
        } else if(currentSeat.getSource() == tableTwoSeatTwo) {
            tableTwoFullPrice = tableTwoFullPrice - tableTwoSeatTwoPrice;
            tableTwoSeatTwoPrice = 0.00;
            tableTwoSeatTwoOrder.clear();
        } else if(currentSeat.getSource() == tableTwoSeatThree) {
            tableTwoFullPrice = tableTwoFullPrice - tableTwoSeatThreePrice;
            tableTwoSeatThreePrice = 0.00;
            tableTwoSeatThreeOrder.clear();
        } else if(currentSeat.getSource() == tableTwoSeatFour) {
            tableTwoFullPrice = tableTwoFullPrice - tableTwoSeatFourPrice;
            tableTwoSeatFourPrice = 0.00;
            tableTwoSeatFourOrder.clear();
        } else if(currentSeat.getSource() == tableThreeSeatOne) {
            tableThreeFullPrice = tableThreeFullPrice - tableThreeSeatOnePrice;
            tableThreeSeatOnePrice = 0.00;
            tableThreeSeatOneOrder.clear();
        } else if(currentSeat.getSource() == tableThreeSeatTwo) {
            tableThreeFullPrice = tableThreeFullPrice - tableThreeSeatTwoPrice;
            tableThreeSeatTwoPrice = 0.00;
            tableThreeSeatTwoOrder.clear();
        } else if(currentSeat.getSource() == tableThreeSeatThree) {
            tableThreeFullPrice = tableThreeFullPrice - tableThreeSeatThreePrice;
            tableThreeSeatThreePrice = 0.00;
            tableThreeSeatThreeOrder.clear();
        } else if(currentSeat.getSource() == tableThreeSeatFour) {
            tableThreeFullPrice = tableThreeFullPrice - tableThreeSeatFourPrice;
            tableThreeSeatFourPrice = 0.00;
            tableThreeSeatFourOrder.clear();
        } else if(currentSeat.getSource() == tableFourSeatOne) {
            tableFourFullPrice = tableFourFullPrice - tableFourSeatOnePrice;
            tableFourSeatOnePrice = 0.00;
            tableFourSeatOneOrder.clear();
        } else if(currentSeat.getSource() == tableFourSeatTwo) {
            tableFourFullPrice = tableFourFullPrice - tableFourSeatTwoPrice;
            tableFourSeatTwoPrice = 0.00;
            tableFourSeatTwoOrder.clear();
        } else if(currentSeat.getSource() == tableFourSeatThree) {
            tableFourFullPrice = tableFourFullPrice - tableFourSeatThreePrice;
            tableFourSeatThreePrice = 0.00;
            tableFourSeatThreeOrder.clear();
        } else if(currentSeat.getSource() == tableFourSeatFour) {
            tableFourFullPrice = tableFourFullPrice - tableFourSeatFourPrice;
            tableFourSeatFourPrice = 0.00;
            tableFourSeatFourOrder.clear();
        }
        if(tableOneSeatOneOrder.getText().equals("") && tableOneSeatTwoOrder.getText().equals("") && tableOneSeatThreeOrder.getText().equals("") && tableOneSeatFourOrder.getText().equals(""))
            tableOneStatus.setFill(Color.LIME);
        if(tableTwoSeatOneOrder.getText().equals("") && tableTwoSeatTwoOrder.getText().equals("") && tableTwoSeatThreeOrder.getText().equals("") && tableTwoSeatFourOrder.getText().equals(""))
            tableOneStatus.setFill(Color.LIME);
        if(tableThreeSeatOneOrder.getText().equals("") && tableThreeSeatTwoOrder.getText().equals("") && tableThreeSeatThreeOrder.getText().equals("") && tableThreeSeatFourOrder.getText().equals(""))
            tableOneStatus.setFill(Color.LIME);
        if(tableFourSeatOneOrder.getText().equals("") && tableFourSeatTwoOrder.getText().equals("") && tableFourSeatThreeOrder.getText().equals("") && tableFourSeatFourOrder.getText().equals(""))
            tableOneStatus.setFill(Color.LIME);
        currentOrderText.clear();
    }

    @FXML private void previewOrder(ActionEvent e) throws IOException {
        if(e.getSource() == tableOnePreviewButton) {
            tableOnePreviewOrder.setText("SEAT ONE:\n" + tableOneSeatOneOrder.getText()
                    + "\nSEAT TWO:\n" + tableOneSeatTwoOrder.getText()
                    + "\nSEAT THREE:\n" + tableOneSeatThreeOrder.getText()
                    + "\nSEAT FOUR:\n" + tableOneSeatFourOrder.getText());
        } else if(e.getSource() == tableTwoPreviewButton) {
            tableTwoPreviewOrder.setText("SEAT ONE:\n" + tableTwoSeatOneOrder.getText()
                    + "\nSEAT TWO:\n" + tableTwoSeatTwoOrder.getText()
                    + "\nSEAT THREE:\n" + tableTwoSeatThreeOrder.getText()
                    + "\nSEAT FOUR:\n" + tableTwoSeatFourOrder.getText());
        } else if(e.getSource() == tableThreePreviewButton) {
            tableThreePreviewOrder.setText("SEAT ONE:\n" + tableThreeSeatOneOrder.getText()
                    + "\nSEAT TWO:\n" + tableThreeSeatTwoOrder.getText()
                    + "\nSEAT THREE:\n" + tableThreeSeatThreeOrder.getText()
                    + "\nSEAT FOUR:\n" + tableThreeSeatFourOrder.getText());
        } else if(e.getSource() == tableFourPreviewButton) {
            tableFourPreviewOrder.setText("SEAT ONE:\n" + tableFourSeatOneOrder.getText()
                    + "\nSEAT TWO:\n" + tableFourSeatTwoOrder.getText()
                    + "\nSEAT THREE:\n" + tableFourSeatThreeOrder.getText()
                    + "\nSEAT FOUR:\n" + tableFourSeatFourOrder.getText());
        }
    }

    @FXML private void submitOrder(ActionEvent e) throws IOException {
        if(e.getSource() == tableOneSubmitButton) {
            if(tableOnePreviewOrder.getText().equals("") || tableOnePreviewOrder.getText().equals("SEAT ONE:\n\nSEAT TWO:\n\nSEAT THREE:\n\nSEAT FOUR:\n"))
                System.out.println("No order to submit!");
            else {
                tableOneStatus.setFill(Color.RED);
                tableOneSubmitLabel.setVisible(true);
                tableOnePaymentTab.setDisable(false);
            }
        } else if(e.getSource() == tableTwoSubmitButton) {
            if(tableTwoPreviewOrder.getText().equals("") || tableTwoPreviewOrder.getText().equals("SEAT ONE:\n\nSEAT TWO:\n\nSEAT THREE:\n\nSEAT FOUR:\n"))
                System.out.println("No order to submit!");
            else {
                tableTwoStatus.setFill(Color.RED);
                tableTwoSubmitLabel.setVisible(true);
                tableTwoPaymentTab.setDisable(false);
            }
        } else if(e.getSource() == tableThreeSubmitButton) {
            if(tableThreePreviewOrder.getText().equals("") || tableThreePreviewOrder.getText().equals("SEAT ONE:\n\nSEAT TWO:\n\nSEAT THREE:\n\nSEAT FOUR:\n"))
                System.out.println("No order to submit!");
            else {
                tableThreeStatus.setFill(Color.RED);
                tableThreeSubmitLabel.setVisible(true);
                tableThreePaymentTab.setDisable(false);
            }
        } else if(e.getSource() == tableFourSubmitButton) {
            if(tableFourPreviewOrder.getText().equals("") || tableFourPreviewOrder.getText().equals("SEAT ONE:\n\nSEAT TWO:\n\nSEAT THREE:\n\nSEAT FOUR:\n"))
                System.out.println("No order to submit!");
            else {
                tableFourStatus.setFill(Color.RED);
                tableFourSubmitLabel.setVisible(true);
                tableFourPaymentTab.setDisable(false);
            }
        }
    }

    @FXML private void paymentProcessing(ActionEvent e) throws IOException {
        if(e.getSource() == tableOneCashButton || e.getSource() == tableOneCardButton) {
            DecimalFormat df = new DecimalFormat("#.##");
            double convertCost = Double.valueOf(df.format(tableOneFullPrice));
            tableOneTotalPrice.setText("Total Cost: £" + convertCost);
            if (e.getSource() == tableOneCashButton) {
                if (tableOneCardPane.isVisible())
                    tableOneCardPane.setVisible(false);
                tableOneCashPane.setVisible(true);
            } else if (e.getSource() == tableOneCardButton) {
                if (tableOneCashPane.isVisible())
                    tableOneCashPane.setVisible(false);
                tableOneCardPane.setVisible(true);
            }
        } else if(e.getSource() == tableTwoCashButton || e.getSource() == tableTwoCardButton) {
            DecimalFormat df = new DecimalFormat("#.##");
            double convertCost = Double.valueOf(df.format(tableTwoFullPrice));
            tableTwoTotalPrice.setText("Total Cost: £" + convertCost);
            if (e.getSource() == tableTwoCashButton) {
                if (tableTwoCardPane.isVisible())
                    tableTwoCardPane.setVisible(false);
                tableTwoCashPane.setVisible(true);
            } else if (e.getSource() == tableTwoCardButton) {
                if (tableTwoCashPane.isVisible())
                    tableTwoCashPane.setVisible(false);
                tableTwoCardPane.setVisible(true);
            }
        } else if(e.getSource() == tableThreeCashButton || e.getSource() == tableThreeCardButton) {
            DecimalFormat df = new DecimalFormat("#.##");
            double convertCost = Double.valueOf(df.format(tableThreeFullPrice));
            tableThreeTotalPrice.setText("Total Cost: £" + convertCost);
            if (e.getSource() == tableThreeCashButton) {
                if (tableThreeCardPane.isVisible())
                    tableThreeCardPane.setVisible(false);
                tableThreeCashPane.setVisible(true);
            } else if (e.getSource() == tableThreeCardButton) {
                if (tableThreeCashPane.isVisible())
                    tableThreeCashPane.setVisible(false);
                tableThreeCardPane.setVisible(true);
            }
        } else if(e.getSource() == tableFourCashButton || e.getSource() == tableFourCardButton) {
            DecimalFormat df = new DecimalFormat("#.##");
            double convertCost = Double.valueOf(df.format(tableFourFullPrice));
            tableFourTotalPrice.setText("Total Cost: £" + convertCost);
            if (e.getSource() == tableFourCashButton) {
                if (tableFourCardPane.isVisible())
                    tableFourCardPane.setVisible(false);
                tableFourCashPane.setVisible(true);
            } else if (e.getSource() == tableFourCardButton) {
                if (tableFourCashPane.isVisible())
                    tableFourCashPane.setVisible(false);
                tableFourCardPane.setVisible(true);
            }
        }
    }

    @FXML private void calcSplitBill(ActionEvent e) throws IOException {
        if(e.getSource() == tableOneCalcButton) {
            try {
                DecimalFormat df = new DecimalFormat("#.##");
                double splitCost = tableOneFullPrice / Integer.parseInt(tableOnePeoplePaying.getText());
                splitCost = Double.valueOf(df.format(splitCost));
                tableOneCostPer.setText("Cost split between " + tableOnePeoplePaying.getText() + ": £" + splitCost);
            } catch(NumberFormatException ex) {
                tableOneCostPer.setText("Payee field requires an integer.");
            }
        } else if(e.getSource() == tableTwoCalcButton) {
              try {
                DecimalFormat df = new DecimalFormat("#.##");
                double splitCost = tableTwoFullPrice / Integer.parseInt(tableTwoPeoplePaying.getText());
                splitCost = Double.valueOf(df.format(splitCost));
                tableTwoCostPer.setText("Cost split between " + tableTwoPeoplePaying.getText() + ": £" + splitCost);
            } catch(NumberFormatException ex) {
                tableTwoCostPer.setText("Payee field requires an integer.");
            }
        } else if(e.getSource() == tableThreeCalcButton) {
             try {
                DecimalFormat df = new DecimalFormat("#.##");
                double splitCost = tableThreeFullPrice / Integer.parseInt(tableThreePeoplePaying.getText());
                splitCost = Double.valueOf(df.format(splitCost));
                tableThreeCostPer.setText("Cost split between " + tableThreePeoplePaying.getText() + ": £" + splitCost);
            } catch(NumberFormatException ex) {
                 tableThreeCostPer.setText("Payee field requires an integer.");
             }
        } else if(e.getSource() == tableFourCalcButton) {
            try {
                DecimalFormat df = new DecimalFormat("#.##");
                double splitCost = tableFourFullPrice / Integer.parseInt(tableFourPeoplePaying.getText());
                splitCost = Double.valueOf(df.format(splitCost));
                tableFourCostPer.setText("Cost split between " + tableFourPeoplePaying.getText() + ": £" + splitCost);
            } catch (NumberFormatException ex) {
                tableFourCostPer.setText("Payee field requires an integer.");
            }
        }
    }

    @FXML private void studentDiscOne(ActionEvent e) {
        if (tableOneStudDisc.isSelected()) {
            double studentDisc = tableOneFullPrice;
            DecimalFormat df = new DecimalFormat("#.##");
            double discCost = studentDisc * 0.9;
            discCost = Double.valueOf(df.format(discCost));
            tableOneTotalPrice.setText("Total Cost: £" + discCost);
        }
        if (!tableOneStudDisc.isSelected()) {
            DecimalFormat df = new DecimalFormat("#.##");
            double convertCost = Double.valueOf(df.format(tableOneFullPrice));
            tableOneTotalPrice.setText("Total Cost: £" + convertCost);
        }
    }

    @FXML private void studentDiscTwo(ActionEvent e) {
        if (tableTwoStudDisc.isSelected()) {
            double studentDisc = tableTwoFullPrice;
            DecimalFormat df = new DecimalFormat("#.##");
            double discCost = studentDisc * 0.9;
            discCost = Double.valueOf(df.format(discCost));
            tableTwoTotalPrice.setText("Total Cost: £" + discCost);
        }
        if (!tableTwoStudDisc.isSelected()) {
            DecimalFormat df = new DecimalFormat("#.##");
            double convertCost = Double.valueOf(df.format(tableTwoFullPrice));
            tableTwoTotalPrice.setText("Total Cost: £" + convertCost);
        }
    }

    @FXML private void studentDiscThree(ActionEvent e) {
        if (tableThreeStudDisc.isSelected()) {
            double studentDisc = tableThreeFullPrice;
            DecimalFormat df = new DecimalFormat("#.##");
            double discCost = studentDisc * 0.9;
            discCost = Double.valueOf(df.format(discCost));
            tableThreeTotalPrice.setText("Total Cost: £" + discCost);
        }
        if (!tableThreeStudDisc.isSelected()) {
            DecimalFormat df = new DecimalFormat("#.##");
            double convertCost = Double.valueOf(df.format(tableThreeFullPrice));
            tableThreeTotalPrice.setText("Total Cost: £" + convertCost);
        }
    }

    @FXML private void studentDiscFour(ActionEvent e) {
        if(tableFourStudDisc.isSelected()) {
            double studentDisc = tableFourFullPrice;
            DecimalFormat df = new DecimalFormat("#.##");
            double discCost = studentDisc * 0.9;
            discCost = Double.valueOf(df.format(discCost));
            tableFourTotalPrice.setText("Total Cost: £" + discCost);
        }
        if(!tableFourStudDisc.isSelected()) {
            DecimalFormat df = new DecimalFormat("#.##");
            double convertCost = Double.valueOf(df.format(tableFourFullPrice));
            tableFourTotalPrice.setText("Total Cost: £" + convertCost);
        }
    }

    @FXML private void finishPayment(ActionEvent e) throws IOException {
        if(e.getSource() == tableOneFinaliseButton) {
            if(tableOneCardPane.isVisible()) {
                try {
                    if (Integer.parseInt(tableOnePIN.getText()) >= 1000 && Integer.parseInt(tableOnePIN.getText()) <= 9999) {
                        if (tableOneStudDisc.isSelected()) {
                            tableOneFullPrice = tableOneFullPrice * 0.9;
                        }
                        Invoice inv = new Invoice(userdisplay.getText(), tableOnePreviewOrder.getText(), 1, tableOneFullPrice);
                        staffReports.add(inv);

                        System.out.println("RECEIPT:\n" + tableOnePreviewOrder.getText() + "\n" + tableOneTotalPrice.getText() + "\n Card Holder:\n Card Exp. Date: ");

                        tableOneFullPrice = 0.00;
                        tableOneSeatOnePrice = 0.00;
                        tableOneSeatTwoPrice = 0.00;
                        tableOneSeatThreePrice = 0.00;
                        tableOneSeatFourPrice = 0.00;
                        tableOneSeatOneOrder.clear();
                        tableOneSeatTwoOrder.clear();
                        tableOneSeatThreeOrder.clear();
                        tableOneSeatFourOrder.clear();
                        tableOneStatus.setFill(Color.LIME);
                        tableOnePreviewOrder.clear();
                        tableOneSubmitLabel.setVisible(false);
                        tableOnePIN.clear();
                        tableOneCardPane.setVisible(false);
                        tableOneTotalPrice.clear();
                        tableOnePaymentTab.setDisable(true);
                    } else {
                        tableOnePIN.clear();
                        System.out.println("Please try again!");
                    }
                } catch(NumberFormatException ex) {
                    tableOnePIN.clear();
                    System.out.println("Please try again!");
                }
            } else if (tableOneCashPane.isVisible()) {
                if (tableOneStudDisc.isSelected()) {
                    tableOneFullPrice = tableOneFullPrice * 0.9;
                }
                Invoice inv = new Invoice(userdisplay.getText(), tableOnePreviewOrder.getText(), 1, tableOneFullPrice);
                staffReports.add(inv);

                System.out.println("RECEIPT:\n" + tableOnePreviewOrder.getText() + "\n" + tableOneTotalPrice.getText() + "\n" + tableOneCostPer.getText());

                tableOneFullPrice = 0.00;
                tableOneSeatOnePrice = 0.00;
                tableOneSeatTwoPrice = 0.00;
                tableOneSeatThreePrice = 0.00;
                tableOneSeatFourPrice = 0.00;
                tableOneSeatOneOrder.clear();
                tableOneSeatTwoOrder.clear();
                tableOneSeatThreeOrder.clear();
                tableOneSeatFourOrder.clear();
                tableOneStatus.setFill(Color.LIME);
                tableOnePreviewOrder.clear();
                tableOneSubmitLabel.setVisible(false);
                tableOneTotalPrice.clear();
                tableOnePaymentTab.setDisable(true);
                tableOneCostPer.clear();
                tableOnePeoplePaying.clear();
                tableOneCashPane.setVisible(false);
            }

        } else if(e.getSource() == tableTwoFinaliseButton) {
            if(tableTwoCardPane.isVisible()) {
                try {
                    if (Integer.parseInt(tableTwoPIN.getText()) >= 1000 && Integer.parseInt(tableTwoPIN.getText()) <= 9999) {
                        if (tableTwoStudDisc.isSelected()) {
                            tableTwoFullPrice = tableTwoFullPrice * 0.9;
                        }
                        Invoice inv = new Invoice(userdisplay.getText(), tableTwoPreviewOrder.getText(), 2, tableTwoFullPrice);
                        staffReports.add(inv);

                        System.out.println("RECEIPT:\n" + tableTwoPreviewOrder.getText() + "\n" + tableTwoTotalPrice.getText() + "\n Card Holder:\n Card Exp. Date: ");

                        tableTwoFullPrice = 0.00;
                        tableTwoSeatOnePrice = 0.00;
                        tableTwoSeatTwoPrice = 0.00;
                        tableTwoSeatThreePrice = 0.00;
                        tableTwoSeatFourPrice = 0.00;
                        tableTwoSeatOneOrder.clear();
                        tableTwoSeatTwoOrder.clear();
                        tableTwoSeatThreeOrder.clear();
                        tableTwoSeatFourOrder.clear();
                        tableTwoStatus.setFill(Color.LIME);
                        tableTwoPreviewOrder.clear();
                        tableTwoSubmitLabel.setVisible(false);
                        tableTwoPIN.clear();
                        tableTwoCardPane.setVisible(false);
                        tableTwoTotalPrice.clear();
                        tableTwoPaymentTab.setDisable(true);
                    } else {
                        tableTwoPIN.clear();
                        System.out.println("Please try again!");
                    }
                } catch(NumberFormatException ex) {
                    tableTwoPIN.clear();
                    System.out.println("Please try again!");
                }
            } else if(tableTwoCashPane.isVisible()) {
                if (tableTwoStudDisc.isSelected()) {
                    tableTwoFullPrice = tableTwoFullPrice * 0.9;
                }
                Invoice inv = new Invoice(userdisplay.getText(), tableTwoPreviewOrder.getText(), 2, tableTwoFullPrice);
                staffReports.add(inv);

                System.out.println("RECEIPT:\n" + tableTwoPreviewOrder.getText() + "\n" + tableTwoTotalPrice.getText() + "\n" + tableTwoCostPer.getText());

                tableTwoFullPrice = 0.00;
                tableTwoSeatOnePrice = 0.00;
                tableTwoSeatTwoPrice = 0.00;
                tableTwoSeatThreePrice = 0.00;
                tableTwoSeatFourPrice = 0.00;
                tableTwoSeatOneOrder.clear();
                tableTwoSeatTwoOrder.clear();
                tableTwoSeatThreeOrder.clear();
                tableTwoSeatFourOrder.clear();
                tableTwoStatus.setFill(Color.LIME);
                tableTwoPreviewOrder.clear();
                tableTwoSubmitLabel.setVisible(false);
                tableTwoCostPer.clear();
                tableTwoPeoplePaying.clear();
                tableTwoCashPane.setVisible(false);
                tableTwoTotalPrice.clear();
                tableTwoPaymentTab.setDisable(true);
            }

        } else if(e.getSource() == tableThreeFinaliseButton) {
            if(tableThreeCardPane.isVisible()) {
                try {
                    if (Integer.parseInt(tableThreePIN.getText()) >= 1000 && Integer.parseInt(tableThreePIN.getText()) <= 9999) {
                        if (tableThreeStudDisc.isSelected()) {
                            tableThreeFullPrice = tableThreeFullPrice * 0.9;
                        }
                        Invoice inv = new Invoice(userdisplay.getText(), tableThreePreviewOrder.getText(), 3, tableThreeFullPrice);
                        staffReports.add(inv);

                        System.out.println("RECEIPT:\n" + tableThreePreviewOrder.getText() + "\n" + tableThreeTotalPrice.getText() + "\n Card Holder:\n Card Exp. Date: ");

                        tableThreeFullPrice = 0.00;
                        tableThreeSeatOnePrice = 0.00;
                        tableThreeSeatTwoPrice = 0.00;
                        tableThreeSeatThreePrice = 0.00;
                        tableThreeSeatFourPrice = 0.00;
                        tableThreeSeatOneOrder.clear();
                        tableThreeSeatTwoOrder.clear();
                        tableThreeSeatThreeOrder.clear();
                        tableThreeSeatFourOrder.clear();
                        tableThreeStatus.setFill(Color.LIME);
                        tableThreePreviewOrder.clear();
                        tableThreeSubmitLabel.setVisible(false);
                        tableThreePIN.clear();
                        tableThreeCardPane.setVisible(false);
                        tableThreeTotalPrice.clear();
                        tableThreePaymentTab.setDisable(true);
                    } else {
                        tableThreePIN.clear();
                        System.out.println("Please try again!");
                    }
                } catch(NumberFormatException ex) {
                    tableThreePIN.clear();
                    System.out.println("Please try again!");
                }
            } else if(tableThreeCashPane.isVisible()) {
                if (tableThreeStudDisc.isSelected()) {
                    tableThreeFullPrice = tableThreeFullPrice * 0.9;
                }
                Invoice inv = new Invoice(userdisplay.getText(), tableThreePreviewOrder.getText(), 3, tableThreeFullPrice);
                staffReports.add(inv);

                System.out.println("RECEIPT:\n" + tableThreePreviewOrder.getText() + "\n" + tableThreeTotalPrice.getText() + "\n" + tableThreeCostPer.getText());

                tableThreeFullPrice = 0.00;
                tableThreeSeatOnePrice = 0.00;
                tableThreeSeatTwoPrice = 0.00;
                tableThreeSeatThreePrice = 0.00;
                tableThreeSeatFourPrice = 0.00;
                tableThreeSeatOneOrder.clear();
                tableThreeSeatTwoOrder.clear();
                tableThreeSeatThreeOrder.clear();
                tableThreeSeatFourOrder.clear();
                tableThreeStatus.setFill(Color.LIME);
                tableThreePreviewOrder.clear();
                tableThreeSubmitLabel.setVisible(false);
                tableThreeCostPer.clear();
                tableThreePeoplePaying.clear();
                tableThreeCashPane.setVisible(false);
                tableThreeTotalPrice.clear();
                tableThreePaymentTab.setDisable(true);
            }
        } else if(e.getSource() == tableFourFinaliseButton) {
            if (tableFourCardPane.isVisible()) {
                try {
                    if (Integer.parseInt(tableFourPIN.getText()) >= 1000 && Integer.parseInt(tableFourPIN.getText()) <= 9999) {
                        if (tableFourStudDisc.isSelected()) {
                            tableFourFullPrice = tableFourFullPrice * 0.9;
                        }
                        Invoice inv = new Invoice(userdisplay.getText(), tableFourPreviewOrder.getText(), 4, tableFourFullPrice);
                        staffReports.add(inv);

                        System.out.println("RECEIPT:\n" + tableFourPreviewOrder.getText() + "\n" + tableFourTotalPrice.getText() + "\n Card Holder:\n Card Exp. Date: ");

                        tableFourFullPrice = 0.00;
                        tableFourSeatOnePrice = 0.00;
                        tableFourSeatTwoPrice = 0.00;
                        tableFourSeatThreePrice = 0.00;
                        tableFourSeatFourPrice = 0.00;
                        tableFourSeatOneOrder.clear();
                        tableFourSeatTwoOrder.clear();
                        tableFourSeatThreeOrder.clear();
                        tableFourSeatFourOrder.clear();
                        tableFourStatus.setFill(Color.LIME);
                        tableFourPreviewOrder.clear();
                        tableFourSubmitLabel.setVisible(false);
                        tableFourPIN.clear();
                        tableFourCardPane.setVisible(false);
                        tableFourTotalPrice.clear();
                        tableFourPaymentTab.setDisable(true);
                    } else {
                        tableFourPIN.clear();
                        System.out.println("Please try again!");
                    }
                } catch (NumberFormatException ex) {
                    tableFourPIN.clear();
                    System.out.println("Please try again!");
                }
            } else if(tableFourCashPane.isVisible()) {
                if (tableFourStudDisc.isSelected()) {
                    tableFourFullPrice = tableFourFullPrice * 0.9;
                }
                Invoice inv = new Invoice(userdisplay.getText(), tableFourPreviewOrder.getText(), 4, tableFourFullPrice);
                staffReports.add(inv);

                System.out.println("RECEIPT:\n" + tableFourPreviewOrder.getText() + "\n" + tableFourTotalPrice.getText() + "\n" + tableFourCostPer.getText());

                tableFourFullPrice = 0.00;
                tableFourSeatOnePrice = 0.00;
                tableFourSeatTwoPrice = 0.00;
                tableFourSeatThreePrice = 0.00;
                tableFourSeatFourPrice = 0.00;
                tableFourSeatOneOrder.clear();
                tableFourSeatTwoOrder.clear();
                tableFourSeatThreeOrder.clear();
                tableFourSeatFourOrder.clear();
                tableFourStatus.setFill(Color.LIME);
                tableFourPreviewOrder.clear();
                tableFourSubmitLabel.setVisible(false);
                tableFourCostPer.clear();
                tableFourPeoplePaying.clear();
                tableFourCashPane.setVisible(false);
                tableFourTotalPrice.clear();
                tableFourPaymentTab.setDisable(true);
            }
        }

    }

    @FXML private void viewStatReports(ActionEvent e) throws IOException {
        statPane.setVisible(true);
        String allReports = "";
        for(int i = 0; i < staffReports.size(); i++)
            allReports = allReports + staffReports.get(i).toString();
        reportViewBox.setText(allReports);
    }

    @FXML private void logOutB(ActionEvent e) throws IOException {
        Parent loginView = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene loginScene = new Scene(loginView);

        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    @FXML private void findAvailTable(ActionEvent e) {
        if(tableOneStatus.getFill().equals(Color.YELLOW) || tableOneStatus.getFill().equals(Color.RED))
            tableOnePane.setVisible(false);
        if(tableTwoStatus.getFill().equals(Color.YELLOW) || tableTwoStatus.getFill().equals(Color.RED))
            tableTwoPane.setVisible(false);
        if(tableThreeStatus.getFill().equals(Color.YELLOW) || tableThreeStatus.getFill().equals(Color.RED))
            tableThreePane.setVisible(false);
        if(tableFourStatus.getFill().equals(Color.YELLOW) || tableFourStatus.getFill().equals(Color.RED))
            tableFourPane.setVisible(false);
    }

    @FXML private void resetFind(ActionEvent e) {
        tableOnePane.setVisible(true);
        tableTwoPane.setVisible(true);
        tableThreePane.setVisible(true);
        tableFourPane.setVisible(true);
    }

    @FXML private void closeStatReports(ActionEvent e) {
        reportViewBox.clear();
        statPane.setVisible(false);
    }

    @FXML private void userManualButton(ActionEvent e) throws IOException {
        try {
            Runtime.getRuntime().exec("open /Users/shahiqram/Desktop/RestaurantApp/UserManual.docx");
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Error loading UserManual.docx");
        }
    }

    @FXML private void registerButton(ActionEvent e) throws IOException {
        Parent registerView = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene registerScene = new Scene(registerView);

        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(registerScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userdisplay.setText(RestaurantApp.getUser());
        if(((userdisplay.getText()).charAt(0)) == ('m')) statReports.setVisible(true);
    }

    public static class MenuItems {
        private final SimpleStringProperty itemName;
        private final SimpleStringProperty itemDesc;
        private final double itemPrice;

        private MenuItems(String iName, String iDesc, double iPrice) {
            this.itemName = new SimpleStringProperty(iName);
            this.itemDesc = new SimpleStringProperty(iDesc);
            this.itemPrice = iPrice;
        }

        public String getItemName() {
            return itemName.get();
        }
        public String getItemDesc() { return itemDesc.get(); }
        public double getItemPrice() { return itemPrice; }
    }

    public static class Invoice {
        private int transactionID;
        private String userName;
        private String orderServed;
        private int tableNumber;
        private double orderCost;
        private Date transactionDate;
        private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        public Invoice(String user, String order, int table, double cost) {
            Random r = new Random();
            transactionID = r.nextInt(10000)+1;
            userName = user;
            orderServed = order;
            tableNumber = table;
            orderCost = cost;
            transactionDate = new Date();
        }

        public String toString() {
            return "\nINVOICE:\n---------------------------------------------\nTransaction ID: " + this.transactionID
                    + "\nStaff Member: " + this.userName
                    + "\nTable Number: " + this.tableNumber
                    + "\nFULL ORDER: \n" + this.orderServed
                    + "\nTotal Price: £" + this.orderCost
                    + "\nTransaction Date: " + dateFormat.format(this.transactionDate) + "\n";
        }
    }
}
