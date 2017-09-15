package com.chojnacki.manufaktura.manuregiony.gui.handlers;

import com.chojnacki.manufaktura.manuregiony.gui.Main;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Generic success handler which only enables GUI
 */
public class GenericSuccessHandler implements EventHandler<WorkerStateEvent> {
    private static final Logger LOGGER = LogManager.getLogger(GenericSuccessHandler.class);
    protected final Main main;

    public GenericSuccessHandler(Main main) {
        this.main = main;
    }

    @Override
    public void handle(WorkerStateEvent event) {
        LOGGER.log(Level.DEBUG, "Task {} finished successfully", event.getSource().getTitle());
        main.setStartEnabled(true);
        main.setFileChooserButtonEnabled(true);
        main.setOptionalTextEnabled(true);
        main.setProcessingTypeComboEnabled(true);
    }
}
