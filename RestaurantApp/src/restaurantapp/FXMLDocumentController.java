package restaurantapp;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static javafx.scene.control.ButtonType.*;

public class FXMLDocumentController implements Initializable {
    @FXML
    TextField username;

    @FXML
    PasswordField password;

    @FXML
    Label errormsg;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        RestaurantApp.setUser(username.getText());

        readFile u = new readFile();
        readFile p = new readFile();

        String user = username.getText();
        String pass = password.getText();
        String error = "e";

        if(user.isEmpty())
            error = "Please enter a user ID.";
        else if (pass.isEmpty()){
            error = "Please enter a password.";
        }
        else{
            u.openFile("users.txt");
            int pos = u.readUsers(user);

            if(pos > 0) {
                p.openFile("passwords.txt");

                if(p.readPasswords(pos, pass)) error="";
                else {
                    error = "Incorrect login. Please try again.";
                    password.clear();
                    p.closeFile();
                }
            } else {
                error = "User does not exist. Please try again.";
                username.clear();
                password.clear();
                u.closeFile();
            }
        }

        if(error.isEmpty()) {

//            ButtonType loginButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
//            Dialog<String> dialog = new Dialog<>();
//            dialog.getDialogPane().getButtonTypes().add(loginButtonType);
//            dialog.getDialogPane().setHeaderText("login successful");
//            boolean disabled = false; // computed based on content of text fields, for example
//            dialog.getDialogPane().lookupButton(loginButtonType).setDisable(disabled);
//
//            Optional<String> result = dialog.showAndWait();


            Alert alert = new Alert(Alert.AlertType.INFORMATION, "***Login Successful***", ButtonType.OK);
            alert.showAndWait();

//            if (alert.getResult() == ButtonType.YES) {
//                //do stuff
//            }


            Parent tableLayoutView = FXMLLoader.load(getClass().getResource("TableLayout.fxml"));
            Scene tableLayoutScene = new Scene(tableLayoutView);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableLayoutScene);
            //window.show();
        } else errormsg.setText(error);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}