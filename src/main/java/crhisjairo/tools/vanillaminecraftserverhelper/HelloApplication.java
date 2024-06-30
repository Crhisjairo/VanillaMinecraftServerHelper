package crhisjairo.tools.vanillaminecraftserverhelper;

import io.graversen.minecraft.rcon.service.ConnectOptions;
import io.graversen.minecraft.rcon.service.MinecraftRconService;
import io.graversen.minecraft.rcon.service.RconDetails;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 230);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        final MinecraftRconService minecraftRconService = new MinecraftRconService(
                RconDetails.localhost("password"),
                ConnectOptions.defaults());
    }

    public static void main(String[] args) {
        launch();
    }
}