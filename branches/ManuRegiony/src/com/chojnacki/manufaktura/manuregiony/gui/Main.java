package com.chojnacki.manufaktura.manuregiony.gui;

import com.chojnacki.manufaktura.manuregiony.gui.controllers.MainViewController;
import com.chojnacki.manufaktura.manuregiony.gui.handlers.*;
import com.chojnacki.manufaktura.manuregiony.input.CSVDataReader;
import com.chojnacki.manufaktura.manuregiony.model.Regions;
import com.chojnacki.manufaktura.manuregiony.output.Colorer;
import com.chojnacki.manufaktura.manuregiony.output.FileStore;
import com.chojnacki.manufaktura.manuregiony.output.ImageBuilder;
import com.chojnacki.manufaktura.manuregiony.processing.CarProcessor;
import com.chojnacki.manufaktura.manuregiony.processing.ReportStore;
import com.chojnacki.manufaktura.manuregiony.processing.ThreadRunner;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Main class
 */
public class Main extends Application {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    private FileStore fileStore;
    private Stage primaryStage;
    private CSVDataReader reader;
    private CarProcessor carProcessor;
    private MainViewController viewController;
    private Colorer colorer;
    private Collection<CSVRecord> inputErrors;
    private ReportStore reportStore;
    private ImageBuilder imgB;
    private ComboListener comboChangeListener;

    public static void main(String[] args) {
        launch(args);
    }

    private void initProcessors() {
        GenericFailureHandler genericFailureHandler = new GenericFailureHandler(this);
        GenericRunningHandler runningHandler = new GenericRunningHandler(this);
        GenericSuccessHandler successHandler = new GenericSuccessHandler(this);
        inputErrors = new HashSet<>(1000);

        reader = new CSVDataReader(this);
        reader.setOnFailed(genericFailureHandler);
        reader.setOnRunning(runningHandler);

        carProcessor = new CarProcessor(reader);
        carProcessor.setOnFailed(genericFailureHandler);
        carProcessor.setOnRunning(runningHandler);
        imgB = new ImageBuilder();

        reinitProcessors();

        reader.setOnSucceeded(successHandler);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        FXMLLoader view = new FXMLLoader(
                getClass().getResource("layouts/view.fxml"));
        Parent loadingLayout = view.load();
        viewController = view.getController();
        viewController.setMain(this);
        comboChangeListener = new ComboListener();
        viewController.setComboListener(comboChangeListener);

        primaryStage.setTitle("ManuRegiony");

        Scene inputScene = new Scene(loadingLayout, 750, 210);
        loadCSS(inputScene.getStylesheets());

        inputScene.setOnDragDropped(new DragDroppedHandler(this));
        inputScene.setOnDragOver(new DragOverSceneHandler());

        primaryStage.setScene(inputScene);
        primaryStage.setOnCloseRequest(event -> ThreadRunner.shutdown());
        primaryStage.show();
    }

    private void loadCSS(ObservableList<String> stylesheets) {
        File external = new File("style.css");
        LOGGER.log(Level.DEBUG, "Trying to load style from external file: {}",
                external.getAbsolutePath());
        if (external.exists())
            stylesheets.add(
                    "file:///" + external.getAbsolutePath().replace("\\", "/"));
        else {
            LOGGER.log(Level.DEBUG,
                    "Loading style from external file failed. Using internal resources.");
            stylesheets.add("style.css");
        }
    }

    public void startCalulations() {
        if (!carProcessor.isDone()) {
            ThreadRunner.run(carProcessor);
        } else {
            ThreadRunner.run(colorer);
        }
    }

    private void setInputPath(String string) {
        viewController.setInputPath(string);
    }

    public void setStartEnabled(boolean enabled) {
        viewController.setStartEnabled(enabled);
    }

    public void setFileChooserButtonEnabled(boolean enabled) {
        viewController.setFileChooserDialogEnabled(enabled);
    }

    public void bind(Worker readOnlyDoubleProperty) {
        viewController.bind(readOnlyDoubleProperty);
    }

    public void onFileLoaded(List<File> input) {
        initProcessors();
        Regions.getInstance().resetCounters();
        viewController.setInputPath("");
        setStartEnabled(false);

        String inputPath;
        if (input.size() == 1) {
            inputPath = input.get(0).getAbsolutePath();
        } else {
            inputPath = input.get(0).getParent() + File.separator + "*.csv";
        }
        setInputPath(inputPath);
        reader.setInput(input);
        ThreadRunner.run(reader);
    }

    public File showSaveDialog() {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter(
                "Pliki obrazów", "*.jpg", "*.jpeg", "*.bmp");
        fc.getExtensionFilters().add(filter);
        fc.setInitialFileName(Regions.getInstance().getProcessingType().toString().toLowerCase() + ".jpg");
        File directory = new File(System.getProperty("user.home"));
        fc.setInitialDirectory(directory);
        LOGGER.log(Level.DEBUG, "Serving file chooser to the user : {}", directory.getAbsolutePath());
        return fc.showSaveDialog(primaryStage.getOwner());
    }

    public Collection<CSVRecord> getInputErrors() {
        return inputErrors;
    }

    public void resetTaskProcessors() {
        reinitProcessors();
    }

    private void reinitProcessors() {
        GenericFailureHandler genericFailureHandler = new GenericFailureHandler(this);
        GenericRunningHandler runningHandler = new GenericRunningHandler(this);
        colorer = new Colorer(this);
        colorer.setOnFailed(genericFailureHandler);
        colorer.setOnRunning(runningHandler);

        fileStore = new FileStore(colorer);
        fileStore.setOnFailed(genericFailureHandler);
        fileStore.setOnRunning(runningHandler);

        reportStore = new ReportStore(this);
        reportStore.setOnFailed(genericFailureHandler);
        reportStore.setOnSucceeded(new ReportSuccessHandler(this));
        reportStore.setOnRunning(runningHandler);

        colorer.setOnSucceeded(new ColorerSuccessHandler(this, fileStore));
        fileStore.setOnSucceeded(new FileSuccessHandler(this, reportStore));
        carProcessor.setOnSucceeded(new CarProcessorSuccessHandler(this, colorer));

        comboChangeListener.cleanListeners();
        comboChangeListener.addListener(Regions.getInstance());
        comboChangeListener.addListener(colorer);
        comboChangeListener.addListener(imgB);
        comboChangeListener.changed(null, null, viewController.getSelectedProcessingType());
    }

    public String getOutputPath() {
        return fileStore.getOutput().getAbsolutePath();
    }

    public String getOptionalText() {
        return viewController.getOptionalText();
    }

    public void setOptionalTextEnabled(boolean enabled) {
        viewController.setOptionalTextEnabled(enabled);
    }

    public void setProcessingTypeComboEnabled(boolean enabled) {
        viewController.setProcessingTypeComboEnabled(enabled);
    }

    public ImageBuilder getImageBuilder() {
        return imgB;
    }
}
