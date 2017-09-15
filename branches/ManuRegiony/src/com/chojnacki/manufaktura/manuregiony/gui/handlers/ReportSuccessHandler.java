package com.chojnacki.manufaktura.manuregiony.gui.handlers;

import com.chojnacki.manufaktura.manuregiony.gui.Main;
import com.chojnacki.manufaktura.manuregiony.gui.ReportDialog;
import com.chojnacki.manufaktura.manuregiony.model.Regions;
import javafx.concurrent.WorkerStateEvent;

/**
 * Handler for file save success
 */
public class ReportSuccessHandler extends GenericSuccessHandler {
    @Override
    public void handle(WorkerStateEvent event) {
        super.handle(event);
        ReportDialog.showDialog("Przetwarzanie zakończone sukcesem.\nPrzetworzono "
                + Regions.ALL.getObjectsNumber() + " pojazdów.\nObraz zapisano "
                + main.getOutputPath(), (String) event.getSource().getValue());
        main.resetTaskProcessors();
    }

    public ReportSuccessHandler(Main main) {
        super(main);
    }
}
