module crhisjairo.tools.vanillaminecraftserverhelper {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires minecraft.rcon;

    opens crhisjairo.tools.vanillaminecraftserverhelper to javafx.fxml;
    exports crhisjairo.tools.vanillaminecraftserverhelper;
    exports crhisjairo.tools.vanillaminecraftserverhelper.controllers;
    opens crhisjairo.tools.vanillaminecraftserverhelper.controllers to javafx.fxml;
}