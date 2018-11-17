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
import java.util.*;
import javax.imageio.ImageIO;

import com.chojnacki.manufaktura.manuliczek.output.PatioColorer;
import org.jdesktop.application.Application;
import org.jdesktop.application.Task;

import static com.chojnacki.manufaktura.manuliczek.model.Place.GALLERY;
import static com.chojnacki.manufaktura.manuliczek.model.Place.PATIO;

/**
 *
 * @author kalosh
 */
public class ProcessingManager extends Task<Void, Void> {

    private static final int TABLE_IMAGE_OFFSET = 200;
    /**
     * Property which holds list with all collected shops.
     */
    protected Map<Place, Colorer> colorers;
    protected ShopCollector shopCollector;
    protected long timeDiff;
    protected File outputFile;
    protected String period;

    public ProcessingManager(Application app) {
        super(app);
    }

    public ProcessingManager(InputDataHolder inputDataHolder) throws Exception {
        super(ManuLiczekMain.getApplication());
        colorers = new HashMap<>();
        colorers.put(PATIO, new PatioColorer());
        colorers.put(GALLERY, new Colorer());
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
        getColorers().paintPeriod(period);
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
        int shopsAllowedCount = shopCollector.getShopsAllowed().size();
        int shopsWCoordsCount = shopCollector.getShopsCurrentFloorWithoutCoordinates().size();
        getColorers().setShopsCount(shopsAllowedCount + shopsWCoordsCount);
        getColorers().setManuImage(ImageIO.read(ManuLiczekMain.getApplication().getInputImageFile()));
        Collection<String> shopsAllowedIds = shopCollector.getShopsAllowed();
        Shop shop;
        int counter = 0;
        for (String shopId : shopsAllowedIds) {
            shop = shops.get(shopId);
            getColorers().colorShop(shop);
            setProgress(counter++, 0, shopsAllowedCount);
        }
    }

    private void paintIds(Map<String, Shop> shops) {
        int tableImageHeight = getColorers().getTableHeight();

        BufferedImage currentManuImage = getColorers().getManuImage();
        BufferedImage tableImage = new BufferedImage(currentManuImage.getWidth(), tableImageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D manuGraphic = currentManuImage.createGraphics();

        Graphics2D tableGraphic = tableImage.createGraphics();
        tableGraphic.setBackground(Color.white);
        tableGraphic.setColor(Color.black);
        initTableGraphics(currentManuImage, tableGraphic);

        String shopId;
        Shop shop;
        Collection<String> shopsAllowedIds = shopCollector.getShopsAllowed();
        List<String> shopsIdToPaint = new ArrayList<>();
        List<String> shopsWCoordinates = new ArrayList<>(shopCollector.getShopsCurrentFloorWithoutCoordinates());
        filterShopsWCoordinates(shopsWCoordinates);
        shopsIdToPaint.addAll(shopsAllowedIds);
        shopsIdToPaint.addAll(shopsWCoordinates);
        Collections.sort(shopsIdToPaint);
        for(int i = 0; i < shopsIdToPaint.size(); i++) {
            shopId = shopsIdToPaint.get(i);
            shop = shops.get(shopId);
            getColorers().paintShopId(shop, manuGraphic);
            getColorers().paintShopInTable(shop, i, tableGraphic);
            setProgress(i, 0, shopsIdToPaint.size());
        }
        int resultImageSize[] = getResultImageSize(tableImage);
        BufferedImage resultImage = new BufferedImage(resultImageSize[0], resultImageSize[1], BufferedImage.TYPE_INT_RGB);
        Graphics2D resultGraphic = resultImage.createGraphics();
        resultGraphic.drawImage(currentManuImage, 0, 0, null);
        int tableImagePosition[] = getTableImagePosition();
        resultGraphic.drawImage(tableImage, tableImagePosition[0], tableImagePosition[1], null);
        getColorers().setManuImage(resultImage);
    }

    private int[] getResultImageSize(BufferedImage tableImage) {
        int x = getColorers().getManuImage().getWidth();
        int y = getColorers().getManuImage().getHeight() + tableImage.getHeight();
        if (ManuLiczekMain.getApplication().getPlace().equals(PATIO)) {
            y = Math.max(TABLE_IMAGE_OFFSET + tableImage.getHeight(),  getColorers().getManuImage().getHeight());
        }
        return new int[] {x, y};
    }

    private int[] getTableImagePosition() {
        int x = 1;
        int y = getColorers().getManuImage().getHeight() - 1;
        if (ManuLiczekMain.getApplication().getPlace().equals(PATIO)) {
            x = 860;
            y = TABLE_IMAGE_OFFSET;
        }
        return new int[] {x, y};
    }

    private void initTableGraphics(BufferedImage currentManuImage, Graphics2D tableGraphic) {
        int width = currentManuImage.getWidth();
        int height = getColorers().getTableHeight();
        if (ManuLiczekMain.getApplication().getPlace().equals(PATIO)) {
            height = currentManuImage.getWidth();
        }
        tableGraphic.clearRect(0,0, width, height);
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
            ImageIO.write(getColorers().getManuImage(), fileName[1], outputFile);
        }
    }
    
    private Colorer getColorers() {
        return colorers.get(ManuLiczekMain.getApplication().getPlace());
    }
}
