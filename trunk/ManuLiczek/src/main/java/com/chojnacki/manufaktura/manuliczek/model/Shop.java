/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chojnacki.manufaktura.manuliczek.model;

import com.chojnacki.manufaktura.manuliczek.ManuLiczekMain;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author kalosh
 */
public class Shop extends ColorHolder {
    public static final boolean VERTICAL = true;
    public static final boolean HORIZONTAL = false;
    
    private int x;
    private int y;
    private String shopId;
    private String shopName;
    private boolean position;
    private String cousine;
    private String shopIdAlias;

    public Shop(String shopId, String shopName) {
        this.shopId = shopId;
        this.shopName = shopName;
    }


    public Shop(int percentage, String shopId, String shopName) {
        this.percentage = percentage;
        this.shopId = shopId;
        this.shopName = shopName;
        this.position = HORIZONTAL;
    }

    public Shop(int percentage, int x, int y, int width_x, int height_y, String shopId, String shopName, boolean position) {
        this(percentage, shopId, shopName);
        this.x = x;
        this.y = y;
        this.position = position;
    }

    public String getShopId() {
        return shopId;
    }

    public String getAliasOrShopId() {
        return StringUtils.isBlank(shopIdAlias) ? shopId : shopIdAlias;
    }

    public String getShopName() {
        return shopName;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return shopId + ", ";
    }

    public boolean getPosition() {
        return position;
    }

    public void setCoordinatesAliasAndLayout(String coordinatesString) throws Exception {
        if (StringUtils.isNotBlank(coordinatesString)) {
            String coordinates[] = coordinatesString.split(",");
            if (coordinates.length >= 2) {
                x = Integer.parseInt(coordinates[0]);
                y = Integer.parseInt(coordinates[1]);
                int intBool = Integer.parseInt(coordinates[2]);
                if (intBool == 1) {
                    position = VERTICAL;
                } else {
                    position = HORIZONTAL;
                }
                if (coordinates.length == 4) {
                    shopIdAlias = coordinates[3];
                }
            } else {
                throw new Exception(ManuLiczekMain.getParametrizedString("errorInCoordinates", Shop.class, shopId));
            }
        }
    }

    public void setCousine(String cousine) {
        this.cousine = cousine;
    }

    public String getCousine() {
        return cousine == null ? "" : cousine;
    }
}