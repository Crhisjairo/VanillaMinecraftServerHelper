package crhisjairo.tools.vanillaminecraftserverhelper.controllers;

import crhisjairo.tools.vanillaminecraftserverhelper.DAOs.RconConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RconConnectionController {
    @FXML
    private TextField ipField;

    @FXML
    private TextField portField;

    @FXML
    private PasswordField pwdField;

    @FXML
    protected void onHelloButtonClick() {
        RconConnection.RconConnectionStatus status = RconConnection.connect(
                ipField.getText(),
                portField.getText(),
                pwdField.getText()
        );

        switch (status) {
            case ALREADY_CONNECTED:
                showAlertDialog("Already connected to a RCON server.", Alert.AlertType.WARNING);
                System.err.println("Error: Already connected to a RCON server.");
                break;
            case CONNECTION_DETAILS_INVALID:
                showAlertDialog("Password cannot be empty. Please specify a password.", Alert.AlertType.ERROR);
                System.err.println("Error: Connection details not valid.");
                break;
            case CONNECTION_ERROR:
                showAlertDialog("Error connecting. Check IP or password.", Alert.AlertType.ERROR);
                System.err.println("Error connecting. Check IP or password.");
                break;
            case CONNECTED:
                // TODO: Implement new window for connected state.
                System.out.println("Connected!");
                break;
        }
    }

    private void showAlertDialog(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("RCON Connection");
        alert.setHeaderText("RCON Connection Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
