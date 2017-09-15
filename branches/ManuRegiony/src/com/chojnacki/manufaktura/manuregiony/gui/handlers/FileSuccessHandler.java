package com.chojnacki.manufaktura.manuregiony.gui.handlers;

import com.chojnacki.manufaktura.manuregiony.gui.Main;
import com.chojnacki.manufaktura.manuregiony.output.FileStore;
import com.chojnacki.manufaktura.manuregiony.processing.ReportStore;
import com.chojnacki.manufaktura.manuregiony.processing.ThreadRunner;
import javafx.concurrent.WorkerStateEvent;

/**
 * Handler for file save success
 */
public class FileSuccessHandler extends GenericSuccessHandler {
    private final ReportStore reportStore;

    @Override
    public void handle(WorkerStateEvent event) {
        super.handle(event);
        FileStore source = (FileStore) event.getSource();
        reportStore.setOutputPath(source.getOutput().getParent());
        ThreadRunner.run(reportStore);
    }

    public FileSuccessHandler(Main main, ReportStore reportStore) {
        super(main);
        this.reportStore = reportStore;
    }
}
