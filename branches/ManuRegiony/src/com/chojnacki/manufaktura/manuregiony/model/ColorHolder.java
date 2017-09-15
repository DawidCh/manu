/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chojnacki.manufaktura.manuregiony.model;
import static com.chojnacki.manufaktura.manuregiony.output.Limits.*;
import static com.chojnacki.manufaktura.manuregiony.processing.ResourceLoader.getResourceFromConfigFile;

/**
 * Class which represents object which can be drawn.
 */
public abstract class ColorHolder implements OrientationHolder, Paintable {

    public final static boolean LOCKED_COLOR = true;
    public final static boolean UNLOCKED_COLOR = !LOCKED_COLOR;


    protected boolean locked;
    protected boolean bold;
    private final int lockedRegionColor;

    public ColorHolder(boolean locked) {
        this.locked = locked;
        lockedRegionColor = makeARGB(
                getResourceFromConfigFile("lockedRegionColor", "0,0,0")
                        .split(","));
    }

    public int getPercentageColor() {
        if (isLocked()) {
            return lockedRegionColor;
        }
        int result;
        if (getObjectsNumber() >= hotLimit[1]+1) {
            result = makeARGB(HOTTEST_COLOR);
        } else if (getObjectsNumber() >= hotLimit[0] && getObjectsNumber() <= hotLimit[1]) {
            result = makeARGB(HOT_RGB);
        } else if (getObjectsNumber() >= mediumLimit[0] && getObjectsNumber() <= mediumLimit[1]) {
            result = makeARGB(MEDIUM_RGB);
        } else if (getObjectsNumber() >= coldLimit[0] && getObjectsNumber() <= coldLimit[1]) {
            result = makeARGB(COLD_RGB);
        } else {
            result = makeARGB(COLDEST_RGB);
        }
        return result;
    }

    private boolean isLocked() {
        return locked == LOCKED_COLOR;
    }

    public abstract int getObjectsNumber();

    public String getObjectsPercentage() {
        float percentageValue = Math.round(((getObjectsNumber() * 100F) / (float) Regions.ALL.getObjectsNumber())*100)/100F;
        String result;
        if (percentageValue < 1F ) {
            result = String.format("%1$.2f", percentageValue);
        } else {
            result = String.format("%1$.0f", percentageValue);
        }
        return result;
    }

    private int makeARGB(int red, int green, int blue) {
        return 255 << 24 | red << 16 | green << 8 | blue;
    }

    private int makeARGB(String[] rgb) {
        return 255 << 24 | Integer.parseInt(rgb[0]) << 16
                | Integer.parseInt(rgb[1]) << 8 | Integer.parseInt(rgb[2]);
    }

    public boolean isBold() {
        return bold;
    }
}
