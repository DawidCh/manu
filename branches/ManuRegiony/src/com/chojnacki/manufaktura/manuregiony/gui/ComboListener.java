package com.chojnacki.manufaktura.manuregiony.gui;

import com.chojnacki.manufaktura.manuregiony.model.ProcessingType;
import com.chojnacki.manufaktura.manuregiony.output.ProcessingTypeListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChojnackiD on 2016-05-16.
 */
public class ComboListener implements ChangeListener<ProcessingType> {

    private List<ProcessingTypeListener> listeners = new ArrayList<>();

    @Override
    public void changed(ObservableValue<? extends ProcessingType> observable, ProcessingType oldValue, ProcessingType newValue) {
        for (ProcessingTypeListener interestedObject : listeners) {
            interestedObject.setProcessingType(newValue);
        }
    }

    public void addListener(ProcessingTypeListener listener) {
        listeners.add(listener);
    }

    public void cleanListeners() {
        listeners.clear();
    }
}
