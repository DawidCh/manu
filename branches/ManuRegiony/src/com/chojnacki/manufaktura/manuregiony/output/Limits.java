package com.chojnacki.manufaktura.manuregiony.output;

import java.awt.*;

import static com.chojnacki.manufaktura.manuregiony.processing.ResourceLoader.getResourceFromConfigFile;

/**
 * Limitations
 */
public class Limits {

    public static String[] HOTTEST_COLOR;
    public static String[] HOT_RGB;
    public static String[] MEDIUM_RGB;
    public static String[] COLD_RGB;
    public static String[] COLDEST_RGB;
    public static Color[] colors;

    public static int[] hotLimit;
    public static int[] mediumLimit;
    public static int[] coldLimit;

    public static final Color HOTTEST;
    public static final Color HOT;
    public static final Color MEDIUM;
    public static final Color COLD;
    public static final Color COLDEST;

    static {
        HOTTEST_COLOR = getResourceFromConfigFile("hottest", "217,92,13").split(",");
        HOT_RGB = getResourceFromConfigFile("hot", "255,149,28").split(",");
        MEDIUM_RGB = getResourceFromConfigFile("medium", "255,211,109").split(",");
        COLD_RGB = getResourceFromConfigFile("cold", "255,243,140").split(",");
        COLDEST_RGB = getResourceFromConfigFile("coldest", "255,255,255").split(",");

        HOTTEST = new Color(Integer.valueOf(HOTTEST_COLOR[0]), Integer.valueOf(HOTTEST_COLOR[1]), Integer.valueOf(HOTTEST_COLOR[2]));
        HOT = new Color(Integer.valueOf(HOT_RGB[0]), Integer.valueOf(HOT_RGB[1]), Integer.valueOf(HOT_RGB[2]));
        MEDIUM = new Color(Integer.valueOf(MEDIUM_RGB[0]), Integer.valueOf(MEDIUM_RGB[1]), Integer.valueOf(MEDIUM_RGB[2]));
        COLD = new Color(Integer.valueOf(COLD_RGB[0]), Integer.valueOf(COLD_RGB[1]), Integer.valueOf(COLD_RGB[2]));
        COLDEST = new Color(Integer.valueOf(COLDEST_RGB[0]), Integer.valueOf(COLDEST_RGB[1]), Integer.valueOf(COLDEST_RGB[2]));
        colors = new Color[]{COLDEST, COLD, MEDIUM, HOT, HOTTEST};

        String[] hotLimits = getResourceFromConfigFile("hotLimit", "26,41").split(",");
        hotLimit = new int[2];
        hotLimit[0] = Integer.valueOf(hotLimits[0]);
        hotLimit[1] = Integer.valueOf(hotLimits[1]);

        String[] mediumLimits = getResourceFromConfigFile("mediumLimit", "21,26").split(",");
        mediumLimit = new int[2];
        mediumLimit[0] = Integer.valueOf(mediumLimits[0]);
        mediumLimit[1] = Integer.valueOf(mediumLimits[1]);

        String[] coldLimits = getResourceFromConfigFile("coldLimit", "13,21").split(",");
        coldLimit = new int[2];
        coldLimit[0] = Integer.valueOf(coldLimits[0]);
        coldLimit[1] = Integer.valueOf(coldLimits[1]);
    }
}
