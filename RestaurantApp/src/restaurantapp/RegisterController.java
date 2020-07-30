package restaurantapp;

import java.util.*;
import java.io.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class RegisterController implements Initializable {

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) throws IOException {
        String username = "";

        readFile u = new readFile();
        u.openFile("users.txt");
        int index = u.findIndex();
        u.closeFile();

        username = "w" + index;
        //else if(accType.equals("Chef")) username = "c" + index;

        String tempPass = randomAlphaNumeric(8);

        addRecords("users.txt", index, username);
        addRecords("passwords.txt", index, tempPass);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "***Registration Complete, email will be sent promptly***", ButtonType.OK);
        alert.showAndWait();

        Parent tableLayoutView = FXMLLoader.load(getClass().getResource("TableLayout.fxml"));
        Scene tableLayoutScene = new Scene(tableLayoutView);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableLayoutScene);
        window.show();
    }

    public static void addRecords(String file, int i, String data) {
        Formatter x = new Formatter();
        try {
            FileWriter f = new FileWriter(file, true);
            x = new Formatter(f);
        } catch(Exception e) {
            System.out.println("File not found.");
        }

        x.format("%d%s%n", i, " " + data);
        x.close();
    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();

        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}