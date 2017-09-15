package com.chojnacki.manufaktura.manuregiony.gui.handlers;

import com.chojnacki.manufaktura.manuregiony.gui.Main;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Generic failure handler which prepares GUI for running tasks.
 */
public class GenericRunningHandler implements EventHandler<WorkerStateEvent> {
    private static final Logger LOGGER = LogManager.getLogger(GenericRunningHandler.class);

    private final Main main;

    public GenericRunningHandler(Main main) {
        this.main = main;
    }

    @Override
    public void handle(WorkerStateEvent event) {
        LOGGER.log(Level.DEBUG, "Starting task {}", event.getSource().getTitle());
        main.bind(event.getSource());
        main.setStartEnabled(false);
        main.setFileChooserButtonEnabled(false);
        main.setOptionalTextEnabled(false);
        main.setProcessingTypeComboEnabled(false);
        event.consume();
    }
}
