package com.chojnacki.manufaktura.manuregiony.gui.handlers;

import com.chojnacki.manufaktura.manuregiony.gui.Main;
import com.chojnacki.manufaktura.manuregiony.output.FileStore;
import com.chojnacki.manufaktura.manuregiony.processing.ThreadRunner;
import javafx.concurrent.WorkerStateEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Listener for success after coloring map
 */
public class ColorerSuccessHandler extends GenericSuccessHandler {

    private static final Logger LOGGER = LogManager.getLogger(ColorerSuccessHandler.class);
    private final FileStore fileStore;


    public ColorerSuccessHandler(Main main, FileStore fileStore) {
        super(main);
        this.fileStore = fileStore;
    }

    @Override
    public void handle(WorkerStateEvent event) {
        super.handle(event);
        File outputFile = main.showSaveDialog();
        if (outputFile != null) {
            LOGGER.log(Level.DEBUG, "Directory has been chosen : {}", outputFile.getAbsolutePath());
            fileStore.setOutput(outputFile);
            ThreadRunner.run(fileStore);
        } else {
            main.resetTaskProcessors();
            main.setStartEnabled(false);
            main.setFileChooserButtonEnabled(true);
        }
    }
}
