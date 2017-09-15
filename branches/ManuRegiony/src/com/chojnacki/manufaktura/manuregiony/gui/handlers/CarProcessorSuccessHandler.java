package com.chojnacki.manufaktura.manuregiony.gui.handlers;

import com.chojnacki.manufaktura.manuregiony.gui.Main;
import com.chojnacki.manufaktura.manuregiony.output.Colorer;
import com.chojnacki.manufaktura.manuregiony.processing.ThreadRunner;
import javafx.concurrent.WorkerStateEvent;

/**
 * Class used for handling success after processing cars.
 */
public class CarProcessorSuccessHandler extends GenericSuccessHandler {
    private final Colorer colorer;

    public CarProcessorSuccessHandler(Main main, Colorer colorer) {
        super(main);
        this.colorer = colorer;
    }

    @Override
    public void handle(WorkerStateEvent event) {
        super.handle(event);
        ThreadRunner.run(colorer);
    }
}
