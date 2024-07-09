package crhisjairo.tools.vanillaminecraftserverhelper.controllers;

import crhisjairo.tools.vanillaminecraftserverhelper.DAOs.RconConnection;
import crhisjairo.tools.vanillaminecraftserverhelper.VanillaMinecraftServerHelperApplication;
import crhisjairo.tools.vanillaminecraftserverhelper.utils.LocaleStrings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.logging.Logger;

public class RconConnectionController {
    @FXML
    private TextField ipField;

    @FXML
    private TextField portField;

    @FXML
    private PasswordField pwdField;

    @FXML
    private ComboBox cbLocales;

    private final ObservableList<LocaleStrings> languages = FXCollections.observableArrayList(
            Arrays.asList(LocaleStrings.values())
    );

    private final Logger logger = VanillaMinecraftServerHelperApplication.getLogger();

    @FXML
    public void initialize() {
        cbLocales.setItems(languages);
        cbLocales.setValue(languages.get(
                VanillaMinecraftServerHelperApplication.getCurrentLocaleStrings().ordinal()
        ));
    }

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
                openServerControlPanel();
                break;
        }
    }

    @FXML
    protected void onLangSelected(ActionEvent event) {
        LocaleStrings selectedLang = (LocaleStrings) cbLocales.getValue();
        System.out.println("Lang selected: " + selectedLang);

        try {
            VanillaMinecraftServerHelperApplication.setLocale(selectedLang);
        } catch (Exception e) {
            System.err.println("exception : " + e);
        }
    }

    private void openServerControlPanel() {
        // Close current windows
        Stage stage = (Stage) ipField.getScene().getWindow();
        stage.close();

        Stage serverControlPanelStage = new Stage();
        serverControlPanelStage.setTitle("");
    }

    private void showAlertDialog(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("RCON Connection");
        alert.setHeaderText("RCON Connection Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
