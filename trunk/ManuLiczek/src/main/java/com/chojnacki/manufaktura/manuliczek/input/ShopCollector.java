/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chojnacki.manufaktura.manuliczek.input;

import com.chojnacki.manufaktura.manuliczek.ManuLiczekMain;
import com.chojnacki.manufaktura.manuliczek.model.InputDataHolder;
import com.chojnacki.manufaktura.manuliczek.model.Level;
import com.chojnacki.manufaktura.manuliczek.model.Place;
import com.chojnacki.manufaktura.manuliczek.model.Shop;

import java.util.*;

import jxl.Sheet;
import jxl.Workbook;
import org.apache.log4j.Logger;

import static com.chojnacki.manufaktura.manuliczek.model.Level.FIRST;
import static com.chojnacki.manufaktura.manuliczek.model.Level.GROUND;
import static com.chojnacki.manufaktura.manuliczek.model.Level.SECOND;
import static com.chojnacki.manufaktura.manuliczek.model.Place.GALLERY;
import static com.chojnacki.manufaktura.manuliczek.model.Place.PATIO;

/**
 *
 * @author kalosh
 */
public class ShopCollector {

    protected Set<String> shopsCurrentFloorWithoutCoordinates;
    protected Set<String> shopsAllowed;
    protected SortedSet<String> shopAllFloorWithoutCoordinates;
    protected Map<String, Shop> shops;
    protected int shopCountInDocument;
    private static final Logger logger = Logger.getLogger(ShopCollector.class);

    protected InputDataHolder inputDataHolder;

    public ShopCollector(InputDataHolder inputDataHolder) {
        this.inputDataHolder = inputDataHolder;
        shopsCurrentFloorWithoutCoordinates = Collections.emptySet();
        shopsAllowed = Collections.emptySet();
        shopAllFloorWithoutCoordinates = Collections.emptySortedSet();
    }

    public Map<String, Shop> collectShops() throws Exception {
        shops = new HashMap<>();
        shopsCurrentFloorWithoutCoordinates = new HashSet<>();
        shopAllFloorWithoutCoordinates = new TreeSet<>();
        shopsAllowed = new HashSet<>();

        Workbook workbook = Workbook.getWorkbook(this.inputDataHolder.getInputFile());
        Sheet sheet = workbook.getSheet(this.inputDataHolder.getSheetName());
        
        PropertyResourceBundle resources = new PropertyResourceBundle(ManuLiczekMain.getApplication().getInputLocales());

        Shop shop;
        String shopId;
        String percentageColumn;
        String companiesName;
        String cousineColumn = null;
        for (int i = 1; i <= sheet.getRows(); i++) {
            shopId = sheet.getCell(inputDataHolder.getShopIdColumn() + i).getContents();
            companiesName = sheet.getCell(inputDataHolder.getCompaniesName() + i).getContents();
            percentageColumn = sheet.getCell(inputDataHolder.getPercentageColumn() + i).getContents();
            if (ManuLiczekMain.getApplication().getPlace().isPatio()) {
                cousineColumn = sheet.getCell(inputDataHolder.getCousineColumn() + i).getContents();
            }
            if (shopId != null) {
                if(shopId.matches("H\\w{1,2}\\d{2,3}\\w?")) {
                    shopCountInDocument++;
                    if (shopId.matches("H[M|B][F|R]?\\d{2,3}")) {
                        if (!"".equals(companiesName) && !"".equals(percentageColumn)) {
                            shop = new Shop(shopId, companiesName);
                            if (percentageColumn.endsWith("%")) {
                                percentageColumn = percentageColumn.substring(0, percentageColumn.length() - 1);
                            }
                            try {
                                shop.setPercentage(Integer.valueOf(percentageColumn));
                            } catch (NumberFormatException exc) {
                                logger.warn("Wrong value set in percentage column: " + percentageColumn);
                            }
                            shop.setCousine(cousineColumn);
                            shops.put(shopId, shop);
                            if (shopHasCoordinates(shopId, resources)) {
                                shopsAllowed.add(shopId);
                                shop.setCoordinatesAliasAndLayout((String) resources.getObject(shopId));
                            } else {
                                Level floor = getFloor(shopId);
                                Place place = getPlace(shopId);
                                if (floor.equals(ManuLiczekMain.getApplication().getFloor()) &&
                                        place.equals(ManuLiczekMain.getApplication().getPlace())) {
                                    logger.info("Shop without coordinates " + shopId + " on the floor " + floor
                                            + " in place " + place);
                                    shopAllFloorWithoutCoordinates.add(shopId);
                                }
                            }
                        }
                    }
                }
            }
        }
        return shops;
    }

    public Map<String, Shop> getShops() {
        return shops;
    }

    public Collection<String> getShopsAllowed() {
        return shopsAllowed;
    }

    public Collection<String> getShopsCurrentFloorWithoutCoordinates() {
        return shopsCurrentFloorWithoutCoordinates;
    }

    public Collection<String> getShopAllFloorWithoutCoordinates() {
        return shopAllFloorWithoutCoordinates;
    }

    protected Level getFloor(String shopId) {
        Level result = GROUND;
        if (shopId.matches("HBF\\d{2}")) {
            result = FIRST;
        } else if (shopId.matches("HBR\\d{2}")) {
            result = GROUND;
        } else if (shopId.matches("H[M|B][2-4]\\d{2}")) {
            result = FIRST;
        } else if (shopId.matches("HMR0[1|7|8|9]")) {
            result = FIRST;
        } else if (shopId.matches("HMR1[0|1|3]")) {
            result = SECOND;
        }
        return result;
    }

    protected Place getPlace(String shopId) {
        Place result;
        if (shopId.matches("H[A-Z]{2}\\d{2}")) {
            result = PATIO;
        } else if (shopId.matches("H[B|M]\\d{3}")) {
            result = GALLERY;
        } else {
            throw new RuntimeException("Shop id in wrong format: " + shopId);
        }
        return result;
    }

    protected boolean  shopHasCoordinates(String shopId, ResourceBundle resources) {
        return resources.containsKey(shopId) && !"".equals(resources.getString(shopId));
    }
}
