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
public abstract class ColorHolder {

    public int percentage;

    public int getPercentageColor() {
        int result;
        String hottest[] = ManuLiczekMain.getParametrizedString("hottest", ColorHolder.class).split(",");
        String hot[] = ManuLiczekMain.getParametrizedString("hot", ColorHolder.class).split(",");
        String medium[] = ManuLiczekMain.getParametrizedString("medium", ColorHolder.class).split(",");
        String cold[] = ManuLiczekMain.getParametrizedString("cold", ColorHolder.class).split(",");
        String coldest[] = ManuLiczekMain.getParametrizedString("coldest", ColorHolder.class).split(",");

        String hotLimit[] = ManuLiczekMain.getParametrizedString("hotLimit", ColorHolder.class).split(",");
        String mediumLimit[] = ManuLiczekMain.getParametrizedString("mediumLimit", ColorHolder.class).split(",");
        String coldLimit[] = ManuLiczekMain.getParametrizedString("coldLimit", ColorHolder.class).split(",");

        if (percentage >= Integer.valueOf(hotLimit[1])) {
            result = ManuLiczekMain.makeARGB(Integer.valueOf(hottest[0]), Integer.valueOf(hottest[1]), Integer.valueOf(hottest[2]));
        } else if (percentage >= Integer.valueOf(hotLimit[0]) && percentage < Integer.valueOf(hotLimit[1])) {
            result = ManuLiczekMain.makeARGB(Integer.valueOf(hot[0]), Integer.valueOf(hot[1]), Integer.valueOf(hot[2]));
        } else if (percentage >= Integer.valueOf(mediumLimit[0]) && percentage < Integer.valueOf(mediumLimit[1])) {
            result = ManuLiczekMain.makeARGB(Integer.valueOf(medium[0]), Integer.valueOf(medium[1]), Integer.valueOf(medium[2]));
        } else if (percentage >= Integer.valueOf(coldLimit[0]) && percentage < Integer.valueOf(coldLimit[1])) {
            result = ManuLiczekMain.makeARGB(Integer.valueOf(cold[0]), Integer.valueOf(cold[1]), Integer.valueOf(cold[2]));
        } else {
            result = ManuLiczekMain.makeARGB(Integer.valueOf(coldest[0]), Integer.valueOf(coldest[1]), Integer.valueOf(coldest[2]));
        }
        return result;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getPercentage() {
        return percentage;
    }
}
