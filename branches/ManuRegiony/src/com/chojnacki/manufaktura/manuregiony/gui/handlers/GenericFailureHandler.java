package com.chojnacki.manufaktura.manuregiony.gui.handlers;

import com.chojnacki.manufaktura.manuregiony.gui.Main;
import com.chojnacki.manufaktura.manuregiony.gui.ReportDialog;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Generic failure handler which logs cause and enabled UI.
 */
public class GenericFailureHandler implements
        javafx.event.EventHandler<javafx.concurrent.WorkerStateEvent> {
    private static final Logger LOGGER = LogManager.getLogger(GenericFailureHandler.class);
    private final Main main;

    public GenericFailureHandler(Main main) {
        this.main = main;
    }

    @Override
    public void handle(WorkerStateEvent event) {
        Worker source = event.getSource();
        Throwable exception = source.getException();
        boolean interrupted = checkIfInterrupted(exception);
        if (!interrupted) {
            LOGGER.log(Level.ERROR, "Error while processing task {}", source.getTitle(), exception);
            main.setStartEnabled(true);
            main.setFileChooserButtonEnabled(true);
            main.setOptionalTextEnabled(true);
            main.setProcessingTypeComboEnabled(true);
            event.consume();
            ReportDialog.showDialog("Błąd podczas zadania: " + source.getTitle(), exception);
        } else {
            LOGGER.log(Level.DEBUG, "Task has been interrupted");
        }
    }

    private boolean checkIfInterrupted(Throwable exception) {
        return exception instanceof InterruptedException;
    }

    protected Main getMain() {
        return main;
    }
}
