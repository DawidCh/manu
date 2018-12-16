/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chojnacki.manufaktura.manuliczek.output;

import com.chojnacki.manufaktura.manuliczek.ManuLiczekMain;
import com.chojnacki.manufaktura.manuliczek.model.ColorHolder;
import com.chojnacki.manufaktura.manuliczek.model.Layout;
import com.chojnacki.manufaktura.manuliczek.model.Shop;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import org.jdesktop.application.Application;

/**
 *
 * @author kalosh
 */
public class Colorer {

    private BufferedImage manuImage;
    private Set<Point> visitedPixels;

    protected int cellHeight;
    protected int cellIdWidth;
    protected int cellNameWidth;
    protected int cellXMargin;
    protected int cellYMargin;
    protected int cellsPerRow;
    private int maxNameLength;
    protected int rows;
    protected int tableWidth;
    protected int tableHeight;
    private Font idsFont;
    private Color idsFontColor;
    protected Layout fillTable;

    public Colorer() {
        cellHeight = Integer.parseInt(Application.getInstance().getContext().getResourceMap(Colorer.class).getString("cellHeight"));
        cellIdWidth = Integer.parseInt(Application.getInstance().getContext().getResourceMap(Colorer.class).getString("cellIdWidth"));
        cellNameWidth = Integer.parseInt(Application.getInstance().getContext().getResourceMap(Colorer.class).getString("cellNameWidth"));
        cellXMargin = Integer.parseInt(Application.getInstance().getContext().getResourceMap(Colorer.class).getString("cellXMargin"));
        cellYMargin = Integer.parseInt(Application.getInstance().getContext().getResourceMap(Colorer.class).getString("cellYMargin"));
        maxNameLength = Integer.parseInt(Application.getInstance().getContext().getResourceMap(Colorer.class).getString("maxNameLength"));
        fillTable = Layout.valueOf(Application.getInstance().getContext().getResourceMap(Colorer.class).getString("fillTable"));
        cellsPerRow = 8;

        Properties fontSettings = ManuLiczekMain.getFontSettings();
        String fontName = (String) fontSettings.get("fontName");
        int fontSize = Integer.parseInt((String) fontSettings.get("fontSize"));
        String[] fontColor = ((String) fontSettings.get("fontColor")).split(",");

        idsFont = new Font(fontName, Font.BOLD, fontSize);
        idsFontColor = new Color(Integer.parseInt(fontColor[0]), Integer.parseInt(fontColor[1]), Integer.parseInt(fontColor[2]));
    }

    public void setManuImage(BufferedImage manuImage) {
        this.manuImage = manuImage;
    }

    public BufferedImage getManuImage() {
        return manuImage;
    }

    public void colorShop(Shop shop) {
        visitedPixels = new HashSet<>();
        regionGrow(shop.getX(), shop.getY(), shop.getPercentageColor());
    }

    String prepareShopName(Shop shop) {
        String shopName = shop.getShopName();
        if (shopName.length() > maxNameLength) {
            shopName = shopName.substring(0, maxNameLength + 1);
        }
        return shopName;
    }

    private void regionGrow(int x, int y, int argb) {
        manuImage.setRGB(x, y, argb);
        visitedPixels.add(new Point(x, y));
        //go left
        if (isFieldPixel(x - 1, y) && !visitedPixels.contains(new Point(x - 1, y))) {
            regionGrow(x - 1, y, argb);
        }

        //go up
        if (isFieldPixel(x, y - 1) && !visitedPixels.contains(new Point(x, y - 1))) {
            regionGrow(x, y - 1, argb);
        }

        //go right
        if (isFieldPixel(x + 1, y) && !visitedPixels.contains(new Point(x + 1, y))) {
            regionGrow(x + 1, y, argb);
        }

        //go down
        if (isFieldPixel(x, y + 1) && !visitedPixels.contains(new Point(x, y + 1))) {
            regionGrow(x, y + 1, argb);
        }
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

    private boolean isFieldPixel(int i, int j) {
        int currentColor = manuImage.getRGB(i, j);
        int borderColor = makeARGB(Application.getInstance().getContext().getResourceMap(ColorHolder.class).getString("borderColor"));
        int margin = Integer.valueOf(Application.getInstance().getContext().getResourceMap(ColorHolder.class).getString("colorMargin"));
        int border[] = printPixelARGB(borderColor);
        int current[] = printPixelARGB(currentColor);

        double distance = Math.sqrt(Math.pow(border[0] - current[0], 2) + Math.pow(border[1] - current[1], 2) + Math.pow(border[2] - current[2], 2));
        return distance > margin;
    }

    private int makeARGB(String rgbString) {
        String colors[] = rgbString.split(",");
        return ManuLiczekMain.makeARGB(Integer.valueOf(colors[0].trim()), Integer.valueOf(colors[1].trim()), Integer.valueOf(colors[2].trim()));
    }

    public void paintShopId(Shop shop, Graphics2D mainGraphic) {
        String shopId = shop.getAliasOrShopId().substring(1);
        mainGraphic.setFont(idsFont);
        mainGraphic.setColor(idsFontColor);
        if (shop.getPosition().isVertical()) {
            BufferedImage idImage = new BufferedImage(7, 25, BufferedImage.TYPE_INT_RGB);
            Graphics2D idGraphics = idImage.createGraphics();
            idGraphics.setBackground(new Color(shop.getPercentageColor()));
            idGraphics.setFont(idsFont);
            idGraphics.setColor(idsFontColor);
            AffineTransform aft =
                    new AffineTransform(
                            Math.cos(Math.toRadians(90)), Math.sin(Math.toRadians(90)), -Math.sin(Math.toRadians(90)), Math.cos(Math.toRadians(90)), 7, -1);
            idGraphics.setTransform(aft);
            idGraphics.clearRect(0, 0, 26, 7);
            idGraphics.drawString(shopId, 0, 7);
            mainGraphic.drawImage(idImage, shop.getX(), shop.getY(), null);
        } else {
            BufferedImage idImage = new BufferedImage(26, 7, BufferedImage.TYPE_INT_RGB);
            Graphics2D idGraphics = idImage.createGraphics();
            idGraphics.setBackground(new Color(shop.getPercentageColor()));
            idGraphics.setFont(idsFont);
            idGraphics.setColor(idsFontColor);
            idGraphics.clearRect(0, 0, 26, 7);
            idGraphics.drawString(shopId, 0, 7);
            mainGraphic.drawImage(idImage, shop.getX()-10, shop.getY() - 7, null);
            if (shop.getAliasOrShopId().startsWith("HM")) {
                String shopName = shop.getShopName().toLowerCase();
                if (shop.getShopName().length() > 6) {
                    StringTokenizer strTok = new StringTokenizer(shopName, " +");
                    String partName;
                    int tokens = strTok.countTokens();
                    for (int i = 1; i <= tokens; i++) {
                        partName = strTok.nextToken();
                        mainGraphic.drawString(partName, shop.getX() - 10, shop.getY() + (10 * i));
                    }
                } else {
                    mainGraphic.drawString(shopName, shop.getX() - 10, shop.getY() + 10);
                }
            }
        }
    }

    public void paintShopInTable(Shop shop, int currentCounter, Graphics2D graphic) {
        int currentRow;
        int currentColumn;

        if (fillTable.isVertical()) {
            currentRow = currentCounter % rows;
            currentColumn = (int) Math.floor(currentCounter / rows);
        } else {
            currentRow = (int) Math.floor(currentCounter / cellsPerRow);
            currentColumn = currentCounter % cellsPerRow;
        }

        //horizontal line
        graphic.drawLine(0, currentRow * cellHeight, tableWidth, currentRow * cellHeight);

        //vertical line
        graphic.drawLine(currentColumn * (cellIdWidth + cellNameWidth), 0, currentColumn * (cellIdWidth + cellNameWidth), tableHeight);
        graphic.setColor(Color.gray);
        graphic.drawLine(currentColumn * (cellIdWidth + cellNameWidth) - cellNameWidth, 0, currentColumn * (cellIdWidth + cellNameWidth) - cellNameWidth, tableHeight);
        graphic.setColor(Color.black);
        //draw strings
        graphic.drawString(shop.getAliasOrShopId(), (currentColumn * (cellIdWidth + cellNameWidth)) + cellXMargin, (currentRow + 1) * cellHeight - cellYMargin);
        graphic.drawString(prepareShopName(shop), (currentColumn * (cellIdWidth + cellNameWidth) + cellIdWidth) + cellXMargin, (currentRow + 1) * cellHeight - cellYMargin);

        //draw border lines
        if (rows - 1 == currentRow && currentColumn == 1) {
            graphic.drawLine(0, tableHeight - 1, tableWidth - 1, tableHeight - 1);
            graphic.drawLine(tableWidth - 1, 0, tableWidth - 1, tableHeight - 1);
            graphic.drawLine(tableWidth - 2, 0, tableWidth - 2, tableHeight - 1);
            graphic.setColor(Color.gray);
            graphic.drawLine(tableWidth - cellNameWidth, 0, tableWidth - cellNameWidth, tableHeight - 1);
            graphic.setColor(Color.black);
        }
    }

    public void setShopsCount(int shopsCount) {
        rows = (int) Math.ceil((float)shopsCount / (float)cellsPerRow);
        tableWidth = cellsPerRow * (cellIdWidth + cellNameWidth);
        tableHeight = rows * cellHeight;
    }

    public int getTableHeight() {
        return tableHeight;
    }

    public void paintPeriod(String period) {
        //1350 642
        if (period != null && period.length() != 0) {
            int imageHeight = 13;
            int imageWidth = 110;

            BufferedImage periodImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D periodGraphics = periodImage.createGraphics();

            periodGraphics.setBackground(Color.white);
            Font periodFont = new Font(idsFont.getName(), idsFont.getStyle(), 12);
            periodGraphics.setFont(periodFont);
            periodGraphics.setColor(Color.black);
            periodGraphics.clearRect(0, 0, imageWidth, imageHeight);

            periodGraphics.drawString(period, 2, imageHeight - 2);

            Graphics2D manuGraphics = manuImage.createGraphics();

            int periodPosition[] = getPeriodPosition();
            manuGraphics.drawImage(periodImage, periodPosition[0], periodPosition[1], null);
        }
    }

    protected int[] getPeriodPosition() {
        return new int[]{1345, 631};
    }
}
