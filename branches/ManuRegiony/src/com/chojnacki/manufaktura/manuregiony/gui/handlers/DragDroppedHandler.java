package com.chojnacki.manufaktura.manuregiony.gui.handlers;

import com.chojnacki.manufaktura.manuregiony.gui.Main;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;

import java.io.File;
import java.util.List;

/**
 * Class handles event with dragged files.
 */
public class DragDroppedHandler implements EventHandler<DragEvent> {

    private final Main main;

    public DragDroppedHandler(Main main) {
        this.main = main;
    }

    @Override
    public void handle(DragEvent event) {
        List<File> files = event.getDragboard().getFiles();
        main.onFileLoaded(files);
        event.consume();
    }
}
