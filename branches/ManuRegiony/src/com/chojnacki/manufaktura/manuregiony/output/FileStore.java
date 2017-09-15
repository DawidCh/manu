package com.chojnacki.manufaktura.manuregiony.output;

import com.chojnacki.manufaktura.manuregiony.processing.Timer;
import javafx.concurrent.Task;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

/**
 * Task for saving image to file.
 */
public class FileStore extends Task<Void> {

    private final Colorer colorer;
    private File output;
    private Timer timer = new Timer();

    public FileStore(Colorer colorer) {
        updateTitle("Zapisywanie pliku.");
        this.colorer = colorer;
    }

    @Override
    protected Void call() throws Exception {
        timer.start();
        updateMessage("Obraz jest zapisywany...");
        try {
            BufferedImage image = colorer.get();
            writeDataToFile(image);
            updateMessage("Obraz zapisany w czasie " + timer.stopAndReturn() + " sekund");
        } catch (Exception e) {
            updateMessage("Błąd podczas zapisywania obrazu");
            setException(e);
            throw e;
        } finally {
            updateProgress(0,1);
        }
        return null;
    }

    private void writeDataToFile(RenderedImage image) throws IOException {
        String fileName[] = output.getName().split("\\.");
        ImageIO.write(image, fileName[1], output);
    }

    public File getOutput() {
        return output;
    }

    public void setOutput(File output) {
        this.output = output;
    }

}
