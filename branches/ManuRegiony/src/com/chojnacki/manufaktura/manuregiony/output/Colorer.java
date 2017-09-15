package com.chojnacki.manufaktura.manuregiony.output;

import com.chojnacki.manufaktura.manuregiony.exceptions.ImageNotFoundException;
import com.chojnacki.manufaktura.manuregiony.gui.Main;
import com.chojnacki.manufaktura.manuregiony.model.ColorHolder;
import com.chojnacki.manufaktura.manuregiony.model.ProcessingType;
import com.chojnacki.manufaktura.manuregiony.model.Region;
import com.chojnacki.manufaktura.manuregiony.model.Regions;
import com.chojnacki.manufaktura.manuregiony.processing.ResourceLoader;
import com.chojnacki.manufaktura.manuregiony.processing.Timer;
import javafx.concurrent.Task;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.chojnacki.manufaktura.manuregiony.model.ProcessingType.LODZKIE;
import static com.chojnacki.manufaktura.manuregiony.processing.ResourceLoader.getResourceFromConfigFile;
import static com.chojnacki.manufaktura.manuregiony.processing.ResourceLoader.loadImage;

/**
 * Task which works on image. Reads it flooding area and prepares output.
 */
public class Colorer extends Task<BufferedImage> implements ProcessingTypeListener {

    private static final Logger LOGGER = LogManager.getLogger(Colorer.class);
    private final Font font;

    private BufferedImage image;
    private int borderColor;
    private int marginColor;
    private ImageBuilder imgB;
    private boolean skipEmptyRegions;
    private Timer timer = new Timer();
    private Main main;

    public Colorer(Main main) {
        updateTitle("Tworzenie obrazu wyjściowego.");

        borderColor = makeARGB(
                getResourceFromConfigFile("borderColor", "0,0,0")
                        .split(","));
        marginColor = Integer.parseInt(getResourceFromConfigFile("colorMargin", "35"));
        skipEmptyRegions = Boolean.valueOf(ResourceLoader.getResourceFromConfigFile("skipEmptyRegions", "true"));
        this.main = main;
        font = new Font(getResourceFromConfigFile("fontName", "Arial"), Font.PLAIN, Integer.valueOf(getResourceFromConfigFile("fontSize", "65")));
        imgB = main.getImageBuilder();
    }

    @Override
    public void setProcessingType(ProcessingType processingType) {
        String imageName = getImageName(processingType);
        try {
            image = loadImage(imageName);
        } catch (IOException e) {
            throw new ImageNotFoundException("Image " + imageName + " is not found.");
        }
        imgB.setInputImage(image);
    }

    public String getImageName(ProcessingType processingType) {
        String imageName;
        if (LODZKIE.equals(processingType)) {
            imageName = "polska.png";
        } else {
            imageName = "polska_small.png";
        }
        return imageName;
    }

    @Override
    public BufferedImage call() throws InterruptedException, IOException {
        timer.start();
        updateMessage("Tworzenie obrazu...");
        Collection<Region> regions = Regions.getInstance().getPaintableRegions();
        double counter = 0;
        double regionSize = regions.size();
        imgB.setUpTableDetails(regionSize);
        imgB.prepareTableGraphics();
        imgB.setOptionalText(main.getOptionalText());
        BufferedImage result;
        try {
            for (Region region : regions) {
                processRegion(counter, regionSize, region);
                counter += 1.0;
            }
            result = imgB.buildResultImage();
            updateMessage("Obraz utworzony w czasie " + timer.stopAndReturn() + " sekund");
        } catch (InterruptedException exc) {
            setException(exc);
            throw exc;
        } catch (Exception exc) {
            updateMessage("Błąd podczas tworzenia obrazu w czasie " + timer.stopAndReturn() + " sekund");
            setException(exc);
            throw exc;
        } finally {
            updateProgress(0, 1);
        }
        return result;
    }

    public void processRegion(double counter, double regionSize, Region region) throws InterruptedException {
        updateProgress(counter, regionSize);
        updateMessage("Przetwarzany region - " + region.getName());
        LOGGER.log(Level.DEBUG,
                "Processing region {}: " + region.toString());
        colorObject(region);
        writeRegionShortcutOnMap(region);
        imgB.paintRegionInTable(region, (int) counter);
    }

    private void writeRegionShortcutOnMap(Region region) {
        if (region.drawShortcutOnMap()) {
            int x = region.getCoordinates()[0].getX();
            int y = region.getCoordinates()[0].getY();
            String shortcut = region.getMainRegionShortcut();

            LOGGER.log(Level.DEBUG, "Writing region {} on the map {}x{}", shortcut, x, y);

            Graphics graphics = image.getGraphics();
            graphics.setColor(Color.BLACK);
            graphics.setFont(font);
            graphics.drawString(shortcut, x, y);
        }
    }

    private void colorObject(ColorHolder holder) throws InterruptedException {
        if (skipRegion(holder)) {
            LOGGER.log(Level.DEBUG, "Skipping empty region.");
            return;
        }
        Set<Point> visitedPixels = new HashSet<>();
        com.chojnacki.manufaktura.manuregiony.model.Point[] coordinates = holder.getCoordinates();
        LOGGER.log(Level.DEBUG, "Holder has {} different coordinates.", coordinates.length);
        for (com.chojnacki.manufaktura.manuregiony.model.Point point : coordinates) {
            regionGrow(point.getX(), point.getY(), holder.getPercentageColor(),
                    visitedPixels, image);
        }
    }

    private boolean skipRegion(ColorHolder holder) {
        boolean result = false;
        if (!holder.isPaintable()) {
            result = true;
        } else if (skipEmptyRegions && holder.getObjectsNumber() == 0) {
            result = true;
        }
        return result;
    }

    private void regionGrow(int x, int y, int argb, Set<Point> visitedPixels, BufferedImage brImage)
            throws InterruptedException {
        try {
            brImage.setRGB(x, y, argb);
        } catch (Exception exc) {
            LOGGER.log(Level.DEBUG, "Exception during setting color on image for coordinates {},{}", x, y);
            throw exc;
        }
        visitedPixels.add(new Point(x, y));
        // go left
        if (isNotTheBorder(x - 1, y)
                && isNotVisited(visitedPixels, new Point(x - 1, y))) {
            regionGrow(x - 1, y, argb, visitedPixels, brImage);
        }

        // go up
        if (isNotTheBorder(x, y - 1)
                && isNotVisited(visitedPixels, new Point(x, y - 1))) {
            regionGrow(x, y - 1, argb, visitedPixels, brImage);
        }

        // go right
        if (isNotTheBorder(x + 1, y)
                && isNotVisited(visitedPixels, new Point(x + 1, y))) {
            regionGrow(x + 1, y, argb, visitedPixels, brImage);
        }

        // go down
        if (isNotTheBorder(x, y + 1)
                && isNotVisited(visitedPixels, new Point(x, y + 1))) {
            regionGrow(x, y + 1, argb, visitedPixels, brImage);
        }
        if (Thread.interrupted()) {
            throw new InterruptedException("Thread has been cancelled");
        }
    }

    private boolean isNotVisited(Set<Point> visitedPixels, Point o) {
        return !visitedPixels.contains(o);
    }

    private boolean isNotTheBorder(int i, int j) {
        boolean result = false;
        int currentColor;
        LOGGER.log(Level.TRACE,
                "Processing point {}, {}", i, j);
        try {
            currentColor = image.getRGB(i, j);
        } catch (Exception exc) {
            LOGGER.error("Error processing pixel {}, {}", i, j);
            throw exc;
        }
        int border[] = printPixelARGB(borderColor);
        int current[] = printPixelARGB(currentColor);

        double distance = Math.sqrt(Math.pow(border[0] - current[0], 2)
                + Math.pow(border[1] - current[1], 2)
                + Math.pow(border[2] - current[2], 2));
        if (distance > marginColor) {
            result = true;
        }
        LOGGER.log(Level.TRACE,
                "Processed point {} {} ({}, {}, {}). Is border: {} with distance {}", i, j, current[0], current[1], current[2],
                !result, distance);
        return result;
    }

    private int[] printPixelARGB(int pixel) {
        int result[] = new int[4];
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        result[0] = alpha;
        result[1] = red;
        result[2] = green;
        result[3] = blue;
        return result;
    }

    private int makeARGB(int red, int green, int blue) {
        return 255 << 24 | red << 16 | green << 8 | blue;
    }

    private int makeARGB(String[] rgb) {
        return 255 << 24 | Integer.parseInt(rgb[0]) << 16
                | Integer.parseInt(rgb[1]) << 8 | Integer.parseInt(rgb[2]);
    }

}
