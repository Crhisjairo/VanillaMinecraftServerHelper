package crhisjairo.tools.vanillaminecraftserverhelper;

import io.graversen.minecraft.rcon.MinecraftRcon;
import io.graversen.minecraft.rcon.service.ConnectOptions;
import io.graversen.minecraft.rcon.service.MinecraftRconService;
import io.graversen.minecraft.rcon.service.RconDetails;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.time.Duration;

public class HelloController {
    @FXML
    private TextField ipField;

    @FXML
    private TextField port;

    @FXML
    private PasswordField pwdField;

    @FXML
    protected void onHelloButtonClick() {
        if(pwdField.getText().isEmpty()){
            System.err.println("Error: Password cannot be empty!");

            return;
        }

        RconDetails details;

        if(ipField.getText().isEmpty()) {
            details = RconDetails.localhost(pwdField.getText());
            System.out.println("IP missing. Loading defaults details!");
        } else {
            // TODO: add int parsing verification
            details = new RconDetails(
                    ipField.getText(),
                    Integer.parseInt(port.getText()),
                    pwdField.getText());
        }

        final MinecraftRconService minecraftRconService = new MinecraftRconService(
                details,
                ConnectOptions.defaults());

        try {
            minecraftRconService.connectBlocking(Duration.ofSeconds(5)); // Tiempo antes de seguir
            final MinecraftRcon minecraftRcon = minecraftRconService.minecraftRcon()
                    .orElseThrow(IllegalStateException::new);
        } catch (IllegalStateException e) {
            System.err.println("Error connecting. Check ip or password.");
        }

        System.out.println("Connected!");
    }

}