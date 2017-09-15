package com.chojnacki.manufaktura.manuregiony.gui.controllers;

import com.chojnacki.manufaktura.manuregiony.gui.Main;
import com.chojnacki.manufaktura.manuregiony.model.ProcessingType;
import com.chojnacki.manufaktura.manuregiony.processing.ResourceLoader;
import com.chojnacki.manufaktura.manuregiony.processing.ThreadRunner;
import javafx.beans.value.ChangeListener;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.StatusBar;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.chojnacki.manufaktura.manuregiony.model.ProcessingType.LODZKIE;
import static com.chojnacki.manufaktura.manuregiony.model.ProcessingType.values;

/**
 * Controller for main view.
 */
public class MainViewController implements Initializable {
    private final int LIMIT;
    @FXML
    private ComboBox processingTypeCombo;
    @FXML
    private TextField optionalText;
    @FXML
    private Button fileChooserDialog;
    @FXML
    private StatusBar statusBar;
    @FXML
    private Button startButton;
    @FXML
    private TextField platesText;
    @FXML
    private Button exit;

    private FileChooser plateFileChooser;
    private Main main;

    public MainViewController() {
        LIMIT = Integer.valueOf(ResourceLoader.getResourceFromConfigFile("optionalTextLimit", "15"));
    }

    public void exit(ActionEvent actionEvent) {
        Stage window = (Stage) exit.getScene().getWindow();
        ThreadRunner.shutdown();
        window.close();
    }

    public void startCalculations(ActionEvent actionEvent) {
        main.startCalulations();
    }

    public void openDialog(ActionEvent actionEvent) {
        Button source = (Button) actionEvent.getSource();
        List<File> selectedFiles = plateFileChooser.showOpenMultipleDialog(source.getScene().getWindow());
        if (selectedFiles != null  && !selectedFiles.isEmpty()) {
            main.onFileLoaded(selectedFiles);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        plateFileChooser = new FileChooser();
        plateFileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV (comma separated value) files", "*.csv"));
        optionalText.lengthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                // Check if the new character is greater than LIMIT
                if (optionalText.getText().length() >= LIMIT) {

                    // if it's 11th character then just setText to previous
                    // one
                    optionalText.setText(optionalText.getText().substring(0, LIMIT));
                }
            }
        });

        processingTypeCombo.getItems().setAll(values());
        processingTypeCombo.getSelectionModel().select(LODZKIE);
    }

    public void setComboListener(ChangeListener comboChangeListener) {
        processingTypeCombo.getSelectionModel().selectedItemProperty().addListener(comboChangeListener);
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setStartEnabled(boolean enabled) {
        this.startButton.setDisable(!enabled);
    }

    public void setInputPath(String string) {
        platesText.setText(string);
    }

    public void bind(Worker worker) {
        statusBar.progressProperty().unbind();
        statusBar.textProperty().unbind();

        statusBar.progressProperty().bind(worker.progressProperty());
        statusBar.textProperty().bind(worker.messageProperty());
    }

    public void setFileChooserDialogEnabled(boolean enabled) {
        fileChooserDialog.setDisable(!enabled);
    }

    public String  getOptionalText() {
        return optionalText.getText();
    }
    public ProcessingType getSelectedProcessingType() {
        return (ProcessingType) processingTypeCombo.getSelectionModel().getSelectedItem();
    }

    public void setOptionalTextEnabled(boolean enabled) {
        optionalText.setDisable(!enabled);
    }

    public void setProcessingTypeComboEnabled(boolean enabled) {
        processingTypeCombo.setDisable(!enabled);
    }
}
