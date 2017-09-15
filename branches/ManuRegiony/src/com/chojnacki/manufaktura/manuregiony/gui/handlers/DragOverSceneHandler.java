package com.chojnacki.manufaktura.manuregiony.gui.handlers;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

import java.io.File;
import java.util.List;

/**
 * Handler which accepts file with proper extensions
 */
public class DragOverSceneHandler implements EventHandler<DragEvent> {
    @Override
    public void handle(DragEvent event) {
        List<File> files = event.getDragboard().getFiles();
        if (areCSVsOnly(event, files)) {
            event.acceptTransferModes(TransferMode.LINK);
        } else {
            event.consume();
        }
    }

    public boolean areCSVsOnly(DragEvent event, List<File> files) {
        boolean onlyCSV = true;
        for (File file : files) {
            if (!file.getName().toLowerCase().endsWith("csv")) {
                onlyCSV = false;
                break;
            }
        }
        return event.getDragboard().hasFiles() && files.size() > 0
                && onlyCSV;
    }
}
