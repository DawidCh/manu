/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chojnacki.manufaktura.manuliczek.model;

import com.chojnacki.manufaktura.manuliczek.ManuLiczekMain;

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

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getShopId() {
        return shopId;
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

    public void setCoordinatesAndLayout(String coordinatesString) throws Exception {
        if (coordinatesString != null && !coordinatesString.equals("")) {
            String coordinates[] = coordinatesString.split(",");
            if (coordinates.length >= 2) {
                x = Integer.parseInt(coordinates[0]);
                y = Integer.parseInt(coordinates[1]);
                if (coordinates.length == 3) {
                    int intBool = Integer.parseInt(coordinates[2]);
                    if (intBool == 1) {
                        position = VERTICAL;
                    } else {
                        position = HORIZONTAL;
                    }
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
        return cousine;
    }
}