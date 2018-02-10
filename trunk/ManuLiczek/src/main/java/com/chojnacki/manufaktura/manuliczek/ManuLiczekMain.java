/*
 * ManuLiczekMain.java
 */
package com.chojnacki.manufaktura.manuliczek;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.util.Properties;

import com.chojnacki.manufaktura.manuliczek.model.Level;
import com.chojnacki.manufaktura.manuliczek.model.Place;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.Task;

/**
 * The main class of the application.
 */
public class ManuLiczekMain extends SingleFrameApplication {

    private File outputFile;
    private File intpuFile;
    private InputStream inputImageFile;
    private ManuLiczekMainView mainWindow;
    private InputStream inputLocales;
    private InputStream reserveInputLocales;

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        mainWindow = new ManuLiczekMainView(this);
        show(mainWindow);
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of ManuLiczekMain
     */
    public static ManuLiczekMain getApplication() {
        return Application.getInstance(ManuLiczekMain.class);
    }

    public void executeTask(Task task) {
        getContext().getTaskService().execute(task);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) throws URISyntaxException {
        File jarFile = new File(ManuLiczekMain.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        PropertyConfigurator.configure(jarFile.getParent() + File.separator + ".." + File.separator + "log4j.properties");
        tieSystemOutAndErrToLog();
        launch(ManuLiczekMain.class, args);
    }

    void setOutputFile(File outputFilePath) {
        this.outputFile = outputFilePath;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setIntpuFile(File intpuFile) {
        this.intpuFile = intpuFile;
    }

    public File getIntpuFile() {
        return intpuFile;
    }

    public static String getParametrizedString(String parametrizedKey, Class objectClass, Object... parameters) {
        return Application.getInstance().getContext().getResourceMap(objectClass).getString(parametrizedKey, parameters);
    }

    public void setInputImageFile(InputStream inputImageFile) {
        this.inputImageFile = inputImageFile;
    }

    public void setInputLocales(InputStream inputLocales) {
        this.inputLocales = inputLocales;
    }

    public void setReserveInputLocales(InputStream reserveInputLocales) {
        this.reserveInputLocales = reserveInputLocales;
    }

    public InputStream getInputImageFile() {
        return inputImageFile;
    }

    public InputStream getInputLocales() {
        return inputLocales;
    }

    public InputStream getReserveInputLocales() {
        return reserveInputLocales;
    }

    public static int makeARGB(int red, int green, int blue) {
        return 255 << 24 | red << 16 | green << 8 | blue;
    }
    private static final Logger logger = Logger.getLogger("rootLogger");

    private static void tieSystemOutAndErrToLog() {
        System.setOut(createLoggingProxy(System.out));
        System.setErr(createLoggingProxy(System.err));
    }

    private static PrintStream createLoggingProxy(final PrintStream realPrintStream) {
        return new PrintStream(realPrintStream) {

            @Override
            public void print(final String string) {
                realPrintStream.print(string);
                logger.info(string);
            }
        };
    }

    public static Properties getFontSettings() {
        Properties result = new Properties();
        try {
            File propsFile = new File(ManuLiczekMain.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            result.load(new FileInputStream(propsFile.getParent() + File.separator + ".." + File.separator + "fontSettings.properties"));
        } catch (Exception ex) {
            ex.printStackTrace();
            result.put("fontName", "Arial");
            result.put("fontColor", "0,0,0");
            result.put("fontSize", "10");
        }
        return result;
    }

    public Level getFloor() {
        return mainWindow.getFloor();
    }

    public Place getPlace() {
        return mainWindow.getPlace();
    }
}
