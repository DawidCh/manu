package com.chojnacki.manufaktura.manuregiony.output;

import com.chojnacki.manufaktura.manuregiony.model.ProcessingType;
import com.chojnacki.manufaktura.manuregiony.model.Region;
import com.chojnacki.manufaktura.manuregiony.model.Regions;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.chojnacki.manufaktura.manuregiony.model.ProcessingType.LODZKIE;
import static com.chojnacki.manufaktura.manuregiony.processing.ResourceLoader.getResourceFromConfigFile;
import static com.chojnacki.manufaktura.manuregiony.processing.ResourceLoader.loadImage;

/**
 * Class for building images.
 */
public class ImageBuilder implements ProcessingTypeListener {

    private static final Logger LOGGER = LogManager.getLogger(ImageBuilder.class);
    private final static int MAX_ROWS_IN_TABLE = 17;
    private final static int MAX_COLUMNS_IN_TABLE = 2;
    private final int scaledMarginY = 100;
    private final int scaledMarginX = 100;
    private final int shortcutWidth;
    private final int cellPercentageWidth;

    private int inputHeight;
    private int inputWidth;
    private Font font;
    private Font fontBold;
    // left and top margin of summarization table
    private int tableXMargin;
    private int tableYMargin;
    private int optionalTextWidth;
    private int optionalTextHeight;
    // legend cocrdinates
    int legendX;
    int legendY;

    private BufferedImage inputImage;
    private int cellsPerRow;
    private int cellHeight;
    private int cellNameWidth;
    private int cellCountWidth;

    // margin of text in cell
    private int cellXMargin;
    private int cellYMargin;
    private int rows;

    //summarization table size
    private int tableWidth;
    private int tableHeight;

    //x margin in name cell
    private int cellNameXMargin;
    private BufferedImage tableImage;
    private Graphics2D tableGraphics;


    //legend sizing
    private int legendCellWidth;
    private int legendFontXMargin;
    private int legendFontYMargin;
    private int legendWidth;
    private int legendHeight;

    //logo coordinates
    private int logoXMargin;
    private int logoY;

    //optional text coords
    private int optionalTextXMargin;
    private int optionalTextY;
    private String optionalText;
    private ProcessingType processingType;

    public ImageBuilder() {
        cellsPerRow = 2;
        cellHeight = 75;
        cellNameWidth = 800;
        cellCountWidth = 200;
        cellXMargin = 12;
        cellYMargin = 20;
        cellNameXMargin = 25;
        tableXMargin = -60;
        tableYMargin = 100;
        legendY = 1800;
        legendX = 100;
        legendCellWidth = 400;
        legendFontXMargin = 40;
        legendFontYMargin = 55;
        legendWidth = 5*legendCellWidth;
        legendHeight = 75;
        logoXMargin = -500;
        logoY = 1600;
        optionalTextXMargin = 1400;
        optionalTextY = 1800;
        optionalTextWidth = 1050;
        optionalTextHeight = 75;
        font = new Font(getResourceFromConfigFile("fontName", "Arial"), Font.PLAIN, Integer.valueOf(getResourceFromConfigFile("fontSize", "65")));
        fontBold = new Font(getResourceFromConfigFile("fontName", "Arial"), Font.BOLD, Integer.valueOf(getResourceFromConfigFile("fontSize", "65")));
        shortcutWidth = 150;
        cellPercentageWidth = 200;
    }

    public void setInputImage(BufferedImage inputImage) {
        this.inputImage = inputImage;
    }

    @Override
    public void setProcessingType(ProcessingType processingType) {
        this.processingType = processingType;
        if (LODZKIE.equals(processingType)) {
            inputWidth = 1920;
            inputHeight = 1920;
        } else {
            inputWidth = inputImage.getWidth();
            inputHeight = inputImage.getHeight();
        }
    }


    public void setUpTableDetails(double regionSize) {
        rows = Math.min((int) Math.ceil(regionSize / (float) cellsPerRow), MAX_ROWS_IN_TABLE);
        tableWidth = cellsPerRow * (cellNameWidth + cellCountWidth + shortcutWidth);
        tableHeight = rows * cellHeight;
        LOGGER.log(Level.DEBUG, "Table sizes: {}x{}", tableWidth, tableHeight);
        LOGGER.log(Level.DEBUG, "Image sizes: {}x{}", inputWidth, inputHeight);
    }

    public void prepareTableGraphics() {
        this.tableImage = new BufferedImage(tableWidth, tableHeight, BufferedImage.TYPE_INT_RGB);
        this.tableGraphics = tableImage.createGraphics();
        tableGraphics.fillRect(0, 0, tableWidth - (tableXMargin), tableHeight);
    }

    public BufferedImage buildResultImage() throws InterruptedException {
        BufferedImage resultImage = new BufferedImage(inputWidth + tableWidth, inputHeight, BufferedImage.TYPE_INT_RGB);
        Graphics resultGraphic = resultImage.getGraphics();
        resultGraphic.setColor(new Color(255, 255, 255));
        resultGraphic.fillRect(0, 0, inputWidth + tableWidth, inputHeight);
        drawMainImg(resultGraphic);
        resultGraphic.drawImage(tableImage, inputWidth + tableXMargin, tableYMargin, null);
        resultGraphic.drawImage(getLegend(), legendX, legendY, null);
        resultGraphic.drawImage(getLogo(), inputWidth + tableWidth + logoXMargin, logoY, null);
        resultGraphic.drawImage(getOptionalText(), inputWidth + optionalTextXMargin, optionalTextY, null);
        return resultImage;
    }

    private BufferedImage getOptionalText() {
        BufferedImage optionalTextImage = new BufferedImage(optionalTextWidth, optionalTextHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D optionalTextGraphics = optionalTextImage.createGraphics();
        optionalTextGraphics.setBackground(Color.white);
        optionalTextGraphics.setColor(Color.black);
        optionalTextGraphics.clearRect(0, 0, optionalTextWidth, optionalTextHeight);
        optionalTextGraphics.setFont(font);
        optionalTextGraphics.drawString(optionalText, 0, 50);
        return optionalTextImage;
    }

    private BufferedImage getLogo() {
        String imageName = "manufaktura_logotyp.png";
        try {
            return loadImage(imageName);
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "Image {} not found.", imageName);
        }
        return null;
    }

    private void drawMainImg(Graphics resultGraphic) {
        Image image = inputImage;
        int marginX = 0;
        int marginY = 0;
        if (LODZKIE.equals(Regions.getInstance().getProcessingType())) {
            image = scaleImage();
            marginX = scaledMarginX;
            marginY = scaledMarginY;
        }
        resultGraphic.drawImage(image, marginX, marginY, null);
    }

    private Image scaleImage() {
        Image scaled = inputImage.getSubimage(3100, 3500, 2100, 2000);
        scaled = scaled.getScaledInstance(1500, 1500, Image.SCALE_DEFAULT);
        return scaled;
    }

    private BufferedImage getLegend() throws InterruptedException {

        BufferedImage legendImage = new BufferedImage(legendWidth, legendHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = legendImage.createGraphics();
        graphics.setBackground(Color.white);
        graphics.setColor(Color.black);
        graphics.clearRect(0, 0, inputWidth, tableHeight);
        graphics.setFont(font);

        for (int i = 0; i < 5; i++) {

            graphics.setColor(Limits.colors[i]);
            graphics.drawRect(i * legendCellWidth, 0, (1 + i) * legendCellWidth - 1, legendHeight - 1);
            graphics.fillRect(i * legendCellWidth, 0, (1 + i) * legendCellWidth - 1, legendHeight - 1);

            graphics.setColor(Color.black);
            graphics.drawLine((i + 1) * legendCellWidth - 1, 0, (i + 1) * legendCellWidth - 1, legendHeight);
        }

        graphics.setColor(Color.black);
        graphics.drawLine(0, 0, legendWidth, 0);
        graphics.drawLine(0, legendHeight - 1, legendWidth, legendHeight - 1);
        graphics.drawLine(0, 0, 0, legendHeight);
        graphics.setColor(Color.black);
        graphics.drawString("0 - " + (Limits.coldLimit[0] - 1), legendFontXMargin, legendFontYMargin);
        graphics.drawString(Limits.coldLimit[0] + " - " + Limits.coldLimit[1], legendCellWidth + legendFontXMargin, legendFontYMargin);
        graphics.drawString(Limits.mediumLimit[0] + " - " + Limits.mediumLimit[1], 2 * legendCellWidth + legendFontXMargin, legendFontYMargin);
        graphics.drawString(Limits.hotLimit[0] + " - " + Limits.hotLimit[1], 3 * legendCellWidth + legendFontXMargin, legendFontYMargin);
        graphics.drawString(">" + (Limits.hotLimit[1] + 1), 4 * legendCellWidth + legendFontXMargin, legendFontYMargin);
        return legendImage;
    }

    public void paintRegionInTable(Region region, int currentCounter) {
        boolean forced = region.isForced();
        LOGGER.log(Level.DEBUG, "Paiting region in table {}.", forced ? "forced" : "not forced");
        int currentRow;
        int currentColumn;

        boolean overSize = !(currentCounter < (MAX_COLUMNS_IN_TABLE * MAX_ROWS_IN_TABLE) - 1);
        if(!forced && overSize) {
            return;
        }

        currentRow = overSize ? MAX_ROWS_IN_TABLE - 1 : currentCounter % rows;
        currentColumn = overSize ? MAX_COLUMNS_IN_TABLE - 1 : (int) Math.floor(currentCounter / rows);

        tableGraphics.setFont(region.isBold() ? fontBold : font);

        // horizontal line
        tableGraphics.drawLine(0, currentRow * cellHeight, tableWidth,
                currentRow * cellHeight);

        int stringMargin;
        if (processingType.equals(LODZKIE)) {
            drawInfoForLdz(region, currentRow, currentColumn);
            stringMargin = this.shortcutWidth;
        } else {
            stringMargin = 0;
        }

        //vertical devider lines
        int upperLeftCorner = (1 + currentColumn ) * (cellNameWidth + cellCountWidth + shortcutWidth);

        tableGraphics.drawLine(upperLeftCorner, 0,
                upperLeftCorner, tableHeight);
        tableGraphics.drawLine(upperLeftCorner - 1, 0,
                upperLeftCorner - 1, tableHeight);
        tableGraphics.setColor(Color.gray);

        tableGraphics.drawLine(
                upperLeftCorner - cellCountWidth,
                0,
                upperLeftCorner - cellCountWidth,
                tableHeight);

        tableGraphics.drawLine(
                upperLeftCorner - cellCountWidth - cellPercentageWidth,
                0,
                upperLeftCorner - cellCountWidth - cellPercentageWidth,
                tableHeight);
        tableGraphics.setColor(Color.black);

        // draw strings
        tableGraphics.drawString(region.getName(),
                (currentColumn * (cellNameWidth + cellCountWidth + this.shortcutWidth)) + cellXMargin + stringMargin,
                (currentRow + 1) * cellHeight - cellYMargin);
        tableGraphics.drawString(Integer.toString(region.getObjectsNumber()), (1+currentColumn) * (cellNameWidth + cellCountWidth + this.shortcutWidth) - cellCountWidth + cellNameXMargin,
                (currentRow + 1) * cellHeight - cellYMargin);
        
        //draw percentage values except all and outhern regions
        if (!region.equals(Regions.ALL) && !region.equals(Regions.OUTERN_POLAND)) {
            tableGraphics.drawString(region.getObjectsPercentage() + "%", (1 + currentColumn) * (cellNameWidth + cellCountWidth + this.shortcutWidth) - cellCountWidth + cellNameXMargin - cellPercentageWidth,
                    (currentRow + 1) * cellHeight - cellYMargin);
        }


        // draw border lines
        if (rows - 1 == currentRow && currentColumn == 0) {
            LOGGER.debug("Drawing border lines for row {} and column {}", currentRow, currentColumn);

            //lower horizontal
            tableGraphics.drawLine(0, tableHeight - 1, tableWidth - 1,
                    tableHeight - 1);
            tableGraphics.drawLine(0, tableHeight - 2, tableWidth - 1,
                    tableHeight - 2);

            // upper horizontal line
            tableGraphics.drawLine(0, 1, tableWidth - 1,
                    1);
            tableGraphics.drawLine(0,  2, tableWidth - 1,
                    2);

            //vertical line
            tableGraphics.drawLine(1, 0,
                                   1, tableHeight - 1);
            tableGraphics.drawLine(2, 0,
                                   2, tableHeight - 1);

            tableGraphics.setColor(Color.black);
        }
    }

    public void drawInfoForLdz(Region region, int currentRow, int currentColumn) {
        tableGraphics.setColor(Color.black);
        tableGraphics.drawString(region.getMainRegionShortcut(),
                (currentColumn * (cellNameWidth + cellCountWidth + shortcutWidth)) + cellXMargin,
                (currentRow + 1) * cellHeight - cellYMargin);
        tableGraphics.drawLine(
                (currentColumn * (cellNameWidth + cellCountWidth + shortcutWidth)) + shortcutWidth,
                0,
                (currentColumn * (cellNameWidth + cellCountWidth + shortcutWidth)) + shortcutWidth,
                tableHeight);
    }

    public void tableForLdz(Region region, int currentRow, int currentColumn) {
    }

    public void setOptionalText(String optionalText) {
        this.optionalText = optionalText;
    }
}
