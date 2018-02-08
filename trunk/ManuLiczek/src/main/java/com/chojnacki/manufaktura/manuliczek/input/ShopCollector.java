/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chojnacki.manufaktura.manuliczek.input;

import com.chojnacki.manufaktura.manuliczek.ManuLiczekMain;
import com.chojnacki.manufaktura.manuliczek.model.InputDataHolder;
import com.chojnacki.manufaktura.manuliczek.model.Shop;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import jxl.Sheet;
import jxl.Workbook;

import static com.chojnacki.manufaktura.manuliczek.model.Level.FIRST;

/**
 *
 * @author kalosh
 */
public class ShopCollector {

    protected List<String> shopsCurrentFloorWithoutCoordinates;
    protected List<String> shopsAllowed;
    protected List<String> shopAllFloorWithoutCoordinates;
    protected Map<String, Shop> shops;
    protected int shopCountInDocument;

    protected InputDataHolder inputDataHolder;

    public ShopCollector(InputDataHolder inputDataHolder) {
        this.inputDataHolder = inputDataHolder;
        shopsCurrentFloorWithoutCoordinates = Collections.emptyList();
        shopsAllowed = Collections.emptyList();
        shopAllFloorWithoutCoordinates = Collections.emptyList();
    }

    public Map<String, Shop> collectShops() throws Exception {
        shops = new HashMap<>();
        shopsCurrentFloorWithoutCoordinates = new ArrayList<>();
        shopAllFloorWithoutCoordinates = new ArrayList<>();
        shopsAllowed = new ArrayList<>();

        Workbook workbook = Workbook.getWorkbook(this.inputDataHolder.getInputFile());
        Sheet sheet = workbook.getSheet(this.inputDataHolder.getSheetName());
        
        PropertyResourceBundle resources = new PropertyResourceBundle(ManuLiczekMain.getApplication().getInputLocales());
        PropertyResourceBundle reserveResources = new PropertyResourceBundle(ManuLiczekMain.getApplication().getReserveInputLocales());

        Shop shop;
        String shopId;
        String percentageColumn;
        String companiesName;
        for (int i = 1; i <= sheet.getRows(); i++) {
            shopId = sheet.getCell(inputDataHolder.getShopIdColumn() + i).getContents();
            companiesName = sheet.getCell(inputDataHolder.getCompaniesName() + i).getContents();
            percentageColumn = sheet.getCell(inputDataHolder.getPercentageColumn() + i).getContents();
            if (shopId != null) {
                if(shopId.matches("H\\w{1,2}\\d{2,3}\\w?")) {
                    shopCountInDocument++;
                    if (shopId.matches("H[M|B]\\d{3}")) {
                        if (!"".equals(companiesName) && !"".equals(percentageColumn)) {
                            shop = new Shop(shopId, companiesName);
                            if (percentageColumn.endsWith("%")) {
                                percentageColumn = percentageColumn.substring(0, percentageColumn.length() - 1);
                            }
                            shop.setPercentage(Integer.valueOf(percentageColumn));
                            shops.put(shopId, shop);
                            if (shopHasCoordinates(shopId, resources)) {
                                shopsAllowed.add(shopId);
                                shop.setCoordinatesAndLayout((String) resources.getObject(shopId));
                            } else if (!shopHasCoordinates(shopId, reserveResources)) {
                                if (currentFloor(shopId)) {
                                    shopsCurrentFloorWithoutCoordinates.add(shopId);
                                    shopAllFloorWithoutCoordinates.add(shopId);
                                }
                            }
                        }
                    }
                }
            }
        }
        Collections.sort(shopAllFloorWithoutCoordinates);
        return shops;
    }

    public Map<String, Shop> getShops() {
        return shops;
    }

    public List<String> getShopsAllowed() {
        return shopsAllowed;
    }

    public int getShopCountInDocument() {
        return shopCountInDocument;
    }

    public int getShopsCount() {
        return shops.size();
    }

    public List<String> getShopsCurrentFloorWithoutCoordinates() {
        return shopsCurrentFloorWithoutCoordinates;
    }

    public List<String> getShopAllFloorWithoutCoordinates() {
        return shopAllFloorWithoutCoordinates;
    }

    protected boolean currentFloor(String shopId) {
        boolean result;
        if (shopId.matches("HM\\d{3}")) {
            result = true;
        } else {
            String id = shopId.substring(2);
            int intId = Integer.valueOf(id);
            result = intId >= 200 && ManuLiczekMain.getApplication().getFloor() == FIRST;
        }
        return result;
    }

    protected boolean  shopHasCoordinates(String shopId, ResourceBundle resources) {
        return resources.containsKey(shopId) && !"".equals(resources.getString(shopId));
    }
}
