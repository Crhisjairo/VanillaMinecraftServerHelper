package crhisjairo.tools.vanillaminecraftserverhelper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VanillaMinecraftServerHelperApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VanillaMinecraftServerHelperApplication.class.getResource("rcon-connection-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 230);
        stage.setTitle("Vanilla Minecraft Server Helper");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}