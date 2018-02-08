/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chojnacki.manufaktura.manuliczek.model;

import com.chojnacki.manufaktura.manuliczek.ManuLiczekMain;
import com.chojnacki.manufaktura.manuliczek.ManuLiczekMainView;
import com.chojnacki.manufaktura.manuliczek.input.ShopCollector;
import com.chojnacki.manufaktura.manuliczek.output.Colorer;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.jdesktop.application.Application;
import org.jdesktop.application.Task;

/**
 *
 * @author kalosh
 */
public class ProcessingManager extends Task<Void, Void> {

    /**
     * Property which holds list with all collected shops.
     */
    protected Colorer colorer;
    protected ShopCollector shopCollector;
    protected long timeDiff;
    protected File outputFile;
    protected String period;

    public ProcessingManager(Application app) {
        super(app);
    }

    public ProcessingManager(InputDataHolder inputDataHolder) throws Exception {
        super(ManuLiczekMain.getApplication());
        colorer = new Colorer();
        shopCollector = new ShopCollector(inputDataHolder);
        period = inputDataHolder.getPeriod();
        outputFile = ManuLiczekMain.getApplication().getOutputFile();
    }

    @Override
    protected Void doInBackground() throws Exception {
        Date startTime = new Date();
        message("collection.processing");
        Map<String, Shop> shops = shopCollector.collectShops();
        int shopsCount = shops.size();
        message("coloring.processing", shopsCount);
        colorShops(shops);
        message("paintingIds.processing", shopsCount);
        paintIds(shops);
        colorer.paintPeriod(period);
        writeDataToFile();
        Date endTime = new Date();
        timeDiff = endTime.getTime() - startTime.getTime();
        return null;
    }

    @Override
    protected void succeeded(Void result) {
        super.succeeded(result);
        message("succeeded", ManuLiczekMain.getApplication().getOutputFile().getAbsoluteFile());
        ((ManuLiczekMainView) ManuLiczekMain.getApplication().getMainView()).showReport(shopCollector, timeDiff);
    }

    @Override
    protected void failed(Throwable cause) {
        super.failed(cause);
        message("failed");
    }

    public void colorShops(Map<String, Shop> shops) throws IOException {
        Shop shop;
        int shopsAllowedCount = shopCollector.getShopsAllowed().size();
        int shopsWCoordsCount = shopCollector.getShopsCurrentFloorWithoutCoordinates().size();
        colorer.setShopsCount(shopsAllowedCount + shopsWCoordsCount);
        colorer.setManuImage(ImageIO.read(ManuLiczekMain.getApplication().getInputImageFile()));
        List<String> shopsAllowedIds = shopCollector.getShopsAllowed();
        String shopId;
        for (int i = 0; i < shopsAllowedCount; i++) {
            shopId = shopsAllowedIds.get(i);
            shop = shops.get(shopId);
            colorer.colorShop(shop);
            setProgress(i, 0, shopsAllowedCount);
        }
    }

    private void paintIds(Map<String, Shop> shops) {
        int tableImageHeight = colorer.getTableHeight();

        BufferedImage currentManuImage = colorer.getManuImage();
        BufferedImage tableImage = new BufferedImage(currentManuImage.getWidth(), tableImageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D manuGraphic = currentManuImage.createGraphics();

        Graphics2D tableGraphic = tableImage.createGraphics();
        tableGraphic.setBackground(Color.white);
        tableGraphic.setColor(Color.black);
        tableGraphic.clearRect(0,0,currentManuImage.getWidth(),tableImageHeight);

        String shopId;
        Shop shop;
        List<String> shopsAllowedIds = shopCollector.getShopsAllowed();
        List<String> shopsIdToPaint = new ArrayList<>();
        List<String> shopsWCoordinates = new ArrayList<String>(shopCollector.getShopsCurrentFloorWithoutCoordinates());
        filterShopsWCoordinates(shopsWCoordinates);
        shopsIdToPaint.addAll(shopsAllowedIds);
        shopsIdToPaint.addAll(shopsWCoordinates);
        Collections.sort(shopsIdToPaint);
        for(int i = 0; i < shopsIdToPaint.size(); i++) {
            shopId = shopsIdToPaint.get(i);
            shop = shops.get(shopId);
            colorer.paintShopId(shop, manuGraphic);
            colorer.paintShopInTable(shop, shopsIdToPaint.size(), i, tableGraphic);
            setProgress(i, 0, shopsIdToPaint.size());
        }
        BufferedImage resultImage = new BufferedImage(currentManuImage.getWidth(), currentManuImage.getHeight() + tableImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D resultGraphic = resultImage.createGraphics();
        resultGraphic.drawImage(currentManuImage, 0, 0, null);
        resultGraphic.drawImage(tableImage, 1, currentManuImage.getHeight() - 1, null);
        colorer.setManuImage(resultImage);
    }

    private void filterShopsWCoordinates(List<String> shopsWCoordinates) {
        String shopsId;
        Shop shop;
        for (int i = 0; i < shopsWCoordinates.size(); i++) {
            shopsId = shopsWCoordinates.get(i);
            shop = shopCollector.getShops().get(shopsId);
            if (shop.getShopId().startsWith("HM") && shop.getX() == 0 && shop.getY() == 0) {
                shopsWCoordinates.remove(shop.getShopId());
            }
        }
    }

    public void writeDataToFile() throws IOException {
        if (outputFile != null) {
            String fileName[] = outputFile.getName().split("\\.");
            ImageIO.write(colorer.getManuImage(), fileName[1], outputFile);
        }
    }
}
