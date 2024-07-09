package crhisjairo.tools.vanillaminecraftserverhelper;

import crhisjairo.tools.vanillaminecraftserverhelper.utils.LocaleStrings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VanillaMinecraftServerHelperApplication extends Application {
    private static Stage primaryStage;
    private static Locale currentLocale = new Locale("en", "US");
    private static LocaleStrings currentLocaleStrings = LocaleStrings.en_US;

    private static final Logger logger = Logger.getLogger(VanillaMinecraftServerHelperApplication.class.getName());

    @Override
    public void start(Stage stage) throws Exception {
        VanillaMinecraftServerHelperApplication.primaryStage = stage;
        setupLogger();
        loadView();
    }

    private static void setupLogger() {
        // TODO: Add FileHandler later.
        logger.setLevel(Level.ALL);
    }

    private static void loadView() throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.strings", currentLocale);

        FXMLLoader fxmlLoader = new FXMLLoader(
                VanillaMinecraftServerHelperApplication.class.getResource("rcon-connection-view.fxml")
        );
        fxmlLoader.setResources(bundle);

        Scene scene = new Scene(fxmlLoader.load(), 500, 230);

        primaryStage.setTitle(bundle.getString("appTitle"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLocale(LocaleStrings selectedLang) throws Exception {
        Locale newLocale = currentLocale;
        LocaleStrings newLocaleStrings = currentLocaleStrings;

        switch (selectedLang) {
            case en_US:
                newLocale = new Locale("en", "US");
                newLocaleStrings = LocaleStrings.en_US;
                break;
            case es_ES:
                newLocale = new Locale("es", "ES");
                newLocaleStrings = LocaleStrings.es_ES;
                break;
            case fr_CA:
                newLocale = new Locale("fr", "CA");
                newLocaleStrings = LocaleStrings.fr_CA;
                break;
            default:
                System.out.println("Locale " + selectedLang + " cannot be found. Using English US.");
                break;
        }

        currentLocale = newLocale;
        currentLocaleStrings = newLocaleStrings;

        loadView();
    }

    public static LocaleStrings getCurrentLocaleStrings() {
        return currentLocaleStrings;
    }

    public static void main(String[] args) {
        launch();
    }
}