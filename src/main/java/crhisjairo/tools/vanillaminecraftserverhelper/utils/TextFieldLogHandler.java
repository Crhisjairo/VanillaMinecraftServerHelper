package crhisjairo.tools.vanillaminecraftserverhelper.utils;

import javafx.scene.control.TextField;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class TextFieldLogHandler extends Handler {

    private TextField textField;

    public TextFieldLogHandler(TextField textField) {
        this.textField = textField;
    }

    @Override
    public void publish(LogRecord record) {
        if(textField != null) {
            textField.appendText(
                    getFormatter().format(record)
            );
        }
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {
        this.textField = null;
    }
}
