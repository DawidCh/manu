package com.chojnacki.manufaktura.manuregiony.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Dialog which displays reports on the end of processing.
 */
public class ReportDialog {

    private static Alert alert;

    public static void showDialog(String content, Throwable throwable) {
        produceDialog(content, getMessage(throwable));
        alert.showAndWait();
    }

    public static void showDialog(String content, String message) {
        produceDialog(content, message);
        alert.showAndWait();
    }

    private static void produceDialog(String content, String message) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Raport");
        alert.setHeaderText("Przetwarzanie zakończone");
        alert.setContentText(content);
        buildExpendableContent(message);
    }

    private static void buildExpendableContent(String message) {
        if (message != null && !message.isEmpty()) {
            TextArea textArea = new TextArea(message);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(new Label("Podczas przetwarzania napotkano błędy"), 0, 0);
            expContent.add(textArea, 0, 1);

            alert.getDialogPane().setExpandableContent(expContent);
        }
    }

    public static String getMessage(Throwable error) {
        return ExceptionUtils.getStackTrace(error);
    }
}
